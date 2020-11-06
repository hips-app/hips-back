package com.hips.api.repositories;

import com.hips.api.models.Account;
import com.hips.api.models.UserAccount;
import org.graalvm.compiler.lir.LIRInstruction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface UserAccountRepository extends CrudRepository<UserAccount, Integer>{
    UserAccount findByAccount(Account account);
    UserAccount getByAccountId(Integer id);
    UserAccount getById(Integer id);
}
