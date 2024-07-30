package com.moli68.library.beans;

import java.io.Serializable;

/**
 * 商品里面的图片bean
 * Created by Administrator on 2018/9/28/0028.
 */

public class MoImgUrlBean implements Serializable {
    /**
     * url : http://gtapp.ngrok.80xc.com:82/upload/vipbackgroundimg/2018-09-28/31db5b96-a609-40fb-bb2f-613ea832c27c.png
     * type : 3
     */

    private String url;
    private int type;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MoImgUrlBean{" +
                "url='" + url + '\'' +
                ", type=" + type +
                '}';
    }
}
