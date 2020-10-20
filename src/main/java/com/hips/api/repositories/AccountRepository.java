package com.hips.api.repositories;

import com.hips.api.models.Account;
import com.hips.api.models.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface AccountRepository extends CrudRepository<Account, Integer>{

    Account findByEmail(String email);
    Account findById(int userId);
}

