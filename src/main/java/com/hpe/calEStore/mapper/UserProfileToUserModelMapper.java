package com.hpe.calEStore.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.hpe.calEStore.dao.entity.UserProfile;
import com.hpe.calEStore.model.User;


public class UserProfileToUserModelMapper implements Mapper {

	
	    /**
	     * 
	     */
	    private ModelMapper mapperFromEntity = new ModelMapper(); 
	 
	    /**
	     * 
	     */
	    public UserProfileToUserModelMapper() { 
	    	
	    	mapperFromEntity = new ModelMapper(); 
	    	
	    	mapperFromEntity.addMappings(new PropertyMap<UserProfile, User>() {

				@Override
				protected void configure() {
					
					map().setUserId(source.getUserId());
					map().setEmailId(source.getEmailId());
					map().setFirstName(source.getFirstName());
					map().setLastName(source.getLastName());
					map().setStatusInd("A");
					
				}
			});
	    	
	    	
	    }
	    	

		/* (non-Javadoc)
		 * @see Mapper#fromEntity(java.lang.Object)
		 */
		@Override
		public Object fromEntity(Object entity) {
			return mapperFromEntity.map(entity, User.class);
		}
	    	
		
		/* (non-Javadoc)
		 * @see Mapper#fromModel(java.lang.Object)
		 */
		@Override
		public Object fromModel(Object model) {
			return model;

		} 
		
		
		
}
