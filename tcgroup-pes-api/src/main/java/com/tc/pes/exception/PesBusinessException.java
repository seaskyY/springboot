
package com.tc.pes.exception;

import org.slf4j.Logger;

/**
 * Scm业务异常
 * 
 * @author Tantal
 * @version v2.0
 * @date 2015/08/13 14:39
 */
public class PesBusinessException extends PesException {
    
    public PesBusinessException() {
    
        super("PES业务异常");
    }
    
    public PesBusinessException(String errorMsg) {
    
        super(errorMsg);
    }
    
    public PesBusinessException(String errorMsg, Throwable cause) {
    
        super(errorMsg, cause);
    }
    
    /**
     * 判断是否为NULL
     * 
     * @throws PesException
     */
    public static void isNull(Object reference, Logger logger, String msg) throws PesException
    {
    
        if (reference == null)
            throw create(logger, msg);
    }
    
    /**
     * 创建异常
     */
    public static PesException create(Logger logger, String msg) {
    
        logger.error(msg);
        return new PesBusinessException(msg);
    }
    
    /**
     * 创建异常
     */
    public static PesException create(Logger logger, String msg, Throwable cause) {
    
        logger.error(msg, cause);
        return new PesBusinessException(msg, cause);
    }
    
    /**
     * 判断是否为TRUE
     */
    public static void isTrue(boolean expression, Logger logger, String msg) throws PesException {
    
        if (expression)
            throw create(logger, msg);
    }
    
}
