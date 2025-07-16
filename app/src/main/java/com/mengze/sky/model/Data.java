package com.mengze.sky.model;

/**
 * 身高数据
 */
public class Data {
    /**
     * 当前身高
     */
    private String currentHeight;
    /**
     * 身高值
     */
    private String height;
    /**
     * 最高身高
     */
    private String maxHeight;
    /**
     * 最矮身高
     */
    private String minHeight;
    /**
     * 体型值
     */
    private String scale;

    public String getCurrentHeight() { return currentHeight; }
    public void setCurrentHeight(String value) { this.currentHeight = value; }

    public String getHeight() { return height; }
    public void setHeight(String value) { this.height = value; }

    public String getMaxHeight() { return maxHeight; }
    public void setMaxHeight(String value) { this.maxHeight = value; }

    public String getMinHeight() { return minHeight; }
    public void setMinHeight(String value) { this.minHeight = value; }

    public String getScale() { return scale; }
    public void setScale(String value) { this.scale = value; }
}