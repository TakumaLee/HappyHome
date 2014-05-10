package com.happy.home.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.util.Log;

public class ConnectForGetJson extends AsyncTask<String, String, String>{
	private static String TAG = ConnectForGetJson.class.getSimpleName();
	
//	GetJSONListener getJSONListener;
//	
//	public ConnectForGetJson(GetJSONListener getJSONListener) {
//		this.getJSONListener = getJSONListener;
//	}
	
	 @Override
	 protected void onPreExecute() {
		 Log.v(TAG, "Connect()");
		 super.onPreExecute();
	 }

	@Override
	protected String doInBackground(String... params) {
		Log.v(TAG, "Connect()");
		return connectGet(params[0]);
	}
	
	public static String connectGet(String url) {
		Log.v(TAG, "Connect()");
		String result = null;
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		HttpResponse response;
		try {
			response = client.execute(get);
			HttpEntity resEntity = response.getEntity();
			result = EntityUtils.toString(resEntity);
			return result;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
//	@Override
//    protected void onPostExecute(String json) {
//		Log.v(TAG, "Connect()");
//        getJSONListener.onRemoteCallComplete(json);
//    }
//
//
//    public interface GetJSONListener {
//        public void onRemoteCallComplete(String jsonFromNet);
//    }

}
