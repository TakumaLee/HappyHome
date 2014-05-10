package com.happy.home.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.happy.home.context.ApplicationContextSingleton;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableInfo;
import com.j256.ormlite.table.TableUtils;

/**
 * Database helper which creates and upgrades the database and provides the DAOs for the app.
 * 
 */
public class DatabaseHelper extends DatabaseTransactionOpenHelper {

	private static final String DATABASE_NAME = "dramas_v203.sql";
	private static final String DATABASE_PATH = ApplicationContextSingleton.getApplicationContext().getFilesDir().getAbsolutePath() +"/";
	private static final int DATABASE_VERSION = 4;
	
	private Context context;
	private SharedPreferences sharedPreferences;
	private SharedPreferences.Editor editor;
	private int		DB_Ver;
	private boolean version2 = false;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_PATH + DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
		getDBVersionFromSharedPreferences();
		if (!checkdatabase()) {
			// If database did not exist, try copying existing database from assets folder.
			copyDatabase();
	        
		}
	}

	@Override
	public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
//		try {
//			if (!checkdatabase()) {
////				copyDatabase();
//			}
//			TableUtils.createTable(connectionSource, HomeDrama.class);
//		} catch (SQLException e) {
//			Log.e(DatabaseHelper.class.getName(), "Unable to create datbases", e);
//		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource, int oldVer, int newVer) {
//		try {
////			TableUtils.dropTable(connectionSource, Drama.class, true);	
//			TableUtils.dropTable(connectionSource, HomeDrama.class, true);
//
//		} catch (SQLException e) {
//			Log.e(DatabaseHelper.class.getName(), "Unable to upgrade database from version " + oldVer + " to new "
//					+ newVer, e);
//		}
	}
	
	public void eraseAllData(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
//		try {
//			TableUtils.clearTable(connectionSource, Drama.class);
//		} catch (SQLException e) {
//			Log.e(DatabaseHelper.class.getName(), "Unable to drop tables", e);
//		}
	}
	
	private void copyDatabase() {
		try {
			AssetManager assetManager = context.getAssets();
	        InputStream in;
			in = assetManager.open(DATABASE_NAME);
			OutputStream out = new FileOutputStream(DATABASE_PATH + DATABASE_NAME);
	        byte[] buffer = new byte[1024];
	        int read;
	        while((read = in.read(buffer)) != -1){
	            out.write(buffer, 0, read);
	        }
	        in.close();
	        out.flush();
	        out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean checkdatabase() {
        boolean checkdb = false;

        String myPath = DATABASE_PATH + DATABASE_NAME;
        File dbfile = new File(myPath);
        checkdb = dbfile.exists();

        Log.i(DatabaseHelper.class.getName(), "DB Exist : " + checkdb);

        return checkdb;
    }
	
	private void getDBVersionFromSharedPreferences() {
		sharedPreferences = context.getSharedPreferences("DATABASE", 0);
		DB_Ver = sharedPreferences.getInt("DATABASE_VERSION", 0);
		version2 = sharedPreferences.getBoolean("DATABASE_VERSION2", false);
	}
	
	private void saveSharedPreferences() {
		editor = sharedPreferences.edit();
		editor.putInt("DATABASE_VERSION", DATABASE_VERSION);
		editor.commit();
	}
	
	private void saveUpdateSharedPreferences() {
		editor = sharedPreferences.edit();
		editor.putBoolean("DATABASE_VERSION2", true);
		editor.commit();
	}
}

