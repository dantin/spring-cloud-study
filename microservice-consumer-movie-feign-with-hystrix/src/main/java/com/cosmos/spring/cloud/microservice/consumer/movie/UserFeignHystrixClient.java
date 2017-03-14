package com.cosmos.spring.cloud.microservice.consumer.movie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by david on 13/03/2017.
 */
@FeignClient(name = "microservice-provider-user", fallback = UserFeignHystrixClient.HystrixClientFallback.class)
public interface UserFeignHystrixClient {

    @RequestMapping("/{id}")
    User findByIdFeign(@RequestParam("id") Long id);

    @Component
    class HystrixClientFallback implements  UserFeignHystrixClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(HystrixClientFallback.class);

        @Override
        public User findByIdFeign(@RequestParam("id") Long id) {
            LOGGER.info("异常, 接收参数: id = {}", id);

            User user = new User();
            user.setId(-1L);
            user.setUsername("default username");
            user.setAge(0);

            return user;
        }
    }

}
