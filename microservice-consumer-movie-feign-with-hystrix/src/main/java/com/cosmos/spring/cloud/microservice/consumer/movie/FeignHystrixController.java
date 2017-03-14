package com.cosmos.spring.cloud.microservice.consumer.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by david on 13/03/2017.
 */
@RestController
public class FeignHystrixController {

    private UserFeignHystrixClient userFeignHystrixClient;

    @Autowired
    public FeignHystrixController(UserFeignHystrixClient userFeignHystrixClient) {
        this.userFeignHystrixClient = userFeignHystrixClient;
    }

    @GetMapping("feign/{id}")
    public User findByIdFeign(@PathVariable Long id) {
        return userFeignHystrixClient.findByIdFeign(id);
    }
}
