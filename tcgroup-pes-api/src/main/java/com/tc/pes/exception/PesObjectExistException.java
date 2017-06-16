package com.tc.pes.exception;

import org.slf4j.Logger;

/**
 * 对象存在异常
 *
 * @author Tantal
 * @version v2.0.0
 * @date 2015/08/24 10:10
 */
public class PesObjectExistException extends RuntimeException {

    public PesObjectExistException() {
        super("SCM对象已存在");
    }

    public PesObjectExistException(String errorMsg) {
        super(errorMsg);
    }

    public PesObjectExistException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
    }

    /**
     * 创建异常
     */
    public static PesObjectExistException create(Logger logger, String msg) {

        logger.error(msg);
        return new PesObjectExistException(msg);
    }


    /**
     * 判断是否为TRUE
     */
    public static void isTrue(boolean expression, Logger logger, String msg) throws PesException {

        if (expression)
            throw create(logger, msg);
    }
    
    /**
     * 判断是否为NULL
     */
    public static void isNull(Object reference, Logger logger, String msg)
    {

        if (reference == null)
            throw create(logger, msg);
    }
}