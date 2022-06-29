package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

public class BookDAOTest extends BaseDAOTest{

	private static BookDAO bookDAO;
	
	@BeforeClass
	public static void setUpClass(){
		BaseDAOTest.setUpClass();
		bookDAO = new BookDAO(entityManager);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}
	
	@Ignore
	@Test
	public void testCreateBook() throws ParseException, IOException {
		Book newBook = new Book();
		Category category = new Category("Advanced Java");
		category.setCategoryId(2);
		newBook.setCategory(category);
		newBook.setTitle("Spring in Action: Covers Spring 4");
		newBook.setDescription("Spring in Action, Fourth Edition is a hands-on guide to the Spring Framework, updated for version 4.");
		newBook.setAuthor("Craig Walls");
		newBook.setPrice(33.99f);
		newBook.setIsbn("161729120X");
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("11/28/2014"); 
		newBook.setPublishDate(publishDate);
		
		String imagePath = "C:\\Users\\Yulia\\Desktop\\books\\Spring in Action.JPG";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		newBook.setImage(imageBytes);
		Book createdBook = bookDAO.create(newBook);
		
		assertTrue(createdBook.getIsbn() != null);
	}
	
	@Ignore
	@Test
	public void testUpdateBook() throws ParseException, IOException {
		Book existBook = new Book();
		existBook.setBookId(1);
		Category category = new Category();
		category.setCategoryId(1);
		existBook.setCategory(category);
		existBook.setTitle("Effective Java (3nd Edition)");
		existBook.setDescription("New coverage of generics, enums, annotations, autoboxing");
		existBook.setAuthor("Joshua Bloch");
		existBook.setPrice(40f);
		existBook.setIsbn("0321256683");
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2008"); 
		existBook.setPublishDate(publishDate);
		
		String imagePath = "C:\\Users\\Yulia\\Desktop\\books\\Effective java.JPG";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
		existBook.setImage(imageBytes);
		Book updatedBook = bookDAO.update(existBook);
		
		assertEquals(existBook.getTitle(),updatedBook.getTitle());
	}

	@Ignore
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteBookFail() {
		Integer bookId = 100;
		bookDAO.delete(bookId);
		
		assertTrue(true);
	}
	
	@Ignore
	@Test
	public void testDeleteBookSuccess() {
		Integer bookId = 1;
		bookDAO.delete(bookId);
		
		assertTrue(true);
	}
	
	@Ignore
	@Test
	public void testGetBookFail() {
		Integer bookId = 99;
		Book book = bookDAO.get(bookId);
		
		assertNull(book);
	}
	
	@Ignore
	@Test
	public void testGetBookSuccess() {
		Integer bookId = 4;
		bookDAO.get(bookId);
		
		assertNotNull(bookId);
	}
	
	@Ignore
	@Test
	public void testListAll() {
		List<Book> listBooks = bookDAO.listAll();
		for(Book aBook : listBooks) {
			System.out.println(aBook.getTitle());
		}
		assertFalse(listBooks.isEmpty());
	}
	
	@Ignore
	@Test
	public void testFindByTitleNotExist() {
		String title = "Thinking in Java";
		Book book = bookDAO.findByTitle(title);
		
		assertNull(book);
	}
	
	@Ignore
	@Test
	public void testFindByTitleExist() {
		String title = "Effective Java (2nd Edition)";
		Book book = bookDAO.findByTitle(title);
		System.out.println(book.getTitle());
		assertNotNull(book);
	}
	
	@Ignore
	@Test
	public void testCount() {
		
		long totalBooks = bookDAO.count();
		
		assertEquals(2,totalBooks);
	}
	@Ignore
	@Test
	public void testListByCategory() {
		int categoryId = 2;
		List<Book> books = bookDAO.listByCategory(categoryId);
		assertTrue(books.size() > 0);
	}
	
	@Test
	public void testSearchBookInTitle() {
		String keyword = "Java";
		List<Book> result = bookDAO.search(keyword);
		
		for(Book aBook : result) {
			System.out.println(aBook.getTitle());
		}
		
		assertEquals(6,result.size());
	}
	
	@Test
	public void testSearchBookInAuthor() {
		String keyword = "Sierra";
		List<Book> result = bookDAO.search(keyword);
		
		for(Book aBook : result) {
			System.out.println(aBook.getTitle());
		}
		
		assertEquals(2,result.size());
	}
	
	@Test
	public void testSearchBookInDescription() {
		String keyword = "Generic";
		List<Book> result = bookDAO.search(keyword);
		
		for(Book aBook : result) {
			System.out.println(aBook.getTitle());
		}
		
		assertEquals(2,result.size());
	}
	
	@Test
	public void testListNewBooks() {
		List<Book> listNewBooks = bookDAO.listNewBooks();
		for(Book aBook: listNewBooks) {
			System.out.println(aBook.getTitle() + " " + aBook.getAuthor() + " " + aBook.getPrice());
		}
		assertEquals(4, listNewBooks.size());
	}
}
