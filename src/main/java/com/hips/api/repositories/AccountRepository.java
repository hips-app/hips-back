package com.hips.api.repositories;

import com.hips.api.models.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AccountRepository extends CrudRepository<Account, Integer>{

    Account findByEmail(String email);
    Account findById(int userId);
    Account getById(Integer id);
}

