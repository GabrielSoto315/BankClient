package com.Bank.BankClient.Controllers;

import com.Bank.BankClient.Mock.ClientMock;
import com.Bank.BankClient.Model.Entities.Client;
import com.Bank.BankClient.Model.Entities.ResponseHandler;
import com.Bank.BankClient.Services.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@WebFluxTest(ClientController.class)
public class ClientControllerTest {
    @Autowired
    private WebTestClient webClient;

    @MockBean
    private ClientService clientService;

    @Test
    void findByIdTest() {

        Client client = ClientMock.randomClient();

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setMessage("Ok");
        responseHandler.setStatus(HttpStatus.OK);
        responseHandler.setData(client);

        Mockito
                .when(clientService.findbyId("1910000000004"))
                .thenReturn(Mono.just(responseHandler));

        webClient.get().uri("/api/Client/{id}", "1910000000004")
                .exchange()
                .expectStatus().isOk()
                .expectBody(ResponseHandler.class);

        Mockito.verify(clientService, times(1)).findbyId("1910000000004");
    }
    
}
