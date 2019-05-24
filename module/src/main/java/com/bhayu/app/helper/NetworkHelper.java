package com.bhayu.app.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by agusn on 12/20/16.
 */
public class NetworkHelper {

	public static boolean isWifiOn(Context context) {
		PreferenceHelper preferenceHelper = new PreferenceHelper(context);
		String isWifiOn = preferenceHelper.getString(PreferenceHelper.WIFI_ON);
		return  (isWifiOn != null && isWifiOn.equals(PreferenceHelper.IS_ON));
	}

	public static boolean isBroadbandOn(Context context) {
		PreferenceHelper preferenceHelper = new PreferenceHelper(context);
		String isBroadbandOn = preferenceHelper.getString(PreferenceHelper.BROADBAND_ON);
		return  (isBroadbandOn != null && isBroadbandOn.equals(PreferenceHelper.IS_ON));
	}

	public static boolean isConnected(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	public static boolean isConnectedWifi(Context context) {
		ConnectivityManager connectivityManager
				= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return (activeNetworkInfo != null && activeNetworkInfo.isConnected()
				&& activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI);
	}

	public static boolean isConnectedBroadband(Context context) {
		ConnectivityManager connectivityManager
				= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return (activeNetworkInfo != null && activeNetworkInfo.isConnected()
				&& activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE);
	}
}