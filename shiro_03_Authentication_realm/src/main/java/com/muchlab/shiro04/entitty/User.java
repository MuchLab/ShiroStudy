package com.muchlab.shiro03.entitty;

import java.util.Date;

public class User {
    private Integer id;
    private String username;
    private String pwd;
    private Date createTime;

    public User() {
    }

    public User(Integer id, String username, String pwd, Date createTime) {
        this.id = id;
        this.username = username;
        this.pwd = pwd;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
