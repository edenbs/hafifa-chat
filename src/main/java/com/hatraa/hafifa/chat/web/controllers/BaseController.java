package com.hatraa.hafifa.chat.web.controllers;

import com.hatraa.hafifa.chat.model.User;
import com.hatraa.hafifa.chat.web.dao.User.UserDAO;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Ebens on 6/4/17.
 */

@Controller
@RestController
public class BaseController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserDAO userDAO;

    protected User getCurrUser() {
        int currUserID = (int)request.getAttribute("userID");
        return userDAO.getById(currUserID);
    }
}
