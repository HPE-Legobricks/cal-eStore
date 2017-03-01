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
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
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
	public ProductCompareDM compareProducts(Integer[] productIds) {

		ProductCompareDM productCompareDM = new ProductCompareDM();
		// Iterate thru the list of input product ids
		for (int productId : productIds) {

			// Fetch the product aspects
			List<ProductAspect> productAspects = getSession()
					.createCriteria(ProductAspect.class)
					.add(Restrictions.eq("id.productId", productId)).list();

			Product product;
			Map<String, List<String>> aspectMap = productCompareDM
					.getAspectMap();

			if (!productAspects.isEmpty()) {

				product = productAspects.get(0).getProduct();

				
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

			SortedMap<String, List<String>> ascSortedMap = new TreeMap<String, List<String>>();
			ascSortedMap.putAll(aspectMap);
			productCompareDM.setAspectMap(ascSortedMap);
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


		Product prod = (Product) createEntityCriteria().add(
				Restrictions.eq("productId", productId)).uniqueResult();
		return prod;
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional
	@Override
	public List<Product> getAllProductsByProductType(String productType) {


		List<Product> productsByType = new ArrayList<Product>();

		if (productType != null && "HW".equalsIgnoreCase(productType)) {
			productsByType = createEntityCriteria()
					.add(Restrictions.eq("productType", "HW"))
					.add(Restrictions.eq("isPublishedInd", "Y"))
					.addOrder(Order.desc("publishedDate")).list();

		} else if (productType != null && "SW".equalsIgnoreCase(productType)) {
			productsByType = createEntityCriteria()
					.add(Restrictions.eq("productType", "SW"))
					.add(Restrictions.eq("isPublishedInd", "Y"))
					.addOrder(Order.desc("publishedDate")).list();

		} else if (productType != null && "SE".equalsIgnoreCase(productType)) {
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


		List<Product> ProductsByCategory = createEntityCriteria()
				.add(Restrictions.eq("category", category))
				.add(Restrictions.eq("isPublishedInd", "Y"))
				.addOrder(Order.desc("publishedDate")).list();
		return ProductsByCategory;
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional
	@Override
	public List<Product> getAllProducts(String productTypeCode, String orderBy,
			String order) {


		ProductType[] productValues = ProductType.values();

		for (ProductType temp : productValues) {

			if (temp.getName().equalsIgnoreCase(productTypeCode)) {
				productTypeCode = String.valueOf(temp);
			}
		}
		List<Product> productsByCategory = null;

		if ("asc".equalsIgnoreCase(order)) {
			productsByCategory = createEntityCriteria()
					.add(Restrictions.eq("productType", productTypeCode))
					.addOrder(Order.asc(orderBy)).list();
		} else if ("desc".equalsIgnoreCase(order)) {
			productsByCategory = createEntityCriteria()
					.add(Restrictions.eq("productType", productTypeCode))
					.addOrder(Order.desc(orderBy)).list();
		}

		return productsByCategory;
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional
	@Override
	public HashMap getCategoryByProduct(String productTypeCode, String OrderBy) {

		ProductType[] productValues = ProductType.values();

		for (ProductType temp : productValues) {
			if (temp.getName().equalsIgnoreCase(productTypeCode)) {
				productTypeCode = String.valueOf(temp);
			}
		}

		HashMap items = new HashMap();

		ProjectionList projectionList2 = Projections.projectionList();
		projectionList2.add(Projections.distinct(Projections
				.property("category")));

		List categoryByProducts = createEntityCriteria()
				.add(Restrictions.eq("productType", productTypeCode))
				.setProjection(projectionList2).list();

		items.put("categoryByProducts", categoryByProducts);

		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.distinct(Projections.property("brand")));

		List brandsByProduct = createEntityCriteria()
				.add(Restrictions.eq("productType", productTypeCode))
				.setProjection(projectionList).list();

		items.put("brandsByProduct", brandsByProduct);

		return items;
	}

	@Transactional
	@Override
	public void publishProducts(List<Integer> productIds) {

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


		List<Product> productsByType;
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


		List<Product> productDetails = createEntityCriteria().add(Restrictions.in("productId", productIds))
				.addOrder(Order.desc("publishedDate")).list();

		return productDetails;

	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Product> getAllPublishedProducts() {
		List<Product> products = createEntityCriteria()
				.add(Restrictions.eq("isPublishedInd", "Y"))
				.addOrder(Order.desc("publishedDate")).list();

		return products;
	}
}
