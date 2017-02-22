/**
 * Copyright (c) 2017 HPE, All rights reserved.
 */

package com.hpe.calEStore.service;

import java.util.List;
import java.util.Map;

import com.hpe.calEStore.dao.entity.Product;
import com.hpe.calEStore.model.ProductCompareDM;
import com.hpe.calEStore.model.ProductType;

/**
 * Interface service class for Product Entity
 * 
 * @author Noothan Y V
 *
 */
public interface ProductService {

	Map<ProductType, List<Product>> getProductCatalog();

	Product getProductDetailsByProductId(int productId);

	List<Product> getAllProductsByProductType(String productType);

	List<Product> getAllProductsByRating(int value);

	List<Product> getAllProductsByCategory(int category);
	
	List<Product> getAllProducts(String productTypeCode,String OrderBy);
    
	void  publishProducts(List<Integer> productIds);
	
	List<Product> getUnpublishedProductsByProductType(String productType);
	
	ProductCompareDM compareProducts(int[] productIds);

}
