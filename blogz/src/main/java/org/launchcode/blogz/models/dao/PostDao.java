package org.launchcode.blogz.models.dao;

import java.util.List;

import org.launchcode.blogz.models.Post;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface PostDao extends CrudRepository<Post, Integer> {
	
	List<Post> findByAuthor(int authorId);

}
