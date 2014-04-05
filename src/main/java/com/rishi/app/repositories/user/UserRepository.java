package com.rishi.app.repositories.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.rishi.app.models.User;

public interface UserRepository {

	User findUserByEmail(String email);
	User save(User user);
}

