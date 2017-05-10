package com.abidi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Currency;
import java.util.List;

import static java.util.Currency.getInstance;

/**
 * Created by houssemabidi on 18/04/17.
 */
@Entity
@Table(name = "T_ACCOUNT")// , uniqueConstraints = @UniqueConstraint(columnNames = "RIB", name = "RIB_UC"))
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    private Long id;
    @Column(name = "BALANCE")
    private Double balance;
    @Column(name = "CURRENCY")
    private String currency;
    @Column(name = "RIB")
    private String rib;
    @ManyToMany(mappedBy = "accounts", cascade = CascadeType.ALL)
    private List<User> users;

    public Account() {
    }

    public Account(AccountBuilder accountBuilder) {
        this.id = accountBuilder.id;
        this.balance = accountBuilder.balance;
        this.currency = accountBuilder.currency;
        this.rib = accountBuilder.rib;
        this.users = accountBuilder.users;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return getInstance(currency);
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

    public static class AccountBuilder {

        private Long id;
        private final String rib;
        private String currency;
        private Double balance;
        private List<User> users;

        public AccountBuilder(String rib) {
            this.rib = rib;
        }

        public AccountBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AccountBuilder balance(Double balance) {
            this.balance = balance;
            return this;
        }

        public AccountBuilder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public AccountBuilder users(List<User> users) {
            this.users = users;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }

}
