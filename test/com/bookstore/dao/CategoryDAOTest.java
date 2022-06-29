package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;

public class CategoryDAOTest extends BaseDAOTest {

	private static CategoryDAO categoryDAO;

	@BeforeClass
	public static void setUpClass() {
		BaseDAOTest.setUpClass();
		categoryDAO = new CategoryDAO(entityManager);
	}

	@Test
	public void testCreateCategory() {
		Category newCat = new Category(8,"Paper");
		Category category = categoryDAO.create(newCat);
		assertEquals(category.getName(),newCat.getName());
	}
	
	@Test
	public void testUpdateCategory() {
		Integer catId = 39;
		Category cat = categoryDAO.get(catId);
		cat.setCategoryName("News");
		Category category = categoryDAO.update(cat);
		assertEquals(cat.getName(),category.getName());
	}
	
	@Test
	public void testGet() {
		Integer catId = 7;
		Category cat = categoryDAO.get(catId);
		assertNotNull(cat);
	}


	@Test
	public void testDeleteCategory() {
		Integer catId = 6;
		categoryDAO.delete(catId);
		Category cat = categoryDAO.get(catId);
		assertNull(cat);
	}
	
	@Test
	public void testListAll() {
		List<Category> listCategory = categoryDAO.listAll();
		listCategory.forEach(c -> System.out.println(c.getName() + " "));
		assertTrue(listCategory.size() > 0);
	}
	
	@Test
	public void testCount() {
		long totalCategories = categoryDAO.count();
		assertEquals(5, totalCategories);
	}
	
	@Test
	public void testFindByName() {
		String name = "Paper";
		Category category = categoryDAO.findByName(name);
		assertNotNull(category);
	}
	
	@Test
	public void testNotfoundByName() {
		String name = "Java5";
		Category category = categoryDAO.findByName(name);
		assertNull(category);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}
}
