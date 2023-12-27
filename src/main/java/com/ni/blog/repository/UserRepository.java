package com.ni.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ni.blog.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findUserByUserId(Integer userId);
	
	@Query(value= "SELECT * FROM USER WHERE USER_Id=?1 AND PASSWORD=?2",nativeQuery=true)
	User signInVerify(Integer userId, String password);
	
	
}
