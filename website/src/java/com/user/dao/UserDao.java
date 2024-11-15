package com.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.user.model.User;

public class UserDao {
    
	private String jdbcURL = "jdbc:mysql://localhost:30006/userdata";
	private String jdbcUserName="root";
	private String jdbcPassword="root";
	
	
	private static final String INSERT_USER_SQL= "INSERT INTO Users"+"(username,password,email)VALUES"+"(?,?,?);";
	private static final String SELECT_USER_BY_ID="SELECT * FROM USERS WHERE ID=?;";
	private static final String SELECT_ALL_USERS="SELECT * FROM USERS;";
	private static final String DELETE_USERS_SQL="DELETE FROM USERS WHERE ID=?;";
	//private static final String UPDATE_USERS_SQL="UPDATE USER SET USERNAME=?,EMAIL=?,PASSWORD=? where ID= ?;";
	
	public UserDao() {
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
	
  public void insertUser(User user)
  {
	  UserDao dao=new UserDao();
	  
	  try (Connection connection=dao.getConnection())
	  {
		  PreparedStatement preparedStatement=connection.prepareStatement(INSERT_USER_SQL);
		  preparedStatement.setString(1, user.getUsername());
		  preparedStatement.setString(2,user.getEmail());
	      preparedStatement.setString(3,user.getPassword());
	      
	      preparedStatement.executeUpdate();
  }
	catch (Exception e) {
		e.printStackTrace();
	}
  }	
  
  public User selectUser(int id)
  {
	  User user= new User();
	  UserDao dao=new UserDao();
	  try (Connection connection=dao.getConnection())
	  {
		  PreparedStatement preparedStatement=connection.prepareStatement(SELECT_USER_BY_ID);   
		  preparedStatement.setInt(1, id);
		  ResultSet resultSet = preparedStatement.executeQuery();
		  
		  while(resultSet.next())
		  {
			  user.setUser_Id(id);
			  user.setUsername(resultSet.getString("username"));
			  user.setEmail(resultSet.getString("email"));
			  user.setPassword(resultSet.getString("password"));
		  }  
		  
	  }
	  catch (Exception e) {
			e.printStackTrace();
		}
	  return user;  
  }
    
     public List<User> selectALLUsers()
     {
    	 List<User> users=new ArrayList<User>();
    	 UserDao dao= new UserDao();
    	  try (Connection connection=dao.getConnection())
    	  {
    		  PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ALL_USERS);
    		  ResultSet resultSet=preparedStatement.executeQuery();
    		  
    		  while(resultSet.next())
    		  {
    			  int id= resultSet.getInt("user_id");
    			  String username=resultSet.getString("username");
    			  String email=resultSet.getString("email");
    			  String password=resultSet.getString("password");
    			  
    			  users.add(new User(id,username,email,password));
    		  }
    	  }
    	  catch (Exception e) {
  			e.printStackTrace();
  		}
    	  return users;
     }
  
 
	  public boolean deleteUser(int id)
	  {
		  boolean status = false;
		  UserDao dao= new UserDao();
		  try (Connection connection=dao.getConnection())
		  {
			  PreparedStatement preparedStatement=connection.prepareStatement(DELETE_USERS_SQL);
			  preparedStatement.setInt(1, id);
			  status = preparedStatement.execute();
		  }
		  catch (Exception e) {
				e.printStackTrace();
			}
		  return status;
	  
  }
  
  
  
  
  
  
  
  
}
