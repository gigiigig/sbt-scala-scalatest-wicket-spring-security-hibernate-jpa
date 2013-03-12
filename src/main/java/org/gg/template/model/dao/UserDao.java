package org.gg.template.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.gg.template.model.Authority;
import org.gg.template.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public User findByEmail(String email) {
		// assuming that you have a User class that implements UserDetails
		try {
			return entityManager
			    .createQuery("from User where email = :email", User.class)
			    .setParameter("email", email).getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}

	public Authority findAuthority(String authority) {
		try {
			return entityManager
			    .createQuery("from Authority where authority = :autohrity",
			        Authority.class).setParameter("authority", authority)
			    .getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public List<User> findAll() {
		return entityManager.createQuery("from User", User.class).getResultList();
	}

	public List<User> findAllSortedBy(String sort, String order, long from,
	    long to) {
		return entityManager
		    .createQuery("from User u order by " + sort + " " + order, User.class)
		    // .setParameter("sort", sort)
		    .setFirstResult((int) from).setMaxResults((int) to).getResultList();
	}

	@Transactional
	public boolean save(User user) {

		for (Authority authority : user.getAuthorities()) {
			Authority temp;
			if ((temp = findAuthority(authority.getAuthority())) != null) {
				authority = temp;
			}
		}

		entityManager.merge(user);
		return true;

	}

	@Transactional
	public boolean save(Authority authority) {
		entityManager.merge(authority);
		return true;

	}

}
