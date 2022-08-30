package com.Bank.BankClient.Controllers;

import com.Bank.BankClient.Mock.ClientCompanyMock;
import com.Bank.BankClient.Model.Documents.ClientCompany;
import com.Bank.BankClient.Model.Entities.ResponseHandler;
import com.Bank.BankClient.Services.ClientCompanyService;
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
@WebFluxTest(ClientCompanyController.class)
public class ClientCompanyControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private ClientCompanyService clientCompanyService;

    @Test
    void findAllTest() {

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setMessage("Ok");
        responseHandler.setStatus(HttpStatus.OK);
        responseHandler.setData(null);

        when(clientCompanyService.findAll()).thenReturn(Mono.just(responseHandler));

        webClient
                .get().uri("/api/ClientCompany/")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(ResponseHandler.class);
    }

    @Test
    void findByIdTest() {
        ClientCompany clientCompany = ClientCompanyMock.randomClient();

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setMessage("Ok");
        responseHandler.setStatus(HttpStatus.OK);
        responseHandler.setData(clientCompany);

        Mockito
                .when(clientCompanyService.find("1910000000001"))
                .thenReturn(Mono.just(responseHandler));

        webClient.get().uri("/api/ClientCompany/{id}", "1910000000001")
                .exchange()
                .expectStatus().isOk()
                .expectBody(ResponseHandler.class);

        Mockito.verify(clientCompanyService, times(1)).find("1910000000001");
    }

    @Test
    void createTest() {

        ClientCompany clientCompany = ClientCompanyMock.randomClient();

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setMessage("Ok");
        responseHandler.setStatus(HttpStatus.OK);
        responseHandler.setData(clientCompany);

        Mockito.when(clientCompanyService.create(clientCompany))
                .thenReturn(Mono.just(responseHandler));

        webClient
                .post()
                .uri("/api/ClientCompany/")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(clientCompany))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void updateTest() {

        ClientCompany clientCompany = ClientCompanyMock.randomClient();

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setMessage("Ok");
        responseHandler.setStatus(HttpStatus.OK);
        responseHandler.setData(clientCompany);

        Mockito
                .when(clientCompanyService.update("1910000000001",clientCompany)).thenReturn(Mono.just(responseHandler));

        webClient
                .put()
                .uri("/api/ClientCompany/{id}", "1910000000001")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(clientCompany))
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
                .when(clientCompanyService.delete("1910000000001"))
                .thenReturn(Mono.just(responseHandler));

        webClient.delete().uri("/api/ClientCompany/{id}", "1910000000001")
                .exchange()
                .expectStatus().isOk();
    }
}
