package com.ecl.popcensus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ProtectedController {
    @GetMapping("/protected")
    public String protectedEndpoint() {
        return "This is a protected endpoint";
    }
}
