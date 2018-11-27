package com.snopkowski.elibrary.configuration;

import com.snopkowski.elibrary.dao.UserProfileDao;
import com.snopkowski.elibrary.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleToUserProfileConverter implements Converter<Object, UserProfileDao> {

    @Autowired
    UserProfileService userProfileService;

    /*
     * Gets UserProfileRepository by Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public UserProfileDao convert(Object element) {
        Integer id = Integer.parseInt((String) element);
        UserProfileDao profile = userProfileService.findById(id);
        System.out.println("Profile : " + profile);
        return profile;
    }

    /*
     * Gets UserProfileRepository by type
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
	/*
	public UserProfileRepository convert(Object element) {
		String type = (String)element;
		UserProfileRepository profile= userProfileService.findByType(type);
		System.out.println("Profile ... : "+profile);
		return profile;
	}
	*/

}