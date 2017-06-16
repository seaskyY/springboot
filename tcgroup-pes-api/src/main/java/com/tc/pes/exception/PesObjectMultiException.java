package com.tc.pes.exception;

import org.slf4j.Logger;

/**
 * 对象不唯一异常
 *
 * @author Tantal
 * @version v2.0
 * @date 2015/08/13 14:36
 */
public class PesObjectMultiException extends RuntimeException {

    public PesObjectMultiException() {
        super("PES对象不唯一");
    }

    public PesObjectMultiException(String errorMsg) {
        super(errorMsg);
    }

    public PesObjectMultiException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
    }

    /**
     * 创建异常
     */
    public static PesObjectMultiException create(Logger logger, String msg) {

        logger.error(msg);
        return new PesObjectMultiException(msg);
    }


    /**
     * 判断是否为TRUE
     */
    public static void isTrue(boolean expression, Logger logger, String msg) throws PesException {

        if (expression)
            throw create(logger, msg);
    }
}
