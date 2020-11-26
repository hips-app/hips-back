package com.hips.api.repositories;

import com.hips.api.models.UserType;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource

public interface UserTypeRespository extends CrudRepository<UserType, Integer>{

}
