package com.rishi.app.services.user;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rishi.app.models.User;
import com.rishi.app.repositories.user.UserRepository;
import com.rishi.app.services.security.PasswordEncoder;

@Service
public class UserService implements UserDetailsService{

	private static Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		return userRepository.findUserByEmail(userName);
	}
	
	public User createUser(String email, String password) {
		String salt = encoder.generateRandomSalt();
		String hash = encoder.encodePassword(password, salt);
		Calendar now = Calendar.getInstance();
		User user = new User();
		user.setEmail(email);
		user.setUserName(email);
		user.setPassword(hash);
		user.setSalt(salt);
		user.setCreateDate(now);
		user.setLastModified(now);
		userRepository.save(user);
		return user;
	}
	
	public User authenticate(String email, String password){
		User u = userRepository.findUserByEmail(email);
		if (u!=null) {
			String userPassword = u.getPassword();
			String userSalt = u.getSalt();
			 if(userPassword.equals(encoder.encodePassword(password, userSalt))){
				return u; 
			 } else {
				 return null;
			 }
		} else {
			return null;
		}
		
	}
}
