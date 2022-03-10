
package model;

import java.sql.Date;

public class Invoice {
    private int id;
    private Date date;
    private float amount;
    private float paid;
    private float owed;
    private Agency agency;
    private Account account;

    public Invoice() {
        agency = new Agency();
        account = new Account();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getPaid() {
        return paid;
    }

    public void setPaid(float paid) {
        this.paid = paid;
    }

    public float getOwed() {
        return owed;
    }

    public void setOwed(float owed) {
        this.owed = owed;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
}
