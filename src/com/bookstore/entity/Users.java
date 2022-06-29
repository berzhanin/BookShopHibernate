package com.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "users", catalog = "bookstoredb", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@NamedQueries({
	@NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u ORDER BY u.userId"),
	@NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
	@NamedQuery(name = "Users.countAll", query = "SELECT Count(*) FROM Users u"),
	@NamedQuery(name = "Users.checkLogin", query = "SELECT u FROM Users u WHERE u.email = :email AND u.password = :password")
})
public class Users implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String email;
	private String password;
	private String fullName;

	public Users() {
	}

	public Users(String email, String fullName, String password) {
		this.email = email;
		this.fullName = fullName;
		this.password = password;
	}

	public Users(Integer userId, String email, String fullName, String password) {
		this(email,fullName,password);
		this.userId = userId;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "email", unique = true, nullable = false, length = 30)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", nullable = false, length = 16)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "full_name", nullable = false, length = 30)
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
