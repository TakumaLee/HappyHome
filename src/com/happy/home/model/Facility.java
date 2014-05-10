package com.happy.home.model;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Facility", daoClass = com.happy.home.dao.FacilityDAO.class)
public class Facility implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(generatedId = true) private int id;
	@DatabaseField private int		type;
	@DatabaseField private String	title;
	@DatabaseField private double	x_long;
	@DatabaseField private double	y_long;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getX_long() {
		return x_long;
	}
	public void setX_long(double x_long) {
		this.x_long = x_long;
	}
	public double getY_long() {
		return y_long;
	}
	public void setY_long(double y_long) {
		this.y_long = y_long;
	}
	
	
	

}
