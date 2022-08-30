package com.Bank.BankClient.Mock;

import com.Bank.BankClient.Model.Entities.Client;

import java.util.Date;

public class ClientMock {

    public static Client randomClient() {
        Client client = new Client();
        client.setIdClient("1910000000004");
        client.setType("Person");
        client.setActive(true);
        client.setRegisterDate(new Date());
        client.setUpdateDate(new Date());

        return client;
    }
}