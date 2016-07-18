package com.example.myfuncdemo.bean;

import java.util.List;

/**
 * Administrator on 2016/7/14.
 */
public class TopSearchBean {

    /**
     * status : true
     * total : 166
     * tngou : [{"count":0,"description":"网易娱乐
     */
    private String msg;
    private boolean status;
    private int total;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * count : 0
     * description : 网易娱乐11月30日报道今日，
     * fcount : 0
     * id : 4746
     * img : http://tnfs.tngou.net/img/top/default.jpg
     * keywords : 蔡康永谈李敖暗讽
     * message : 　　"马晓康"组合。 　　网易娱乐
     * rcount : 0
     * time : 1468463665183
     * title : 蔡康永谈李敖暗讽:谢谢评论!祝健康快乐
     * topclass : 0
     */

    private List<TngouBean> tngou;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<TngouBean> getTngou() {
        return tngou;
    }

    public void setTngou(List<TngouBean> tngou) {
        this.tngou = tngou;
    }

    public static class TngouBean {
        private int count;
        private String description;
        private int fcount;
        private int id;
        private String img;
        private String keywords;
        private String message;
        private int rcount;
        private long time;
        private String title;
        private int topclass;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getFcount() {
            return fcount;
        }

        public void setFcount(int fcount) {
            this.fcount = fcount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getRcount() {
            return rcount;
        }

        public void setRcount(int rcount) {
            this.rcount = rcount;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getTopclass() {
            return topclass;
        }

        public void setTopclass(int topclass) {
            this.topclass = topclass;
        }
    }
}
