package pl.coderslab.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.Solution;
import pl.coderslab.model.User;
import pl.coderslab.util.DbUtil;

/**
 * Servlet implementation class UserInfo
 */
@WebServlet("/UserInfo")
public class UserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getParameter("id")!=null){
			try{
				int id = Integer.parseInt(request.getParameter("id"));
				Connection con = DbUtil.getConnection();
				User user = User.loadUserById(con, id);
				request.setAttribute("user", user);
				Solution[] solutions = Solution.loadAllByUserId(con, id);
				request.setAttribute("solutions", solutions);
				getServletContext().getRequestDispatcher("/WEB-INF/views/userInfo.jsp").forward(request, response);
			}catch(Exception e){
				response.sendRedirect("AllGroups");
			}
		}else{
			response.sendRedirect("AllGroups");
}
	}

}
