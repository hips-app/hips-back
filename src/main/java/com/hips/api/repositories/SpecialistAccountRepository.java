package com.hips.api.repositories;

import com.hips.api.models.SpecialistAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SpecialistAccountRepository extends CrudRepository<SpecialistAccount, Integer> {

}
