package com.hatraa.hafifa.chat.web.controllers;

import com.hatraa.hafifa.chat.model.User;
import com.hatraa.hafifa.chat.services.AuthService;
import com.hatraa.hafifa.chat.web.dao.User.UserDAO;
import com.hatraa.hafifa.chat.web.dto.objects.LoginDTO;
import com.hatraa.hafifa.chat.web.dto.objects.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    UserDAO userDAO;

    @Autowired
    AuthService authService;

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

    @RequestMapping(path="/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity register(@RequestBody RegisterDTO registerData) {
        try {
            String salt = authService.getSalt();
            String hashedPwd = authService.hashPassword(registerData.getPassword(), salt);
            User user = new User(registerData.getUsername(), hashedPwd, salt, registerData.getEmail());
            String id = userDAO.save(user).toString();

            return new ResponseEntity(HttpStatus.CREATED);
        }
        catch (DataIntegrityViolationException ex) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path="/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginDTO loginData) {
        User wantedUser = userDAO.getByEmail(loginData.getEmail());
        String hashedGivenPwd = authService.hashPassword(loginData.getPassword(), wantedUser.getSalt());

        if (!hashedGivenPwd.equals(wantedUser.getPassword())) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        String usrToken = authService.encodeUser(Integer.toString(wantedUser.getId()));

        return new ResponseEntity(new Object() {
            public final String token = usrToken;
        }, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") int id) {
        User user = userDAO.getById(id);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        userDAO.delete(id);
        return new ResponseEntity<User>(HttpStatus.OK);
    }
}
