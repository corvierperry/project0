package com.revature.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Account;
import com.revature.beans.User;

/**
 * Implementation of AccountDAO which reads/writes to files
 */
public class AccountDaoFile implements AccountDao {
	// use this file location to persist the data to
	public static String fileLocation = "C:\\revature\\project0-Corvier-master\\src\\main\\resources\\UserDaoFile.txt";

	private static File userFile = new File(fileLocation);
	private static List<Account> myAccount = new ArrayList<Account>();

	public Account addAccount(Account a) {
		// TODO Auto-generated method stub
		try {
			FileOutputStream fos = new FileOutputStream(fileLocation);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userFile, true));

			FileInputStream fis = new FileInputStream(fileLocation);
			// ObjectInputStream ois = new ObjectInputStream(myObjectInputStream);
			// ois.readObject();
			oos.writeObject(a);

			System.out.println("Registered successfully");
		}
		catch (IOException e) {
		}
		return a;
	}
	
	
	

	public Account getAccount(Integer actId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Account> getAccountsByUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	public Account updateAccount(Account a) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean removeAccount(Account a) {
		// TODO Auto-generated method stub
		return false;
	}

}
