package com.moli68.library.beans;

import java.io.Serializable;

/**
 * 登录返回的数据类
 */
public class MoLoginResultBean extends MoBaseResult implements Serializable {
    private MoLoginDataBean data;

    public MoLoginDataBean getData() {
        return data;
    }

    public void setData(MoLoginDataBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MoLoginResultBean{" +
                "data=" + (data!=null?data.toString():"null") +
                '}';
    }
}
