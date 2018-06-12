package org.xfs.common.exception;

import java.io.StringWriter;
import java.util.Arrays;

public class BaseException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 6357175635235084211L;
    // 所属模块
    private String module;

    /**
     * 错误码
     */
    private int code;

    /**
     * 错误码对应的参数
     */
    private Object[] args;

    /**
     * 错误消息
     */
    private String message;
    public BaseException(String message) {
        this("", 0, null, message);
    }
    public BaseException(int code,String message) {
        this("", code, null, message);
    }

    public BaseException(String message,Throwable cause){
        //this("", 0, null, message);
        super(message,cause);
    }
    public BaseException(String module,int code,String message) {
        this(module, code, null, message);
    }
    public BaseException(String module, int code, Object[] args, String message) {
        this.module = module;
        this.code = code;
        this.args = args;
        this.message = message;
    }

    public BaseException(String module, int code, Object[] args) {
        this(module, code, args, null);
    }

    public BaseException(String module, String message) {
        this(module, 0, null, message);
    }

    public BaseException(int code, Object[] args) {
        this("", code, args, null);
    }




    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BaseException{" +
                "module='" + module + '\'' +
                ", code=" + code +
                ", args=" + Arrays.toString(args) +
                ", message='" + message + '\'' +
                '}';
    }




    /**
     * 重写异常堆栈方法，去除堆栈信息，优化异常的内存占用
     */
    @Override
    public StackTraceElement[] getStackTrace() {
        return new StackTraceElement[0];
    }

    /**
     * 项目名称：webservice <br>
     * 类名称：ExceptionUtil.java<br>
     * 方法描述： 返回错误信息字符串<br>
     * Copyright: Copyright (c) 西风团 <br>
     * Result:String<br>
     * Param:@param e 错误信息字符串 Param:@return<br>
     * 创建人：神风逐胜<br>
     * 创建时间：2014年11月14日 上午10:49:49 <br>
     * 修改说明：<br>
     *
     * @version 1.0
     */
    public static String getExceptionMessage(Exception ex) {
        StringWriter sw = new StringWriter();
        ex.printStackTrace();
        return sw.toString();
    }

}
