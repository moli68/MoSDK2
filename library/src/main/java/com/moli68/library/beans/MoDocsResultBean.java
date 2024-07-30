package com.moli68.library.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/10/9/0009.
 */

public class MoDocsResultBean extends MoBaseResult implements Serializable {

    /**
     * data : [{"id":1,"title":"联系方式","type":1,"msg":"qq 123456","url":"http://ap.moli68.com/h5/help/edit-1.html"},{"id":2,"title":"帮助文档","type":2,"msg":"","url":"http://ap.moli68.com/h5/help/edit-2.html"}]
     * issucc : true
     * msg :
     * code :
     */

    private List<MoDocBean> data;


    public List<MoDocBean> getData() {
        return data;
    }

    public void setData(List<MoDocBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MoDocsResultBean{" +
                "data=" + data +
                '}';
    }
}
