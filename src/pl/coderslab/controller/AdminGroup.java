package pl.coderslab.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.Group;
import pl.coderslab.util.DbUtil;

/**
 * Servlet implementation class AdminGroup
 */
@WebServlet("/AdminGroup")
public class AdminGroup extends HttpServlet {
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
				Group group = Group.loadGroupById(conn, id);
				request.setAttribute("group", group);
				getServletContext().getRequestDispatcher("/WEB-INF/views/adminEditGroup.jsp").forward(request,
						response);
			} else if (action.equals("add")) {
				getServletContext().getRequestDispatcher("/WEB-INF/views/adminAddGroup.jsp").forward(request, response);
			} else if (action.equals("del")) {
				int id = Integer.parseInt(request.getParameter("id"));
				Group group = Group.loadGroupById(conn, id);
				group.delete(conn);
				response.sendRedirect("AdminGroup");
			} else {
				Group[] groups = Group.loadAllGroups(conn);
				request.setAttribute("groups", groups);
				getServletContext().getRequestDispatcher("/WEB-INF/views/adminViewGroup.jsp").forward(request,
						response);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			response.sendRedirect("AdminGroup");
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
				// new element
				Group group = new Group(request.getParameter("name"));
				group.saveToDB(conn);
			} else {
				// edit element
				int id = Integer.parseInt(request.getParameter("id"));
				Group group = Group.loadGroupById(conn, id);
				group.setName(request.getParameter("name"));
				group.saveToDB(conn);
			}
		} catch (Exception e) {

		}
		response.sendRedirect("AdminGroup");
	}
}
