package com.example.admin.e_sapa_ver_3_00.RecourseFile.SQLITE;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Optimus on 13.09.2015.
 */
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "EsapaDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table E_sapa2 ("
                + "id integer primary key autoincrement,"
                + "code text,"
                +"result boolean,"
                +"dataofresult text,"
                +"date text,"
                +"latitude INTEGER,"
                +"longitude INTEGER" + ");");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
