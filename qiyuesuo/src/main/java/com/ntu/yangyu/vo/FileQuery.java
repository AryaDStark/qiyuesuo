package com.ntu.yangyu.vo;

public class FileQuery {
    private String size;
    private String type;
    private String originalName;
    private String createTime;
    private String path;
    private String UUID;

    public FileQuery() {
    }

    public FileQuery(String size, String type, String originalName, String createTime, String path, String UUID) {
        this.size = size;
        this.type = type;
        this.originalName = originalName;
        this.createTime = createTime;
        this.path = path;
        this.UUID = UUID;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    @Override
    public String toString() {
        return "File:{" +
                "size:'" + size + '\'' +
                ", type:'" + type + '\'' +
                ", originalName:'" + originalName + '\'' +
                ", createTime:'" + createTime + '\'' +
                ", path:'" + path + '\'' +
                '}';
    }
}
