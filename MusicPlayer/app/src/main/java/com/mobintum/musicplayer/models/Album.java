package com.mobintum.musicplayer.models;

import android.content.ContentValues;
import android.content.Context;

import com.mobintum.musicplayer.database.DatabaseAdapter;

/**
 * Created by Rick on 13/05/15.
 */
public class Album {

    public static final String TABLE_NAME = "album";
    public static final String ALBUM_ID = "albumId";
    public static final String NAME = "name";
    public static final String POSTER_PIC = "posterPic";
    public static final String FK_ARTIST_ID = "fk_artistId";
    public static final String FK_GENRE_ID = "fk_genreId";

    private int albumId;
    private String name;
    private String posterPic;
    private int fkArtistId;
    private int fkGenreId;

    public Album(String name, String posterPic, int fkArtistId, int fkGenreId) {
        this.name = name;
        this.posterPic = posterPic;
        this.fkArtistId = fkArtistId;
        this.fkGenreId = fkGenreId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosterPic() {
        return posterPic;
    }

    public void setPosterPic(String posterPic) {
        this.posterPic = posterPic;
    }

    public int getFkArtistId() {
        return fkArtistId;
    }

    public void setFkArtistId(int fkArtistId) {
        this.fkArtistId = fkArtistId;
    }

    public int getFkGenreId() {
        return fkGenreId;
    }

    public void setFkGenreId(int fkGenreId) {
        this.fkGenreId = fkGenreId;
    }

    public static long insert(Context context, Album album){
        ContentValues cv = new ContentValues();
        cv.put(NAME, album.getName());
        cv.put(POSTER_PIC, album.getPosterPic());
        cv.put(FK_ARTIST_ID, album.getFkArtistId() );
        cv.put(FK_GENRE_ID, album.getFkGenreId() );

        return DatabaseAdapter.getDB(context).insert(TABLE_NAME, null,cv);

    }
}
