package com.mobintum.musicplayer.models;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.mobintum.musicplayer.database.DatabaseAdapter;

import java.util.ArrayList;

/**
 * Created by Rick on 13/05/15.
 */
public class SongOld {

    private String title;
    private String artist;
    private String album;
    private String urlSong;
    private String time;
    private String albumImage;

    public SongOld(String title, String artist, String album, String urlSong, String time, String albumImage) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.urlSong = urlSong;
        this.time = time;
        this.albumImage = albumImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getUrlSong() {
        return urlSong;
    }

    public void setUrlSong(String urlSong) {
        this.urlSong = urlSong;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(String albumImage) {
        this.albumImage = albumImage;
    }
/*
    public static ArrayList<SongOld> getSongs(Context context){

        ArrayList<SongOld> arraySongs = new ArrayList<SongOld>();


        arraySongs.add(new SongOld("Get Lucky", "Daft Punk", "Get Lucky","song_getlucky","5:03",context.getResources().getDrawable(R.mipmap.thumb_get_lucky)));
        arraySongs.add(new SongOld("Tachas y Perico", "Galatzia", "Unknow","song_tachas","5:03",context.getResources().getDrawable(R.mipmap.thumb_galatzia_tachas)));
        arraySongs.add(new SongOld("Love Me Again", "John Newman", "Unknow","john_newman_loveme_again","5:03",context.getResources().getDrawable(R.mipmap.ic_john_newman)));
        return arraySongs;

    }*/

    public static ArrayList<SongOld> getSongs(Context contex){

        Cursor cursor;
        ArrayList<SongOld> songs = new ArrayList<SongOld>();

        try{
            cursor = DatabaseAdapter.getDB(contex).rawQuery(
              "select s.name as title, a.name as artist, al.name as album, s.fileName, al.posterPic " +
              "from song s, artist a, album al " +
              "where s.fk_albumId= al.albumId " +
              "and al.fk_artistId=a.artistId"
             ,null);

            if(cursor!=null){
                Log.e("SONG", "2");
                for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                    String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                    String artist = cursor.getString(cursor.getColumnIndexOrThrow("artist"));;
                    String album = cursor.getString(cursor.getColumnIndexOrThrow("album"));;
                    String fileName = cursor.getString(cursor.getColumnIndexOrThrow("fileName"));;
                    String posterPic = cursor.getString(cursor.getColumnIndexOrThrow("posterPic"));
                    songs.add(new SongOld(title,artist,album,fileName,"00:00",posterPic));

                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }



        return songs;
    }

}
