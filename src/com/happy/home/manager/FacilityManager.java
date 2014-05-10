package com.happy.home.manager;

import java.util.List;

import com.happy.home.dao.DAOFactory;
import com.happy.home.model.Facility;

import android.content.Context;

public class FacilityManager {
	
	protected Context context;
	private static FacilityManager instance;
	
	private FacilityManager(Context context) {
		this.context = context;
	}
	
	public static synchronized FacilityManager initSingleton(Context context) {
		if (instance == null && context != null) {
			Context appContext = context.getApplicationContext();
			instance = new FacilityManager(appContext);
		}
		return instance;
	}
	
	public static FacilityManager getInstance() {
		return instance;
	}
	
	public List<Facility> fetchAroundFacilities(int type, double x, double y) {
		List<Facility> facilities = null;
		facilities = DAOFactory.getInstance().getfacilityDAO().fetch500MeterAround(type, x, y);
		return facilities;
	}

}
