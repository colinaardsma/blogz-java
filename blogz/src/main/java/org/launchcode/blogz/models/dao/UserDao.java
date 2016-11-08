package org.launchcode.blogz.models.dao;

import java.util.List;

import org.launchcode.blogz.models.User;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Integer> {
	
	User findByUid(int uid);
	
	List<User> finalAll();

}
