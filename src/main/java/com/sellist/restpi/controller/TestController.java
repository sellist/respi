package com.sellist.restpi.controller;

import com.sellist.restpi.dao.JdbcTestDao;
import com.sellist.restpi.model.TestEntry;
import com.sellist.restpi.model.dto.NewTestMessageDto;
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
    public TestEntry getMessageById(@PathVariable int id) {
        return testDao.getMessageById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/random")
    public TestEntry getRandomMessage() {
        return testDao.getRandomMessage();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public TestEntry createNewMessage(@RequestBody NewTestMessageDto newMessage) {
        return testDao.create(newMessage);
    }
}
