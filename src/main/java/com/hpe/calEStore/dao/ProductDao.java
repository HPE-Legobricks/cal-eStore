/**
 * Copyright (c) 2017 HPE, All rights reserved.
 */
package com.hpe.calEStore.dao;

import java.util.List;
import java.util.Map;

import com.hpe.calEStore.dao.entity.Product;

/**
 * interface for Product Database entity
 * @author Noothan Y V
 *
 */
public interface ProductDao {
	
	Map<String, List<Product>> getProductCatalog();

}
