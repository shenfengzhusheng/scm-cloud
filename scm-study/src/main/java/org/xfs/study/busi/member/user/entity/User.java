package org.xfs.study.busi.member.user.entity;

public class User {
    /** 用户ID **/
    private Long userId;

    /** 用户名 **/
    private String userName;

    /** 手机号 **/
    private String userPhone;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }
}