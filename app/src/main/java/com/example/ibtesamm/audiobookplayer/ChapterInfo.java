package com.example.ibtesamm.audiobookplayer;

import android.media.MediaMetadata;

public class ChapterInfo {

    //Class that represents a chapter

    public String name; //Name of the Book
    public String url; //Url for the song

    public ChapterInfo() {

    }

    public ChapterInfo(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
