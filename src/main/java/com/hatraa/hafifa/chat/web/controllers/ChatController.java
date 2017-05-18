package com.hatraa.hafifa.chat.web.controllers;

import com.hatraa.hafifa.chat.model.User;
import io.jsonwebtoken.Claims;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
@RestController
@RequestMapping("/chat")
public class ChatController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getChatsByUser(HttpServletRequest request) {
        //(User)request.getAttribute("user").;
        return null;
    }
}
