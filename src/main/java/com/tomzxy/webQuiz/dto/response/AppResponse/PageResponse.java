package com.tomzxy.webQuiz.dto.response.AppResponse;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PageResponse<T> implements Serializable {
    private int page;
    private int size;
    private long total;
    private T items;
}
