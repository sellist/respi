package com.sellist.restpi.controller;

import com.sellist.restpi.dao.JdbcLcdMessageDao;
import com.sellist.restpi.model.LcdMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class LcdMessageController {

    @Autowired
    JdbcLcdMessageDao lcdMessageDao;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public LcdMessage getMessageById(@PathVariable int id) {
        return lcdMessageDao.getMessageById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/random")
    public LcdMessage getRandomMessage() {
        return lcdMessageDao.getRandomMessage();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public LcdMessage createNewMessage(@Valid @RequestBody LcdMessage newMessage) {
        return lcdMessageDao.create(newMessage);
    }
}
