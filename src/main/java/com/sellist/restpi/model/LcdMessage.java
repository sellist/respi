package com.sellist.restpi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;

public class LcdMessage {
    @Size(min = 1, max = 16)
    @JsonProperty("top_line")
    public String topLine;

    @Size(min = 0, max = 16)
    @JsonProperty("bottom_line")
    public String bottomLine;

    public LcdMessage(String topLine, String bottomLine) {
        this.topLine = topLine;
        this.bottomLine = bottomLine;
    }

    public LcdMessage() {
    }

    public String getTopLine() {
        return topLine;
    }

    public void setTopLine(String topLine) {
        this.topLine = topLine;
    }

    public String getBottomLine() {
        return bottomLine;
    }

    public void setBottomLine(String bottomLine) {
        this.bottomLine = bottomLine;
    }
}
