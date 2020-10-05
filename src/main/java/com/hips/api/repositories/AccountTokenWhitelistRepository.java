package com.hips.api.repositories;

import com.hips.api.models.AccountTokenWhitelist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AccountTokenWhitelistRepository extends CrudRepository<AccountTokenWhitelist, Integer> {

}
