package com.hpe.calEStore.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.hpe.calEStore.dao.entity.Product;
import com.hpe.calEStore.model.ProductCompareDM;
import com.hpe.calEStore.model.ProductType;
import com.hpe.calEStore.service.ProductService;

@Controller
@SessionAttributes("prodCompList")
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
			@RequestParam("OrderBy") String OrderBy) {

		ModelAndView mv = new ModelAndView("product.list");
		List<Product> products = productService.getAllProducts(productTypeCode,
				OrderBy);
		mv.addObject("productList", products);
		return mv;
	}

	@RequestMapping(value = "/markForComparision2")
	@ResponseStatus(value = HttpStatus.OK)
	public void addToCompareList2(@RequestParam("productId") int productId,
			HttpSession session) {
		List<Integer> prodCompList = (List<Integer>) session
				.getAttribute("prodCompList");
		if (prodCompList == null) {
			prodCompList = new ArrayList<Integer>();
		}
		prodCompList.add(productId);
		session.setAttribute("prodCompList", prodCompList);
	}

	@RequestMapping(value = "/markForComparision")
	@ResponseStatus(value = HttpStatus.OK)
	public void addToCompareList(@RequestParam("productId") int productId,
			@ModelAttribute("prodCompList") ArrayList<Integer> prodCompList) {
		if (prodCompList == null) {
			prodCompList = new ArrayList<Integer>();
		}
		prodCompList.add(productId);
	}
	
	@RequestMapping(value = "/markForComparision1", method = RequestMethod.POST)
	public ModelAndView addToCompareList1(Model model,
			@ModelAttribute("prodCompList") ArrayList<Integer> prodCompList,
			@RequestParam("productId") int productId) {
		System.out.println("\n\n PRODUCT IS : " + productId);
		ModelAndView mv = new ModelAndView("");
		if (!model.containsAttribute("prodCompList")) {
			prodCompList = new ArrayList<Integer>();
		}
		prodCompList.add(productId);
		model.addAttribute("prodCompList", prodCompList);
		return mv;
	}

	@RequestMapping(value = "/compareProducts", method = RequestMethod.GET)
	public ModelAndView compareProducts() {

		ModelAndView mv = new ModelAndView("product.comparision");

		int[] productIds = { 13, 14 };
		ProductCompareDM productComparisionDM = productService
				.compareProducts(productIds);
		mv.addObject("productComparisionDM", productComparisionDM);
		return mv;
	}
}
