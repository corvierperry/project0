package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Account;
import com.revature.beans.User;

/**
 * Implementation of AccountDAO which reads/writes to a database
 */
public class AccountDaoDB implements AccountDao {

//	ArrayList<Account> myAccountList = new ArrayList<>();
//	AccountDaoDB accountDao = new AccountDaoDB();
	
	public Account addAccount(Account a) {
		// TODO Auto-generated method stub
		
//		accountDao.addAccount(a);
//		myAccountList.add(a);
		
		
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
