package pl.coderslab.model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import pl.coderslab.util.DbUtil;

public class Solution {
	private int id;
	private Date created;
	private Date updated;
	private int exercise_id;
	private int user_id;
	private String description;

	public Solution(Date created, Date updated, String description) {
		this.created = created;
		this.updated = updated;
		this.description = description;
	}

	public Solution() {
	}

	public int getId() {
		return id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public int getExercise_id() {
		return exercise_id;
	}

	public void setExercise_id(int exercise_id) {
		this.exercise_id = exercise_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public User getUser() throws SQLException {

		Connection conn = DbUtil.getConnection();
		User user = User.loadUserById(conn, this.user_id);
		return user;

	}
	
	public Exercise getExercise() throws SQLException { // ta metoda jest do wyciagania excercise id

		Connection conn = DbUtil.getConnection();
		Exercise exer = Exercise.loadExerciseById(conn, this.exercise_id);
		return exer;

	}

	static public Solution[] loadAllSolutions(Connection conn) throws SQLException {

		ArrayList<Solution> solutions = new ArrayList<Solution>();
		String sql = "SELECT * FROM solutions";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Solution loadedSolutions = new Solution();
			loadedSolutions.id = resultSet.getInt("id");
			loadedSolutions.created = resultSet.getDate("created");
			loadedSolutions.updated = resultSet.getDate("updated");
			loadedSolutions.exercise_id = resultSet.getInt("excercise_id");
			loadedSolutions.user_id = resultSet.getInt("user_id");
			loadedSolutions.description = resultSet.getString("description");
			solutions.add(loadedSolutions);
		}
		Solution[] sArray = new Solution[solutions.size()];
		sArray = solutions.toArray(sArray);
		return sArray;
	}
	
	static public Solution[] loadAllSolutions(Connection conn, int limit) throws SQLException {

		ArrayList<Solution> solutions = new ArrayList<Solution>();
		String sql = "SELECT * FROM solutions ORDER BY created DESC LIMIT ? ;";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1,  limit);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		
		while (resultSet.next()) {
			Solution loadedSolutions = new Solution();
			loadedSolutions.id = resultSet.getInt("id");
			loadedSolutions.created = resultSet.getDate("created");
			loadedSolutions.updated = resultSet.getDate("updated");
			loadedSolutions.exercise_id = resultSet.getInt("excercise_id");
			loadedSolutions.user_id = resultSet.getInt("user_id");
			loadedSolutions.description = resultSet.getString("description");
			solutions.add(loadedSolutions);
		}
		Solution[] sArray = new Solution[solutions.size()];
		sArray = solutions.toArray(sArray);
		return sArray;
	}

	static public Solution loadSolutionById(Connection conn, int id) throws SQLException {

		String sql = "SELECT * FROM solutions where id=?";
		PreparedStatement preparedStatement;
		preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			Solution loadedSolution = new Solution();
			loadedSolution.id = resultSet.getInt("id");
			loadedSolution.created = resultSet.getDate("created");
			loadedSolution.updated = resultSet.getDate("updated");
			loadedSolution.exercise_id = resultSet.getInt("exercise_id");
			loadedSolution.user_id = resultSet.getInt("user_id");
			loadedSolution.description = resultSet.getString("description");
			return loadedSolution;
		}
		return null;
	}

	public void delete(Connection conn) throws SQLException {

		if (this.id != 0) {

			String sql = "DELETE FROM solutions WHERE id= ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, this.id);
			preparedStatement.executeUpdate();
			this.id = 0;
		}
	}

	public void saveToDB(Connection conn) throws SQLException {
		if (this.id == 0) {
			String sql = "INSERT INTO solutions(created, updated, exercise_id, user_id, description) VALUES (?, ?, ?, ?, ?)";
			String generatedColumns[] = { "ID" };
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql, generatedColumns);
			preparedStatement.setDate(1, (java.sql.Date) this.created);
			preparedStatement.setDate(2, (java.sql.Date) this.updated);
			preparedStatement.setInt(3, this.exercise_id);
			preparedStatement.setInt(4, this.user_id);
			preparedStatement.setString(5, this.description);
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
		} else {
			String sql = "UPDATE solutions SET created=?, updated=?, exercise_id=?, user_id=?, description=? where id = ?";
			PreparedStatement preparedStatement;
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setDate(1, (java.sql.Date) this.created);
			preparedStatement.setDate(2, (java.sql.Date) this.updated);
			preparedStatement.setInt(3, this.exercise_id);
			preparedStatement.setInt(4, this.user_id);
			preparedStatement.setString(5, this.description);
			preparedStatement.setInt(6, this.id);
			preparedStatement.executeUpdate();
		}
	}
	public static Solution[] loadAllByUserId(Connection con, int id) throws SQLException{
		ArrayList<Solution> solutions = new ArrayList<Solution>();
		String query = "SELECT * FROM solutions WHERE user_id= ?";
		PreparedStatement ps;
		ps = con.prepareStatement(query);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Solution loaded = new Solution();
			loaded.id = rs.getInt("id");
			loaded.exercise_id = rs.getInt("excercise_id");
			loaded.user_id = rs.getInt("user_id");
			loaded.description = rs.getString("description");
			loaded.created = rs.getDate("created");
			loaded.updated = rs.getDate("updated");
			solutions.add(loaded);
		}
		Solution[] sArray = new Solution[solutions.size()];
		sArray = solutions.toArray(sArray);
		return sArray;
}

}
