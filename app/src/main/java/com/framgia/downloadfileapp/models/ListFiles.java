package com.framgia.downloadfileapp.models;

import java.util.ArrayList;

/**
 * Created by framgiavn on 03/03/2015.
 */
public class ListFiles {
    ArrayList<FileModel> listFiles;

    public ListFiles() {
        super();
        this.listFiles = new ArrayList<FileModel>();
    }

    public ArrayList<FileModel> getListFiles() {
        return listFiles;
    }

    public void setListFiles(ArrayList<FileModel> listFiles) {
        this.listFiles = listFiles;
    }

    public void addFileDownload(FileModel fileDownload) {
        this.listFiles.add(fileDownload);
    }

    public void removeFileDownload(int posLink) {
        this.listFiles.remove(posLink);
    }

    public FileModel getFileDownload(int posLink) {
        return this.listFiles.get(posLink);
    }
}
