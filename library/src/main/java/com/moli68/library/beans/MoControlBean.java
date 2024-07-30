package com.moli68.library.beans;

import java.io.Serializable;

/**
 * 开关类
 * Created by Administrator on 2018/9/28/0028.
 */

public class MoControlBean implements Serializable {
    /**
     * key : 开关1
     * value1 : S0010001
     * value2 : 111
     * value3 : 222
     */

    //对应后台名字
    private String key;
    /**
     * 对应后台代号
     */
    private String value1;
    /**
     * 对应后台值1
     */
    private int value2;
    /**
     * 对应后台值2
     */
    private String value3;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public int getValue2() {
        return value2;
    }

    public void setValue2(int value2) {
        this.value2 = value2;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }

    @Override
    public String toString() {
        return "MoControlBean{" +
                "key='" + key + '\'' +
                ", value1='" + value1 + '\'' +
                ", value2=" + value2 +
                ", value3='" + value3 + '\'' +
                '}';
    }
}
