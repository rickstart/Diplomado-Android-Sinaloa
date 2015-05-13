package com.mobintum.musicplayer.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.mobintum.musicplayer.database.DatabaseAdapter;

/**
 * Created by Rick on 13/05/15.
 */
public class Artist {

    //Contants to relation to table
    public static final String TABLE_NAME = "artist";
    public static final String ARTIST_ID = "artistId";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String PHOTO = "photo";

    //atributes
    private int artistId;
    private String name;
    private int age;
    private String photo;

    public Artist(String name, int age, String photo) {

        this.name = name;
        this.age = age;
        this.photo = photo;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public static long insert(Context context, Artist artist){
        ContentValues cv = new ContentValues();
        cv.put(NAME, artist.getName());
        cv.put(AGE, artist.getAge());
        cv.put(PHOTO, artist.getPhoto());

        return DatabaseAdapter.getDB(context).insert(TABLE_NAME, null,cv);

    }

    public static int getIdArtist(Context context, String nameArtist){

        try {
            int id=0;
            Cursor cursor = DatabaseAdapter.getDB(context).query(TABLE_NAME, null, NAME + "=?", new String[]{nameArtist}, null, null, null);
            if (cursor != null) {
                for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(ARTIST_ID));
                }
            }
            return id;
        }catch (Exception e){
            e.printStackTrace();
            return 0;

        }
    }

}
