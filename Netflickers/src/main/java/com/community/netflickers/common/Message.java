package com.community.netflickers.common;

import lombok.Data;

@Data
public class Message {

    private String message;
    private Object data;
    private String url;

    public Message() {
        this.data = null;
        this.message = null;
        this.url = null;
    }
}