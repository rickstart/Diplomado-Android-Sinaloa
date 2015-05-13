package com.mobintum.musicplayer.models;

import android.content.ContentValues;
import android.content.Context;

import com.mobintum.musicplayer.database.DatabaseAdapter;

/**
 * Created by Rick on 06/05/15.
 */
public class Song {

    public static final String TABLE_NAME = "song";
    public static final String SONG_ID = "songId";
    public static final String NAME= "name";
    public static final String FILE_NAME = "fileName";
    public static final String FK_ALBUM_ID = "fk_albumId";


    private int songId;
    private String name;
    private String fileName;
    private int fkAlbumId;

    public Song(String name, String fileName, int fkAlbumId) {
        this.name = name;
        this.fileName = fileName;
        this.fkAlbumId = fkAlbumId;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFkAlbumId() {
        return fkAlbumId;
    }

    public void setFkAlbumId(int fkAlbumId) {
        this.fkAlbumId = fkAlbumId;
    }

    public static long insert(Context context, Song song){
        ContentValues cv = new ContentValues();
        cv.put(NAME, song.getName());
        cv.put(FILE_NAME, song.getFileName());
        cv.put(FK_ALBUM_ID, song.getFkAlbumId());
        return DatabaseAdapter.getDB(context).insert(TABLE_NAME, null,cv);

    }
}
