package com.happy.home.utils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.happy.home.context.ApplicationContextSingleton;

public class PositionRetreiver {
	private static final String TAG = PositionRetreiver.class.getSimpleName();
	
	static double a = 6378137.0;
	static double b = 6356752.314245;
    static double lon0 = 121 * Math.PI / 180;
    static double k0 = 0.9999;
    static int dx = 250000;

	public static String getAddressStringFromLatitudeAndLongitude(double lat, double lng) throws IOException {
		Log.v(TAG, "getAddressStringFromLatitudeAndLongitude()");
	   
		Geocoder geocoder;
		List<Address> addresses;
		String address = "";
		
		geocoder = new Geocoder(ApplicationContextSingleton.getApplicationContext(), Locale.getDefault());
		addresses = geocoder.getFromLocation(lat, lng, 1);

		if (addresses.size() > 0)
			address = addresses.get(0).getAddressLine(0) + ", " + addresses.get(0).getLocality() + ", " + addresses.get(0).getCountryName();
		return address;
	}
	
	public static LatLng getGPSLocationFromAddressString(String add) {
		LatLng markPoint = null;
		
		try {
			add = URLEncoder.encode(add, "UTF-8");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		String url = ApiUrlFormater.getGoogleConvertAddressToLatLngUrl(add);
		JSONObject jsonObj = JSONConnector.getJSONObject(new ConnectForGetJson(), url);
		JSONObject location;
		double longitude;
		double latitude;
		try {
			if (jsonObj.getJSONArray("results").length() > 0) {
				location = jsonObj.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
				longitude = location.getDouble("lng");
				latitude = location.getDouble("lat");
				markPoint = new LatLng(latitude, longitude);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return markPoint;
	}
	
	public static LatLng getGPSLocationFromTWD97(double x, double y) {
		LatLng lonlat = Cal_TWD97_To_lonlat(x, y);
        return lonlat;
	}
	
	private static LatLng Cal_TWD97_To_lonlat(double x, double y)
    {

        double dy = 0;
        double e = Math.pow((1- Math.pow(b,2)/Math.pow(a,2)), 0.5);

        x -= dx;
        y -= dy;

        // Calculate the Meridional Arc
        double M = y/k0;

        // Calculate Footprint Latitude
        double mu = M/(a*(1.0 - Math.pow(e, 2)/4.0 - 3*Math.pow(e, 4)/64.0 - 5*Math.pow(e, 6)/256.0));
        double e1 = (1.0 - Math.pow((1.0 - Math.pow(e, 2)), 0.5)) / (1.0 + Math.pow((1.0 - Math.pow(e, 2)), 0.5));

        double J1 = (3*e1/2 - 27*Math.pow(e1, 3)/32.0);
        double J2 = (21*Math.pow(e1, 2)/16 - 55*Math.pow(e1, 4)/32.0);
        double J3 = (151*Math.pow(e1, 3)/96.0);
        double J4 = (1097*Math.pow(e1, 4)/512.0);

        double fp = mu + J1*Math.sin(2*mu) + J2*Math.sin(4*mu) + J3*Math.sin(6*mu) + J4*Math.sin(8*mu);

        // Calculate Latitude and Longitude

        double e2 = Math.pow((e*a/b), 2);
        double C1 = Math.pow(e2*Math.cos(fp), 2);
        double T1 = Math.pow(Math.tan(fp), 2);
        double R1 = a*(1-Math.pow(e, 2))/Math.pow((1-Math.pow(e, 2)*Math.pow(Math.sin(fp), 2)), (3.0/2.0));
        double N1 = a/Math.pow((1-Math.pow(e, 2)*Math.pow(Math.sin(fp), 2)), 0.5);

        double D = x/(N1*k0);

        // 計算緯度
        double Q1 = N1*Math.tan(fp)/R1;
        double Q2 = (Math.pow(D, 2)/2.0);
        double Q3 = (5 + 3*T1 + 10*C1 - 4*Math.pow(C1, 2) - 9*e2)*Math.pow(D, 4)/24.0;
        double Q4 = (61 + 90*T1 + 298*C1 + 45*Math.pow(T1, 2) - 3*Math.pow(C1, 2) - 252*e2)*Math.pow(D, 6)/720.0;
        double lat = fp - Q1*(Q2 - Q3 + Q4);

        // 計算經度
        double Q5 = D;
        double Q6 = (1 + 2*T1 + C1)*Math.pow(D, 3)/6;
        double Q7 = (5 - 2*C1 + 28*T1 - 3*Math.pow(C1, 2) + 8*e2 + 24*Math.pow(T1, 2))*Math.pow(D, 5)/120.0;
        double lon = lon0 + (Q5 - Q6 + Q7)/Math.cos(fp);

        lat = (lat * 180) / Math.PI; //緯
        lon = (lon * 180) / Math.PI; //經


        LatLng lonlat = new LatLng(lat, lon);
        return lonlat;
    }
	
	public static double distance(double lat1, double lon1, double lat2, double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		return dist;
	}

	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}
	
	private static double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
    }
}
