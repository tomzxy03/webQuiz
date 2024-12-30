package com.tomzxy.webQuiz.dto.response;

public class ResponseError extends ResponseData{
    public ResponseError(int code, String messages) {
        super(code, messages);
    }
}
