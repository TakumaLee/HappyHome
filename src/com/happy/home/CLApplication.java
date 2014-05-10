package com.happy.home;

import com.happy.home.config.AppConfig;
import com.happy.home.context.ApplicationContextSingleton;
import com.happy.home.dao.DAOFactory;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.ActivityManager.MemoryInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.hardware.Camera.Face;
import android.os.Handler;
import android.util.Log;

public class CLApplication extends Application {
	private static String TAG = CLApplication.class.getSimpleName();
	DAOFactory daoFactory;
    
    public ApplicationContextSingleton 	appContext;
    private static Activity 			activity;
    
    public void onCreate() {
        appContext = ApplicationContextSingleton.initialize(getApplicationContext());
        AppConfig.initConfig(getApplicationContext(), "0");
        daoFactory = DAOFactory.initSingleton(getApplicationContext());
    }
    
    public static void setCurrentActivity(Activity currentActivity) {
        activity = currentActivity;
    }

    public static Activity currentActivity() {
        return activity;
    }

    @Override
    public void onLowMemory() {
        Log.e(TAG, "On Low Memory!!!!");
        MemoryInfo mi = new MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        int usableMemory = activityManager.getMemoryClass();
        Log.v(TAG, String.valueOf(usableMemory));
        long availableMegs = mi.availMem / 1048576L;
        long thresholdMegs = mi.threshold / 1048576L;
        Log.e(TAG, "availableMegs:"+availableMegs+" thresholdMegs:"+thresholdMegs);
//        BitmapAjaxCallback.clearCache();
        super.onLowMemory();
    }

}
