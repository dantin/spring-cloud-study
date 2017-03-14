package com.cosmos.spring.cloud.microservice.consumer.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by david on 13/03/2017.
 */
@RestController
public class RibbonHystrixController {

    private RibbonHystrixService ribbonHystrixService;

    @Autowired
    public RibbonHystrixController(RibbonHystrixService ribbonHystrixService) {
        this.ribbonHystrixService = ribbonHystrixService;
    }

    @GetMapping("/ribbon/{id}")
    public User findById(@PathVariable Long id) {
        return ribbonHystrixService.findById(id);
    }
}
