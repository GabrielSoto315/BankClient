package com.Bank.BankClient.Controllers;

import com.Bank.BankClient.Model.Documents.ClientCompany;
import com.Bank.BankClient.Model.Entities.ResponseHandler;
import com.Bank.BankClient.Repository.Data.ClientCompanyRepository;
import com.Bank.BankClient.Services.ClientCompanyService;
import com.Bank.BankClient.Services.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

import static com.Bank.BankClient.Model.Entities.Client.SEQUENCE_NAME;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/ClientCompany/")
public class ClientCompanyController {

    @Autowired
    private ClientCompanyService clientCompanyService;


    private static final Logger log = LoggerFactory.getLogger(ClientCompanyController.class);

    /**
     * Lista todos los resultados
     * @return
     */
    @GetMapping()
    public Mono<ResponseHandler> List(){
        return clientCompanyService.findAll();
    }

    /**
     * Obtener resultado por id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Mono<ResponseHandler> FindbyId(@PathVariable("id") String id){
        return clientCompanyService.find(id);
    }

    /**
     * Guardar nuevo cliente empresa
     * @param oClient
     * @return
     */
    @PostMapping()
    public Mono<ResponseHandler> Save(@RequestBody ClientCompany clientCompany){
        return clientCompanyService.create(clientCompany);
    }

    /**
     * Actualizar datos de cliente empresa
     * @param oClient
     * @return
     */
    @PutMapping("/{id}")
    public Mono<ResponseHandler> Update(@PathVariable("id") String id,@RequestBody ClientCompany clientCompany){
        return clientCompanyService.update(id, clientCompany);
    }

    /**
     * Borrar datos por id
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseHandler> DeletebyId(@PathVariable("id") String id){
        return clientCompanyService.delete(id);
    }
}
