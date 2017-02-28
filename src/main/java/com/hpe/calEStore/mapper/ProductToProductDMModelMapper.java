/**
 * 
 */
package com.hpe.calEStore.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.hpe.calEStore.dao.entity.Product;
import com.hpe.calEStore.model.ProductDM;
import com.hpe.calEStore.model.User;





/**
 * @author sangaras
 *
 */
public class ProductToProductDMModelMapper implements Mapper {

	
    /**
     * 
     */
    private ModelMapper mapperFromEntity = new ModelMapper(); 
 
    /**
     * 
     */
    public ProductToProductDMModelMapper() { 
    	
    	mapperFromEntity = new ModelMapper(); 
    	
    	mapperFromEntity.addMappings(new PropertyMap<Product, ProductDM>() {

			@Override
			protected void configure() {
				
				map().setProductId(source.getProductId());
				map().setBrand(source.getBrand().getBrandName());
				map().setProductType(source.getProductType());
				map().setPricePerUnit(source.getPricePerUnit());
				map().setCategory(source.getCategory().getCategoryName());
			}
		});
    	
    	
    }

	@Override
	public Object fromEntity(Object entity) {
		// TODO Auto-generated method stub
		return mapperFromEntity.map(entity, ProductDM.class);
	}

	@Override
	public Object fromModel(Object model) {
		// TODO Auto-generated method stub
		return null;
	}
 

}
