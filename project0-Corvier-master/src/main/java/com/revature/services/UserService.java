package com.revature.services;

import java.util.ArrayList;

import java.util.List;

import com.revature.beans.User;
import com.revature.dao.AccountDao;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoDB;
import com.revature.exceptions.InvalidCredentialsException;
import com.revature.exceptions.UsernameAlreadyExistsException;

/**
 * This class should contain the business logic for performing operations on users
 */
public class UserService {
	
	UserDao userDao;
	AccountDao accountDao;
	
	public UserService(UserDao udao, AccountDao adao) {
		this.userDao = udao;
		this.accountDao = adao;
	}
	
	/**
	 * Validates the username and password, and return the User object for that user
	 * @throws InvalidCredentialsException if either username is not found or password does not match
	 * @return the User who is now logged in
	 */
	
		public User login(String username, String password) {
	        User User = userDao.getUser(username, password); // data access object = DAO
	        if (User == null) {
	        	System.out.println("Invalid Entry!");
	            throw new InvalidCredentialsException();
	            
	        } else
	            return User;
	    
		}
	
	/**
	 * Creates the specified User in the persistence layer
	 * @param newUser the User to register
	 * @throws UsernameAlreadyExistsException if the given User's username is taken
	 */
	public void register(User newUser) {
		
	
		UserDaoDB udb = new UserDaoDB();
//      CREATE LIST OF USERS
//      CREATE LIST OF USERNAMES FROM USERS LIST
//      THROW EXCEPTION IF USERNAME EXISTS
//          CREATE NEW USER FILE
		List<User> users = new ArrayList<User>();
		 
		users = udb.getAllUsers();
		
		
		for(User i : users) {
            if(i.getUsername().equals(newUser.getUsername())) {
            	System.out.println("User already taken");
            	throw new UsernameAlreadyExistsException();
                
            }
		
            else {
            	userDao.addUser(newUser);
            }
		}
		
		System.out.println("User sucessfully registered");
		
	}
}