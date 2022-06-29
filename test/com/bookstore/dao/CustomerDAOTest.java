package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.bookstore.entity.Customer;

public class CustomerDAOTest extends BaseDAOTest {
	
	private static CustomerDAO customerDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		CustomerDAOTest.setUpClass();
		customerDAO = new CustomerDAO(entityManager);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception{
		CustomerDAOTest.tearDownAfterClass();
	}
	

	@Ignore
	@Test
	public void testCreateCustomer() {
		Customer customer = new Customer();
		customer.setEmail("jim.bean@gmail.com");
		customer.setFullname("Jim Bean");
		customer.setCity("London");
		customer.setCountry("England");
		customer.setAdress("99 Averbrook street");
		customer.setPassword("secret");
		customer.setPhone("+4555001900");
		customer.setZipCode("45000");
		
		Customer savedCustomer =  customerDAO.create(customer);
		
		assertTrue(savedCustomer.getEmail() != null);
	}
	
	@Ignore
	@Test
	public void testGet() {
		Integer customerId = 6;
		Customer customer = customerDAO.get(customerId);
		
		assertNotNull(customer);
	}
	
	@Ignore
	@Test
	public void testUpdateCustomer() {
		Integer customerId = 6;
		Customer customer = customerDAO.get(customerId);
		String fullName = "Tom Tom Tom";
		customer.setFullname(fullName);
		Customer updatedCustomer = customerDAO.update(customer);
		
		assertTrue(updatedCustomer.getFullname().equals(fullName));
	}
	
	@Ignore
	@Test
	public void testDeleteCustomer() {
		Integer customerId = 6;
		customerDAO.delete(customerId);
		Customer customer = customerDAO.get(customerId);
		
		assertNull(customer);
	}
	
	
	@Test
	public void testListAll() {
		List<Customer> listCustomers = customerDAO.listAll();  

		assertFalse(listCustomers.isEmpty());
	}
	
	@Test
	public void testCountAll() {
		int totalCustomers = (int) customerDAO.count();  

		assertEquals(4, totalCustomers);
	}
	
	@Test
	public void testfindByEmail() {
		String email = "sam@gmail.com";
		Customer customer = customerDAO.findByEmail(email);  

		assertNotNull(customer);
	}
	
	@Test
	public void checkLoginSuccess() {
		String email = "epam@web.com";
		String password = "123456";
		
		Customer customer = customerDAO.checkLogin(email, password);
		
		assertNotNull(customer);
	}
	
	@Test
	public void checkLoginFail() {
		String email = "epam@web.com";
		String password = "999";
		
		Customer customer = customerDAO.checkLogin(email, password);
		
		assertNull(customer);
	}
}
