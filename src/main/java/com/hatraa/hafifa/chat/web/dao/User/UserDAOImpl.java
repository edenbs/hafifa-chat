package com.hatraa.hafifa.chat.web.dao.User;

import com.hatraa.hafifa.chat.model.User;
import com.hatraa.hafifa.chat.model.hiberante.Factory;
import com.hatraa.hafifa.chat.web.dao.BaseDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.*;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

    //private Factory factory = new Factory();

    @Autowired
    private SessionFactory sessionFactory;

    /*@Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        /*users.add(new User(1, "EdenBs", "bens@gmail.com"));
        users.add(new User(2, "Litov", "omri@gmail.com"));
        users.add(new User(3, "Nudler", "roni@gmail.com"));
     /*   this.sessionFactory = sessionFactory;
    }*/

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
        Serializable bla = getCurrentSession().save(user);
        return bla;
    }

    public void delete(int id) {
        User user = getById(id);
        getCurrentSession().delete(user);
    }
}
