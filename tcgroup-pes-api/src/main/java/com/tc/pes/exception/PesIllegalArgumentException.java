package com.tc.pes.exception;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

/**
 * 参数非法异常
 *
 * @author Tantal
 * @version v3.0
 * @date 2015/08/24 10:11
 */
public class PesIllegalArgumentException extends IllegalArgumentException {

    /** TODO 字段含义 */

	private static final long serialVersionUID = 1L;

	public PesIllegalArgumentException() {
        super("参数非法异常");
    }

    public PesIllegalArgumentException(String errorMsg) {
        super(errorMsg);
    }

    public PesIllegalArgumentException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
    }


    /**
     * 创建异常
     */
    public static PesIllegalArgumentException create(Logger logger, String msg) {

        logger.error(msg);
        return new PesIllegalArgumentException(msg);
    }

    /**
     * 判断是否为TRUE
     */
    public static void isTrue(boolean expression, Logger logger, String msg) throws PesIllegalArgumentException {

        if (expression)
            throw create(logger, msg);
    }

    /**
     * 判断是否为NULL
     */
    public static void isNull(Object reference, Logger logger, String msg) throws PesIllegalArgumentException {

        if (reference == null)
            throw create(logger, msg);

        if(reference instanceof Long && (Long)reference <= 0){
            throw create(logger, msg);
        }

        if(reference instanceof String && StringUtils.isBlank((String)reference)){
            throw create(logger, msg);
        }
    }
}