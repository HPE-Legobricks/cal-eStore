/**
 * 
 */
package com.hpe.calEStore.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sangaras
 *
 */
public class ProductCompareDM implements java.io.Serializable {

	private List<String> productNames = new ArrayList<String>();

	private List<Integer> productIds = new ArrayList<Integer>();

	private Map<String, List<String>> aspectMap = new HashMap<String, List<String>>();

	public List<String> getProductNames() {
		return productNames;
	}

	public void setProductNames(List<String> productNames) {
		this.productNames = productNames;
	}

	public List<Integer> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<Integer> productIds) {
		this.productIds = productIds;
	}

	public Map<String, List<String>> getAspectMap() {
		return aspectMap;
	}

	public void setAspectMap(Map<String, List<String>> aspectMap) {
		this.aspectMap = aspectMap;
	}

	public String toString() {
		StringBuffer buff = new StringBuffer();
		for (String productName : productNames) {
			buff.append("\nProduct Name : " + productName);
		}
		for (int productId : productIds) {
			buff.append("\nProduct Id : " + productId);
		}
		return buff.toString();
	}
}
