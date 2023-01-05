package com.sellist.restpi.model;

public class TestEntry {

    public String message;

    public TestEntry(String message) {
        this.message = message;
    }

    public TestEntry() {}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
