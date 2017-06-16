package com.tc.pes.exception;

import org.slf4j.Logger;

/**
 * 对象空异常
 *
 * @author Tantal
 * @version v2.0
 * @date 2015/08/13 14:36
 */
public class PesObjectNullException extends RuntimeException {

    public PesObjectNullException() {
        super("SCM对象空异常");
    }

    public PesObjectNullException(String errorMsg) {
        super(errorMsg);
    }

    public PesObjectNullException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
    }

    /**
     * 创建异常
     */
    public static PesObjectNullException create(Logger logger, String msg) {

        logger.error(msg);
        return new PesObjectNullException(msg);
    }


    /**
     * 判断是否为TRUE
     */
    public static void isTrue(boolean expression, Logger logger, String msg)
    {

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
