package com.bookstore.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.entity.Review;

public class ReviewDAO extends JpaDAO<Review> implements GenericDAO<Review> {

	public ReviewDAO(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public Review get(Object reviewId) {
		return super.find(Review.class, reviewId);
	}

	@Override
	public void delete(Object reviewId) {
		super.delete(Review.class, reviewId);
	}

	@Override
	public List<Review> listAll() {
		return super.findWithNamedQuery("Review.listAll");
	}
	
	@Override
	public long count() {
		return super.countWithNamedQuery("Review.countALL");
	}

	@Override
	public Review create(Review review) {
		review.setReviewTime(new Date());
		return super.create(review);
	}
}
