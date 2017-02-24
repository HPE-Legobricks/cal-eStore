package com.hpe.calEStore.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.hpe.calEStore.dao.entity.Department;
import com.hpe.calEStore.dao.entity.UserProfile;
import com.hpe.calEStore.model.User;
import com.hpe.calEStore.util.DecodeUtil;


public class UserToUserProfileModelMapper implements Mapper {

	
		User user = new User();
	
	    /**
	     * 
	     */
	    private ModelMapper mapperFromModel = new ModelMapper(); 
	 
	    /**
	     * 
	     */
	    public UserToUserProfileModelMapper() { 
	    	
	    	mapperFromModel = new ModelMapper(); 
	    	
	    	mapperFromModel.addMappings(new PropertyMap<User, UserProfile>() {

				@Override
				protected void configure() {
					
					map().setUserId(0);
					map().setEmailId(source.getEmailId());
					map().setEmailPrefInd("Y");
					map().setSmsPrefInd("N");
					map().setAddresses(null);
					map().setGender("M");
					map().setMobileNumber(source.getMobileNumber());
					map().setFirstName(source.getFirstName());
					map().setLastName(source.getLastName());
					map().setProfileInd("U");
					map().setPassword(DecodeUtil.encodedeWithBase64(source.getPassword()));
					map().setStatusInd("N");
					
					
					/*Address address = new Address();
					address.setAddressLine1(source.getAddress().getAddressLine1());*/
					
					/*address.setAddressLine2(source.getAddress().getAddressLine2());
					address.setCity(source.getAddress().getCity());
					address.setState(source.getAddress().getState());
					address.setZipCode(source.getAddress().getZipCode());
					address.setIsDfltInd("Y");*/
										
					
				}
			});
	    	
	    	//mapperFromModel.map(source, destinationType)
	    	
	    }
	    	


		/* (non-Javadoc)
		 * @see Mapper#fromEntity(java.lang.Object)
		 */
		@Override
		public Object fromEntity(Object entity) {
			return null;
		}
	    	
		
		/* (non-Javadoc)
		 * @see Mapper#fromModel(java.lang.Object)
		 */
		@Override
		public Object fromModel(Object model) {
			return mapperFromModel.map(model, UserProfile.class); 
		} 
		
		
		public static void main(String[] a){
			
			/*User user= new User();
			user.setEmailId("ss@kk.com");
			user.setPassword("ddddd");
			
			
	        UserProfile profile = (UserProfile)new UserToUserProfileModelMapper().fromModel(user);
	        
	        System.out.println(profile.getEmailId());
	        System.out.println(profile.getPassword());*/
			
			User user= new User();
			user.setEmailId("ss@kk.com");
			user.setPassword("ddddd");
			
			UserProfile uf = new UserToUserProfileModelMapper(user).getUserProfile();
			System.out.println(uf.getEmailId());
			System.out.println(uf.getPassword());
			
			
			
		}


		/**
		 * @param user
		 */
		public UserToUserProfileModelMapper(User user) {
			this.user= user;
		}

		
		/**
		 * @return
		 */
		public UserProfile getUserProfile() {
			
			UserProfile userProfile = new UserProfile();
			
			userProfile.setUserId(0);
			userProfile.setEmailId(user.getEmailId());
			userProfile.setEmailPrefInd("Y");
			userProfile.setSmsPrefInd("N");
			userProfile.setAddresses(null);
			userProfile.setGender("M");
			userProfile.setMobileNumber(user.getMobileNumber());
			userProfile.setFirstName(user.getFirstName());
			userProfile.setLastName(user.getLastName());
			userProfile.setProfileInd("U");
			userProfile.setPassword(DecodeUtil.encodedeWithBase64(user.getPassword()));
			userProfile.setStatusInd("N");
			return userProfile;
		}
		
}
