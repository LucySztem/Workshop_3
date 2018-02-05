package pl.coderslab.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.Group;
import pl.coderslab.model.User;
import pl.coderslab.util.DbUtil;

/**
 * Servlet implementation class AdminUsers
 */
@WebServlet("/AdminUsers")
public class AdminUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String action = request.getParameter("action");
			if (action == null) {
				action = "";
			}
			Connection conn = DbUtil.getConnection();
			if (action.equals("edit")) {
				int id = Integer.parseInt(request.getParameter("id"));
				User user = User.loadUserById(conn, id);
				request.setAttribute("user", user);
				Group[] groups = Group.loadAllGroups(conn);
				request.setAttribute("groups", groups);
				getServletContext().getRequestDispatcher("/WEB-INF/views/adminEditUser.jsp").forward(request, response);
			} else if (action.equals("add")) {
				Group[] groups = Group.loadAllGroups(conn);
				request.setAttribute("groups", groups);
				getServletContext().getRequestDispatcher("/WEB-INF/views/adminAddUser.jsp").forward(request, response);
			} else if (action.equals("del")) {
				int id = Integer.parseInt(request.getParameter("id"));
				User user = User.loadUserById(conn, id);
				user.delete(conn);
				response.sendRedirect("AdminUsers");
			} else {
				User[] users = User.loadAllUsers(conn);
				request.setAttribute("users", users);
				getServletContext().getRequestDispatcher("/WEB-INF/views/adminViewUser.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			response.sendRedirect("AdminUsers");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Connection conn = DbUtil.getConnection();
			if (request.getParameter("id") == null) {		
				User user = new User();
				user.setEmail(request.getParameter("email"));
				user.setPassword(request.getParameter("password"));
				user.setUsername(request.getParameter("username"));
				user.setUser_group_id(Integer.parseInt(request.getParameter("group")));
				user.saveToDB(conn);
			} else {				
				int id = Integer.parseInt(request.getParameter("id"));
				User user = User.loadUserById(conn, id);
				user.setEmail(request.getParameter("email"));
				user.setPassword(request.getParameter("password"));
				user.setUsername(request.getParameter("username"));
				user.setUser_group_id(Integer.parseInt(request.getParameter("group")));
				user.saveToDB(conn);
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
		response.sendRedirect("AdminUsers");

	}

}
