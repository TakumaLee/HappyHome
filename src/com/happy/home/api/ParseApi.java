package com.happy.home.api;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;

public class ParseApi
{
	// type 1: parking, 2: Recycle, 3: Hospital
	
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
