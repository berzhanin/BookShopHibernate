package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

public class UserDAOTest extends BaseDAOTest{

	private static UsersDAO userDAO;

	@BeforeClass
	public static void setUpClass() {
		BaseDAOTest.setUpClass();
		userDAO = new UsersDAO(entityManager);
	}

	@Test
	public void testCreateUsers() {
		Users user1 = new Users();
		user1.setEmail("test.email@gmail.com");
		user1.setFullName("Test Test");
		user1.setPassword("tst");
		user1 = userDAO.create(user1);
		assertTrue(user1.getUserId() > 0);
	}

	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldsNotSet() {
		Users user1 = new Users();
		user1 = userDAO.create(user1);
	}

	@Test
	public void testUpdateUsers() {
		Users user = new Users();
		user.setUserId(12);
		user.setEmail("test.email@apache.net");
		user.setFullName("Test person");
		user.setPassword("mysecret");
		user = userDAO.update(user);
		String expected = "mysecret";
		String actual = user.getPassword();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetUsersFound() {
		Integer userId = 12;
		Users user = userDAO.get(userId);
		if (user != null) {
			System.out.println(user.getEmail());
		}
		assertNotNull(user);
	}

	@Test
	public void testGetUsersNotFound() {
		Integer userId = 99;
		Users user = userDAO.get(userId);
		assertNull(user);
	}

	@Test
	public void testDeleteUsers() {
		Integer userId = 11;
		userDAO.delete(userId);
		userDAO.get(userId);
		assertNull(userId);
	}

	@Test(expected = EntityNotFoundException.class)
	public void testDeleteNonExistUsers() {
		Integer userId = 55;
		userDAO.delete(userId);
	}

	@Test
	public void testListAll() {
		List<Users> listUsers = userDAO.listAll();
		for (Users user : listUsers) {
			System.out.println(user.getEmail());
		}
		assertTrue(listUsers.size() > 0);
	}

	@Test
	public void testCountAll() {
		long totalUsers = userDAO.countWithNamedQuery("Users.countAll");
		System.out.println("Count of users: " + totalUsers);
		assertEquals(4, totalUsers);
	}
	

	@Test
	public void testFindByEmail() {
		String email = "amazon@yahoo.com";
		Users user = userDAO.findByEmail(email);
		//System.out.println("FindByEmal:" + user.getEmail());
		assertNotNull(user);
	}
	
	@Test
	public void testCheckLoginSuccess() {
		String email = "epam@gmail.com";
		String password = "superSecret";
		boolean result = userDAO.checkLogin(email, password);
		assertTrue(result);
	}
	
	@Test
	public void testCheckLoginFailed() {
		String email = "11111@gmail.com";
		String password = "12321321321";
		boolean result = userDAO.checkLogin(email, password);
		assertFalse(result);
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}

}
