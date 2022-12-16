package com.bitcamp.gabojago.vo.event;

public class EventAttachedFile {

    private Integer fileNo;
    private String filePath;
    private String fileName;
    private Integer itemNo;

    public EventAttachedFile() {

    }

    public EventAttachedFile(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "EventAttachedFile{" +
                "fileNo=" + fileNo +
                ", filePath='" + filePath + '\'' +
                ", fileName='" + fileName + '\'' +
                ", itemNo=" + itemNo +
                '}';
    }

    public Integer getFileNo() {
        return fileNo;
    }

    public void setFileNo(Integer fileNo) {
        this.fileNo = fileNo;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getItemNo() {
        return itemNo;
    }

    public void setItemNo(Integer itemNo) {
        this.itemNo = itemNo;
    }
}
