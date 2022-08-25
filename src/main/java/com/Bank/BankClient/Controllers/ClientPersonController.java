package com.Bank.BankClient.Controllers;

import com.Bank.BankClient.Model.Documents.ClientCompany;
import com.Bank.BankClient.Model.Documents.ClientPerson;
import com.Bank.BankClient.Model.Entities.ResponseHandler;
import com.Bank.BankClient.Repository.Data.ClientPersonRepository;
import com.Bank.BankClient.Services.ClientCompanyService;
import com.Bank.BankClient.Services.ClientPersonService;
import com.Bank.BankClient.Services.SequenceGeneratorService;
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
@RequestMapping("api/ClientPerson/")
public class ClientPersonController {

    @Autowired
    private ClientPersonService clientPersonRepository;

    private static final Logger log = LoggerFactory.getLogger(ClientCompanyController.class);

    /**
     * Lista todos los resultados
     * @return
     */
    @GetMapping()
    public Mono<ResponseHandler> List(){
        return clientPersonRepository.findAll();
    }

    /**
     * Obtener resultado por id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Mono<ResponseHandler> FindbyId(@PathVariable("id") String id){
        return clientPersonRepository.find(id);
    }

    /**
     * Guardar nuevo cliente empresa
     * @param clientPerson
     * @return
     */
    @PostMapping()
    public Mono<ResponseHandler> Save(@RequestBody ClientPerson clientPerson){
        return clientPersonRepository.create(clientPerson);
    }

    /**
     * Actualizar datos de cliente persona
     * @param clientPerson
     * @return
     */
    @PutMapping("/{id}")
    public Mono<ResponseHandler> Update(@PathVariable("id") String id,@RequestBody ClientPerson clientPerson){
        return clientPersonRepository.update(id, clientPerson);
    }

    /**
     * Borrar datos por id
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseHandler> DeletebyId(@PathVariable("id") String id){
        return clientPersonRepository.delete(id);
    }


}
