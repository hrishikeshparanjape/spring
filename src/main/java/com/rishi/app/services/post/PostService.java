package com.rishi.app.services.post;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishi.app.models.Post;
import com.rishi.app.models.User;
import com.rishi.app.repositories.post.PostRepository;

@Service
public class PostService {

	private static Logger log = LoggerFactory.getLogger(PostService.class);

	@Autowired
	private PostRepository postRepository;

	public Post createPost(User u, String text) {
		Calendar now = Calendar.getInstance();
		Post p = new Post();
		p.setText(text);
		p.setCreateDate(now);
		p.setLastModified(now);
		p.setUser(u);
		postRepository.create(p);
		return p;
	}

	public List<Post> getAllPostByUser(User u) {
		return postRepository.findPostsByUser(u);
	}

	public Post edit(Long id, String text, User u) {
		Post p = postRepository.find(id);
		if (p !=null) {
			if (p.getUser().getId() == u.getId()) {
				p.setLastModified(Calendar.getInstance());
				p.setText(text);
				postRepository.update(p);
				return p;				
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

}
