package com.moli68.library.beans;

import java.io.Serializable;

/**
 * 会员信息
 * Created by Administrator on 2018/9/28/0028.
 */

public class MoMemberBean implements Serializable {
    /**
     * count : 0
     * end_time :
     */

    //是否是时间点之前的用户
    private boolean isstime;
    //注册时间
    private String stime;
    private int count;
    private String end_time;
    private int over;  //会员是否过期  1表示过期了  0没过期

    public int getOver() {
        return over;
    }

    public void setOver(int over) {
        this.over = over;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getEnd_time() {
        return end_time;
    }

    public boolean isIsstime() {
        return isstime;
    }

    public void setIsstime(boolean isstime) {
        this.isstime = isstime;
    }


    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    @Override
    public String toString() {
        return "MoMemberBean{" +
                "count=" + count +
                ", end_time='" + end_time + '\'' +
                ", over=" + over +
                '}';
    }
}
