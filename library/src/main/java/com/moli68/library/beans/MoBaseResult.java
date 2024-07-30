package com.moli68.library.beans;

import java.io.Serializable;

public class MoBaseResult implements Serializable {

    /**
     * 调用是否成功
     */
    private boolean issucc;
    private String msg;
    private String code;

    public boolean isIssucc() {
        return issucc;
    }

    public void setIssucc(boolean issucc) {
        this.issucc = issucc;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "MoBaseResult{" +
                "issucc=" + issucc +
                ", msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
