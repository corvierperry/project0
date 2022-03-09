package com.revature.services;

import com.revature.beans.Account;
import com.revature.beans.User;
import com.revature.dao.AccountDao;
import com.revature.exceptions.OverdraftException;
import com.revature.exceptions.UnauthorizedException;

/**
 * This class should contain the business logic for performing operations on Accounts
 */
public class AccountService {
	
	public AccountDao actDao;
	public static final double STARTING_BALANCE = 25d;
	AccountService actSrv;
	Account myAccount = new Account();
	public AccountService(AccountDao dao) {
		this.actDao = dao;
	}
	
	
	/**
	 * Withdraws funds from the specified account
	 * @throws OverdraftException if amount is greater than the account balance
	 * @throws UnsupportedOperationException if amount is negative
	 */
	public void withdraw(Account a, Double amount) {
		
		Account myAccount = new Account();
		myAccount.getBalance();
		try {
			actSrv.withdraw(myAccount, amount);
		}
		catch(OverdraftException x) {
			System.out.println(x);
		}
		catch(UnsupportedOperationException y) {
			System.out.println(y);
		}
	}
	
	/**
	 * Deposit funds to an account
	 * @throws UnsupportedOperationException if amount is negative
	 */
	public void deposit(Account a, Double amount) {
		Account myAccount = new Account();
		myAccount.getBalance();
		if (!a.isApproved()) {
			throw new UnsupportedOperationException();
		}
		else {
			actSrv.deposit(myAccount, amount);
		}
	}
	
	/**
	 * Transfers funds between accounts
	 * @throws UnsupportedOperationException if amount is negative or 
	 * the transaction would result in a negative balance for either account
	 * or if either account is not approved
	 * @param fromAct the account to withdraw from
	 * @param toAct the account to deposit to
	 * @param amount the monetary value to transfer
	 */
	public void transfer(Account fromAct, Account toAct, double amount) {
		//Account myAccount = new Account();
		try {
		actSrv.transfer(fromAct, toAct, amount);
		}
		catch(UnsupportedOperationException e) {
		}	
	}
	
	
	/**
	 * Creates a new account for a given User
	 * @return the Account object that was created
	 */
	public Account createNewAccount(User u) {
		//Account account = new Account();
		User user = new User();
		actSrv.createNewAccount(user);
		return  myAccount;
	}
	
	/**
	 * Approve or reject an account.
	 * @param a
	 * @param approval
	 * @throws UnauthorizedException if logged in user is not an Employee
	 * @return true if account is approved, or false if unapproved
	 */
	public boolean approveOrRejectAccount(Account a, boolean approval) {
		
		if(actSrv.approveOrRejectAccount(a, approval) == true) {
			return true;
		}
		
		if(actSrv.approveOrRejectAccount(a, approval) == false) {
			return false;
		}
		
		else {
			throw new UnauthorizedException();
		}

	}
}
