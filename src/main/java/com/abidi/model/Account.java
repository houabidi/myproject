package com.abidi.model;

import javax.persistence.*;
import java.util.Currency;
import java.util.List;
import java.util.Set;

/**
 * Created by houssemabidi on 18/04/17.
 */
@Entity
@Table(name = "T_ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    private Long id;
    @Column(name = "BALANCE")
    private Double balance;
    @Column(name = "CURRENCY")
    private String currency;
    @Column(name = "RIB")//, unique = true)
    private String rib;
    @ManyToMany(mappedBy = "accounts", cascade = CascadeType.ALL)
    private List<User> users;

    public Account() {
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return Currency.getInstance(currency);
    }

    public void setCurrency(Currency currency) {
        this.currency = currency.getCurrencyCode();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
