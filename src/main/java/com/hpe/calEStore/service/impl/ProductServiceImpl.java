package com.hpe.calEStore.service.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hpe.calEStore.dao.ProductDao;
import com.hpe.calEStore.dao.entity.Product;
import com.hpe.calEStore.model.ProductCompareDM;
import com.hpe.calEStore.model.ProductType;
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
	public Map<ProductType, List<Product>> getProductCatalog() {
		return prodDao.getProductCatalog();
	}

	@Override
	public Product getProductDetailsByProductId(int productId) {
		return prodDao.getProductDetailsByProductId(productId);
	}

	@Override
	public List<Product> getAllProductsByProductType(String productType) {
		return prodDao.getAllProductsByProductType(productType);
	}

	@Override
	public List<Product> getAllProductsByRating(int value) {
		return prodDao.getAllProductsByRating(value);
	}

	@Override
	public List<Product> getAllProductsByCategory(int category) {
		return prodDao.getAllProductsByCategory(category);
	}

	@Override
	public List<Product> getAllProducts(String productTypeCode, String OrderBy) {
		return prodDao.getAllProducts(productTypeCode, OrderBy);
	}

	@Override
	public void publishProducts(List<Integer> productIds) {
		prodDao.publishProducts(productIds);
	}

	@Override
	public List<Product> getUnpublishedProductsByProductType(String productType) {
		return prodDao.getUnpublishedProductsByProductType(productType);
	}

	@Override
	public ProductCompareDM compareProducts(int[] productIds) {
		return prodDao.compareProducts(productIds);
	}

	@Override
	public List<Product> getDetailsByProductId(List<Integer> productIds) {
		// TODO Auto-generated method stub
		return prodDao.getDetailsByProductId(productIds);
	}
}
