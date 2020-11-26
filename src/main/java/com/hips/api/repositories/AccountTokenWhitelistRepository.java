package com.hips.api.repositories;

import com.hips.api.models.AccountTokenWhitelist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AccountTokenWhitelistRepository extends CrudRepository<AccountTokenWhitelist, Integer> {

    List<AccountTokenWhitelist> deleteByToken(String token);

    boolean existsByToken(String token);
}
