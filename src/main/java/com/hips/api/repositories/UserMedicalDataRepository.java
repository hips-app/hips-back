package com.hips.api.repositories;

import com.hips.api.models.UserMedicalData;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserMedicalDataRepository extends CrudRepository<UserMedicalData, Integer>{
    UserMedicalData getByUserAccountId(Integer id);

}
