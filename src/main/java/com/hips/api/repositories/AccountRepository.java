package com.hips.api.repositories;

import com.hips.api.models.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {

    Account getById(Integer id);
}
