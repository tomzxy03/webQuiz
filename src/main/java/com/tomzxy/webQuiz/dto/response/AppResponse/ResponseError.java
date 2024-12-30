package com.tomzxy.webQuiz.dto.response.AppResponse;

public class ResponseError extends ResponseData{
    public ResponseError(int code, String messages) {
        super(code, messages);
    }
}
