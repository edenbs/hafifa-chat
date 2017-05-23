package com.hatraa.hafifa.chat.web.dao.User;

import com.hatraa.hafifa.chat.model.User;
import com.hatraa.hafifa.chat.model.hiberante.Factory;
import com.hatraa.hafifa.chat.web.dao.BaseDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.*;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAll() {
        return entityManager.createQuery("From User").getResultList();
    }

    public User getById(int id) {
        Query query =  entityManager.createQuery("From User where id=:id");
        query.setParameter("id", id);

        return (User)query.getSingleResult();
    }

    public Serializable save(User user) {
        entityManager.persist(user);
        return user.getId();
    }

    public void delete(int id) {
        User user = getById(id);
        entityManager.remove(user);
    }

    public User getByEmail(String email) {
        Query query = entityManager.createNamedQuery("getByEmail");
        query.setParameter("email", email);

        return (User)(query.getSingleResult());
    }
}
