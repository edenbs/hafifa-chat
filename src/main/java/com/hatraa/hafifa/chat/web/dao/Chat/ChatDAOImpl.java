package com.hatraa.hafifa.chat.web.dao.Chat;

import com.hatraa.hafifa.chat.model.Chat;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("chatDAO")
@Transactional
public class ChatDAOImpl implements ChatDAO{
    public List<Chat> getChatsByUser(int id) {
        return null;
    }
}
