package com.my.repository.jpa;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.my.entity.Users;

public interface UserDao extends PagingAndSortingRepository<Users, Long>, JpaSpecificationExecutor<Users> {

	Users findByName(String name);

	Users findByLoginName(String loginName);
	
}
