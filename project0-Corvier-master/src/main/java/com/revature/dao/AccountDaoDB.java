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

	private static Connection conn;
	private static Statement stmt; // statement
	private static PreparedStatement pstmt; // prepared statement
	private static ResultSet rs;  //result set
	
	public AccountDaoDB() {
		conn = ConnectionUtil.getConnection();
	}
	
	public Account addAccount(Account a) {
		// TODO Auto-generated method stub
		String query = "insert into myaccounts (ownerId, accountBalance, accountType, approved) values (?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, a.getOwnerId());
			pstmt.setDouble(2, a.getBalance());
			pstmt.setObject(3, a.getType());
			pstmt.setBoolean(4, a.isApproved());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	public Account getAccount(Integer actId) {
		// TODO Auto-generated method stub
		String query = "select * from myaccounts where accountId="+actId.intValue();
		Account a = new Account();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()) {
				a.setId(rs.getInt("accountId"));
				a.setOwnerId(rs.getInt("ownerId"));
				a.setBalance(rs.getDouble("accountBalance"));
				a.setType((AccountType) rs.getObject("accountType"));
				a.setApproved(rs.getBoolean("approved"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	public List<Account> getAccounts() {
		// TODO Auto-generated method stub
		String query = "select * from myaccounts";
		List<Account> myAccountList = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				Account a = new Account();
				a.setId(rs.getInt("accountId"));
				a.setOwnerId(rs.getInt("ownerId"));
				a.setBalance(rs.getDouble("accountBalance"));
				a.setType((AccountType) rs.getObject("accountType"));
				a.setApproved(rs.getBoolean("approved"));
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
		String query = "select * from myaccounts where ownerId="+u.getId();
		List<Account> myAccountList = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				Account a = new Account();
				a.setId(rs.getInt("accountId"));
				a.setOwnerId(rs.getInt("ownerId"));
				a.setBalance(rs.getDouble("accountBalance"));
				a.setType((AccountType) rs.getObject("accountType"));
				a.setApproved(rs.getBoolean("approved"));
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
