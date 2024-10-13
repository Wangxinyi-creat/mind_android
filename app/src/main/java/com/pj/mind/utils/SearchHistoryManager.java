package com.pj.mind.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class SearchHistoryManager {

    private static final String PREF_NAME = "SearchHistory";
    private static final String KEY_SEARCH_HISTORY = "search_goods";
    private static final int MAX_HISTORY_SIZE = 5;
    private static final String DELIMITER = ","; // 分隔符


    private final SharedPreferences preferences;

    private static SearchHistoryManager instance;
    private OnSearchResultChangeListener listener;

    private SearchHistoryManager(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized SearchHistoryManager getInstance(Context context) {
        if (instance == null) {
            instance = new SearchHistoryManager(context.getApplicationContext());
        }
        return instance;
    }

    public void setOnSearchResultChangeListener(OnSearchResultChangeListener listener) {
        this.listener = listener;
    }

    public void addSearchHistory(String query) {
        String history = preferences.getString(KEY_SEARCH_HISTORY, "");

        // 如果历史记录中已经包含了该搜索项，则先移除再添加
        List<String> historyList = new ArrayList<>();
        for (String item : history.split(DELIMITER)) {
            if (!item.equals(query)) {
                historyList.add(item);
            }
        }

        // 将新搜索项插入到第一位
        historyList.add(0, query);

        // 如果历史记录超过了最大尺寸，则移除最后一项
        while (historyList.size() > MAX_HISTORY_SIZE) {
            historyList.remove(historyList.size() - 1);
        }

        // 保存更新后的搜索历史
        StringBuilder updatedHistory = new StringBuilder();
        for (String item : historyList) {
            updatedHistory.append(item).append(DELIMITER);
        }
        preferences.edit().putString(KEY_SEARCH_HISTORY, updatedHistory.toString()).apply();
        if (listener != null) {
            listener.onChange(getSearchHistory());
        }

    }

    public List<String> getSearchHistory() {
        String history = preferences.getString(KEY_SEARCH_HISTORY, "");
        String[] historyArray = history.split(DELIMITER);
        List<String> historyList = new ArrayList<>();
        for (String item : historyArray) {
            if (!item.isEmpty()) {
                historyList.add(item);
            }
        }
        return historyList;
    }

    public void clearSearchHistory() {
        preferences.edit().remove(KEY_SEARCH_HISTORY).apply();
    }

    public interface OnSearchResultChangeListener {
        void onChange(List<String> list);
    }
}
