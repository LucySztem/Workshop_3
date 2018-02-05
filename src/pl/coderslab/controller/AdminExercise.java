package pl.coderslab.controller;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.Exercise;
import pl.coderslab.model.Solution;
import pl.coderslab.util.DbUtil;

/**
 * Servlet implementation class AdminExercise
 */
@WebServlet("/AdminExercise")
public class AdminExercise extends HttpServlet {
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
				Exercise exercise = Exercise.loadExerciseById(conn, id);
				request.setAttribute("exercise", exercise);
				getServletContext().getRequestDispatcher("/WEB-INF/views/adminEditExercise.jsp").forward(request,
						response);

			} else if (action.equals("add")) {
				getServletContext().getRequestDispatcher("/WEB-INF/views/adminAddExercise.jsp").forward(request,
						response);
			} else if (action.equals("del")) {
				int id = Integer.parseInt(request.getParameter("id"));
				Exercise ex = Exercise.loadExerciseById(conn, id);
				ex.delete(conn);
				response.sendRedirect("AdminExercise");
			}
		} catch (Exception e) {
			response.sendRedirect("AdminExercise");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Connection con = DbUtil.getConnection();
			if (request.getParameter("add") != null) { // nowy element

				Exercise exercise = new Exercise();
				exercise.setTitle(request.getParameter("title"));
				exercise.setDescription(request.getParameter("description"));
				exercise.saveToDB(con);
			} else if (request.getParameter("edit") != null) { // edycja

				int id = Integer.parseInt(request.getParameter("id"));
				Exercise exercise = Exercise.loadExerciseById(con, id);
				exercise.setTitle(request.getParameter("title"));
				exercise.setDescription(request.getParameter("description"));
				exercise.saveToDB(con);
			} else if (request.getParameter("assign") != null) { 
											 /* przydzielamy zadania użytkownikom
											 */

				int exId = Integer.parseInt(request.getParameter("id"));
				if (request.getParameter("solution") != null) {
					for (String rawUserId : request.getParameterValues("solution")) {
						int userId = Integer.parseInt(rawUserId);
						Solution solution = new Solution();
						java.sql.Date date = java.sql.Date.valueOf(LocalDate.now());
						solution.setCreated(date);
						solution.setExercise_id(exId);
						solution.setUser_id(userId);
						solution.saveToDB(con);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("AdminExercise");
	}

}
