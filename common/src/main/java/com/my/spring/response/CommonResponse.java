package com.my.spring.response;

import lombok.Getter;

@Getter
public class CommonResponse {
    private ResponseStatus status;

    private Object data;

    private String message;

    public CommonResponse() {
    }

    public CommonResponse(ResponseStatus status, Object data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }
}
