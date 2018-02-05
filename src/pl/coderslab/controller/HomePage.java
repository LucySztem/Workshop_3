package pl.coderslab.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.Solution;

import pl.coderslab.util.DbUtil;

/**
 * Servlet implementation class HomePage
 */
@WebServlet("/")
public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			Integer limit = Integer.parseInt(getServletContext().getInitParameter("number-solutions"));
			request.setAttribute("limit", limit);
			Connection conn = DbUtil.getConnection();
			Solution[] solutions = Solution.loadAllSolutions(conn);

			request.setAttribute("solutions", solutions); // dzieki temu
															// wrzucamy do jsp

			getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
