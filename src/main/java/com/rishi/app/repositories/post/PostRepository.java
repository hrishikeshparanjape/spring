package com.rishi.app.repositories.post;

import java.util.List;

import com.rishi.app.models.Post;
import com.rishi.app.models.User;

public interface PostRepository {

	Post create(Post p);
	Post update(Post p);
	Post delete(Long id);
	List<Post> findPostsByUser(User u);
	Post edit(Post p);
	Post find(Long id);
}

