package com.example.admin.e_sapa_ver_3_00.RecourseFile.Preferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by admin on 09.09.2015.
 */
public class preferenceSave_Load {
    private static final String sharedFName = "sharedPreferenceFile";
    private static SharedPreferences sharedPref;

    public preferenceSave_Load(Activity activity) {
        sharedPref = activity.getSharedPreferences(sharedFName, Context.MODE_PRIVATE);
    }

    public void saveThemeTag(String saveTag, int value) {
        SharedPreferences.Editor ed = sharedPref.edit();
        ed.putString(saveTag, String.valueOf(value));
        ed.commit();
    }

    public void saveLangTag(String savetag, String langValue) {
        SharedPreferences.Editor edLang = sharedPref.edit();
        edLang.putString(savetag, langValue);
        edLang.commit();
    }

    public String loadLangTag(String loadTag, String defValue) {
        return sharedPref.getString(loadTag, defValue);
    }

    public int loadThemeTag(String loadTag, int defValue) {
        return Integer.parseInt(sharedPref.getString(loadTag, String.valueOf(defValue)));
    }

    public void savePageNum(String pageN_tag, int i) {
        SharedPreferences.Editor edpageN=sharedPref.edit();
        edpageN.putInt(pageN_tag,i);
        edpageN.commit();
    }

    public int loadPageN(String pageN_tag, int i) {
        return sharedPref.getInt(pageN_tag,i);
    }
}
