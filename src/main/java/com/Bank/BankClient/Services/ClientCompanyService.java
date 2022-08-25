package com.Bank.BankClient.Services;

import com.Bank.BankClient.Model.Documents.ClientCompany;
import com.Bank.BankClient.Model.Entities.ResponseHandler;
import com.Bank.BankClient.Services.Implement.ClientCompanyServiceImp;
import reactor.core.publisher.Mono;

public interface ClientCompanyService {

    Mono<ResponseHandler> findAll();

    Mono<ResponseHandler> find(String id);

    Mono<ResponseHandler> create(ClientCompany client);

    Mono<ResponseHandler> update(String id, ClientCompany client);

    Mono<ResponseHandler> delete(String id);
}
