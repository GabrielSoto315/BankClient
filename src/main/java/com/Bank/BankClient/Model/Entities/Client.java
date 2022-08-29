package com.Bank.BankClient.Model.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Transient
    public static final String SEQUENCE_NAME = "ClientSequence";

    @Id
    private String idClient;
    private String type;
    private Boolean active;
    private Date registerDate;
    private Date updateDate;
}
