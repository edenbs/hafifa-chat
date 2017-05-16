package com.hatraa.hafifa.chat.web.dao.User;

import com.hatraa.hafifa.chat.model.User;
import com.hatraa.hafifa.chat.model.hiberante.Factory;
import com.hatraa.hafifa.chat.web.dao.BaseDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.*;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        /*SessionFactory sessionFactory = (SessionFactory)factory.sessionFactory();
        return sessionFactory.getCurrentSession();*/
        return this.sessionFactory.getCurrentSession();
    }

    public List<User> getAll() {
        Session session = getCurrentSession();

        return session.createQuery("From User").list();
    }

    public User getById(int id) {
        return getCurrentSession().get(User.class, id);
    }

    public Serializable save(User user) {
        return getCurrentSession().save(user);
    }

    public void delete(int id) {
        User user = getById(id);
        getCurrentSession().delete(user);
    }

    public User getByEmail(String email) {
        Query query = getCurrentSession().getNamedQuery("getByEmail");
        query.setParameter("email", email);

        return (User)(query.uniqueResult());
    }
}
