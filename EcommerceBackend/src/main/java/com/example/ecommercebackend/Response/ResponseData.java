package com.example.ecommercebackend.Response;

import org.springframework.http.HttpStatus;

public class ResponseData {

    private Object data;

    private int status_code;
    private HttpStatus httpStatus;

    public ResponseData(Object data, int status_code, HttpStatus status) {
        this.data = data;
        this.status_code = status_code;
        this.httpStatus = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getStatus_code() {
        return status_code;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
