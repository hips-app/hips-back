package com.hips.api.repositories;

import com.hips.api.models.SpecialistType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SpecialistTypeRepository extends CrudRepository<SpecialistType, Integer> {
}
