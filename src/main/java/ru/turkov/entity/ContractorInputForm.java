package ru.turkov.entity;

import lombok.Data;

@Data
public class ContractorInputForm {
    private String name;
    private int inn;
    private int kpp;
    private int bik;
    private int account;

    public int getInn() {
        return inn;
    }

    public int getKpp() {
        return kpp;
    }

    public int getBik() {
        return bik;
    }

    public int getAccount() {
        return account;
    }
}
