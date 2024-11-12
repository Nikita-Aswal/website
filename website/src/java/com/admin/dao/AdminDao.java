package com.admin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.admin.model.Admin;

public class AdminDao {
    
	private String jdbcURL = "jdbc:mysql://localhost:30006/admindata";
	private String jdbcUserName="root";
	private String jdbcPassword="root";
	
	
	private static final String INSERT_ADMIN_SQL= "INSERT INTO ADMINS"+"(username,password,email)VALUES"+"(?,?,?);";
	private static final String SELECT_ADMIN_BY_ID="SELECT * FROM ADMINS WHERE ID=?;";
	private static final String SELECT_ALL_ADMINS="SELECT * FROM ADMINS;";
	private static final String DELETE_ADMINS_SQL="DELETE FROM ADMINS WHERE ID=?;";
	
	public AdminDao() {
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
	
  public void insertAdmin(Admin admin)
  {
	  AdminDao dao=new AdminDao();
	  
	  try (Connection connection=dao.getConnection())
	  {
		  PreparedStatement preparedStatement=connection.prepareStatement(INSERT_ADMIN_SQL);
		  preparedStatement.setString(1, admin.getUsername());
		  preparedStatement.setString(2,admin.getEmail());
	      preparedStatement.setString(3,admin.getPassword());
	      
	      preparedStatement.executeUpdate();
  }
	catch (Exception e) {
		e.printStackTrace();
	}
  }	
  
  public Admin selectAdmin(int id)
  {
	  Admin admin= new Admin();
	  AdminDao dao=new AdminDao();
	  try (Connection connection=dao.getConnection())
	  {
		  PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ADMIN_BY_ID);   
		  preparedStatement.setInt(1, id);
		  ResultSet resultSet = preparedStatement.executeQuery();
		  
		  while(resultSet.next())
		  {
			  admin.setAdmin_Id(id);
			  admin.setUsername(resultSet.getString("username"));
			  admin.setEmail(resultSet.getString("email"));
			  admin.setPassword(resultSet.getString("password"));
		  }  
		  
	  }
	  catch (Exception e) {
			e.printStackTrace();
		}
	  return admin;  
  }
    
     public List<Admin> selectALLAdmins()
     {
    	 List<Admin> admins=new ArrayList<Admin>();
    	 AdminDao dao= new AdminDao();
    	  try (Connection connection=dao.getConnection())
    	  {
    		  PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ALL_ADMINS);
    		  ResultSet resultSet=preparedStatement.executeQuery();
    		  
    		  while(resultSet.next())
    		  {
    			  int id= resultSet.getInt("admin_id");
    			  String username=resultSet.getString("username");
    			  String email=resultSet.getString("email");
    			  String password=resultSet.getString("password");
    			  
    			  admins.add(new Admin(id,username,email,password));
    		  }
    	  }
    	  catch (Exception e) {
  			e.printStackTrace();
  		}
    	  return admins;
     }
  
 
	  public boolean deleteadmin(int id)
	  {
		  boolean status = false;
		  AdminDao dao= new AdminDao();
		  try (Connection connection=dao.getConnection())
		  {
			  PreparedStatement preparedStatement=connection.prepareStatement(DELETE_ADMINS_SQL);
			  preparedStatement.setInt(1, id);
			  status = preparedStatement.execute();
		  }
		  catch (Exception e) {
				e.printStackTrace();
			}
		  return status;
	  
  }
  
  
  
  
  
  
  
  
}