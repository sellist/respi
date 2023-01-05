package com.sellist.restpi.dao;

import com.sellist.restpi.model.LcdMessage;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcLcdMessageDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcLcdMessageDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public LcdMessage getMessageById(int id) {
        String sql = "SELECT * FROM message WHERE ? = message_id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        LcdMessage output = new LcdMessage();

        if (results.next()) {
            output.setTopLine(results.getString("top_line"));
            output.setBottomLine(results.getString("bottom_line"));
        }
        return output;
    }

    public LcdMessage create(LcdMessage msg){

        String createSql = "INSERT INTO message (top_line, bottom_line) VALUES (?, ?) RETURNING message_id;";
        Integer newMessageId = jdbcTemplate.queryForObject(
                createSql,
                Integer.class,
                msg.getTopLine(),
                msg.getBottomLine()
        );
        if (newMessageId != null) {
            return getMessageById(newMessageId);
        }
        return null;
    }

    public LcdMessage getRandomMessage() {
        String sql = "SELECT * FROM message OFFSET floor(random() * (SELECT COUNT(*) from message)) LIMIT 1;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        LcdMessage output = new LcdMessage();

        if (results.next()) {
            output.setTopLine(results.getString("top_line"));
            output.setBottomLine(results.getString("bottom_line"));
        }
        return output;
    }
}
