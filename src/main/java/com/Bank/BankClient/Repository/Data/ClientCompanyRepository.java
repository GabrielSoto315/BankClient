package com.Bank.BankClient.Repository.Data;

import com.Bank.BankClient.Model.Documents.ClientCompany;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientCompanyRepository extends ReactiveMongoRepository<ClientCompany, String> {

}
