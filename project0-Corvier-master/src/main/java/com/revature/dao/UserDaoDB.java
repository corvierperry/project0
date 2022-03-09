package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Account;
import com.revature.beans.User;
import com.revature.beans.User.UserType;
import com.revature.utils.ConnectionUtil;

/**
 * Implementation of UserDAO that reads/writes to a relational database
 */
public class UserDaoDB implements UserDao {

	//private static List<User> myUser = new ArrayList<Account>();
	private static Connection conn;
	private static Statement stmt; // statement
	private static PreparedStatement pstmt; // prepared statement
	private static ResultSet rs;  //result set
	
	
	public UserDaoDB(){
		conn = ConnectionUtil.getConnection();
	}
	
	public User addUser(User user) {
		// TODO Auto-generated method stub
		String query = "insert into myusers (firstName, lastName, username, password, userType) values (?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query); //preparedStatement
			
			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getUsername());
			pstmt.setString(4, user.getPassword());
			pstmt.setObject(5, UserType.CUSTOMER);
			//pstmt.setObject(7, account.);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	public User getUser(Integer userId) {
		// TODO Auto-generated method stub
		String query = "select * from myusers where id=" +userId.intValue();
		User user = new User();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);  //resultSet
			if(rs.next()) {
				user.setId(rs.getInt("id"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setUserType((UserType)rs.getObject("userType"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		return user;
	}

	public User getUser(String username, String pass) {
		// TODO Auto-generated method stub
		
		String query = "select * from myusers where userName=? and password=?";
		User user = new User();
		try {
			pstmt = conn.prepareStatement(query); //prepared statment
			pstmt.setString(1, username);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery(query);  //resultSet
			if(rs.next()) {
				user.setId(rs.getInt("id"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setUserType((UserType)rs.getObject("userType"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		return user;
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		String query = "select * from myusers";
		List<User> userList = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setFirstName(rs.getString("firstName"));
				u.setLastName(rs.getString("lastName"));
				u.setUsername(rs.getString("username"));
				u.setUserType((UserType) rs.getObject("userType"));
				userList.add(u);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return userList;
	}

	public User updateUser(User u) {
		// TODO Auto-generated method stub
		String query = "update myusers set firstName=?, lastName=?, username=?, password=?, userType=? where id = ?";
		try {
			pstmt = conn.prepareStatement(query); //preparedStatement
			
			pstmt.setString(1, u.getFirstName());
			pstmt.setString(2, u.getLastName());
			pstmt.setString(3, u.getUsername());
			pstmt.setString(4, u.getPassword());
			pstmt.setObject(5, UserType.CUSTOMER);
			pstmt.setInt(6, u.getId());
			//pstmt.setObject(7, account.);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	
	}

	public boolean removeUser(User u) {
		// TODO Auto-generated method stub
		String query = "delete from myusers where id =" + u.getId();
		boolean status = false;
		try {
			stmt = conn.createStatement(); //Statement
			status = stmt.execute(query);
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

}
