package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.dao.UsersDAO;
import com.bookstore.entity.Users;

public class UserServices {
	private UsersDAO usersDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public UserServices(EntityManager entityManager,
			HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		usersDAO = new UsersDAO(entityManager);
	}

	public void listUser() throws ServletException, IOException {

		listUser(null);
	}

	public void listUser(String message) throws ServletException, IOException {
		List<Users> listUsers = usersDAO.listAll();

		request.setAttribute("listUsers", listUsers);

		if (message != null) {
			request.setAttribute("message", message);
		}
		String listPage = "user_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);

		requestDispatcher.forward(request, response);
	}

	public void createUser() throws ServletException, IOException {

		String email = request.getParameter("email");
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");

		Users existUser = usersDAO.findByEmail(email);

		if (existUser != null) {

			String message = "Could not create user. A user with email " + email + " already exist.";
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
			dispatcher.forward(request, response);
		} else {
			Users newUser = new Users(email, fullname, password);
			usersDAO.create(newUser);
			listUser("New user created successfully");
		}

	}

	public void editUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));
		Users user = usersDAO.get(userId);

		String editPage = "user_form.jsp";
		request.setAttribute("user", user);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}

	public void updateUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullname");
		String password = request.getParameter("password");

		Users userById = usersDAO.get(userId);

		Users userByEmail = usersDAO.findByEmail(email);

		if (userByEmail != null && userByEmail.getUserId() != userById.getUserId()) {
			String message = "Could not update user. User with email: " + email + " already exist.";
			request.setAttribute("message", message);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("message.jsp");
			requestDispatcher.forward(request, response);
		} else {

			Users user = new Users(userId, email, fullName, password);
			usersDAO.update(user);
			String message = "User has been updated successfully";
			listUser(message);
		}
	}

	public void deleteUser() throws ServletException, IOException {
		
		int userId = Integer.parseInt(request.getParameter("id"));
		usersDAO.delete(userId);
		
		String message = "User has been deleted successfully";
		listUser(message);
	}

	public void login() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		boolean loginResult = usersDAO.checkLogin(email, password);
		
		if(loginResult) {
			System.out.println("User ist authenticated");
			request.getSession().setAttribute("useremail", email);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/");
			dispatcher.forward(request, response);
		} else {
			String message = "Login failed!";
			request.setAttribute("message", message);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	}
}
