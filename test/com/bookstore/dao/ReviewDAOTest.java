package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewDAOTest extends BaseDAOTest{
	
	private static ReviewDAO reviewDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ReviewDAOTest.setUpClass();
		reviewDao = new ReviewDAO(entityManager);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		ReviewDAOTest.tearDownAfterClass();
	}

	@Test
	public void testGet() {
		Integer reviewId = 100;
		Review review = reviewDao.get(reviewId);
		
		assertNotNull(review);
	}
	
	@Test
	public void testDeleteObject() {
		int reviewId = 100;
		reviewDao.delete(reviewId);
		
		Review review = reviewDao.get(reviewId);
		
		assertNull(review);
	}

	@Test
	public void testListAll() {
		List<Review> listReview = reviewDao.listAll();
		
		assertTrue(listReview.size() > 0);
	}

	@Test
	public void testCount() {
		long totalReviews = reviewDao.count();
		
		assertEquals(totalReviews, 2);
	}

	@Test
	public void testCreateReview() {
		 Review review = new Review();
		 //review.setReviewId(100);
		 Book book = new Book();
		 book.setBookId(16);
		 
		 Customer customer = new Customer();
		 customer.setCustomerId(11);
		 
		 review.setBook(book);
		 review.setCustomer(customer);
		 
		 review.setHeadline("trulalal");
		 review.setRating(5);
		 review.setComment("coooooommmmmmeeeeeet");
		 
		 Review savedReview = reviewDao.create(review);
		 
		 assertTrue(savedReview.getHeadline() != null);
	}

	@Test
	public void testUpdateReview() {
		Integer reviewId = 101;
		Review review = reviewDao.get(reviewId);
		review.setHeadline("Excellent book");
		
		Review updatedReview = reviewDao.update(review);
		
		assertEquals(review.getHeadline(),updatedReview.getHeadline());
	}
}
