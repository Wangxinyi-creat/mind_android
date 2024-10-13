package com.pj.mind.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.pj.mind.app.AppApplication;

import java.util.ArrayList;
import java.util.List;


public class PreferencesUtils {
    // 应用程序级别，退出账号不清空
    private static final String APP = "app";
    // 用户级别，退出账号清空
    private static final String TOKEN = "token";
    // 初次标示
    private static final String IS_FIRST = "isFirst";
    //主页状态
    private static final String STATUS = "Status";


    private static PreferencesUtils instance;

    public static synchronized PreferencesUtils getInstance() {
        if (instance == null) {
            synchronized (PreferencesUtils.class) {
                if (instance == null) {
                    instance = new PreferencesUtils();
                }
            }
        }
        return instance;
    }

    public String get(String key, String defValue) {
        SharedPreferences preferences = AppApplication.getInstance().getSharedPreferences(APP, Context.MODE_PRIVATE);
        return preferences.getString(key, defValue);
    }

    public boolean get(String key, boolean defValue) {
        SharedPreferences mPreference = AppApplication.getInstance().getSharedPreferences(APP, Context.MODE_PRIVATE);
        return mPreference.getBoolean(key, defValue);
    }

    public int get(String key, int defValue) {
        SharedPreferences mPreference = AppApplication.getInstance().getSharedPreferences(APP, Context.MODE_PRIVATE);
        return mPreference.getInt(key, defValue);
    }

    public float get(String key, float defValue) {
        SharedPreferences mPreference = AppApplication.getInstance().getSharedPreferences(APP, Context.MODE_PRIVATE);
        return mPreference.getFloat(key, defValue);
    }

    public long get(String key, long defValue) {
        SharedPreferences mPreference = AppApplication.getInstance().getSharedPreferences(APP, Context.MODE_PRIVATE);
        return mPreference.getLong(key, defValue);
    }


    public void put(String key, String value) {
        putString(key, value);
    }

    public void put(String key, int value) {
        putInt(key, value);
    }

    public void put(String key, float value) {
        putFloat(key, value);
    }

    public void put(String key, boolean value) {
        putBoolean(key, value);
    }

    public void put(String key, long value) {
        putLong(key, value);
    }

    public void putFloat(String key, float value) {
        if (AppApplication.getInstance() != null) {
            SharedPreferences preferences = AppApplication.getInstance().getSharedPreferences(APP, Context.MODE_PRIVATE);
            Editor editor = preferences.edit();
            editor.putFloat(key, value);
            editor.apply();
        }
    }

    public void putString(String key, String value) {
        if (AppApplication.getInstance() != null) {
            SharedPreferences preferences = AppApplication.getInstance().getSharedPreferences(APP, Context.MODE_PRIVATE);
            Editor editor = preferences.edit();
            editor.putString(key, value);
            editor.apply();
        }
    }

    public void putLong(String key, long value) {
        if (AppApplication.getInstance() != null) {
            SharedPreferences preferences = AppApplication.getInstance().getSharedPreferences(APP, Context.MODE_PRIVATE);
            Editor editor = preferences.edit();
            editor.putLong(key, value);
            editor.apply();
        }
    }

    public void putBoolean(String key, boolean value) {
        if (AppApplication.getInstance() != null) {
            SharedPreferences preferences = AppApplication.getInstance().getSharedPreferences(APP, Context.MODE_PRIVATE);
            Editor editor = preferences.edit();
            editor.putBoolean(key, value);
            editor.apply();
        }
    }

    public void putInt(String key, int value) {
        if (AppApplication.getInstance() != null) {
            SharedPreferences preferences = AppApplication.getInstance().getSharedPreferences(APP, Context.MODE_PRIVATE);
            Editor editor = preferences.edit();
            editor.putInt(key, value);
            editor.apply();
        }
    }

    public void saveValueByIds(Context context, String key, int value) {
        SharedPreferences preferences = context.getSharedPreferences(APP, Context.MODE_PRIVATE);
        String str = preferences.getString(key, "");
        List<Integer> list = new ArrayList<>();
        if (!NullUtil.isStringEmpty(str)){
            for (String item : str.split(",")) {
                if (Integer.parseInt(item) != value) {
                    list.add(Integer.parseInt(item));
                }
            }
        }
        // 将新搜索项插入到第一位
        list.add(0, value);

        StringBuilder updatedHistory = new StringBuilder();
        for (int item : list) {
            updatedHistory.append(item).append(",");
        }
        preferences.edit().putString(key, updatedHistory.toString()).apply();
    }

    public List<Integer> getValueWithIds(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(APP, Context.MODE_PRIVATE);
        String str = preferences.getString(key, "");
        String[] strArray = str.split(",");
        List<Integer> strList = new ArrayList<>();
        if (NullUtil.isStringEmpty(str)){
            return strList;
        }
        for (String item : strArray) {
            if (Integer.parseInt(item) != 0) {
                strList.add(Integer.parseInt(item));
            }
        }
        return strList;
    }

    public boolean isInValues(Context context, String key, int value) {
        SharedPreferences preferences = context.getSharedPreferences(APP, Context.MODE_PRIVATE);
        String str = preferences.getString(key, "");
        if (NullUtil.isStringEmpty(str)){
            return false;
        }
        for (String item : str.split(",")) {
            if (Integer.parseInt(item) == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否第一次启动
     *
     * @param context context
     */
    public void saveNotFirstOpen(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(IS_FIRST, Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        editor.putBoolean("isFirst", false);
        editor.apply();
    }

    public boolean isFirstOpen(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(IS_FIRST, Context.MODE_PRIVATE);
        return preferences.getBoolean("isFirst", true);
    }

    public void saveToken(Context context, String token) {
        SharedPreferences preferences = context.getSharedPreferences(TOKEN, Context.MODE_PRIVATE);
        preferences.edit().putString("token", token).apply();
    }

    public String getToken(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(TOKEN, Context.MODE_PRIVATE);
        return preferences.getString("token", "");
    }

    public void savePhone(Context context, String phone) {
        SharedPreferences preferences = context.getSharedPreferences(TOKEN, Context.MODE_PRIVATE);
        preferences.edit().putString("phone", phone).apply();
    }

    public String getPhone(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(TOKEN, Context.MODE_PRIVATE);
        return preferences.getString("phone", "");
    }

    public void saveUserId(Context context, String userId) {
        SharedPreferences preferences = context.getSharedPreferences(TOKEN, Context.MODE_PRIVATE);
        preferences.edit().putString("userId", userId).apply();
    }

    public String getUserId(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(TOKEN, Context.MODE_PRIVATE);
        return preferences.getString("userId", "");
    }

    public String getSUserId(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(TOKEN, Context.MODE_PRIVATE);
        return preferences.getString("sUserId", "");
    }

    public void saveSUserId(Context context, String sUserId) {
        SharedPreferences preferences = context.getSharedPreferences(TOKEN, Context.MODE_PRIVATE);
        preferences.edit().putString("sUserId", sUserId).apply();
    }

    public void saveTestCust(Context context, String testCust) {
        SharedPreferences preferences = context.getSharedPreferences(TOKEN, Context.MODE_PRIVATE);
        preferences.edit().putString("testCust", testCust).apply();
    }

    public String getTestCust(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(TOKEN, Context.MODE_PRIVATE);
        return preferences.getString("testCust", "");
    }

    public void saveHomeStatus(Context context, int dueBakery, int untrueBathrobe) {
        SharedPreferences preferences = context.getSharedPreferences(STATUS, Context.MODE_PRIVATE);
        preferences.edit().putInt("oStatus", dueBakery).apply();
        preferences.edit().putInt("lStatus", untrueBathrobe).apply();
    }


    public void saveMulStatus(Context context, boolean mul) {
        SharedPreferences preferences = context.getSharedPreferences(STATUS, Context.MODE_PRIVATE);
        preferences.edit().putBoolean("mul", mul).apply();
    }

    public boolean getMulStatus(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(STATUS, Context.MODE_PRIVATE);
        return preferences.getBoolean("mul", false);
    }

    /***
     * 清除参数
     */
    public void clear(Context context) {
        //获得SharedPreferences对象
        SharedPreferences preferences = context.getSharedPreferences(TOKEN, Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        editor.clear().apply();
    }

    //清除本地保存的
    public static void remove(String sp_name) {
        if (sp_name != null && AppApplication.getInstance() != null) {
            SharedPreferences preferences = AppApplication.getInstance().getSharedPreferences(APP, Context.MODE_PRIVATE);
            Editor editor = preferences.edit();
            editor.remove(sp_name);
            editor.apply();
        }
    }
}
