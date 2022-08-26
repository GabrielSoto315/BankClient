package com.Bank.BankClient.Services.Implement;

import com.Bank.BankClient.Model.Entities.Client;
import com.Bank.BankClient.Model.Entities.ResponseHandler;
import com.Bank.BankClient.Repository.Data.ClientCompanyRepository;
import com.Bank.BankClient.Repository.Data.ClientPersonRepository;
import com.Bank.BankClient.Services.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImp implements ClientService {

    @Autowired
    ClientCompanyRepository clientCompanyRepository;
    @Autowired
    ClientPersonRepository clientPersonRepository;

    private static final Logger log = LoggerFactory.getLogger(ClientServiceImp.class);

    @Override
    public Mono<ResponseHandler> findbyId(String id) {
        Client client = new Client();
        client.setId_client(id);
        return clientCompanyRepository.existsById(id).flatMap(checkCompany-> {
            if (checkCompany){
                client.setType("Company");
                log.info(client.toString());
                return Mono.just( new ResponseHandler("Done", HttpStatus.OK, client));
            }
            else{
                return clientPersonRepository.existsById(id).flatMap(checkPerson ->{
                    if (checkPerson){
                        client.setType("Person");
                        log.info(client.toString());
                        return Mono.just(new ResponseHandler("Done",HttpStatus.OK,client));
                    }
                    else{
                        return Mono.just(new ResponseHandler("Not found",HttpStatus.NOT_FOUND,null));
                    }
                });
            }
        });
    }
}
