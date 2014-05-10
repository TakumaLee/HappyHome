package com.happy.home.dao;

import java.util.List;

import com.happy.home.model.Facility;

public interface FacilityDAOInterface extends BaseDAOInterface<Facility, Integer> {
	
	public List<Facility> fetch500MeterAround(int type, double x, double y);

}
