package com.Bank.BankClient.Controllers;

import com.Bank.BankClient.Model.Entities.ResponseHandler;
import com.Bank.BankClient.Services.ClientCompanyService;
import com.Bank.BankClient.Services.ClientService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/Client/")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/{id}")
    public Mono<ResponseHandler> FindbyId(@PathVariable("id") String id){
        return clientService.findbyId(id);
    }

}
