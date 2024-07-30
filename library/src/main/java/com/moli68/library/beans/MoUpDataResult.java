package com.moli68.library.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/9/28/0028.
 */

public class MoUpDataResult extends MoBaseResult implements Serializable{



    /**
     * member : {"count":0,"end_time":""}
     * imgs : [{"name":"广告1","url_link":"http://www.baidu.con","url_img":"http://gtapp.ngrok.80xc.com:82/upload/ads/2018-09-28/5d60acfc-609d-4c9f-b727-3be18fb34d2c.png"}]
     * node : [{"key":"开关1","value1":"S0010001","value2":111,"value3":"222"}]
     * service : [{"id":1,"val":"1","key":"会员1","ali":0.01,"wechat":0.01,"msg":"备注","price":100,"img_url":[{"url":"http://gtapp.ngrok.80xc.com:82/upload/vipbackgroundimg/2018-09-28/31db5b96-a609-40fb-bb2f-613ea832c27c.png","type":3},{"url":"http://gtapp.ngrok.80xc.com:82/upload/vipbackgroundimg/2018-09-28/31db5b96-a609-40fb-bb2f-613ea832c27c.png","type":4}],"pay":"[1][2]"}]
     * qq : qq 123456
     * issucc : true
     * msg :
     * code :
     */

    private MoMemberBean member;
    private String qq;
    private List<MoAdsBean> imgs;
    private List<MoControlBean> node;
    private List<MoGoodsBean> service;


    public MoMemberBean getMember() {
        return member;
    }

    public void setMember(MoMemberBean member) {
        this.member = member;
    }

    public List<MoAdsBean> getImgs() {
        return imgs;
    }

    public void setImgs(List<MoAdsBean> imgs) {
        this.imgs = imgs;
    }

    public List<MoControlBean> getNode() {
        return node;
    }

    public void setNode(List<MoControlBean> node) {
        this.node = node;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }


    public List<MoGoodsBean> getService() {
        return service;
    }

    public void setService(List<MoGoodsBean> service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "MoUpDataResult{" +
                "member=" + member +
                ", qq='" + qq + '\'' +
                ", imgs=" + imgs +
                ", node=" + node +
                ", service=" + service +
                '}';
    }
}
