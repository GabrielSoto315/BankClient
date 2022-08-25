package com.Bank.BankClient.Repository.Data;

import com.Bank.BankClient.Model.Documents.ClientPerson;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientPersonRepository extends ReactiveMongoRepository<ClientPerson, String> {

}
