package com.hatraa.hafifa.chat.web.dao.User;

import com.hatraa.hafifa.chat.model.User;
import com.hatraa.hafifa.chat.web.dao.BaseDAO;

import java.util.List;

public interface UserDAO extends BaseDAO<User> {
    List<User> getAll();
    User getByEmail(String email);
    User getEagerById(int id);
}
