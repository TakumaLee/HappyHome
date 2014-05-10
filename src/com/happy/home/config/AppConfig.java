package com.happy.home.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class AppConfig {

    private static String TAG = AppConfig.class.getSimpleName();

    private static SharedPreferences sharedPrefs = null;
    private static boolean isInDebugMode = false;
    private static String Revision = null;
    private static String appVersion = "";
    private static String appName = "";
    private static int appVersionCode = 0;
    

    private AppConfig() {
    }

    public static void initConfig(Context context, String currentRevision) {
        if (sharedPrefs == null) {
            sharedPrefs = context.getSharedPreferences("ConfigChocolabsApp", Context.MODE_PRIVATE);
        }
        Revision = currentRevision;
        try {
            PackageInfo infos = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            appVersion = infos.versionName;
            appVersionCode = infos.versionCode;
            ApplicationInfo appinfo = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), 0);
            if ((appinfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0) {
                isInDebugMode = true;
            }

            appName = context.getPackageName();
        } catch (NameNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    private static void checkInitConfig() {
        if (sharedPrefs == null) {
            throw new RuntimeException(
                    "Configuration have to be initialize with AppConfig.initConfig(Context context)");
        }
    }

    public static Menu createConfigMenu(Menu menu) {
        checkInitConfig();
        if (isInDebugMode) {
//            menu.add(1, INTEGRATION, 1, "integration");
//            menu.add(1, PREPROD, 2, "preprod");
//            menu.add(1, PROD, 3, "prod");
//            menu.add(1, INFOS, 4, "infos");
        }
        return menu;
    }

    public static void configMenuItemSelected(MenuItem item, Context context) {
//        switch (item.getItemId()) {
//        case INTEGRATION:
//            setIntegration();
//            break;
//        case PREPROD:
//            setPreProduction();
//            break;
//        case PROD:
//            setProduction();
//            break;
//        default:
//            showInfoToast(context);
//            break;
//        }
    }

    private static void setProduction() {
        Editor editPrefs = sharedPrefs.edit();
        editPrefs.commit();
        System.exit(0);
    }

    private static void setPreProduction() {
        Editor editPrefs = sharedPrefs.edit();
//        editPrefs.putString("IWANTAPP_URL", IWANTAPP_URL_PREPROD);
//        editPrefs.putString("IWANTAPP_STATIC_FILES_URL", IWANTAPP_URL_PREPROD);
//        editPrefs.putString("IWANTAPP_SUBDOMAIN", IWANTAPP_SUBDOMAIN_PREPROD);
//        editPrefs.putString("IWANTAPP_SITE_ID", IWANTAPP_SITE_ID_PREPROD);
//        editPrefs.putString("IWANTAPP_SUB_SITE", IWANTAPP_SUB_SITE_PREPROD);
        editPrefs.commit();
        System.exit(0);
    }

    private static void setIntegration() {
        Editor editPrefs = sharedPrefs.edit();
//        editPrefs.putString("IWANTAPP_URL", IWANTAPP_URL_INTEGRATION);
//        editPrefs.putString("IWANTAPP_STATIC_FILES_URL", IWANTAPP_URL_INTEGRATION);
//        editPrefs.putString("IWANTAPP_SUBDOMAIN", IWANTAPP_SUBDOMAIN_INTEGRATION);
//        editPrefs.putString("IWANTAPP_SITE_ID", IWANTAPP_SITE_ID_INTEGRATION);
//        editPrefs.putString("IWANTAPP_SUB_SITE", IWANTAPP_SUB_SITE_INTEGRATION);
        editPrefs.commit();
        System.exit(0);
    }

    private static void showInfoToast(Context context) {
        StringBuilder config = new StringBuilder(5);

        config.append("\nAPP VERSION : ");
        config.append(appVersion);
        config.append("\nSVN REVISION : ");
        config.append(Revision);
        Toast.makeText(context, "This is current configuration : \n\n" + config, Toast.LENGTH_LONG)
                .show();
    }

//    public static String getJBOSSURL() {
//        checkInitConfig();
//        if (isInDebugMode) {
//            return sharedPrefs.getString("IWANTAPP_JBOSS_URL", IWANTAPP_JBOSS_URL_PROD);
//        }
//        return IWANTAPP_JBOSS_URL_PROD;
//    }
    
//    public static String getSFSURL() {
//        checkInitConfig();
//        if (isInDebugMode) {
//            return sharedPrefs.getString("IWANTAPP_SFS_URL", IWANTAPP_SFS_URL_PROD);
//        }
//        return IWANTAPP_SFS_URL_PROD;
//    }
    
//    public static String getStaticFilesURL() {
//        checkInitConfig();
//        if (isInDebugMode) {
//            return sharedPrefs.getString("IWANTAPP_STATIC_FILES_URL_FOR_USER", IWANTAPP_STATIC_FILES_URL_PROD);
//        }
//        return IWANTAPP_STATIC_FILES_URL_PROD;
//    }

//    public static String getStaticCurrentEventURL(){
//    	checkInitConfig();
//    	if(isInDebugMode){
//    		return sharedPrefs.getString("IWANTAPP_CURRENT_EVENT_URL", IWANTAPP_CURRENT_EVENT_URL_PROD);
//    	}
//    	return IWANTAPP_CURRENT_EVENT_URL_PROD;
//    }

    public static boolean isInDebugMode() {
        return isInDebugMode;
    }
    
    public static int getAppVersionCode() {
    	return appVersionCode;
    }

    public static String getAppVersion() {
        return appVersion;
    }

    public static String getAppName() {
        return appName;
    }
    
}

