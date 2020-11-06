package com.hips.api.repositories;

import com.hips.api.models.SubscriptionType;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
 /**
  * SubscriptionTypeRepository
  */
 public interface SubscriptionTypeRepository extends CrudRepository<SubscriptionType, Integer>{


 }
