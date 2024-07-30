package com.moli68.library.beans;

import java.io.Serializable;

public class ApliyBean extends MoBaseResult implements Serializable {

    private String appid;
    private float amount;
    private String package_str;


    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getPackage_str() {
        return package_str;
    }

    public void setPackage_str(String package_str) {
        this.package_str = package_str;
    }

    @Override
    public String toString() {
        return "ApliyBean{" +
                ", appid='" + appid + '\'' +
                ", amount=" + amount +
                ", package_str='" + package_str + '\'' +
                '}';
    }
}
