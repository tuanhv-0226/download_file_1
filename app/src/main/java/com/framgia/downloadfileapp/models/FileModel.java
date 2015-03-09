package com.framgia.downloadfileapp.models;

/**
 * Created by framgiavn on 03/03/2015.
 */
public class FileModel {
    private int rowId;
    private String linkDownload;
    private String fileName;
    private long fileSize;
    private int fileStatus;
    private String saveTo;
    private String fileDate;

    public FileModel(String sLink) {
        this.linkDownload = sLink;
    }

    public FileModel(String sLink, String sSaveTo) {
        this.linkDownload = sLink;
        this.saveTo = sSaveTo;
    }

    public FileModel(String sLink, String sSaveTo, String fileName) {
        this.linkDownload = sLink;
        this.saveTo = sSaveTo;
        this.fileName = fileName;
        this.fileStatus = 0;
    }

    public FileModel(String sLink, String sSaveTo, String fileName, int fileStatus) {
        this.linkDownload = sLink;
        this.saveTo = sSaveTo;
        this.fileName = fileName;
        this.fileStatus = fileStatus;
    }

    public FileModel(int rowId, String sLink, String sSaveTo, String fileName, int fileStatus) {
        this.rowId = rowId;
        this.linkDownload = sLink;
        this.saveTo = sSaveTo;
        this.fileName = fileName;
        this.fileStatus = fileStatus;
    }

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    public String getLinkDownload() {
        return linkDownload;
    }

    public void setLinkDownload(String linkDownload) {
        this.linkDownload = linkDownload;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public int getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(int fileStatus) {
        this.fileStatus = fileStatus;
    }

    public String getSaveTo() {
        return saveTo;
    }

    public void setSaveTo(String saveTo) {
        this.saveTo = saveTo;
    }

    public String getFileDate() {
        return fileDate;
    }

    public void setFileDate(String fileDate) {
        this.fileDate = fileDate;
    }
}
