package com.hatraa.hafifa.chat.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class Base {
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> hello() {
        String Bla = "Hello World";
        return new ResponseEntity<String>(Bla, HttpStatus.OK);
    }
}
