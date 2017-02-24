package com.hpe.calEStore.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hpe.calEStore.model.User;
import com.hpe.calEStore.service.ProfileService;

@Controller
public class PaginationController {
    
    /**
     * 
     */
    @Autowired
    ProfileService service;
    
    
    /**
     * @param pathVariablesMap
     * @param req
     * @return
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value = {"/renderPaginationViewAll/{type}","/renderPaginationViewAll"}, method = RequestMethod.GET)
    public ModelAndView all(@PathVariable Map<String, String> pathVariablesMap, HttpServletRequest req, Model model) {
        
        PagedListHolder<User> productList = null;
        
        String type = pathVariablesMap.get("type");
        
        //First request fired.
        if(null == type) {
        	
            // First Request, Return first set of list
            List<User> userList = null;
			try {
				userList = service.fetchPendingUserProfiles();
			} catch (Exception e) {
				e.printStackTrace();
			}
            
            productList = new PagedListHolder<User>();
            productList.setSource(userList);
            productList.setPageSize(4);
            
            req.getSession().setAttribute("userList",  productList);
            
            printPageDetails(productList);
            
        } else if("next".equals(type)) {

        	//Return next set of list
        	productList = (PagedListHolder<User>) req.getSession().getAttribute("userList");
            productList.nextPage();
            printPageDetails(productList);
            
        } else if("prev".equals(type)) {
        	
            // Return previous set of list
            productList = (PagedListHolder<User>) req.getSession().getAttribute("userList");
            productList.previousPage();
            printPageDetails(productList);
            
        } else {
        	
            // Return specific index set of list
            System.out.println("type:" + type);
            
            productList = (PagedListHolder<User>) req.getSession().getAttribute("userList");
            
            int pageNum = Integer.parseInt(type);
            productList.setPage(pageNum);
            printPageDetails(productList);
        }
                    
        ModelAndView mv = new ModelAndView("manage.user");
        mv.addObject("maxPages", productList.getPageCount());
        return  mv;
    }

    private void printPageDetails(PagedListHolder productList) {
        
        System.out.println("curent page - productList.getPage() :"+ productList.getPage());
        System.out.println("Total Num of pages - productList.getPageCount :"+ productList.getPageCount());
        System.out.println("is First page - productList.isFirstPage :"+ productList.isFirstPage());
        System.out.println("is Last page - productList.isLastPage :"+ productList.isLastPage());
    }
}