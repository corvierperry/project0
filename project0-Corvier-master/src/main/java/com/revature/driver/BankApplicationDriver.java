package com.revature.driver;

import java.util.ArrayList;

import java.lang.NullPointerException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;
import com.revature.services.AccountService;
import com.revature.services.UserService;
import com.mysql.cj.xdevapi.Statement;
import com.revature.beans.Account;
import com.revature.beans.Account.AccountType;
import com.revature.beans.User;
import com.revature.beans.User.UserType;
import com.revature.beans.Account;
import com.revature.beans.Account.AccountType;
import com.revature.beans.User;
import com.revature.beans.User.UserType;
import com.revature.dao.AccountDao;
import com.revature.dao.AccountDaoDB;
import com.revature.dao.TransactionDao;
import com.revature.dao.TransactionDaoDB;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoDB;
import com.revature.exceptions.InvalidCredentialsException;
import com.revature.exceptions.OverdraftException;
import com.revature.exceptions.UnauthorizedException;
import com.revature.exceptions.UsernameAlreadyExistsException;
import com.revature.services.AccountService;
import com.revature.services.UserService;
import com.revature.utils.SessionCache;
import com.revature.dao.AccountDao;
import com.revature.dao.AccountDaoDB;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoDB;
import com.revature.dao.UserDaoFile;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;

import com.revature.utils.SessionCache;

public class BankApplicationDriver {

	public static void main(String[] args) {

		// TODO Auto-generated method stub

		int select = 0;
		int id = 0;
		Scanner sc = new Scanner(System.in);
		String firstName = null;
		String lastName = null;
		String userName = null;
		String password = null;
		UserDao myUserDao = new UserDaoDB();
		AccountDao myccountDao = new AccountDaoDB();
		UserService userService = new UserService(myUserDao, myccountDao);
		AccountService accountService = new AccountService(myccountDao);
		{

		}

		// ---------------MAIN MENU
		while (select < 6) {
			System.out.println("---WELCOME TO REVBANK---");
			System.out.println();
			System.out.println("1 - Register ");
			System.out.println("2 - Login");
			System.out.println("3 - View Users ");
			System.out.println("4 - Remove User ");
			System.out.println("5 - Update User");
			System.out.println("6 - Exit");

			select = sc.nextInt();

			// -----------------------REGISTER USER
			switch (select) {
			case 1:
				id = UserDaoFile.usersList.size();

				System.out.print("Enter First Name:_____");
				firstName = sc.next();

				System.out.print("Enter Last Name:_____");
				lastName = sc.next();

				System.out.print("Enter Username:_____");
				userName = sc.next();

				System.out.print("Enter Password:_____");
				password = sc.next();

				User user = new User(userName, password, firstName, lastName, UserType.CUSTOMER);
				userService.register(user);
				break;

			// ------------LOGGING IN
			case 2:
				System.out.print("Enter Username:_____");
				userName = sc.next();

				System.out.print("Enter Password:_____");
				password = sc.next();

				User loggedUser = userService.login(userName, password);

				// Prints out the user that has logged in and all of his attributes
				System.out.println(loggedUser);

				if (loggedUser != null) {
					System.out.println("Sucessfully Logged In...");
					SessionCache.setCurrentUser(loggedUser);

					int option = 0;
					int accountType = 0;
					double startingBalance = 0;

					while (option <= 6) {
						System.out.println();
						System.out.println("1 - Apply for an Account");
						System.out.println("2 - Deposit");
						System.out.println("3 - Withdraw");
						System.out.println("4 - Transfer money");
						System.out.println("5 - View Account Balance");
						System.out.println("6 - Approve/Reject Account");
						System.out.println("7 - Logout");
						System.out.println();

						option = sc.nextInt();
						switch (option) {
						case 1:
							System.out.print("Select an account:");
							System.out.println("1 - Checking");
							System.out.println("2 - Savings");
							System.out.println();
							accountType = sc.nextInt();
							System.out.println();

							System.out.print("Enter a starting balance:");
							startingBalance = sc.nextDouble();

							Account account = new Account();

							account.setBalance(startingBalance);
							System.out.println();

							System.out.println("User ID: " + SessionCache.getCurrentUser().get().getId());
							account.setOwnerId(loggedUser.getId());
							//account.setType(accountType == 1 ? AccountType.CHECKING.toString():AccountType.SAVINGS.toString());
							if(accountType == 1) {
								account.setType(AccountType.CHECKING);
							}
							if(accountType == 2) {
								account.setType(AccountType.SAVINGS);
							}
							List<Account> accountList = new ArrayList<Account>();
							accountList.add(account);
							loggedUser.setAccounts(accountList);
							accountService.createNewAccount(loggedUser);
							break;

						case 2:
							System.out.println("User's available accounts");
							System.out.println();

							System.out.println("---DEPOSIT---");
							System.out.print("Enter Account ID:_____");
							int accountId = 0;
							accountId = sc.nextInt();
							System.out.print("Enter the amount:_____");
							System.out.println();

							double amount = 0;
							amount = sc.nextDouble();
							account = myccountDao.getAccount(accountId);
							accountService.deposit(account, amount);
							break;

						case 3:
							System.out.println("---WITHDRAW---");
							//accountService.getAccounts(loggedUser).forEach(System.out::println);
							System.out.print("Enter Account ID to Withdraw:");
							accountId = 0;
							accountId = sc.nextInt();
							System.out.println();

							System.out.print("Enter an ammount to withdraw:_____");
							amount = 0;
							amount = sc.nextDouble();
							account = myccountDao.getAccount(accountId);
							accountService.withdraw(account, amount);
							System.out.println();
							
							account.setBalance(account.getBalance() - amount);
							break;

						case 4:
							System.out.println("---TRANSER FUNDS---");
							accountId = 0;
							System.out.print("Enter client Id for Transfer of Funds :");
							accountId = sc.nextInt();
							System.out.println();

							int toAccountId = 0;
							System.out.print("Enter Account ID for Transfer of Funds :");
							toAccountId = sc.nextInt();
							amount = 0;
							System.out.println();

							System.out.print("Enter transfer ammount:_____");
							amount = sc.nextDouble();
							Account fromAccount = myccountDao.getAccount(accountId);
							Account toAccount = myccountDao.getAccount(toAccountId);
							accountService.transfer(fromAccount, toAccount, amount);
							break;

						case 5: // View Balance

							System.out.println("View Balance");
							System.out.println("Enter account ID");
							System.out.println();

							int balance = sc.nextInt();
							account = myccountDao.getAccount(balance);
							System.out.println(account);
							break;

						case 6: // Approving / Rejecting Accounts
							System.out.println("Approving / Rejecting Accounts");
							System.out.println();
							System.out.println("1 - Approved");
							System.out.println("2 - Reject");

							select = sc.nextInt();
							switch (select) {

							// ----APPROVE ACCOUNT
							case 1:
								System.out.println("Enter account ID:_____");
								accountId = sc.nextInt();
								account = myccountDao.getAccount(accountId);
								//account.setApproved(true);
								accountService.approveOrRejectAccount(account, true);
								System.out.println(account);
								break;

							// ----REJECT ACCOUNT
							case 2:
								System.out.println("Enter account ID:_____");
								accountId = sc.nextInt();
								account = myccountDao.getAccount(accountId);
								account.setApproved(false);
								System.out.println(account);
								break;
								
								default:
									System.out.println("Invalid input");
									break;
							}
							
							// ----LOGGING OUT
						case 7:
							System.out.print("Log out?");
							System.out.println("1 - Yes");
							System.out.println("2 - No");
							
							int logout = 0;
							logout = sc.nextInt();

							if (logout == 1) {
								System.out.println("");
								System.exit(0);
								
							}
							else {
								SessionCache.setCurrentUser(null);
							}

							break;
							
						default:
							System.out.println("Invalid input...");
							break;
						}
					}
				}
				break;

			case 3:
				myUserDao.getAllUsers().forEach(System.out::println);
				break;

			case 4:
				System.out.println("---REMOVE USER---");
				System.out.print("Enter user ID:");
				id = sc.nextInt();
				User u = myUserDao.getUser(id);
				myUserDao.removeUser(u);
				break;

			case 5:
				System.out.println("---UPDATE USER---");
				System.out.print("Enter user ID:_____");
				id = sc.nextInt();
				System.out.println();

				System.out.print("Enter first name:_____");
				firstName = sc.next();
				System.out.println();

				System.out.print("Enter last name:_____");
				lastName = sc.next();
				System.out.println();

				System.out.print("Enter new password:_____");
				password = sc.next();
				System.out.println();

				User updatedUser = new User();
				updatedUser.setId(id);
				updatedUser.setFirstName(firstName);
				updatedUser.setLastName(lastName);
				updatedUser.setPassword(password);
				myUserDao.updateUser(updatedUser);
				break;
			case 6:
				System.out.println("Thank You for using REVBANK");
				System.exit(0);
				break;

			default:
				break;
			}
		}
		sc.close();
	}
}