package com.Bank.BankClient.Controllers;

import com.Bank.BankClient.Mock.ClientPersonMock;
import com.Bank.BankClient.Model.Documents.ClientPerson;
import com.Bank.BankClient.Model.Entities.ResponseHandler;
import com.Bank.BankClient.Services.ClientPersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(ClientPersonController.class)
public class ClientPersonControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private ClientPersonService clientPersonService;

    @Test
    void findAllTest() {

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setMessage("Ok");
        responseHandler.setStatus(HttpStatus.OK);
        responseHandler.setData(null);

        when(clientPersonService.findAll()).thenReturn(Mono.just(responseHandler));

        webClient
                .get().uri("/api/ClientPerson/")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(ResponseHandler.class);
    }

    @Test
    void findByIdTest() {
        ClientPerson clientPerson = ClientPersonMock.randomClient();

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setMessage("Ok");
        responseHandler.setStatus(HttpStatus.OK);
        responseHandler.setData(clientPerson);

        Mockito
                .when(clientPersonService.find("1910000000004"))
                .thenReturn(Mono.just(responseHandler));

        webClient.get().uri("/api/ClientPerson/{id}", "1910000000004")
                .exchange()
                .expectStatus().isOk()
                .expectBody(ResponseHandler.class);

        Mockito.verify(clientPersonService, times(1)).find("1910000000004");
    }

    @Test
    void createTest() {

        ClientPerson clientPerson = ClientPersonMock.randomClient();

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setMessage("Ok");
        responseHandler.setStatus(HttpStatus.OK);
        responseHandler.setData(clientPerson);

        Mockito.when(clientPersonService.create(clientPerson))
                .thenReturn(Mono.just(responseHandler));

        webClient
                .post()
                .uri("/api/ClientPerson/")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(clientPerson))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void updateTest() {

        ClientPerson clientPerson = ClientPersonMock.randomClient();

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setMessage("Ok");
        responseHandler.setStatus(HttpStatus.OK);
        responseHandler.setData(clientPerson);

        Mockito
                .when(clientPersonService.update("1910000000004",clientPerson)).thenReturn(Mono.just(responseHandler));

        webClient
                .put()
                .uri("/api/ClientPerson/{id}", "1910000000004")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(clientPerson))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void deleteTest() {

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setMessage("Ok");
        responseHandler.setStatus(HttpStatus.OK);
        responseHandler.setData(null);

        Mockito
                .when(clientPersonService.delete("1910000000004"))
                .thenReturn(Mono.just(responseHandler));

        webClient.delete().uri("/api/ClientPerson/{id}", "1910000000004")
                .exchange()
                .expectStatus().isOk();
    }
}
