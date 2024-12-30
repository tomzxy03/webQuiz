package com.tomzxy.webQuiz.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.io.Serializable;
@Getter
public class ResponseData<T> implements Serializable {
    private final int code;

    private final String messages;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    /**
     * Response data when the API executes successfully or getting error. For PUT, PATCH, DELETE
     * @param code
     * @param messages
     */
    public ResponseData(int code, String messages) {
        this.code = code;
        this.messages = messages;
    }

    /**
     * Response data for the API to retrieve data successfully. For GET, POST only
     * @param code
     * @param messages
     * @param data
     */
    public ResponseData(int code, String messages, T data) {
        this.code = code;
        this.messages = messages;
        this.data=data;
    }
}
