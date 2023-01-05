package com.sellist.restpi.controller;

import com.sellist.restpi.dao.JdbcTestDao;
import com.sellist.restpi.model.LcdMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class TestController {

    @Autowired
    JdbcTestDao testDao;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public LcdMessage getMessageById(@PathVariable int id) {
        return testDao.getMessageById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/random")
    public LcdMessage getRandomMessage() {
        return testDao.getRandomMessage();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public LcdMessage createNewMessage(@RequestBody LcdMessage newMessage) {
        return testDao.create(newMessage);
    }
}
