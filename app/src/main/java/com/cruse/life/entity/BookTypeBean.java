package com.cruse.life.entity;


import java.util.List;

/**
 * Created by lulanqin on 2017/5/10.
 */
public class BookTypeBean {

    /**
     * status : true
     * tngou : [{"description":"孕妇育儿","id":1,"keywords":"","name":"孕妇育儿","seq":1,"title":""},{"description":"美容美体，服装时尚","id":2,"keywords":"","name":"美容时尚","seq":2,"title":""},{"description":"健康养生","id":3,"keywords":"","name":"健康养生","seq":3,"title":""},{"description":"两性生活","id":4,"keywords":"","name":"两性生活","seq":4,"title":""},{"description":"美食烹饪","id":5,"keywords":"","name":"美食烹饪","seq":5,"title":""},{"description":"修养礼仪，心里励志","id":6,"keywords":"","name":"修养心里","seq":6,"title":""},{"description":"家庭教育","id":7,"keywords":"","name":"家庭教育","seq":7,"title":""},{"description":"幽默笑话","id":8,"keywords":"","name":"幽默笑话","seq":8,"title":""},{"description":"生活杂谈","id":9,"keywords":"","name":"生活杂谈","seq":9,"title":""},{"description":"其它","id":10,"keywords":"","name":"其它","seq":10,"title":""}]
     */

    private boolean status;
    /**
     * description : 孕妇育儿
     * id : 1
     * keywords :
     * name : 孕妇育儿
     * seq : 1
     * title :
     */

    private List<TngouBean> tngou;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<TngouBean> getTngou() {
        return tngou;
    }

    public void setTngou(List<TngouBean> tngou) {
        this.tngou = tngou;
    }

    public static class TngouBean {
        private String description;
        private int id;
        private String keywords;
        private String name;
        private int seq;
        private String title;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSeq() {
            return seq;
        }

        public void setSeq(int seq) {
            this.seq = seq;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
