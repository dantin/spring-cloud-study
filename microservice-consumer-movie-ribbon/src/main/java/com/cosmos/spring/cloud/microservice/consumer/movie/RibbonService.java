package com.cosmos.spring.cloud.microservice.consumer.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by david on 10/03/2017.
 */
@Service
public class RibbonService {

    private RestTemplate restTemplate;

    @Autowired
    public RibbonService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public User findById(Long id) {
        return restTemplate.getForObject("http://microservice-provider-user/" + id, User.class);
    }
}
