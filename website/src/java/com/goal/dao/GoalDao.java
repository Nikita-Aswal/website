package com.goal.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.goal.model.Goal;
import com.user.dao.UserDao;

public class GoalDao {
    
	private String jdbcURL = "jdbc:mysql://localhost:30006/userdata";
	private String jdbcUserName="root";
	private String jdbcPassword="root";
	
	
	
public GoalDao() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	public Connection getConnection()
	{
		Connection connection=null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
     		connection=DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
     		
		}
		catch(SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return connection;
	}





    // Create a new goal
    public void createGoal(int userId, String goalDescription) {
        String sql = "INSERT INTO Goals (user_id, goal_description) VALUES (?, ?)";
        GoalDao dao=new GoalDao();
        try (Connection connection = dao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, goalDescription);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get a goal by its ID
    public Goal getGoal(int goalId) {
        String sql = "SELECT * FROM Goals WHERE goal_id = ?";
        GoalDao dao=new GoalDao();
        try (Connection connection = dao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, goalId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Goal(
                    rs.getInt("goal_id"),
                    rs.getString("title"),
                    rs.getString("goal_description"),
                    rs.getTimestamp("created_at"),
                    rs.getTimestamp("updated_at")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
 // Get all goals for a specific user
    public List<Goal> getGoalsByUser (int userId) {
        List<Goal> goals = new ArrayList<>();
        String sql = "SELECT * FROM Goals WHERE user_id = ?";
        GoalDao dao=new GoalDao();
        try (Connection connection = dao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                goals.add(new Goal(
                    rs.getInt("goal_id"),
                    rs.getString("title"),
                    rs.getString("goal_description"),
                    rs.getTimestamp("created_at"),
                    rs.getTimestamp("updated_at")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goals;
    }
}

    
    
    
    
    
    