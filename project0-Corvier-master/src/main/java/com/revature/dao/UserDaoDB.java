package com.revature.dao;

import java.net.PasswordAuthentication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.User;
import com.revature.beans.User.UserType;
import com.revature.utils.ConnectionUtil;

/**
 * Implementation of UserDAO that reads/writes to a relational database
 */
public class UserDaoDB implements UserDao {

	private static Connection myConnection;
	private static Statement myStatement;
	private static PreparedStatement preparedStatemnt;
	private static ResultSet myResultSet;

	public UserDaoDB() {
		myConnection = ConnectionUtil.getConnection();
	}

	public User addUser(User user) {
		// TODO Auto-generated method stub
		String query = "insert into p0_user (first_name,last_name, username,password, user_type) values (?,?,?,?,?);";
		int status = 0;
		try {
			preparedStatemnt = myConnection.prepareStatement(query);
			preparedStatemnt.setString(1, user.getFirstName());
			preparedStatemnt.setString(2, user.getLastName());
			preparedStatemnt.setString(3, user.getUsername());
			preparedStatemnt.setString(4, user.getPassword());
			preparedStatemnt.setObject(5, user.getUserType().toString());
			status = preparedStatemnt.executeUpdate();
			if (status > 0)
				System.out.println("User has bee added to the database");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}

	public User getUser(Integer userId) {
		// TODO Auto-generated method stub
		String query = "select * from p0_user where id=" + userId.intValue();
		User user = new User();
		try {
			myStatement = myConnection.createStatement();
			myResultSet = myStatement.executeQuery(query);
			if (myResultSet.next()) {
				user.setId(myResultSet.getInt("id"));
				user.setFirstName(myResultSet.getString("first_name"));
				user.setLastName(myResultSet.getString("last_name"));
				user.setUsername(myResultSet.getString("username"));
				user.setPassword(myResultSet.getString("password"));
				//user.setUserType(myResultSet.getString("user_type"));
				user.setUserType(UserType.CUSTOMER);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}

	public User getUser(String username, String pass) {
		// TODO Auto-generated method stub
		String query = "select * from p0_user where username='"+username+"' and password='"+pass+"';";
		User user = null;
		try {
			myStatement = myConnection.createStatement();
			myResultSet = myStatement.executeQuery(query);
			if (myResultSet.next()) {
				user = new User();
				user.setId(myResultSet.getInt("id"));
				user.setFirstName(myResultSet.getString("first_name"));
				user.setLastName(myResultSet.getString("last_name"));
				user.setUsername(myResultSet.getString("username"));
				user.setPassword(myResultSet.getString("password"));
				//user.setUserType(myResultSet.getString("user_type"));
				user.setUserType(UserType.CUSTOMER);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		String query = "select * from p0_user";
		List<User> userList = new ArrayList<User>();

		try {
			myStatement = myConnection.createStatement();
			myResultSet = myStatement.executeQuery(query);

			while (myResultSet.next()) {
				User myUser = new User();
				myUser.setId(myResultSet.getInt("id"));
				myUser.setFirstName(myResultSet.getString("first_name"));
				myUser.setLastName(myResultSet.getString("last_name"));
				myUser.setUsername(myResultSet.getString("username"));
				myUser.setPassword(myResultSet.getString("password"));
				//myUser.setUserType(myResultSet.getString("user_type"));
				((PreparedStatement) myUser).setString(6, myUser.getUserType().name());
				userList.add(myUser);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return userList;
	}

	public User updateUser(User u) {
		// TODO Auto-generated method stub
		String query = "update p0_user set first_name=?, last_name=?, username=?, password=?, user_type=? where id = ?";
		try {
			preparedStatemnt = myConnection.prepareStatement(query);

			preparedStatemnt.setString(1, u.getFirstName());
			preparedStatemnt.setString(2, u.getLastName());
			preparedStatemnt.setString(3, u.getUsername());
			preparedStatemnt.setString(4, u.getPassword());
			preparedStatemnt.setObject(5, UserType.CUSTOMER);
			preparedStatemnt.setInt(6, u.getId());
			preparedStatemnt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}

	public boolean removeUser(User u) {
		// TODO Auto-generated method stub
		String query = "delete from p0_user where id =" + u.getId();
		boolean status = false;
		try {
			myStatement = myConnection.createStatement();
			status = myStatement.execute(query);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

}