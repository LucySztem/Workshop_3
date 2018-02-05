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
 * Servlet implementation class AllGroups
 */
@WebServlet("/AllGroups")
public class AllGroups extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			Connection con = DbUtil.getConnection();
			Group[] groups = Group.loadAllGroups(con);
			request.setAttribute("groups", groups);
			getServletContext().getRequestDispatcher("/WEB-INF/views/allGroups.jsp").forward(request, response);
		}catch(Exception e){
			
}
	}

}
