package com.hatraa.hafifa.chat.web.controllers;

import com.hatraa.hafifa.chat.model.Chat;
import com.hatraa.hafifa.chat.model.Message;
import com.hatraa.hafifa.chat.model.User;
import com.hatraa.hafifa.chat.web.dao.Chat.ChatDAO;
import com.hatraa.hafifa.chat.web.dao.User.UserDAO;
import com.hatraa.hafifa.chat.web.dto.objects.ChatDTO;
import io.jsonwebtoken.Claims;
import javafx.geometry.Pos;
import org.apache.log4j.helpers.DateTimeDateFormat;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Filter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import sun.util.calendar.BaseCalendar;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/chat")
public class ChatController extends BaseController {

    @Autowired
    public ChatDAO chatDAO;

    @Autowired
    public UserDAO userDAO;

    @Autowired
    ModelMapper mapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getChatsByUser() {
        List<Chat> chats = getCurrUser().getChats();
        List<ChatDTO> chatsDTO = new ArrayList<ChatDTO>();

        for (Chat chat:chats) {
            chatsDTO.add(mapper.map(chat, ChatDTO.class));
        }

        return new ResponseEntity(chatsDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createChat(@RequestBody Chat chat) {
        User currUser = getCurrUser();
        chat.addParticipant(currUser);
        chat = chatDAO.save(chat);

        return new ResponseEntity(chat, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{id}")
    public ResponseEntity addUserToChat(@RequestBody String email, @PathVariable("id") int id) {
        User currUser = getCurrUser();
        User userToAdd = userDAO.getByEmail(email);
        Chat chat = chatDAO.getById(id);

        //chat.getParticipants().contains(currUser) ---Filter's job - needs to be checked
        if (!chat.getParticipants().contains(userToAdd)) {
            chat.addParticipant(userToAdd);
            chat = chatDAO.update(chat);

            return new ResponseEntity(chat, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.CONFLICT);
    }

    @RequestMapping(method = RequestMethod.POST, path= "/add")
    public ResponseEntity addDummy() {
        User currUser = getCurrUser();
        User userToAdd = userDAO.getById(1);
        User userToAdd2 = userDAO.getById(4);
        User userToAdd3 = userDAO.getById(3);

        Chat chat = new Chat();
        chat.setIsDirect(true);
        chat.addParticipant(currUser);
        chat.addParticipant(userToAdd);
        chat = chatDAO.save(chat);

        Message msg1 = new Message();
        msg1.setSender(currUser);
        msg1.setChatID(chat.getId());
        msg1.setSentTime(new Date());
        msg1.setText("miss me?");

        Message msg2 = new Message();
        msg2.setSender(currUser);
        msg2.setChatID(chat.getId());
        msg2.setSentTime(new Date());
        msg2.setText(":)");

        Message msg3 = new Message();
        msg3.setSender(userToAdd);
        msg3.setText("Yep");
        msg3.setChatID(chat.getId());
        msg3.setSentTime(new Date());

        chat.addMessage(msg1);
        chat.addMessage(msg2);
        chat.addMessage(msg3);

        chatDAO.update(chat);

        /*Chat grp = new Chat();
        grp.setIsDirect(false);
        grp.setName("Best B");
        grp.addParticipant(currUser);
        grp.addParticipant(userToAdd);
        grp.addParticipant(userToAdd2);
        grp.addParticipant(userToAdd3);

        grp = chatDAO.save(grp);

        Message grpmsg1 = new Message();
        grpmsg1.setChatID(grp.getId());
        grpmsg1.setSender(userToAdd);
        grpmsg1.setText("Welcome guis");
        grpmsg1.setSentTime(new Date());

        Message grpmsg2 = new Message();
        grpmsg2.setChatID(grp.getId());
        grpmsg2.setSender(userToAdd2);
        grpmsg2.setText("Niceeee");
        grpmsg2.setSentTime(new Date());

        Message grpmsg3 = new Message();
        grpmsg3.setChatID(grp.getId());
        grpmsg3.setSender(currUser);
        grpmsg3.setText("Finally, tonight?");
        grpmsg3.setSentTime(new Date());

        Message grpmsg4 = new Message();
        grpmsg4.setChatID(grp.getId());
        grpmsg4.setSender(userToAdd2);
        grpmsg4.setText("yas");
        grpmsg4.setSentTime(new Date());

        grp.addMessage(grpmsg1);
        grp.addMessage(grpmsg2);
        grp.addMessage(grpmsg3);
        grp.addMessage(grpmsg4);

        chatDAO.update(grp);*/

        return new ResponseEntity(HttpStatus.OK);
    }
}
