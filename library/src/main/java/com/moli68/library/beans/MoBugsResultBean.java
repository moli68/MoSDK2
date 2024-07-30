package com.moli68.library.beans;

import java.io.Serializable;
import java.util.List;

/**
 *
 * Created by Administrator on 2018/10/8/0008.
 */

public class MoBugsResultBean extends MoBaseResult implements Serializable {

    /**
     * page : 0
     * count : 3
     * items : [{"id":3,"msg":"测试测试反馈bug","photos":"upload\\files\\2018\\10\\08\\e9efe0ca388e4b8b902662e68dd56b5c.png","dispose":"","ctime":"2018-10-08 22:20:57","dtime":""},{"id":2,"msg":"测试测试反馈bug","photos":"upload\\files\\2018\\10\\08\\9afb9d26d1264290b1c919261bd5340b.png","dispose":"","ctime":"2018-10-08 22:20:19","dtime":""},{"id":1,"msg":"测试测试反馈bug","photos":"upload\\files\\2018\\10\\08\\40495155b1d84a25a256bfb64e3c57bb.png","dispose":"","ctime":"2018-10-08 22:17:52","dtime":""}]
     * issucc : true
     * msg :
     * code :
     */

    private int page;
    private int count;
    private List<MoBugBean> items;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public List<MoBugBean> getItems() {
        return items;
    }

    public void setItems(List<MoBugBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
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
            return "ItemsBean{" +
                    "id=" + id +
                    ", msg='" + msg + '\'' +
                    ", photos='" + photos + '\'' +
                    ", dispose='" + dispose + '\'' +
                    ", ctime='" + ctime + '\'' +
                    ", dtime='" + dtime + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MoBugsResultBean{" +
                "page=" + page +
                ", count=" + count +
                ", items=" + items +
                '}';
    }
}
