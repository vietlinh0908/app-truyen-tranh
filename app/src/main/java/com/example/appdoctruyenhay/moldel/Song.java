package com.example.appdoctruyenhay.moldel;

public class Song {
    private String Title;
    private int File;///trong resource đều để kiểu int

    public Song(String title, int file) {
        Title = title;
        File = file;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getFile() {
        return File;
    }

    public void setFile(int file) {
        File = file;
    }
}

