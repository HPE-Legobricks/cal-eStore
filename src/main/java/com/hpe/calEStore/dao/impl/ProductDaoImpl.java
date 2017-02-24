/**
 * Copyright (c) 2017 HPE, All rights reserved.
 */
package com.hpe.calEStore.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hpe.calEStore.dao.AbstractDAO;
import com.hpe.calEStore.dao.ProductDao;
import com.hpe.calEStore.dao.entity.Product;
import com.hpe.calEStore.dao.entity.ProductAspect;
import com.hpe.calEStore.model.ProductCompareDM;
import com.hpe.calEStore.model.ProductType;
import com.mysql.jdbc.StringUtils;

/**
 * Implementation class for Product Catalog
 * 
 * @author Noothan Y V
 *
 */
@Repository
public class ProductDaoImpl extends AbstractDAO<Serializable, Product>
		implements ProductDao {

	@SuppressWarnings({ "unchecked" })
	@Transactional
	@Override
	public ProductCompareDM compareProducts(int[] productIds) {

		ProductCompareDM productCompareDM = new ProductCompareDM();
		// Iterate thru the list of input product ids
		for (int productId : productIds) {

			// Fetch the product aspects
			List<ProductAspect> productAspects = getSession()
					.createCriteria(ProductAspect.class)
					.add(Restrictions.eq("id.productId", productId)).list();

			Product product = null;
			Map<String, List<String>> aspectMap = productCompareDM
					.getAspectMap();

			if (productAspects.size() > 0) {

				product = productAspects.get(0).getProduct();

				// Map<String, List<String>> aspectMap = productCompareDM
				// .getAspectMap();

				// Iterate through the prod aspect records for each product id
				for (ProductAspect productAspect : productAspects) {

					List<String> aspectValues = aspectMap.get(productAspect
							.getAspect().getAspectName());

					if (aspectValues == null) {
						// Create a new list of aspect values
						aspectValues = new ArrayList<String>();
						// Attach the list in the aspectmap with the AspectName
						aspectMap.put(
								productAspect.getAspect().getAspectName(),
								aspectValues);
					}
					// Add the new aspect value to the list
					aspectValues.add(productAspect.getAspectVal());
				}
			} else {
				// Get product details by product id
				product = getProductDetailsByProductId(productId);
			}

			// Add the product name here
			productCompareDM.getProductNames().add(product.getProductName());
			// Add the product Id here
			productCompareDM.getProductIds().add(productId);

			addNewAspect(aspectMap, "Brand", product.getBrand().getBrandName());
			addNewAspect(aspectMap, "Price", "$ " + product.getPricePerUnit());
			addNewAspect(aspectMap, "Rating", product.getRating().toString());
			addNewAspect(aspectMap, "Image", product.getImgPath());
			addNewAspect(aspectMap, "Summary", product.getProductDesc());
		}
		return productCompareDM;
	}

	private void addNewAspect(Map<String, List<String>> aspectMap,
			String aspectName, String aspectValue) {
		List<String> aspectValues = aspectMap.get(aspectName);
		if (aspectValues == null) {
			aspectValues = new ArrayList<String>();
		}
		aspectValues.add(aspectValue);
		aspectMap.put(aspectName, aspectValues);
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional
	@Override
	public Map<ProductType, List<Product>> getProductCatalog() {
		// TODO Auto-generated method stub

		// Map<String,List<Product>> prodMap = null;
		Map<ProductType, List<Product>> prodMap = new HashMap<ProductType, List<Product>>();

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
			prodMap.put(ProductType.HW, hardwareList);
		}
		if (!softwareList.isEmpty()) {
			prodMap.put(ProductType.SW, softwareList);
		}
		if (!servicesList.isEmpty()) {
			prodMap.put(ProductType.SE, servicesList);
		}
		SortedMap<ProductType, List<Product>> ascSortedMap = new TreeMap();
		ascSortedMap.putAll(prodMap);
		return ascSortedMap;
	}

	@Transactional
	@Override
	public Product getProductDetailsByProductId(int productId) {
		// TODO Auto-generated method stub

		Product prod = (Product) createEntityCriteria().add(
				Restrictions.eq("productId", productId)).uniqueResult();
		return prod;
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional
	@Override
	public List<Product> getAllProductsByProductType(String productType) {
		// TODO Auto-generated method stub

		List<Product> productsByType = new ArrayList<Product>();

		if (productType != null && productType.equalsIgnoreCase("HW")) {
			productsByType = createEntityCriteria()
					.add(Restrictions.eq("productType", "HW"))
					.add(Restrictions.eq("isPublishedInd", "Y"))
					.addOrder(Order.desc("publishedDate")).list();

		} else if (productType != null && productType.equalsIgnoreCase("SW")) {
			productsByType = createEntityCriteria()
					.add(Restrictions.eq("productType", "SW"))
					.add(Restrictions.eq("isPublishedInd", "Y"))
					.addOrder(Order.desc("publishedDate")).list();

		} else if (productType != null && productType.equalsIgnoreCase("SE")) {
			productsByType = createEntityCriteria()
					.add(Restrictions.eq("productType", "SE"))
					.add(Restrictions.eq("isPublishedInd", "Y"))
					.addOrder(Order.desc("publishedDate")).list();

		}

		return productsByType;
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional
	@Override
	public List<Product> getAllProductsByRating(int value) {
		// TODO Auto-generated method stub

		List<Product> ProductsByRating = createEntityCriteria()
				.add(Restrictions.eq("rating", value))
				.add(Restrictions.eq("isPublishedInd", "Y"))
				.addOrder(Order.desc("publishedDate")).list();

		return ProductsByRating;
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional
	@Override
	public List<Product> getAllProductsByCategory(int category) {
		// TODO Auto-generated method stub

		List<Product> ProductsByCategory = createEntityCriteria()
				.add(Restrictions.eq("category", category))
				.add(Restrictions.eq("isPublishedInd", "Y"))
				.addOrder(Order.desc("publishedDate")).list();
		return ProductsByCategory;
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional
	@Override
	public List<Product> getAllProducts(String productTypeCode, String OrderBy) {
		// TODO Auto-generated method stub

		ProductType[] productValues = ProductType.values();

		for (ProductType temp : productValues) {
			// System.out.println(temp.getName().equalsIgnoreCase(productTypeCode));
			if (temp.getName().equalsIgnoreCase(productTypeCode)) {
				productTypeCode = String.valueOf(temp);
			}
		}

		List<Product> ProductsByCategory = createEntityCriteria()
				.add(Restrictions.eq("productType", productTypeCode))
				.addOrder(Order.asc(OrderBy)).list();

		return ProductsByCategory;
	}

	@Transactional
	@Override
	public void publishProducts(List<Integer> productIds) {
		// TODO Auto-generated method stub
		String hqlUpdate = "update Product set IS_PUBLISHED_IND='Y' where Product_Id=:id";
		for (Integer prodId : productIds) {
			Query query = getSession().createQuery(hqlUpdate);

			query.setParameter("id", prodId);
			query.executeUpdate();
		}
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional
	@Override
	public List<Product> getUnpublishedProductsByProductType(String productType) {
		// TODO Auto-generated method stub

		List<Product> productsByType = new ArrayList<Product>();
		if (StringUtils.isNullOrEmpty(productType)) {
			productsByType = createEntityCriteria()
					.add(Restrictions.eq("isPublishedInd", "N"))
					.addOrder(Order.desc("publishedDate")).list();
		} else {
			productsByType = createEntityCriteria()
					.add(Restrictions.eq("productType", productType))
					.add(Restrictions.eq("isPublishedInd", "N"))
					.addOrder(Order.desc("publishedDate")).list();

		}
		return productsByType;

	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Product> getDetailsByProductId(List<Integer> productIds) {
		// TODO Auto-generated method stub

		List<Product> productDetails = createEntityCriteria()
				.add(Restrictions.in("productId", productIds))
				.addOrder(Order.desc("publishedDate")).list();

		return productDetails;

	}

}
