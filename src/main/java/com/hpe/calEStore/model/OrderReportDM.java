/**
 * 
 */
package com.hpe.calEStore.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sangaras
 *
 */
public class OrderReportDM implements java.io.Serializable {

	/**
	 * 
	 */
	public OrderReportDM() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}



	private String date;

	private Map<String, Integer> departmentMap = new HashMap<String, Integer>();

	/**
	 * @return the departmentMap
	 */
	public Map<String, Integer> getDepartmentMap() {
		return departmentMap;
	}

	/**
	 * @param departmentMap the departmentMap to set
	 */
	public void setDepartmentMap(Map<String, Integer> departmentMap) {
		this.departmentMap = departmentMap;
	}


	
	

}
