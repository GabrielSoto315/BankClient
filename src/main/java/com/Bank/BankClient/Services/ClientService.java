package com.Bank.BankClient.Services;

import com.Bank.BankClient.Model.Entities.ResponseHandler;
import reactor.core.publisher.Mono;

public interface ClientService {

    Mono<ResponseHandler> findbyId(String id);

}
