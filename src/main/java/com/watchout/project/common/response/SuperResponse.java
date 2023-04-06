package com.watchout.project.common.response;

import lombok.Getter;

@Getter
public class SuperResponse<T> {

    public int status;
    public boolean success;
    public String message;
    public T data;
//    T getDate();

}
