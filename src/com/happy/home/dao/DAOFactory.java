package com.happy.home.dao;

import java.sql.SQLException;

import com.happy.home.database.DatabaseHelper;
import com.happy.home.database.DatabaseUtil;
import com.happy.home.model.Facility;

import android.content.Context;

public class DAOFactory {
	
	private static final String TAG = DAOFactory.class.getSimpleName();
    private static DAOFactory instance = null;

    protected Context 						context;
    protected DatabaseHelper 				dbHelper;
    protected FacilityDAOInterface			facilityDAO;

    /* Constructors */
    private DAOFactory(Context context) {
        this.context = context;
        this.dbHelper = new DatabaseHelper(context);
    }
    
    public static synchronized DAOFactory initSingleton(Context context) {
        if (instance == null && context != null) {
            Context appContext = context.getApplicationContext();
            instance = new DAOFactory(appContext);
        }
        return instance;
    }

    public static synchronized DAOFactory getInstance() {
        return instance;
    }

    /* Transaction-oriented methods */
    public synchronized void beginTransaction() {
        dbHelper.beginTransaction();
    }

    public synchronized void commitTransaction() {
        dbHelper.commit();
    }

    public synchronized void rollbackTransaction() {
        dbHelper.rollBack();
    }

    /* End of Transaction-oriented methods */
    
    public synchronized FacilityDAOInterface getfacilityDAO() {
        if (facilityDAO == null) {
            try {
            	facilityDAO = (FacilityDAOInterface) dbHelper.getDao(Facility.class);
            } catch (SQLException e) {
            	DatabaseUtil.throwAndroidSQLException(TAG, e);
            }
        }
        return facilityDAO;
    }
    
    public synchronized void EraseAllData() {
    	dbHelper.eraseAllData(dbHelper.getReadableDatabase(), dbHelper.getConnectionSource());
    }
}
