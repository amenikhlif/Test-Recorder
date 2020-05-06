package net.javaguides.springboot.springsecurity.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import net.javaguides.springboot.springsecurity.model.User;
import net.javaguides.springboot.springsecurity.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);

	void deleteUser(Long userId);

	List<User> getUserAllLists();

	void addUser(User user);

	User getUserById(Long userId);

	 User saveUp(User user);

	List<User> findByfirstName(String firstName);

	
}
