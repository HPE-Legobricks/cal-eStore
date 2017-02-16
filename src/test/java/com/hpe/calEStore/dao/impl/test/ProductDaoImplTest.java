/**
 * Copyright (c) 2017 HPE, All rights reserved.
 */
package com.hpe.calEStore.dao.impl.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.hpe.calEStore.dao.ProductDao;
import com.hpe.calEStore.dao.entity.Product;

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
			Map<String, List<Product>> prod= productDao.getProductCatalog();
			if(!prod.isEmpty()){
				Assert.notEmpty(prod);
			}else{
				Assert.isTrue(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
