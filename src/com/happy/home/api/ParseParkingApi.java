package com.happy.home.api;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;

import com.google.android.gms.maps.model.LatLng;
import com.happy.home.dao.DAOFactory;
import com.happy.home.model.Facility;
import com.happy.home.utils.PositionRetreiver;

import android.app.Activity;

public class ParseParkingApi
{

	// String uri = "android.resource://" + getPackageName() +
	// "/"+R.raw.filename;

	static void parseParking(Activity mActivity, String file_url)
	{

		String message = loadJSONFromAsset(mActivity, file_url);

		JSONArray jArray;
		try
		{
			jArray = new JSONArray(message);
			for (int i = 0; i < jArray.length(); i++)
			{
				int type = 1;

				String title = "";
				try
				{
					title = jArray.getJSONObject(i).getString("name");
				} catch (Exception e)
				{
					// TODO: handle exception
				}

				LatLng xy = null;
				try
				{
					double tw97x = jArray.getJSONObject(i).getDouble("tw97x");
					double tw97y = jArray.getJSONObject(i).getDouble("tw97y");
					xy = PositionRetreiver.getGPSLocationFromTWD97(tw97x, tw97y);
				} catch (Exception e)
				{
					// TODO: handle exception
				}
				
				Facility facility = new Facility();
				facility.setX_long(xy.latitude);
				facility.setY_long(xy.longitude);
				facility.setType(type);
				facility.setTitle(title);	

			}

		} catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String loadJSONFromAsset(Activity mActivity, String file_url)
	{
		String json = null;
		try
		{

			InputStream is = mActivity.getAssets().open("parking_data.json");

			int size = is.available();

			byte[] buffer = new byte[size];

			is.read(buffer);

			is.close();

			json = new String(buffer, "UTF-8");

		} catch (IOException ex)
		{
			ex.printStackTrace();
			return null;
		}
		return json;

	}

}
