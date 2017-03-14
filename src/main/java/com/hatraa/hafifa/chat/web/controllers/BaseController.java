package com.hatraa.hafifa.chat.web.controllers;

import com.hatraa.hafifa.chat.model.User;
import com.hatraa.hafifa.chat.web.dao.BaseDAO;
import com.hatraa.hafifa.chat.web.dao.User.UserDAO;
import com.hatraa.hafifa.chat.web.dao.User.UserDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
@RestController
@RequestMapping("/user")
public class BaseController {

    @Autowired
    UserDAO userDAO;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userDAO.getAll();
        if (users.isEmpty()) {
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<User> getById(@PathVariable("id") int id) {
        System.out.println("Fetching user with id " + id);
        User user = userDAO.getById(id);
        if (user == null) {
            System.out.println(String.format("User with id {0} not found", id));

            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> saveUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating user" + user.getName());
        userDAO.save(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") int id) {
        User user = userDAO.getById(id);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        userDAO.delete(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}
