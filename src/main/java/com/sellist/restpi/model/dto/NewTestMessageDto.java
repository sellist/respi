package com.sellist.restpi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewTestMessageDto {

    @JsonProperty("text_message")
    private String textMessage;

    public NewTestMessageDto(String textMessage) {
        this.textMessage = textMessage;
    }

    public NewTestMessageDto() {}

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }
}
