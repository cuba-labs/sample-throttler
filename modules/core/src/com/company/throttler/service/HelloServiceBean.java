package com.company.throttler.service;

import org.springframework.stereotype.Service;

@Service(HelloService.NAME)
public class HelloServiceBean implements HelloService {

    @Override
    public String sayHello(String name) {
        return String.format("Hello %s!", name);
    }

}