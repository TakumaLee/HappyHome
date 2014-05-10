<<<<<<< HEAD
package com.happy.home.dao;

import java.util.List;

import com.happy.home.model.Facility;

public interface FacilityDAOInterface extends BaseDAOInterface<Facility, Integer> {
	
	public List<Facility> fetch500MeterAround(int type, double x, double y);

}
=======
package com.happy.home.dao;

import com.happy.home.model.Facility;

public interface FacilityDAOInterface extends BaseDAOInterface<Facility, Integer> {
	
	public void fetch500MeterAround();

}
>>>>>>> ca35be773accb03e03373f4ed158421264ef8bc4
