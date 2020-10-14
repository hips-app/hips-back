package com.hips.api.repositories;

import com.hips.api.models.AccountType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AccountTypeRepository extends CrudRepository<AccountType, Integer>{

    List<AccountType> findByName(String name);
}