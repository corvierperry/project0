package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Account;
import com.revature.beans.Account.AccountType;
import com.revature.beans.User;
import com.revature.utils.ConnectionUtil;

/**
 * Implementation of AccountDAO which reads/writes to a database
 */
public class AccountDaoDB implements AccountDao {

	private static Connection myConnection;
	private static Statement myStatement; // statement
	private static PreparedStatement myPreparedStatement; // prepared statement
	private static ResultSet myResultSet;  //result set
	
	public AccountDaoDB() {
		myConnection = ConnectionUtil.getConnection();
	}
	
	public Account addAccount(Account a) {
		// TODO Auto-generated method stub
		String query = "insert into p0_user (ownerId, accountBalance, accountType, approved) values (?,?,?,?)";
		try {
			myPreparedStatement =myConnection.prepareStatement(query);
			myPreparedStatement.setInt(1, a.getOwnerId());
			myPreparedStatement.setDouble(2, a.getBalance());
			myPreparedStatement.setObject(3, a.getType());
			myPreparedStatement.setBoolean(4, a.isApproved());
			myPreparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	public Account getAccount(Integer actId) {
		// TODO Auto-generated method stub
		String query = "select * from p0_user where accountId="+actId.intValue();
		Account a = new Account();
		try {
			myStatement = myConnection .createStatement();
			 myResultSet = myStatement.executeQuery(query);
			if( myResultSet.next()) {
				a.setId( myResultSet.getInt("accountId"));
				a.setOwnerId( myResultSet.getInt("ownerId"));
				a.setBalance( myResultSet.getDouble("accountBalance"));
				a.setType((AccountType)  myResultSet.getObject("accountType"));
				a.setApproved( myResultSet.getBoolean("approved"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		String query = "select * from p0_user";
		List<Account> myAccountList = new ArrayList<>();
		try {
			myStatement = myConnection .createStatement();
			 myResultSet = myStatement.executeQuery(query);
			while( myResultSet.next()) {
				Account a = new Account();
				a.setId( myResultSet.getInt("accountId"));
				a.setOwnerId( myResultSet.getInt("ownerId"));
				a.setBalance( myResultSet.getDouble("accountBalance"));
				a.setType((AccountType)  myResultSet.getObject("accountType"));
				a.setApproved( myResultSet.getBoolean("approved"));
				myAccountList.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myAccountList;
	}

	public List<Account> getAccountsByUser(User u) {
		// TODO Auto-generated method stub
		String query = "select * from p0_user where ownerId="+u.getId();
		List<Account> myAccountList = new ArrayList<>();
		try {
			myStatement = myConnection .createStatement();
			 myResultSet = myStatement.executeQuery(query);
			while( myResultSet.next()) {
				Account a = new Account();
				a.setId( myResultSet.getInt("accountId"));
				a.setOwnerId( myResultSet.getInt("ownerId"));
				a.setBalance( myResultSet.getDouble("accountBalance"));
				a.setType((AccountType)  myResultSet.getObject("accountType"));
				a.setApproved( myResultSet.getBoolean("approved"));
				myAccountList.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myAccountList;
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
