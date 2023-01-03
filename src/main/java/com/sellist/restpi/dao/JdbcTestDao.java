package com.sellist.restpi.dao;

import com.sellist.restpi.model.TestEntry;
import com.sellist.restpi.model.dto.NewTestMessageDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcTestDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public TestEntry getMessageById(int id) {
        String sql = "SELECT * FROM message WHERE ? = message_id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        TestEntry output = new TestEntry();

        if (results.next()) {
            output.setMessage(results.getString("text_message"));
        }
        return output;
    }

    public TestEntry create(NewTestMessageDto msg) {
        String createSql = "INSERT INTO message (text_message) VALUES (?) RETURNING message_id;";
        Integer newMessageId = jdbcTemplate.queryForObject(createSql, Integer.class, msg.getTextMessage());

        if (newMessageId != null) {
            return getMessageById(newMessageId);
        }
        return null;
    }

    public TestEntry getRandomMessage() {
        String sql = "SELECT * FROM message OFFSET floor(random() * (SELECT COUNT(*) from message)) LIMIT 1;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        TestEntry output = new TestEntry();

        if (results.next()) {
            output.setMessage(results.getString("text_message"));
        }
        return output;
    }
}
