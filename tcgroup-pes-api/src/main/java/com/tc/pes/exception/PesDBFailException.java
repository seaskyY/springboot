package com.tc.pes.exception;

import org.slf4j.Logger;

/**
 * 数据库异常
 *
 * @author Tantal
 * @version v3.0
 * @date 2015/08/11 10:05
 */
public class PesDBFailException extends PesException {
    public PesDBFailException() {
        super("数据库异常");
    }

    public PesDBFailException(String errorMsg) {
        super(errorMsg);
    }

    public PesDBFailException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
    }


    /**
     * 创建异常
     */
    public static PesException create(Logger logger, String msg) {

        logger.error(msg);
        return new PesDBFailException(msg);
    }


    /**
     * 判断是否为TRUE
     */
    public static void isTrue(boolean expression, Logger logger, String msg) throws PesException {

        if (expression)
            throw create(logger, msg);
    }

}
