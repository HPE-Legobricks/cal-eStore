package com.hpe.calEStore.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import springfox.documentation.annotations.ApiIgnore;

import com.hpe.calEStore.dao.entity.Product;
import com.hpe.calEStore.model.ProductCompareDM;
import com.hpe.calEStore.model.ProductType;
import com.hpe.calEStore.service.ProductService;

@Controller @ApiIgnore
// @SessionAttributes({ "prodCompList", "catgForComp" })
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/productCatalogue", method = RequestMethod.GET)
	public ModelAndView displayProductCatalogue() {

		ModelAndView mv = new ModelAndView("user.home");
		Map<ProductType, List<Product>> catalogueMap = productService
				.getProductCatalog();
		mv.addObject("catalogueMap", catalogueMap);
		return mv;
	}

	@RequestMapping(value = "/productDetail", method = RequestMethod.GET)
	public ModelAndView showProductDetails(@RequestParam int productId) {

		ModelAndView mv = new ModelAndView("product.detail");
		Product product = productService
				.getProductDetailsByProductId(productId);
		mv.addObject("product", product);
		return mv;
	}

	@RequestMapping(value = "/productList", method = RequestMethod.GET)
	public ModelAndView showProductDetails(
			@RequestParam("productTypeCode") String productTypeCode,
			@RequestParam("OrderBy") String OrderBy,
			@RequestParam("Order") String Order) {

		ModelAndView mv = new ModelAndView("product.list");

		List<Product> products;
		HashMap items;

		if ("".equalsIgnoreCase(OrderBy) || OrderBy.isEmpty()) {
			products = productService.getAllProducts(productTypeCode,
					"productName", "asc");
			items = productService.getCategoryByProduct(productTypeCode,
					OrderBy);
		} else {
			products = productService.getAllProducts(productTypeCode, OrderBy,
					Order);
			items = productService.getCategoryByProduct(productTypeCode,
					OrderBy);
		}

		mv.addObject("category", items.get("categoryByProducts"));
		mv.addObject("brand", items.get("brandsByProduct"));
		mv.addObject("productList", products);
		return mv;
	}

	@RequestMapping(value = "/markForComparision")
	@ResponseStatus(value = HttpStatus.OK)
	public void addToCompareList(@RequestParam("productId") int productId,
			@RequestParam("categoryId") int categoryId,
			// @ModelAttribute("prodCompList") ArrayList<Integer> prodCompList,
			HttpSession session) {
		List<Integer> prodCompList = new ArrayList<Integer>();
		if (session.getAttribute("prodCompList") != null) {
			prodCompList = (List<Integer>) session.getAttribute("prodCompList");
		}
		session.setAttribute("catgForComp", Integer.valueOf(categoryId));
		prodCompList.add(productId);
		session.setAttribute("prodCompList", prodCompList);
	}

	@RequestMapping(value = "/compareProducts", method = RequestMethod.GET)
	public ModelAndView compareProducts(
	// @ModelAttribute("prodCompList") ArrayList<Integer> prodCompList,
			HttpSession session) {

		ModelAndView mv = new ModelAndView("product.comparision");

		List<Integer> prodCompList = (List<Integer>) session
				.getAttribute("prodCompList");

		Integer[] productIds = prodCompList.toArray(new Integer[prodCompList
				.size()]);

		ProductCompareDM productComparisionDM = productService
				.compareProducts(productIds);
		mv.addObject("productComparisionDM", productComparisionDM);
		prodCompList = new ArrayList<Integer>();
		session.setAttribute("prodCompList", prodCompList);
		session.removeAttribute("catgForComp");
		return mv;
	}
}
