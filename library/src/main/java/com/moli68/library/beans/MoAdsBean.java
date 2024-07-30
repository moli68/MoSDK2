package com.moli68.library.beans;

import java.io.Serializable;

/**
 * 不同位置的广告bean
 *  by Administrator on 2018/9/28/0028.
 */

public class MoAdsBean implements Serializable {
    /**
     * name : 广告1
     * url_link : http://www.baidu.con
     * url_img : http://gtapp.ngrok.80xc.com:82/upload/ads/2018-09-28/5d60acfc-609d-4c9f-b727-3be18fb34d2c.png
     */

    private String name;
    private String url_link;
    private String url_img;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl_link() {
        return url_link;
    }

    public void setUrl_link(String url_link) {
        this.url_link = url_link;
    }

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    @Override
    public String toString() {
        return "MoAdsBean{" +
                "name='" + name + '\'' +
                ", url_link='" + url_link + '\'' +
                ", url_img='" + url_img + '\'' +
                '}';
    }
}
