package com.hatraa.hafifa.chat.web.dao;

import com.hatraa.hafifa.chat.model.User;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public interface BaseDAO<T> {
    List<T> getAll();
    User getById(int id);
    Serializable save(T user);
    void delete(int id);
}
