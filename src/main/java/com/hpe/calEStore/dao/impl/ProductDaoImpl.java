/**
 * Copyright (c) 2017 HPE, All rights reserved.
 */
package com.hpe.calEStore.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hpe.calEStore.dao.AbstractDAO;
import com.hpe.calEStore.dao.ProductDao;
import com.hpe.calEStore.dao.ProfileDAO;
import com.hpe.calEStore.dao.entity.Product;
import com.hpe.calEStore.dao.entity.UserProfile;

/**
 * Implementation class for Product Catalog
 * 
 * @author Noothan Y V
 *
 */
@Repository
public class ProductDaoImpl extends AbstractDAO<Serializable, Product>
		implements ProductDao {

	@SuppressWarnings({ "null", "unchecked" })
	@Transactional
	@Override
	public Map<String, List<Product>> getProductCatalog() {
		// TODO Auto-generated method stub

		// Map<String,List<Product>> prodMap = null;
		Map<String, List<Product>> prodMap = new HashMap<String, List<Product>>();

		List<Product> hardwareList = createEntityCriteria()
				.add(Restrictions.eq("productType", "HW"))
				.add(Restrictions.eq("isPublishedInd", "Y"))
				.addOrder(Order.desc("publishedDate")).setMaxResults(5).list();
		List<Product> softwareList = createEntityCriteria()
				.add(Restrictions.eq("productType", "SW"))
				.add(Restrictions.eq("isPublishedInd", "Y"))
				.addOrder(Order.desc("publishedDate")).setMaxResults(5).list();
		List<Product> servicesList = createEntityCriteria()
				.add(Restrictions.eq("productType", "SE"))
				.add(Restrictions.eq("isPublishedInd", "Y"))
				.addOrder(Order.desc("publishedDate")).setMaxResults(5).list();
		if (!hardwareList.isEmpty()) {
			prodMap.put("HW", hardwareList);

		}
		if (!softwareList.isEmpty()) {
			prodMap.put("SW", softwareList);

		}
		if (!servicesList.isEmpty()) {
			prodMap.put("HW", servicesList);

		}

		return prodMap;
	}

}
