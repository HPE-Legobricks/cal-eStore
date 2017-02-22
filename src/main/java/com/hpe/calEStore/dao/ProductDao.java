package com.hpe.calEStore.dao;

import java.util.List;
import java.util.Map;

import com.hpe.calEStore.dao.entity.Product;
import com.hpe.calEStore.model.ProductCompareDM;
import com.hpe.calEStore.model.ProductType;

/**
 * interface for Product Database entity
 * 
 * @author Noothan Y V
 *
 */
public interface ProductDao {

	Map<ProductType, List<Product>> getProductCatalog();

	Product getProductDetailsByProductId(int productId);

	List<Product> getAllProductsByProductType(String productType);

	List<Product> getAllProductsByRating(int value);

	List<Product> getAllProductsByCategory(int category);
	
	List<Product> getAllProducts(String productTypeCode,String OrderBy);

	ProductCompareDM compareProducts(int[] productIds);
	
	void  publishProducts(List<Integer> productIds);

	List<Product> getUnpublishedProductsByProductType(String productType);
	

}
