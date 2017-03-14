package com.cosmos.spring.cloud.microservice.consumer.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by david on 10/03/2017.
 */
@RestController
public class RibbonController {

    private RibbonService ribbonService;

    @Autowired
    public RibbonController(RibbonService ribbonService) {
        this.ribbonService = ribbonService;
    }

    @GetMapping("/ribbon/{id}")
    public User findById(@PathVariable Long id) {
        return ribbonService.findById(id);
    }
}
