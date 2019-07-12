package com.management.service.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Result<T> {

    private int code;
    private String mesg;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public Result() {
    }

    public Result(int code, String mesg, T data) {
        this.code = code;
        this.mesg = mesg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMesg() {
        return mesg;
    }

    public void setMesg(String mesg) {
        this.mesg = mesg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
