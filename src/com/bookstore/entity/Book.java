package com.bookstore.entity;

import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "book", catalog = "bookstoredb", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
@NamedQueries({
	@NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b"),
	@NamedQuery(name = "Book.findByTitle", query = "SELECT b FROM Book b WHERE b.title = :title"),
	@NamedQuery(name = "Book.countAll", query = "SELECT COUNT(*) FROM Book b"),
	@NamedQuery(name = "Book.findByCategory", query = "SELECT b FROM Book b JOIN "
			+ "Category c ON b.category.categoryId = c.categoryId AND c.categoryId = :catId"),
	@NamedQuery(name = "Book.listNew", query = "SELECT b FROM Book b ORDER BY b.publishDate DESC"),
	@NamedQuery(name = "Book.search", query = "SELECT b FROM Book b WHERE b.title LIKE '%' || :keyword || '%'"
				+ " OR b.author LIKE '%' || :keyword || '%'"
				+ " OR b.description LIKE '%' || :keyword || '%'"),
})
public class Book implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int bookId;
	private Category category;
	private String title;
	private String author;
	private String description;
	private String isbn;
	private byte[] image;
	private String base64image;
	private float price;
	private Date publishDate;
	private Date updatedOn;
	private Set<Review> reviews = new HashSet<Review>(0);
	private Set<OrderDetail> orderDetails = new HashSet<OrderDetail>(0);

	public Book() {
	}

	public Book(int bookId, Category category, String title, String author, String description, String isbn,
			byte[] image, float price, Date publishDate, Date updatedOn) {
		this.bookId = bookId;
		this.category = category;
		this.title = title;
		this.author = author;
		this.description = description;
		this.isbn = isbn;
		this.image = image;
		this.price = price;
		this.publishDate = publishDate;
		this.updatedOn = updatedOn;
	}

	public Book(int bookId, Category category, String title, String author, String description, String isbn,
			byte[] image, float price, Date publishDate, Date updatedOn, Set<Review> reviews,
			Set<OrderDetail> orderDetails) {
		this.bookId = bookId;
		this.category = category;
		this.title = title;
		this.author = author;
		this.description = description;
		this.isbn = isbn;
		this.image = image;
		this.price = price;
		this.publishDate = publishDate;
		this.updatedOn = updatedOn;
		this.reviews = reviews;
		this.orderDetails = orderDetails;
	}

	@Id
	@Column(name = "book_id", unique = true, nullable = false)
	public int getBookId() {
		return this.bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(name = "title", unique = true, nullable = false, length = 128)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "author", nullable = false, length = 64)
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "description", nullable = false, length = 16777215)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "isbn", nullable = false, length = 15)
	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Column(name = "image", nullable = false)
	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Column(name = "price", nullable = false, precision = 12, scale = 0)
	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "publish_date", nullable = false, length = 10)
	public Date getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_on", nullable = false, length = 19)
	public Date getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
	public Set<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
	public Set<OrderDetail> getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	@Transient
	public String getBase64Image() {
		this.base64image = Base64.getEncoder().encodeToString(this.image);
		return base64image;
	}
	
	@Transient
	public void setBase65Image(String base64image) {
		this.base64image = base64image;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (bookId != other.bookId)
			return false;
		return true;
	}
	
	
}
