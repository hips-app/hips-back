package com.hips.api.repositories;

import com.hips.api.models.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Account, Integer>{

    Account findByEmail(String email);
    Account findById(int userId);
    Account getById(Integer id);

    @Query(value="SELECT count(*) FROM account", nativeQuery= true)
    int valor();
}

