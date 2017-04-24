package com.abidi.repository;

import com.abidi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by houssemabidi on 18/04/17.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
