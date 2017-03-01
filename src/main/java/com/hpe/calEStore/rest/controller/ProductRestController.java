package com.hpe.calEStore.rest.controller;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hpe.calEStore.dao.entity.Product;
import com.hpe.calEStore.mapper.ProductToProductDMModelMapper;
import com.hpe.calEStore.model.ProductDM;
import com.hpe.calEStore.service.ProductService;

@Api(value = "Product List/Details")
@Controller
public class ProductRestController {

	@Autowired
	private ProductService productService;

	@ApiOperation(value = "Fetch product details by Product Id")
	@RequestMapping(value = "/getProductDetailsByProductId", method = RequestMethod.GET)
	public ResponseEntity<ProductDM> getProductDetailsByProductId(
			@RequestParam("productId") int productId) {

		Product product = productService
				.getProductDetailsByProductId(productId);
		ProductDM productDM = (ProductDM) new ProductToProductDMModelMapper()
				.fromEntity(product);
		return new ResponseEntity<ProductDM>(productDM, HttpStatus.OK);
	}

	@ApiOperation(value = "Fetch all unpublished products")
	@RequestMapping(value = "/getAllUnpublishedProducts", method = RequestMethod.GET)
	public ResponseEntity<List<ProductDM>> getAllUnpublishedProducts() {

		List<Product> productList = productService
				.getUnpublishedProductsByProductType(null);
		List<ProductDM> productDMList = new ArrayList<ProductDM>();
		for (Product product : productList) {
			ProductDM productDM = (ProductDM) new ProductToProductDMModelMapper()
					.fromEntity(product);
			productDMList.add(productDM);
		}

		return new ResponseEntity<List<ProductDM>>(productDMList, HttpStatus.OK);
	}

}
