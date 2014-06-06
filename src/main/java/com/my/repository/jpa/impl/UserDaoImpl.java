package com.my.repository.jpa.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.my.entity.Users;
import com.my.repository.jpa.UserDao;

//@Repository
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Iterable<Users> findAll(Sort sort) {

		return null;
	}

	@Override
	public Page<Users> findAll(Pageable pageable) {

		return null;
	}

	@Override
	public <S extends Users> S save(S entity) {

		return null;
	}

	@Override
	public <S extends Users> Iterable<S> save(Iterable<S> entities) {

		return null;
	}

	@Override
	public Users findOne(Long id) {

		return null;
	}

	@Override
	public boolean exists(Long id) {

		return false;
	}

	@Override
	public Iterable<Users> findAll() {

		return null;
	}

	@Override
	public Iterable<Users> findAll(Iterable<Long> ids) {

		return null;
	}

	@Override
	public long count() {

		return 0;
	}

	@Override
	public void delete(Long id) {

	}

	@Override
	public void delete(Users entity) {

	}

	@Override
	public void delete(Iterable<? extends Users> entities) {

	}

	@Override
	public void deleteAll() {

	}

	@Override
	public Users findOne(Specification<Users> spec) {

		return null;
	}

	@Override
	public List<Users> findAll(Specification<Users> spec) {

		return null;
	}

	@Override
	public Page<Users> findAll(Specification<Users> spec, Pageable pageable) {

		return null;
	}

	@Override
	public List<Users> findAll(Specification<Users> spec, Sort sort) {

		return null;
	}

	@Override
	public long count(Specification<Users> spec) {

		return 0;
	}

	@Override
	public Users findByName(String name) {

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Users findByLoginName(String loginName) {
		List<Users> result = em
				.createQuery("from User u where u.username=:username")
				.setParameter("username", loginName).getResultList();
		if (result != null && result.size() > 0)
			return result.get(0);
		else
			return null;
	}
}
