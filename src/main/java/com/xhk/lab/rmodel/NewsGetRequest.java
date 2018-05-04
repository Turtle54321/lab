package com.xhk.lab.rmodel;

/**
 * create by xhk on 2018/4/30
 */
public class NewsGetRequest {
    private Integer whichPage;
    private Integer perCount;
    private Integer type;

    public Integer getWhichPage() {
        return whichPage;
    }

    public void setWhichPage(Integer whichPage) {
        this.whichPage = whichPage;
    }

    public Integer getPerCount() {
        return perCount;
    }

    public void setPerCount(Integer perCount) {
        this.perCount = perCount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
