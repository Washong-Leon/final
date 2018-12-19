package com.clin.entity;

import java.sql.Date;

public class Message {
    private String mid;
    private String uid;
    private String tid;
    private String cid;
    private String mcontent;
    private String mhttp;
    private long mfav;
    private long mreply;
    private long mcopy;
    private Date mdatetime;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getMcontent() {
        return mcontent;
    }

    public void setMcontent(String mcontent) {
        this.mcontent = mcontent;
    }

    public String getMhttp() {
        return mhttp;
    }

    public void setMhttp(String mhttp) {
        this.mhttp = mhttp;
    }

    public long getMfav() {
        return mfav;
    }

    public void setMfav(long mfav) {
        this.mfav = mfav;
    }

    public long getMreply() {
        return mreply;
    }

    public void setMreply(long mreply) {
        this.mreply = mreply;
    }

    public long getMcopy() {
        return mcopy;
    }

    public void setMcopy(long mcopy) {
        this.mcopy = mcopy;
    }

    public Date getMdatetime() {
        return mdatetime;
    }

    public void setMdatetime(Date mdatetime) {
        this.mdatetime = mdatetime;
    }
}
