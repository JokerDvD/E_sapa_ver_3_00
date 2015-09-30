package com.example.admin.e_sapa_ver_3_00.RecourseFile.dbObject;

/**
 * Created by Optimus on 16.09.2015.
 */
public class dbObject {
    private String history;
    private String code;
    private String result;
    private String dataodresult;
    private int id;
    private double lat;
    private double lon;
    private boolean isEmpty=false;

    public dbObject() {
    }

    public dbObject(String history) {

    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDataofresult() {
        return dataodresult;
    }

    public void setDataofresult(String dataodresult) {
        this.dataodresult = dataodresult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }
}
