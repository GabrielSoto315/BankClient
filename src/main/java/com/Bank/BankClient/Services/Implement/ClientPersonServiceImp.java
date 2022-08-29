package com.Bank.BankClient.Services.Implement;

import com.Bank.BankClient.Model.Documents.ClientPerson;
import com.Bank.BankClient.Model.Entities.ResponseHandler;
import com.Bank.BankClient.Repository.Data.ClientPersonRepository;
import com.Bank.BankClient.Services.ClientPersonService;
import com.Bank.BankClient.Services.SequenceGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;

import static com.Bank.BankClient.Model.Entities.Client.SEQUENCE_NAME;

@Service
public class ClientPersonServiceImp implements ClientPersonService {
    @Autowired
    private ClientPersonRepository clientPersonRepository;
    @Autowired
    private SequenceGeneratorService sequenceService;
    private static final Logger log = LoggerFactory.getLogger(ClientCompanyServiceImp.class);

    @Override
    public Mono<ResponseHandler> findAll() {
            return clientPersonRepository.findAll()
                    .doOnNext(client -> log.info(client.toString()))
                    .filter(f -> f.getActive().equals(true))
                    .collectList()
                    .map(res -> new ResponseHandler("Done", HttpStatus.OK, res))
                    .onErrorResume(error -> Mono.just(new ResponseHandler(error.getMessage(), HttpStatus.BAD_REQUEST, null)));
    }

    @Override
    public Mono<ResponseHandler> find(String id) {
        return clientPersonRepository.existsById(id).flatMap(exist -> {
            if (exist){
                return clientPersonRepository.findById(id)
                        .doOnNext(client -> log.info(client.toString()))
                        .map(res -> {
                            if (res.getActive()){
                                return new ResponseHandler("Done", HttpStatus.OK, res);
                            } else {
                                return new ResponseHandler("Not found", HttpStatus.NOT_FOUND, null);
                            }
                        })
                        .onErrorResume(error -> Mono.just(new ResponseHandler(error.getMessage(), HttpStatus.BAD_REQUEST, null)));
            } else {
                return Mono.just(new ResponseHandler("Not found", HttpStatus.NOT_FOUND, null));
            }
        });
    }

    @Override
    public Mono<ResponseHandler> create(ClientPerson client) {
        return sequenceService.getSequenceNumber(SEQUENCE_NAME).flatMap(seq -> {
            client.setIdClient(String.format("191%010d", seq));
            client.setActive(true);
            client.setRegisterDate(new Date());
            client.setType("Person");
            log.info("Client data : " + client);
            return clientPersonRepository.save(client)
                    .map(x -> new ResponseHandler("Done", HttpStatus.OK, x))
                    .onErrorResume(error -> Mono.just(new ResponseHandler(error.getMessage(), HttpStatus.BAD_REQUEST, null)));

        });
    }

    @Override
    public Mono<ResponseHandler> update(String id, ClientPerson client) {
        return clientPersonRepository.existsById(id).flatMap(check -> {
            if (check){
                return clientPersonRepository.findById(id).flatMap(findClient ->{
                    findClient.setIdCard(client.getIdCard());
                    findClient.setFirstName(client.getFirstName());
                    findClient.setLastName(client.getLastName());
                    findClient.setUpdateDate(new Date());
                    return clientPersonRepository.save(findClient)
                            .map(x -> new ResponseHandler("Done", HttpStatus.OK, x)                )
                            .onErrorResume(error -> Mono.just(new ResponseHandler(error.getMessage(), HttpStatus.BAD_REQUEST, null)));
                });
            }
            else
                return Mono.just(new ResponseHandler("Not found", HttpStatus.NOT_FOUND, null));
        });
    }

    @Override
    public Mono<ResponseHandler> delete(String id) {
        return clientPersonRepository.existsById(id).flatMap(check ->{
            if (check){
                return clientPersonRepository.findById(id).flatMap(x ->{
                    x.setActive(false);
                    return clientPersonRepository.save(x)
                            .then(Mono.just(new ResponseHandler("Done", HttpStatus.OK, null)));
                });
            }
            else {
                return Mono.just(new ResponseHandler("Not found", HttpStatus.NOT_FOUND,null));
            }
        });
    }
}
