package com.Bank.BankClient.Services.Implement;

import com.Bank.BankClient.Model.Documents.ClientCompany;
import com.Bank.BankClient.Model.Entities.ResponseHandler;
import com.Bank.BankClient.Repository.Data.ClientCompanyRepository;
import com.Bank.BankClient.Services.ClientCompanyService;
import com.Bank.BankClient.Services.SequenceGeneratorService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Date;

import static com.Bank.BankClient.Model.Entities.Client.SEQUENCE_NAME;

@Service
public class ClientCompanyServiceImp implements ClientCompanyService {
    @Autowired
    private ClientCompanyRepository clientCompanyRepository;
    @Autowired
    private SequenceGeneratorService sequenceService;
    private static final Logger log = LoggerFactory.getLogger(ClientCompanyServiceImp.class);

    @Override
    public Mono<ResponseHandler> findAll() {
        return clientCompanyRepository.findAll()
                .doOnNext(client -> log.info(client.toString()))
                .collectList()
                .map(x -> new ResponseHandler("Done", HttpStatus.OK, x))
                .onErrorResume(error -> Mono.just(new ResponseHandler(error.getMessage(), HttpStatus.BAD_REQUEST, null)));
    }

    @Override
    public Mono<ResponseHandler> find(String id) {
        return clientCompanyRepository.findById(id)
                .doOnNext(client -> log.info(client.toString()))
                .map(x -> new ResponseHandler("Done", HttpStatus.OK, x))
                .onErrorResume(error -> Mono.just(new ResponseHandler(error.getMessage(), HttpStatus.BAD_REQUEST, null)));
    }

    @Override
    public Mono<ResponseHandler> create(ClientCompany client) {
        client.setId_client(String.format("191%010d", sequenceService.getSequenceNumber(SEQUENCE_NAME)));
        client.setActive(true);
        client.setRegister_date(new Date());
        return clientCompanyRepository.save(client)
                    .map(x -> new ResponseHandler("Done", HttpStatus.OK, x))
                    .onErrorResume(error -> Mono.just(new ResponseHandler(error.getMessage(), HttpStatus.BAD_REQUEST, null)));
    }

    @Override
    public Mono<ResponseHandler> update(String id, ClientCompany client) {
        return clientCompanyRepository.existsById(id).flatMap(check -> {
            if (check){
                client.setUpdate_date(new Date());
                return clientCompanyRepository.save(client)
                        .map(x -> new ResponseHandler("Done", HttpStatus.OK, x)                )
                        .onErrorResume(error -> Mono.just(new ResponseHandler(error.getMessage(), HttpStatus.BAD_REQUEST, null)));
            }
            else
                return Mono.just(new ResponseHandler("Not found", HttpStatus.NOT_FOUND, null));
        });
    }

    @Override
    public Mono<ResponseHandler> delete(String id) {
        return clientCompanyRepository.existsById(id).flatMap(check ->{
            if (check){
                return clientCompanyRepository.findById(id).flatMap(x ->{
                    x.setActive(false);
                    return clientCompanyRepository.save(x)
                            .then(Mono.just(new ResponseHandler("Done", HttpStatus.OK, null)));
                });
            }
            else {
                return Mono.just(new ResponseHandler("Not found", HttpStatus.NOT_FOUND,null));
            }
        });
    }
}
