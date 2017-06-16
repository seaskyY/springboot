package com.tc.pes.exception;

/**
 * Created by yuyichuan on 10/21/16.
 */
public class PesException extends Exception {

    public PesException() {
        super("PES自定义异常");
    }

    public PesException(String errorMsg) {
        super(errorMsg);
    }

    public PesException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
    }
}
