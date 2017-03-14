package com.cosmos.spring.cloud.microservice.provider.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by david on 10/03/2017.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
