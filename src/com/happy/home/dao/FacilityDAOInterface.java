package com.happy.home.dao;

import com.happy.home.model.Facility;

public interface FacilityDAOInterface extends BaseDAOInterface<Facility, Integer> {
	
	public void fetch500MeterAround();

}
