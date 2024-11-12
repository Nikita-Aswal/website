package com.time.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import com.time.model.Time;


public class TimeDao {
    
	private String jdbcURL = "jdbc:mysql://localhost:30006/userdata";
	private String jdbcUserName="root";
	private String jdbcPassword="root";
	
	
	
	public TimeDao() {
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
	
	 // Create a new time entry
    public void createTimeEntry(int userId, Timestamp startTime, Timestamp endTime, String description) {
    	
    	TimeDao dao=new TimeDao();
        String sql = "INSERT INTO time_entries (user_id, start_time, end_time, description) VALUES (?, ?, ?, ?)";
        try (Connection connection = dao.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setTimestamp(2, startTime);
            stmt.setTimestamp(3, endTime);
            stmt.setString(4, description);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
     // Get a time entry by its ID
        public Time getTimeEntries(int entryId) {
        	
        	TimeDao dao=new TimeDao();
            String sql = "SELECT * FROM time_entries WHERE entry_id = ?";
            try (Connection connection = dao.getConnection();
            		PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, entryId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return new Time(
                        rs.getInt("entry_id"),
                        rs.getTimestamp("start_time"),
                        rs.getTimestamp("end_time"),
                        rs.getString("description"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
        // Get all time entries for a specific user
        public List<Time> getTimeEntriesByUser (int userId) {
            List<Time> timeEntries = new ArrayList<>();
            TimeDao dao=new TimeDao();
            String sql = "SELECT * FROM time_entries WHERE user_id = ?";
            try (Connection connection = dao.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {
            	stmt.setInt(1, userId);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    timeEntries.add(new Time(
                        rs.getInt("user_id"),
                        rs.getTimestamp("start_time"),
                        rs.getTimestamp("end_time"),
                        rs.getString("description"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                   ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return timeEntries;
        }

     // Update a time entry
        public void updateTime(int entryId, Timestamp startTime, Timestamp endTime, String description) {
        	
        	 TimeDao dao=new TimeDao();
            String sql = "UPDATE time_entries SET start_time = ?, end_time = ?, description = ? WHERE entry_id = ?";
            try (Connection connection = dao.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setTimestamp(1, startTime);
                stmt.setTimestamp(2, endTime);
                stmt.setString(3, description);
                stmt.setInt(4, entryId);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
     // Delete a time entry
        public void deleteTimeEntry(int entryId) {
        	
        	TimeDao dao=new TimeDao();
            String sql = "DELETE FROM time_entries WHERE entry_id = ?";
            try (Connection connection = dao.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, entryId);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
        
        
	
	
	