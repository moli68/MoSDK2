package com.moli68.library.beans;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/10/8/0008.
 */

public class MoBugBean implements Serializable {


    /**
     * id : 3
     * msg : 测试测试反馈bug
     * photos : upload\files\2018\10\08\e9efe0ca388e4b8b902662e68dd56b5c.png
     * dispose :
     * ctime : 2018-10-08 22:20:57
     * dtime :
     */

    private int id;
    private String msg;
    private String photos;
    private String dispose;
    private String ctime;
    private String dtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getDispose() {
        return dispose;
    }

    public void setDispose(String dispose) {
        this.dispose = dispose;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }

    @Override
    public String toString() {
        return "MoBugBean{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", photos='" + photos + '\'' +
                ", dispose='" + dispose + '\'' +
                ", ctime='" + ctime + '\'' +
                ", dtime='" + dtime + '\'' +
                '}';
    }
}
