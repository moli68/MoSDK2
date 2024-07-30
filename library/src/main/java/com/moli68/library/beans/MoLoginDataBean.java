package com.moli68.library.beans;

import java.io.Serializable;

/**
 * 登录返回的数据类
 * {"id":425844,"uk":"bcc6ad3b4a6243dd8fc07c5fb41111dd","img":"","na":"","tl":"18874145751"}
 */
public class MoLoginDataBean implements Serializable {

    private long id;
    /**
     * key
     */
    private String uk;
    /**
     * 头像
     */
    private String img;
    /**
     * 昵称
     */
    private String na;
    /**
     * 电话
     */
    private String tl;
    /**
     * 登录类型 1：微信登录
     */
    private int wc;
    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public void setUk(String uk) {
        this.uk = uk;
    }
    public String getUk() {
        return uk;
    }

    public void setImg(String img) {
        this.img = img;
    }
    public String getImg() {
        return img;
    }

    public void setNa(String na) {
        this.na = na;
    }
    public String getNa() {
        return na;
    }

    public void setTl(String tl) {
        this.tl = tl;
    }
    public String getTl() {
        return tl;
    }

    @Override
    public String toString() {
        return "MoLoginDataBean{" +
                "id=" + id +
                ", uk='" + uk + '\'' +
                ", img='" + img + '\'' +
                ", na='" + na + '\'' +
                ", tl='" + tl + '\'' +
                ", wc='" + wc + '\'' +
                '}';
    }

    public int getWc() {
        return wc;
    }

    public void setWc(int wc) {
        this.wc = wc;
    }
}
