package org.xfs.common.exception;

import java.io.StringWriter;

/**
 * Created by 神风逐胜 on 2017/9/24 0024.9:21
 * version:1.0
 */
public class BusinessException extends BaseException {

    /**
     *
     */
    private static final long serialVersionUID = 6680346093252653285L;

    /**
     * 构造函数
     *
     * @param message
     */
    public BusinessException(String message) {
       super(message);
    }

    public BusinessException(String message,Throwable throwable){
        super(message,throwable);
    }

    /**
     * 构造函数
     *
     * @param message
     */
    public BusinessException(int code,String message) {
        super(code,message);
    }


    /**
     * 构造函数
     *
     * @param message
     */
    public BusinessException(String module,int code,String message) {
        super(module, code, null, message);
    }



    /**
     * 构造函数
     *
     * @param message
     */
    public BusinessException(String module,int code,Object[] args,String message) {
        super(module, code, args, message);
    }


}
