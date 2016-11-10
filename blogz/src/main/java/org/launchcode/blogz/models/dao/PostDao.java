package org.launchcode.blogz.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.launchcode.blogz.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional // required for hibernate and spring to automatically use this interface
@Repository  // required for hibernate and spring to automatically use this interface
public interface PostDao extends CrudRepository<Post, Integer> {
	
	List<Post> findByAuthor(int authorId); // required for hibernate and spring to automatically use this interface
	
	List<Post> finalAll(); // required for hibernate and spring to automatically use this interface

	List<Post> finalByUid(int uid); // required for hibernate and spring to automatically use this interface
	
}
