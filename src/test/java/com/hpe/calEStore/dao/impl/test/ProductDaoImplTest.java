/**
 * Copyright (c) 2017 HPE, All rights reserved.
 */
package com.hpe.calEStore.dao.impl.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.hpe.calEStore.dao.ProductDao;
import com.hpe.calEStore.dao.entity.Product;
import com.hpe.calEStore.model.ProductCompareDM;
import com.hpe.calEStore.model.ProductType;

/**
 * Test class for ProductDao
 *
 * @author Noothan Y V
 */
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/dispatcher-servlet.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ProductDaoImplTest {

	@Autowired
	private ProductDao productDao;

	/**
	 * Test method to verify the Product Catalog map has values
	 */
	@Test
	public void testGetProductCatalog() {
		try {
			Map<ProductType, List<Product>> prod = productDao
					.getProductCatalog();
			if (!prod.isEmpty()) {
				Assert.notEmpty(prod);
			} else {
				Assert.isTrue(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetProductDetailsByProductId() {
		try {
			Product product = productDao.getProductDetailsByProductId(1);
			if (!(product == null)) {
				Assert.isTrue(true);
			} else {
				Assert.isTrue(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testCompareProducts() {
		try {
			int[] productIds = new int[] { 13, 14 };
			ProductCompareDM productCompareDm = productDao
					.compareProducts(productIds);
			Assert.isTrue(productCompareDm != null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Rollback(false)
	@Test
	public void publishProductsListTest() {
		try {

			List<Integer> listOfProducts = new ArrayList<Integer>();
			listOfProducts.add(4);
			listOfProducts.add(5);
			productDao.publishProducts(listOfProducts);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void unpublishedSProductsListTest() {
		try {

			List<Product> unpublishedproducts = productDao
					.getUnpublishedProductsByProductType("SW");
			if (!(unpublishedproducts == null)) {
				Assert.isTrue(true);
			} else {
				Assert.isTrue(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void unpublishedSProductsListTestForNull() {
		try {

			List<Product> unpublishedproducts = productDao
					.getUnpublishedProductsByProductType(null);
			if (!(unpublishedproducts == null)) {
				Assert.isTrue(true);
			} else {
				Assert.isTrue(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Rollback(false)
	@Test
	public void productDetails() {
		try {

			List<Integer> listOfProducts = new ArrayList<Integer>();
			listOfProducts.add(4);
			listOfProducts.add(5);
			listOfProducts.add(6);
			listOfProducts.add(7);
			listOfProducts.add(13);

			productDao.getDetailsByProductId(listOfProducts);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
