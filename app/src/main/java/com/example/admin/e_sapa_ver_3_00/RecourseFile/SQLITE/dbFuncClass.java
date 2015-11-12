package com.example.admin.e_sapa_ver_3_00.RecourseFile.SQLITE;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.admin.e_sapa_ver_3_00.RecourseFile.dbObject.dbObject;
import com.google.android.gms.maps.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Optimus on 13.09.2015.
 */
public class dbFuncClass {
    int idColIndex, codeColIndex, resultColIndex, resullofDataColIndex, latitudeColIndex, longitudeColIndex, dateTimeColIndex;
    private String LOG_SQLITE = "LOG_SQLITE", tableName = "E_sapa2", tag_id = "id", tag_result = "result", tag_code = "code", tag_dataofresult = "dataofresult", tag_latitude = "latitude", tag_longitude = "longitude", tag_dateTime = "date";
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private ContentValues cv;
    private Cursor cursor;
    private List<String> crudList;
    private List<String> description;
    private List<String> bool;
    private List<String> dateTime;
    private List<String> data_select_item;
    private LatLng latlng;
    private dbObject dbObject;


    public dbFuncClass(Activity activity) {

        Log.d(LOG_SQLITE, "dbFuncClass Contr");
        dbHelper = new DBHelper(activity);
        db = dbHelper.getWritableDatabase();
        cv = new ContentValues();
        crudList = new ArrayList<>();
        description = new ArrayList<>();
        bool = new ArrayList<>();
        data_select_item = new ArrayList<>();
        dateTime=new ArrayList<>();

        dbObject = new dbObject();
    }

    public List<String> get_all_barcode() {
        cursor = db.query(tableName, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            codeColIndex = cursor.getColumnIndex(tag_code);
            do {
                crudList.add(" " + cursor.getString(codeColIndex) + " ");
            } while (cursor.moveToNext());
        } else {
            crudList.add("No data to show");
        }
        return crudList;
    }

    public List<String> get_all_result_boolean() {
        cursor = db.query(tableName, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            resultColIndex = cursor.getColumnIndex(tag_result);

            do {
                bool.add(cursor.getString(resultColIndex));
            } while (cursor.moveToNext());
        } else {
            bool.add("3");
        }
        return bool;
    }

    public List<String> get_all_description() {
        Log.d(LOG_SQLITE, "dbFuncClass get_all_description");
        cursor = db.query(tableName, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            resullofDataColIndex = cursor.getColumnIndex(tag_dataofresult);
            do {
                description.add(" " + cursor.getString(resullofDataColIndex) + " ");
            } while (cursor.moveToNext());
        } else {
            description.add("3");
        }
        return description;
    }

    public List<String> get_all_dateTime() {
        Log.d(LOG_SQLITE, "getDateTime");
        cursor = db.query(tableName, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            dateTimeColIndex = cursor.getColumnIndex(tag_dateTime);
            do {
                dateTime.add(" " + cursor.getString(dateTimeColIndex));
            } while (cursor.moveToNext());
        } else {
                dateTime.add("No data to show");
        }
        return dateTime;

    }


    public LatLng get_location(int id) {

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


    public void write_data(String code, boolean result, String dataofresult, double latitude, double longitude) {

        Log.d(LOG_SQLITE, "Write SQLITE DB data");

        cv.put(tag_code, code);
        cv.put(tag_result, result);
        cv.put(tag_dataofresult, dataofresult);
        cv.put(tag_latitude, latitude);
        cv.put(tag_longitude, longitude);
        cv.put(tag_dateTime, getDateTime());
        db.insert(tableName, null, cv);
    }

    public dbObject get_db_object(int id) {

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

    public List<String> get_item_selected(int position) {
        data_select_item.clear();
        String str = new String(String.valueOf(position));
        cursor = db.query(tableName, new String[]{tag_code, tag_dataofresult}, tag_id + "=" + str, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            /*codeColIndex = cursor.getColumnIndex(tag_code);*/
            resullofDataColIndex = cursor.getColumnIndex(tag_dataofresult);

            do {
                data_select_item.add(/*cursor.getString(codeColIndex) + " " +*/ cursor.getString(resullofDataColIndex));
            } while (cursor.moveToNext());
        } else {
            data_select_item.add("No Selected Point");
        }

        return data_select_item;
    }


    public String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}