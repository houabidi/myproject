package com.abidi.fixture.load;

import fit.ColumnFixture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Created by houssemabidi on 02/05/17.
 */
public abstract class LoadFixture<T, ID extends Serializable, R extends JpaRepository<T, ID>> extends ColumnFixture {

    final Class<R> repositoryClass;

    @Autowired
    private ApplicationContext applicationContext;

    protected LoadFixture(Class<R> repositoryClass) {
        this.repositoryClass = repositoryClass;
    }

    protected boolean save(T object) {
        if (applicationContext != null) {
            R repository = applicationContext.getBean(repositoryClass);
            if (repository != null) {
                repository.save(object);
                return true;
            }
        }
        return false;
    }
}
