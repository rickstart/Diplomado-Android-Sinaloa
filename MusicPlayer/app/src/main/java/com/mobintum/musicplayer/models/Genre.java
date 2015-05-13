package com.mobintum.musicplayer.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.mobintum.musicplayer.database.DatabaseAdapter;

/**
 * Created by Rick on 13/05/15.
 */
public class Genre {

    public static final String TABLE_NAME = "genre";
    public static final String GENRE_ID = "genreId";
    public static final String NAME = "name";

    private int genreId;
    private String name;

    public Genre(String name) {
        this.name = name;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static long insert(Context context, Genre genre){
        ContentValues cv = new ContentValues();
        cv.put(NAME, genre.getName());
        return DatabaseAdapter.getDB(context).insert(TABLE_NAME, null,cv);

    }

    public static int getIdGenre(Context context, String nameGenre){

        try {
            int id=0;
            Cursor cursor = DatabaseAdapter.getDB(context).query(TABLE_NAME, null, NAME + "=?", new String[]{nameGenre}, null, null, null);
            if (cursor != null) {
                for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(GENRE_ID));
                }
            }
            return id;
        }catch (Exception e){
            e.printStackTrace();
            return 0;

        }
    }
}
