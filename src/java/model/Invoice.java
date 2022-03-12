package model;

import java.sql.Date;

public class Invoice {

    private int id;
    private Date date;
    private long amount;
    private long paid;
    private long owed;
    private Agency agency;
    private Account account;
    private Buyer buyer;

    public Invoice() {
        agency = new Agency();
        account = new Account();
        buyer = new Buyer();
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

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getPaid() {
        return paid;
    }

    public void setPaid(long paid) {
        this.paid = paid;
    }

    public long getOwed() {
        return owed;
    }

    public void setOwed(long owed) {
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

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }
}
