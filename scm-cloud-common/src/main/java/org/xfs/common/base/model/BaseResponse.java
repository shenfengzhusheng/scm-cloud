package org.xfs.common.base.model;

import org.xfs.common.constant.ErrorCode;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {
    private int code=10001;
    private String messageg;
    private T data;
    public BaseResponse(){}
    public BaseResponse(int code,String message){
        this.code=code;
        this.messageg=message;
    }
    public BaseResponse(int code,String message,T data){
        this.code=code;
        this.messageg=message;
        this.data=data;
    }

    public static BaseResponse<String> success(String message){
        return new BaseResponse<String>(ErrorCode.SUCCESS,message);
    }

    public static <T>  BaseResponse<T> success(String message,T data){
        return new BaseResponse<T>(ErrorCode.SUCCESS,message,data);
    }

    public static BaseResponse<String> fail(String msg){
        return new BaseResponse<String>(ErrorCode.SYSTEM_ERROR_10001,msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessageg() {
        return messageg;
    }

    public void setMessageg(String messageg) {
        this.messageg = messageg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", messageg='" + messageg + '\'' +
                ", data=" + data +
                '}';
    }
}
