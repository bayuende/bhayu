package com.bhayu.app.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

/**
 * Created by agusn on 9/19/16.
 */
public class PreferenceHelper {

	public static final String KEY_FCM_ID = ".fcm_id";
	public static final String KEY_LANG = ".lang";
	public static final String IS_ON = "1";
	public static final String IS_OFF = "0";
	public static final String WIFI_ON = "wifi_on";
	public static final String BROADBAND_ON = "broadband_on";

	private SharedPreferences sharedPreferences;

	public PreferenceHelper(Context context) {
		sharedPreferences = getSharedPreference(context);
	}

	public SharedPreferences getSharedPreference(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context);
	}

	public void putString(String key, String isi) {
		sharedPreferences.edit().putString(key, isi).apply();
	}

	public void putInt(String key, int num) {
		sharedPreferences.edit().putInt(key, num).apply();
	}

	public <T> void putList(String key, List<T> list){
		Gson gson = new Gson();
		String json = gson.toJson(list);
		putString(key, json);
	}

	public <T> void putObj(String key, T obj) {
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		putString(key, json);
	}

	public String getString(String key) {
		return sharedPreferences.getString(key, null);
	}

	public int getInt(String key) {
		return sharedPreferences.getInt(key, 0);
	}

	public <T> List<T> getList(String key, final Class<T[]> cls){
		Gson gson = new Gson();
		String json = getString(key);
		T[] list = gson.fromJson(json, cls);
		return list != null ? Arrays.asList(list) : null;
	}

	public <T> T getObj(String key, final Class<T> cls) {
		Gson gson = new Gson();
		String json = getString(key);
		T obj = gson.fromJson(json, cls);
		return obj != null ? obj : null;
	}

	public void clear(String key) {
		sharedPreferences.edit().remove(key).apply();
	}

	public void clear() {
		sharedPreferences.edit().clear().apply();
	}

}
