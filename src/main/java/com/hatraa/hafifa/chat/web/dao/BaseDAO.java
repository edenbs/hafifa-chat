package com.hatraa.hafifa.chat.web.dao;

import com.hatraa.hafifa.chat.model.User;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public interface BaseDAO<T> {
    T getById(int id);
    T save(T obj);
    void delete(int id);
    T update(T obj);
}
