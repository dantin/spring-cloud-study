package com.cosmos.spring.cloud.microservice.provider.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by david on 10/03/2017.
 */
@RestController
public class UserController {

    private DiscoveryClient discoveryClient;

    private UserRepository userRepository;

    @Autowired
    public UserController(DiscoveryClient discoveryClient, UserRepository userRepository) {
        this.discoveryClient = discoveryClient;
        this.userRepository = userRepository;
    }

    /**
     * 备注 @GetMapping("{id}") 等价于 @RequestMapping(value = "/id", method = RequestMethod.GET)
     * 同 @PostMapping
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userRepository.findOne(id);
    }

    /**
     * 本地服务实例的信息
     *
     * @return
     */
    @GetMapping("/instance-info")
    public ServiceInstance showInfo() {
        return discoveryClient.getLocalServiceInstance();
    }

}
