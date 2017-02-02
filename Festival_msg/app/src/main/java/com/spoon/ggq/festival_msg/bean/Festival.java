package com.spoon.ggq.festival_msg.bean;

import java.util.Date;

/**
 * Created by ggq on 2017/1/29.
 */
public class Festival {
    private int id;
    private String name;
    private  String desc;
    private Date date;

    public Festival(int id, String name) {
        this.id=id;
        this.name=name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
