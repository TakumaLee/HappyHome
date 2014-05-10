package com.happy.home.dao;

import java.sql.SQLException;

import com.happy.home.model.Facility;

public class FacilityDAO extends BaseDAO<Facility, Integer> implements FacilityDAOInterface {

	public FacilityDAO(Class<Facility> dataClass) throws SQLException {
		super(dataClass);
	}
	
	public void fetch500MeterAround() {
		
	}

}
