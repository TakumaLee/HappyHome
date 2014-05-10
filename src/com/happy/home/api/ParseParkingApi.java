package com.happy.home.api;

import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;

import com.google.android.gms.maps.model.LatLng;
import com.happy.home.R;
import com.happy.home.dao.DAOFactory;
import com.happy.home.model.Facility;
import com.happy.home.utils.PositionRetreiver;

public class ParseParkingApi
{

	// String uri = "android.resource://" + getPackageName() +
	// "/"+R.raw.filename;

	public static void parseParking(Activity mActivity)
	{

		String message = ParseApi.loadJSONFromAsset(mActivity, "parking_data.json");

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
				try
				{
					DAOFactory.getInstance().getfacilityDAO().createOrUpdate(facility);
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

