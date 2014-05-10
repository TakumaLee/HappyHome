package com.happy.home.api;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;

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

				double x_lat = 0;
				try
				{
					double tw97x = jArray.getJSONObject(i).getDouble("tw97x");
					x_lat = ParseTW97(tw97x);
				} catch (Exception e)
				{
					// TODO: handle exception
				}

			}

		} catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static double ParseTW97(double tw97x)
	{
		// TODO Auto-generated method stub
		return 0;
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
