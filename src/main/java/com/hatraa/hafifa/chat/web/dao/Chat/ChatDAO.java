package com.hatraa.hafifa.chat.web.dao.Chat;

import com.hatraa.hafifa.chat.model.Chat;

import java.util.List;

public interface ChatDAO {
    List<Chat> getChatsByUser(int userID);
}
