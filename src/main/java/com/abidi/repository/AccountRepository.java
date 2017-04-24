package com.abidi.repository;

import com.abidi.model.Account;
import com.abidi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by houssemabidi on 18/04/17.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByRib(String rib);

    List<Account> findByUsers(List<User> users);
}
