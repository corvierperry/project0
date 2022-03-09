package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Account;
import com.revature.beans.Transaction;
import com.revature.beans.Transaction.TransactionType;
import com.revature.utils.ConnectionUtil;

public class TransactionDaoDB implements TransactionDao {

	private static Connection conn;
	private static Statement stmt; // statement
	private static PreparedStatement pstmt; // prepared statement
	private static ResultSet rs;  //result set
	
	public TransactionDaoDB() {
		conn = ConnectionUtil.getConnection();
	}
	
	public List<Transaction> getAllTransactions() {
		// TODO Auto-generated method stub
		List<Transaction> myTransactionList = new ArrayList<>();
		String query = "select * from mytransactions";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				Transaction myTransaction = new Transaction();
				myTransaction.setType((TransactionType) rs.getObject("transactionType"));
				myTransaction.setSender((Account) rs.getObject("fromAccountId"));
				if(rs.getObject("transactionType")== TransactionType.TRANSFER) {
					myTransaction.setRecipient((Account) rs.getObject("toAccountId"));
				}
				myTransaction.setTimestamp((LocalDateTime) rs.getObject("timeStamp"));
				myTransaction.setAmount(rs.getDouble("ammount"));
				myTransactionList.add(myTransaction);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myTransactionList;
	}

}
