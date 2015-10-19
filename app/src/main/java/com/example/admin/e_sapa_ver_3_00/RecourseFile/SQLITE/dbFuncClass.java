package com.example.admin.e_sapa_ver_3_00.RecourseFile.SQLITE;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.admin.e_sapa_ver_3_00.RecourseFile.dbObject.dbObject;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Optimus on 13.09.2015.
 */
public class dbFuncClass {
    int idColIndex, codeColIndex, resultColIndex, resullofDataColIndex, latitudeColIndex, longitudeColIndex;
    private String LOG_SQLITE = "LOG_SQLITE", tableName = "E_sapa2", tag_id = "id", tag_result = "result", tag_code = "code", tag_dataofresult = "dataofresult", tag_latitude = "latitude", tag_longitude = "longitude";
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private ContentValues cv;
    private Cursor cursor;
    private List<String> crudList;
    private List<Double> position;
    private LatLng latlng;
    private dbObject dbObject;


    public dbFuncClass(Activity activity) {

        Log.d(LOG_SQLITE, "Create SQLITE DB");
        dbHelper = new DBHelper(activity);
        db = dbHelper.getWritableDatabase();
        cv = new ContentValues();
        crudList = new ArrayList<>();
        dbObject = new dbObject();
    }

    public List<String> getFullDB() {
        Log.d(LOG_SQLITE, "Reade SQLITE DB data");
        cursor = db.query(tableName, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            crudList.add("Нажмите сюда");

            codeColIndex = cursor.getColumnIndex(tag_code);

            do {
                crudList.add(" " + cursor.getString(codeColIndex) + " ");
            } while (cursor.moveToNext());
        } else {
            crudList.add("no data");
        }
        return crudList;
    }

    public List<String> getAllCode() {
        Log.d(LOG_SQLITE, "Reade SQLITE DB data");
        cursor = db.query(tableName, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            crudList.add("Нажмите сюда");

            codeColIndex = cursor.getColumnIndex(tag_code);

            do {
                crudList.add(" " + cursor.getString(resultColIndex) + " ");
            } while (cursor.moveToNext());
        } else {
            crudList.add("no data");
        }
        return crudList;
    }

    public List<String> getAllResult() {
        Log.d(LOG_SQLITE, "Reade SQLITE DB data");
        cursor = db.query(tableName, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            crudList.add("Нажмите сюда");

            resultColIndex = cursor.getColumnIndex(tag_result);

            do {
                crudList.add(" " + cursor.getString(resultColIndex) + " ");
            } while (cursor.moveToNext());
        } else {
            crudList.add("no data");
        }
        return crudList;
    }

    public List<String> getAllDescription() {
        Log.d(LOG_SQLITE, "Reade SQLITE DB data");
        cursor = db.query(tableName, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            crudList.add("Нажмите сюда");

            resullofDataColIndex = cursor.getColumnIndex(tag_dataofresult);
            do {
                crudList.add(" " + cursor.getString(resullofDataColIndex) + " ");
            } while (cursor.moveToNext());
        } else {
            crudList.add("no data");
        }
        return crudList;
    }


    public LatLng getLocation(int id) {

        String str = new String(String.valueOf(id));
        cursor = db.query(tableName, new String[]{tag_latitude, tag_longitude}, tag_id + "=" + str, null, null, null, null, null);

        if (cursor.moveToFirst()) {

            latitudeColIndex = cursor.getColumnIndex(tag_latitude);
            longitudeColIndex = cursor.getColumnIndex(tag_longitude);

            do {
                latlng = new LatLng(cursor.getDouble(latitudeColIndex), cursor.getInt(longitudeColIndex));
            } while (cursor.moveToNext());
        } else {
            latlng = new LatLng(10, 10);
        }
        return latlng;
    }


    public void writeSQLITE(String code, boolean result, String dataofresult, double latitude, double longitude) {

        Log.d(LOG_SQLITE, "Write SQLITE DB data");

        cv.put(tag_code, code);
        cv.put(tag_result, result);
        cv.put(tag_dataofresult, dataofresult);
        cv.put(tag_latitude, latitude);
        cv.put(tag_longitude, longitude);
        db.insert(tableName, null, cv);
    }

    public dbObject getDataObject(int id) {

        String str = new String(String.valueOf(id));
        cursor = db.query(tableName, new String[]{tag_code, tag_result, tag_dataofresult}, tag_id + "=" + str, null, null, null, null, null);

        if (cursor.moveToFirst()) {

            codeColIndex = cursor.getColumnIndex(tag_code);
            resultColIndex = cursor.getColumnIndex(tag_result);
            resullofDataColIndex = cursor.getColumnIndex(tag_dataofresult);

            do {
                dbObject.setCode(cursor.getString(codeColIndex));
                dbObject.setResult(cursor.getString(resultColIndex));
                dbObject.setDataofresult(cursor.getString(resullofDataColIndex));
            } while (cursor.moveToNext());
        } else {

            dbObject.setIsEmpty(false);
        }
        return dbObject;
    }


}