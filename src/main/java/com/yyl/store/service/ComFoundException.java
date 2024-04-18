package com.yyl.store.service;

/**
 * @author 65199
 * @ClassName ComFoundException
 * @description: TODO
 * @date 2024年04月18日
 * @version: 1.0
 */
public class ComFoundException extends RuntimeException{
    public ComFoundException() {
        super();
    }

    public ComFoundException(String message) {
        super(message);
    }

    public ComFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComFoundException(Throwable cause) {
        super(cause);
    }

    public ComFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
