/**
 * Copyright (c) 2017 HPE, All rights reserved.
 */

package com.hpe.calEStore.service;

import java.util.List;
import java.util.Map;

import com.hpe.calEStore.dao.entity.Product;

/**
 * Interface service class for Product Entity
 * @author Noothan Y V
 *
 */
public interface ProductService {
	
	 Map<String, List<Product>> getViewAllProductsByTpe();

}
