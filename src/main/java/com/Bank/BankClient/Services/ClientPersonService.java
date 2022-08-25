package com.Bank.BankClient.Services;

import com.Bank.BankClient.Model.Documents.ClientCompany;
import com.Bank.BankClient.Model.Documents.ClientPerson;
import com.Bank.BankClient.Model.Entities.ResponseHandler;
import reactor.core.publisher.Mono;

public interface ClientPersonService {
    Mono<ResponseHandler> findAll();

    Mono<ResponseHandler> find(String id);

    Mono<ResponseHandler> create(ClientPerson client);

    Mono<ResponseHandler> update(String id, ClientPerson client);

    Mono<ResponseHandler> delete(String id);
}
