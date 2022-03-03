package com.revature.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.revature.beans.User;

/**
 * Implementation of UserDAO that reads and writes to a file
 */
public class UserDaoFile implements UserDao {
	
	public static String fileLocation = "C:\\revature\\project0-Corvier-master\\src\\main\\resources";
	FileInputStream myFileInputStream;
	FileOutputStream myFileOutputStream;
	ObjectInputStream myObjectInputStream;
	ObjectOutputStream myObjectOutputStream;
	
	ArrayList<User> userArray = new ArrayList<>();
	UserDaoFile udf = new UserDaoFile();
	User user = new User();
	
	
	
	public User addUser(User user) {
		// TODO Auto-generated method stub
		
		userArray.add(user);
		
		try {
			
			FileOutputStream fos = new FileOutputStream(fileLocation);
			ObjectOutputStream oos = new ObjectOutputStream(myFileOutputStream);
			
			
			FileInputStream fis = new FileInputStream(fileLocation);
			ObjectInputStream ois = new ObjectInputStream(myObjectInputStream);
			ois.readObject();
			oos.writeObject(user);
		
			oos.close();
		}
		catch(FileNotFoundException e) {
			System.out.println(e);	
		}
		catch(IOException x) {
			System.out.println(x);
		}
		catch(ClassNotFoundException n) {
			System.out.println(n);
		}
		return user;
	}

	
	public User getUser(Integer userId) {
		// TODO Auto-generated method stub

		for(int i = 0; i < userArray.size(); i++) {
			if(user.getId().equals(userId)) {
				return user;
			}
		}
		return null;
	}

	public User getUser(String username, String pass) {
		// TODO Auto-generated method stub
		
		return null;
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public User updateUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean removeUser(User u) {
		// TODO Auto-generated method stub
		return false;
	}

}
