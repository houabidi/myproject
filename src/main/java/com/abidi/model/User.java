package com.abidi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by houssemabidi on 18/04/17.
 */
@Entity
@Table(name = "T_USER")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @JsonIgnore
    @ManyToMany(cascade = {PERSIST, DETACH, REMOVE}, fetch = EAGER)
    @JoinTable(name = "T_USER_ACCOUNT", joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ACCOUNT_ID")})
    private List<Account> accounts;

    public User() {
    }

    public User(UserBuilder userBuilder) {
        this.firstName = userBuilder.firstName;
        this.lastName = userBuilder.lastName;
        this.accounts = userBuilder.accounts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean linkAccount(Account account) {
        if (accounts == null) {
            accounts = new ArrayList<>();
        }
        return accounts.add(account);
    }

    public static class UserBuilder {
        private final String firstName;
        private final String lastName;
        private List<Account> accounts;

        public UserBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public UserBuilder accounts(List<Account> accounts) {
            this.accounts = accounts;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }


}
