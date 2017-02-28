package com.hpe.calEStore.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hpe.calEStore.dao.entity.Product;
import com.hpe.calEStore.dao.entity.UserProfile;
import com.hpe.calEStore.mapper.ProductToProductDMModelMapper;
import com.hpe.calEStore.mapper.UserProfileToUserModelMapper;
import com.hpe.calEStore.model.ProductDM;
import com.hpe.calEStore.model.User;
import com.hpe.calEStore.service.ProductService;
import com.hpe.calEStore.service.ProfileService;

@Controller
public class PaginationController {

	/**
     * 
     */
	@Autowired
	ProfileService service;
	@Autowired
	ProductService productService;

	/**
	 * @param pathVariablesMap
	 * @param req
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/renderPaginationViewAll/{type}",
			"/renderPaginationViewAll" }, method = RequestMethod.GET)
	public ModelAndView all(@PathVariable Map<String, String> pathVariablesMap,
			HttpServletRequest req, Model model) {

		PagedListHolder<User> productList = null;

		String type = pathVariablesMap.get("type");

		// First request fired.
		if (null == type) {

			// First Request, Return first set of list
			req.getSession().removeAttribute("userInSession");
			List<User> userList = null;
			try {
				userList = service.fetchPendingUserProfiles();
				
				if(userList == null){
					userList = new ArrayList<User>();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			productList = new PagedListHolder<User>();
			productList.setSource(userList);
			productList.setPageSize(4);

			req.getSession().setAttribute("userList", productList);

			printPageDetails(productList);

		} else if ("next".equals(type)) {

			// Return next set of list
			productList = (PagedListHolder<User>) req.getSession()
					.getAttribute("userList");
			productList = getListOfProductsAfterSessionEvaluation(req,
					productList);

			productList.nextPage();
			printPageDetails(productList);

		} else if ("prev".equals(type)) {

			// Return previous set of list
			productList = (PagedListHolder<User>) req.getSession()
					.getAttribute("userList");
			productList = getListOfProductsAfterSessionEvaluation(req,
					productList);
			productList.previousPage();
			printPageDetails(productList);

		} else {

			// Return specific index set of list
			System.out.println("type:" + type);

			productList = (PagedListHolder<User>) req.getSession()
					.getAttribute("userList");
			productList = getListOfProductsAfterSessionEvaluation(req,
					productList);

			int pageNum = Integer.parseInt(type);
			productList.setPage(pageNum);
			printPageDetails(productList);
		}

		ModelAndView mv = new ModelAndView("manage.user");
		mv.addObject("maxPages", productList.getPageCount());
		return mv;
	}
	
	/**
	 * @param pathVariablesMap
	 * @param req
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/renderPaginationViewAllProducts/{type}",
			"/renderPaginationViewAllProducts" }, method = RequestMethod.GET)
	public ModelAndView allProducts(@PathVariable Map<String, String> pathVariablesMap,
			HttpServletRequest req, Model model) {

		PagedListHolder<ProductDM> productList = null;

		String type = pathVariablesMap.get("type");

		// First request fired.
		if (null == type) {

			// First Request, Return first set of list
			req.getSession().removeAttribute("userInSession");
			List<ProductDM> userList = null;
			List<Product> products = null;
			try {
				products = productService.getUnpublishedProductsByProductType("");
				if(!products.isEmpty()){
					
					userList = new ArrayList<ProductDM>();
					for(Product product: products){
						userList.add((ProductDM) new ProductToProductDMModelMapper().fromEntity(product));
					}
				}
					
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			productList = new PagedListHolder<ProductDM>();
			productList.setSource(userList);
			productList.setPageSize(4);

			req.getSession().setAttribute("userList", productList);

			printPageDetails(productList);

		} else if ("next".equals(type)) {

			// Return next set of list
			productList = (PagedListHolder<ProductDM>) req.getSession()
					.getAttribute("userList");
			productList = getListOfUnpublishedProductsAfterSessionEvaluation(req,
					productList);

			productList.nextPage();
			printPageDetails(productList);

		} else if ("prev".equals(type)) {

			// Return previous set of list
			productList = (PagedListHolder<ProductDM>) req.getSession()
					.getAttribute("userList");
			productList = getListOfUnpublishedProductsAfterSessionEvaluation(req,
					productList);
			productList.previousPage();
			printPageDetails(productList);

		} else {

			// Return specific index set of list
			System.out.println("type:" + type);

			productList = (PagedListHolder<ProductDM>) req.getSession()
					.getAttribute("userList");
			productList = getListOfUnpublishedProductsAfterSessionEvaluation(req,
					productList);

			int pageNum = Integer.parseInt(type);
			productList.setPage(pageNum);
			printPageDetails(productList);
		}

		ModelAndView mv = new ModelAndView("manage.products");
		mv.addObject("maxPages", productList.getPageCount());
		return mv;
	}


	/**
	 * @param req
	 * @param attribute
	 * @return
	 */
	private PagedListHolder<User> getListOfProductsAfterSessionEvaluation(
			HttpServletRequest request, PagedListHolder<User> productList) {

		String[] selectedUserIDvalues = request
				.getParameterValues("selectedUserBox");

		String uncheckedIDvalues = request.getParameter("unchecked");
		String[] unSelectedUserIDvalues = uncheckedIDvalues.split(",");

		// int increment = 0;
		/* operation on selected user checkboxes */
		if (null != selectedUserIDvalues) {

			Object[] userArrayFromSession = (Object[]) request.getSession().getAttribute("userInSession");
			List<String> list = null;
			if(userArrayFromSession==null){
				 list = new ArrayList<String>();
			}
			else{
				String[] arrayOfString =  Arrays.copyOf(userArrayFromSession, userArrayFromSession.length, String[].class);
				list = new ArrayList<String>(Arrays.asList(arrayOfString));
			}

			for (String toSearch : selectedUserIDvalues) {

				if (null != userArrayFromSession) {

					if (!list.contains(toSearch)) {
						list.add(toSearch);
					}
				} else {
					list.add(toSearch);
				}
			}

			request.getSession().setAttribute("userInSession",list.toArray());
		}

		/* operation on unselected user checkboxes */
		if (null != unSelectedUserIDvalues) {

			Object[] userArrayFromSession = (Object[]) request.getSession().getAttribute("userInSession");
			
			if(userArrayFromSession != null){
				
				String[] arrayOfString =  Arrays.copyOf(userArrayFromSession, userArrayFromSession.length, String[].class);
				List<String> list = new ArrayList<String>(Arrays.asList(arrayOfString));
				
				for (String toSearchUnselected : unSelectedUserIDvalues) {
	
					if (null != userArrayFromSession) {
						if (list.contains(toSearchUnselected)) {
							list.remove(toSearchUnselected);
						}
					}
				}
				if (list != null) {
					request.getSession().setAttribute("userInSession",list.toArray());
				}
				
				

				/*iterating over the list and selecting preference as "checked"*/
				for (User user : productList.getSource()) {
					
					String[] dummyStringArray =  Arrays.copyOf(userArrayFromSession, userArrayFromSession.length, String[].class);
					List<String> dummylist = new ArrayList<String>(Arrays.asList((dummyStringArray)));
					List<Integer> intList = new ArrayList<Integer>();
					for(String s : dummylist) intList.add(Integer.valueOf(s));
					if(intList.contains(user.getUserId())){
						user.setSelectPreference("checked");
					}
					else {
						user.setSelectPreference(null);
					}
				}
			}
			
			
			
		}
		return productList;
	}
	
	
	/**
	 * @param req
	 * @param attribute
	 * @return
	 */
	private PagedListHolder<ProductDM> getListOfUnpublishedProductsAfterSessionEvaluation(
			HttpServletRequest request, PagedListHolder<ProductDM> productList) {

		String[] selectedUserIDvalues = request
				.getParameterValues("selectedUserBox");

		String uncheckedIDvalues = request.getParameter("unchecked");
		String[] unSelectedUserIDvalues = uncheckedIDvalues.split(",");

		// int increment = 0;
		/* operation on selected user checkboxes */
		if (null != selectedUserIDvalues) {

			Object[] userArrayFromSession = (Object[]) request.getSession().getAttribute("userInSession");
			List<String> list = null;
			if(userArrayFromSession==null){
				 list = new ArrayList<String>();
			}
			else{
				String[] arrayOfString =  Arrays.copyOf(userArrayFromSession, userArrayFromSession.length, String[].class);
				list = new ArrayList<String>(Arrays.asList(arrayOfString));
			}

			for (String toSearch : selectedUserIDvalues) {

				if (null != userArrayFromSession) {

					if (!list.contains(toSearch)) {
						list.add(toSearch);
					}
				} else {
					list.add(toSearch);
				}
			}

			request.getSession().setAttribute("userInSession",list.toArray());
		}

		/* operation on unselected user checkboxes */
		if (null != unSelectedUserIDvalues) {

			Object[] userArrayFromSession = (Object[]) request.getSession().getAttribute("userInSession");
			
			if(userArrayFromSession != null){
				
				String[] arrayOfString =  Arrays.copyOf(userArrayFromSession, userArrayFromSession.length, String[].class);
				List<String> list = new ArrayList<String>(Arrays.asList(arrayOfString));
				
				for (String toSearchUnselected : unSelectedUserIDvalues) {
	
					if (null != userArrayFromSession) {
						if (list.contains(toSearchUnselected)) {
							list.remove(toSearchUnselected);
						}
					}
				}
				if (list != null) {
					request.getSession().setAttribute("userInSession",list.toArray());
				}
				
				

				/*iterating over the list and selecting preference as "checked"*/
				for (ProductDM productDM : productList.getSource()) {
					
					String[] dummyStringArray =  Arrays.copyOf(userArrayFromSession, userArrayFromSession.length, String[].class);
					List<String> dummylist = new ArrayList<String>(Arrays.asList((dummyStringArray)));
					List<Integer> intList = new ArrayList<Integer>();
					for(String s : dummylist) intList.add(Integer.valueOf(s));
			if(intList.contains(productDM.getProductId())){
				productDM.setSelectPreference("checked");
					}
			else {
				productDM.setSelectPreference(null);
					}
				}
			}
			
			
			
		}
		return productList;
	}


	private void printPageDetails(PagedListHolder productList) {

		System.out.println("curent page - productList.getPage() :"
				+ productList.getPage());
		System.out.println("Total Num of pages - productList.getPageCount :"
				+ productList.getPageCount());
		System.out.println("is First page - productList.isFirstPage :"
				+ productList.isFirstPage());
		System.out.println("is Last page - productList.isLastPage :"
				+ productList.isLastPage());
	}
}