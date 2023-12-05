package org.example;

public class Accounts {
    private String accountid;
    private String iban;
    private Float balance;
    private Integer clientid;

    public Accounts(String accountid, String iban, Float balance, Integer clientId) {
        this.accountid = accountid;
        this.iban = iban;
        this.balance = balance;
        this.clientid = clientId;
    }

    public Accounts(String iban, Float balance, Integer clientId) {
        this.iban = iban;
        this.balance = balance;
        this.clientid = clientId;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Integer getClientid() {
        return clientid;
    }

    public void setClientid(Integer clientid) {
        this.clientid = clientid;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "accountid='" + accountid + '\'' +
                ", iban='" + iban + '\'' +
                ", balance=" + balance +
                ", clientId=" + clientid +
                '}';
    }
}
