package com.hips.api.repositories;

import java.util.List;

import com.hips.api.models.UserAccount;
import com.hips.api.models.UserSubscription;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource

public interface UserSubscriptionRepository extends CrudRepository<UserSubscription, Integer> {
    UserSubscription findByUserAccount (UserAccount userAccount);

    List<UserSubscription> findAllByUserAccount(UserAccount userAccount);
}
