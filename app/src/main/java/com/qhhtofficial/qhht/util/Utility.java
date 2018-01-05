package com.qhhtofficial.qhht.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.qhhtofficial.qhht.Constants;
import java.lang.reflect.Type;


/**
 * Created by GeneHsieh on 2017/5/2.
 */

public class Utility {

    public static final String TAG = Utility.class.getSimpleName();

    public static final String UTF8 = "UTF-8";
    public static Context appContext = null;
    public static Application mApplication = null;

    public static Context getAppContext() {
        return appContext;
    }

    public static void setAppContext(Context appContext) {
        Utility.appContext = appContext;
    }

    /**
     * Save data into SharePreference
     *
     * @param ctx
     * @param key
     * @param value
     */
    public static void saveStringValueForKey(Context ctx, String key, String value) {
        SharedPreferences mSettingSp = ctx.getSharedPreferences(Constants.SP_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor PE = mSettingSp.edit();

        PE.putString(key, value);
        PE.commit();
    }

    /**
     * Get data from SharePreference
     *
     * @param ctx
     * @param key
     * @return String value
     */
    public static String getStringValueForKey(Context ctx, String key) {
        SharedPreferences mSettingSp = ctx.getSharedPreferences(Constants.SP_SETTINGS, Context.MODE_PRIVATE);

        return mSettingSp.getString(key, "");
    }

    public static void saveBooleanState(Context ctx, String key, boolean state) {
        SharedPreferences mSettingSp = ctx.getSharedPreferences(Constants.SP_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor PE = mSettingSp.edit();

        PE.putBoolean(key, state);
        PE.commit();
    }

    public static boolean getBooleanState(Context ctx, String key) {
        SharedPreferences mSettingSp = ctx.getSharedPreferences(Constants.SP_SETTINGS, Context.MODE_PRIVATE);

        return mSettingSp.getBoolean(key, false);
    }

    /**
     * 可用於存時間戳記
     *
     * @param ctx
     * @param key
     * @param value
     */
    public static void saveLongState(Context ctx, String key, long value) {
        SharedPreferences mSettingSp = ctx.getSharedPreferences(Constants.SP_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor PE = mSettingSp.edit();

        PE.putLong(key, value);
        PE.commit();
    }

    /**
     * 用於取的時間戳記
     *
     * @param ctx
     * @param key
     * @return
     */
    public static long getLongValueForKey(Context ctx, String key) {
        SharedPreferences mSettingSp = ctx.getSharedPreferences(Constants.SP_SETTINGS, Context.MODE_PRIVATE);

        return mSettingSp.getLong(key, 0);
    }

    public static void putObject(Context context, String key, Object object, Type type) {
        SharedPreferences mSettingSp = context.getSharedPreferences(Constants.SP_SETTINGS, Context.MODE_PRIVATE);
        SharedPreferences.Editor PE = mSettingSp.edit();
        String json = convertGsonToString(object, type);
        PE.putString(key, json);
        PE.commit();
    }

    public static Object getObject(Context context, String key, Type type) {
        SharedPreferences mSettingSp = context.getSharedPreferences(Constants.SP_SETTINGS, Context.MODE_PRIVATE);
        String json = mSettingSp.getString(key, "");
        return convertStringToGsonObj(json, type);
    }

    /**
     * Convert Json object to String
     *
     * @param gsonObj
     * @return JsonString
     */
    public static String convertGsonToString(Object gsonObj) {
        Gson gson = new Gson();
        return gson.toJson(gsonObj);
    }

    public static String convertGsonToString(Object gsonObj, Type type) {
        Gson gson = new Gson();
        String json = gson.toJson(gsonObj, type);
        return json;
    }

    /**
     * Convert String to Json object
     *
     * @param jsonStr
     * @param GsonCls
     * @return
     */
    public static Object convertStringToGsonObj(String jsonStr, Class<?> GsonCls) {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, GsonCls);
    }

    public static Object convertStringToGsonObj(String jsonStr, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, type);
    }


}
