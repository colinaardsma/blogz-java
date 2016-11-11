package org.launchcode.blogz.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.launchcode.blogz.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional // required for hibernate and spring to automatically use this interface
@Repository  // required for hibernate and spring to automatically use this interface
public interface UserDao extends CrudRepository<User, Integer> {
	
	User findByUid(int uid); // required for hibernate and spring to automatically use this interface
	
	User findByUsername(String username); // the portion following "findBy" must match a method in the user class (for username must be "Username" first letter cap, rest lower)
	
	List<User> findAll(); // required for hibernate and spring to automatically use this interface
	
	// we can use any method named "findBy"[variable in class]

}
