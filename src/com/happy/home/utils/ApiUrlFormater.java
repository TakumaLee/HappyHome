package com.happy.home.utils;

import android.util.Log;

public class ApiUrlFormater {
	private static String TAG = ApiUrlFormater.class.getSimpleName();
	
	/**
	 * Google map convert address to LatLng
	 * */
	
	public static String getGoogleConvertAddressToLatLngUrl(String address) {
		Log.v(TAG, "getGoogleConvert()");
		String googleConvert = "http://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&sensor=false";
		return googleConvert;
	}

}
