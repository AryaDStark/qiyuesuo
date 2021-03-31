package com.ntu.yangyu.vo;

public class TimeAndType {
    private String createTime;
    private String Path;

    public TimeAndType() {
    }

    public TimeAndType(String createTime, String path) {
        this.createTime = createTime;
        Path = path;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    @Override
    public String toString() {
        return "TimeAndType{" +
                "createTime='" + createTime + '\'' +
                ", Path='" + Path + '\'' +
                '}';
    }
}
