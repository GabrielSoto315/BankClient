package com.Bank.BankClient.Mock;

import com.Bank.BankClient.Model.Documents.ClientCompany;
import com.Bank.BankClient.Services.ClientCompanyService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class ClientCompanyMock {

    public static ClientCompany randomClient(){
        ClientCompany clientCompany = new ClientCompany();
        clientCompany.setIdClient("1910000000001");
        clientCompany.setIdNumber("20123456780");
        clientCompany.setName("The Test Company S.A.C.");
        clientCompany.setActive(true);
        clientCompany.setRegisterDate(new Date());
        clientCompany.setUpdateDate(new Date());

        return clientCompany;
    }
}
