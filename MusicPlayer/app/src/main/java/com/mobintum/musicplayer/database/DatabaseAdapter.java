package com.mobintum.musicplayer.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 *
 * Created by Rick on 12/05/15.
 *
 */
public class DatabaseAdapter {

    private static DatabaseHelper mDbHelper;
    private static SQLiteDatabase mDb;

    public static SQLiteDatabase getDB(Context context){
        if(mDb == null)
            openDB(context);

        return mDb;
    }

    public static boolean openDB(Context context) {

        if(mDbHelper != null)
            mDbHelper.close();

        mDbHelper = new DatabaseHelper(context);

        try{
            mDb = mDbHelper.getWritableDatabase();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

}
