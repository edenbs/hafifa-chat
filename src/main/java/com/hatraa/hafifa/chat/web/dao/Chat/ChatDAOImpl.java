package com.hatraa.hafifa.chat.web.dao.Chat;

import com.hatraa.hafifa.chat.model.Chat;
import com.hatraa.hafifa.chat.model.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

@Repository("chatDAO")
public class ChatDAOImpl implements ChatDAO{

    @PersistenceContext
    private EntityManager entityManager;

    public Chat getById(int id) {
        Query query = entityManager.createQuery("From Chat chat where chat.id=:id");
        query.setParameter("id", id);

        return (Chat)query.getSingleResult();
    }

    public Chat save(Chat chat) {
        entityManager.persist(chat);
        return chat;
    }

    public void delete(int id) {
        Chat chat = getById(id);
        entityManager.remove(chat);
    }

    public Chat update(Chat chat) {
        return entityManager.merge(chat);
    }
}
