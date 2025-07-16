package com.mengze.sky.model;

public class ApifoxModel {
    /**
     * 装扮数据
     */
    private Adorn adorn;
    /**
     * 剩余次数
     */
    private long balance;
    /**
     * 状态码，200成功，400常规错误（添加好友失败等），500服务器错误，501余额不足
     */
    private long code;
    /**
     * 身高数据
     */
    private Data data;
    private String msg;

    public Adorn getAdorn() { return adorn; }
    public void setAdorn(Adorn value) { this.adorn = value; }

    public long getBalance() { return balance; }
    public void setBalance(long value) { this.balance = value; }

    public long getCode() { return code; }
    public void setCode(long value) { this.code = value; }

    public Data getData() { return data; }
    public void setData(Data value) { this.data = value; }

    public String getMsg() { return msg; }
    public void setMsg(String value) { this.msg = value; }
}