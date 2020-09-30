package com.example.commons.exception;

/**
 * @Title:
 * @auther: raohr
 * @Date: 2020/9/30 17:24
 * @param:
 * @Description:
 * @return:
 * @throws:
 */
public class AppException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public AppException() {
    }

    public AppException(Throwable cause) {
        super(cause);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(String message) {
        super(message);
    }
}
