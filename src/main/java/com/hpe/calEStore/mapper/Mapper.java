package com.hpe.calEStore.mapper;

/**
 * @author mishrani
 *
 */
public abstract interface Mapper {

		 
	    /**
	     * @param entity
	     * @return
	     */
	    Object fromEntity(Object entity); 
	 
	    
	    /**
	     * @param model
	     * @return
	     */
	    Object fromModel(Object model); 
	 
}
