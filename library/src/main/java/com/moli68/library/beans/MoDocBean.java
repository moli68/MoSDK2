package com.moli68.library.beans;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/10/9/0009.
 */

public class MoDocBean implements Serializable {

    /**
     * id : 1
     * title : 联系方式
     * type : 1
     * msg : qq 123456
     * url : http://ap.moli68.com/h5/help/edit-1.html
     */

    private int id;
    private String title;
    private int type;
    private String msg;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MoDocBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", msg='" + msg + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
