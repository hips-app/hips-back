package com.hips.api.repositories;

import com.hips.api.models.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface WeekDayRepository extends CrudRepository<WeekDay, Integer>{
    WeekDay findById(int weekDayId);
}

