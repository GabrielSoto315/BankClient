package com.Bank.BankClient.Model.Documents;

import com.Bank.BankClient.Model.Entities.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "ClientCompany")
public class ClientCompany extends Client {

    private String idNumber;
    private String name;
}
