package com.Bank.BankClient.Mock;

import com.Bank.BankClient.Model.Documents.ClientPerson;

import java.util.Date;

public class ClientPersonMock {

    public static ClientPerson randomClient(){
        ClientPerson clientPerson = new ClientPerson();
        clientPerson.setIdClient("1910000000004");
        clientPerson.setIdCard("70123450");
        clientPerson.setFirstName("Luis");
        clientPerson.setLastName("Abregu Saenz");
        clientPerson.setActive(true);
        clientPerson.setRegisterDate(new Date());
        clientPerson.setUpdateDate(new Date());

        return clientPerson;
    }
}
