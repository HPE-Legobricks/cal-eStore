/**
 * Copyright (c) 2017 HPE, All rights reserved.
 */
package com.hpe.calEStore.service.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hpe.calEStore.dao.ProductDao;
import com.hpe.calEStore.dao.entity.Product;
import com.hpe.calEStore.service.ProductService;
/** 
 * Service class for Product Entity
 *
 * @author Noothan Y V
 */
@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

	
	@Autowired
	private ProductDao prodDao;
	
	
	
	@Override
	public Map<String, List<Product>> getViewAllProductsByTpe() {
		
		return prodDao.getProductCatalog();
		
	}
	

}
