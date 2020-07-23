package com.company.throttler.portal.controllers;

import com.company.throttler.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
public class TestRestController {

    @Inject
    private HelloService helloService;

    @GetMapping(value = "hello/{name}", produces = "text/plain")
    public String doSomething(@PathVariable String name) {
        return helloService.sayHello(name);
    }

}
