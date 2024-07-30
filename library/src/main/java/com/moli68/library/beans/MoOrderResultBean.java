package com.moli68.library.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/10/7/0007.
 */

public class MoOrderResultBean extends MoBaseResult implements Serializable {

    /**
     * app_key : 2018100761638338
     * money : 0.01
     * timestramp :
     * nonce :
     * package : app_id=2018100761638338&biz_content=%7b%22subject%22%3a%22%e4%bc%9a%e5%91%981%22%2c%22out_trade_no%22%3a%2220181007214101259477422%22%2c%22total_amount%22%3a0.01%7d&charset=utf-8&format=JSON&method=alipay.trade.app.pay&notify_url=http%3a%2f%2fap.moli68.com%2fnotifys-2-20181007214101259477422.html&sign_type=RSA2&timestamp=2018-10-07+21%3a41%3a01&version=1.0&sign=o%2bTrOKhylKVCLh7B11CcyHd%2bTq01pdoj7uCwLhjMGxfiW0Zvl4xIxfWA4nANHJIx4qlev6%2byNHZE8cqMXm6Mgqy4c0FtvPxwEPthvCXSulhO2WxAEcp7TK%2frBt0mQwnxMwA57%2bpOwU8UUv0oSMG0ZEWlE3bbkwMMH79gY%2brzPqweYXw9dJNxGUUzViPld4S7%2ffEMEV8NiDUS7Vik8EWKcPnBfztD%2fKGrd346uqvXCkW6s%2f27PNoP6bdqiOi6MNW%2bMkcqx5ajvGBZTrk4yxpP%2f20UsWadAmJQztyeitRY6netsPMwq01AH1HGblVUwAU7%2bs%2bYIRtodZq1zNuWOzVrHw%3d%3d
     * sign :
     * qrcode :
     * weburl :
     * partner_id :
     * prepay_id :
     * sign_str :
     * issucc : true
     * msg :
     * code :
     */

    private String app_key;
    private String money;
    private String timestramp;
    private String nonce;
    @SerializedName("package")
    private String packageX;
    private String sign;
    private String qrcode;
    private String weburl;
    private String partner_id;
    private String prepay_id;
    private String sign_str;


    public String getApp_key() {
        return app_key;
    }

    public void setApp_key(String app_key) {
        this.app_key = app_key;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTimestramp() {
        return timestramp;
    }

    public void setTimestramp(String timestramp) {
        this.timestramp = timestramp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getWeburl() {
        return weburl;
    }

    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }

    public String getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(String partner_id) {
        this.partner_id = partner_id;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getSign_str() {
        return sign_str;
    }

    public void setSign_str(String sign_str) {
        this.sign_str = sign_str;
    }


    @Override
    public String toString() {
        return "MoOrderResultBean{" +
                "app_key='" + app_key + '\'' +
                ", money='" + money + '\'' +
                ", timestramp='" + timestramp + '\'' +
                ", nonce='" + nonce + '\'' +
                ", packageX='" + packageX + '\'' +
                ", sign='" + sign + '\'' +
                ", qrcode='" + qrcode + '\'' +
                ", weburl='" + weburl + '\'' +
                ", partner_id='" + partner_id + '\'' +
                ", prepay_id='" + prepay_id + '\'' +
                ", sign_str='" + sign_str + '\'' +
                '}';
    }
}
