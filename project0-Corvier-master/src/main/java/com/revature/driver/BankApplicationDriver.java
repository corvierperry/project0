package com.revature.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.beans.Account;
import com.revature.beans.User;
import com.revature.beans.Account.AccountType;
import com.revature.beans.User.UserType;
import com.revature.exceptions.InvalidCredentialsException;
import com.revature.services.UserService;

/**
 * This is the entry point to the application
 */
public class BankApplicationDriver {

	public static void main(String[] args) {

//		customer can apply for new bank account with a starting balance - 3pt
//		user can register with username and password - 1pt
//		user can log in with username and password - 2pt
//		customer can view the balance of a specific account - 1pt
//		customer can make a deposit to specific account - 2pt
//		customer can withdraw from specific account - 2pt
//		system will reject deposits of negative money - 2pt
		
		

//		the system will reject registration if username already exist - 1pt
//		system will reject the wrong login attempts - 1pt
//		system will reject and prevent overdrafts - 1pt

//---------------------------------------------------------------------------------------

//		registerNamePass(user1.getUsername(), user1.getPassword());
//		userLogIn(user1.getUsername(), user1.getPassword());

		System.out.println("Welcome to RevBank");
		System.out.println("Press 'Y' to enter or 'N' to Exit");

		Scanner sc = new Scanner(System.in);

		char enter = sc.next().charAt(0);
		if (enter == 'y' || enter == 'Y') {
			mainMenu();
		} else {
			System.exit(enter);
		}
	}

	public static void mainMenu() {

		
		ArrayList<Account> accountArray = new ArrayList<>();
		
		//------------------------------------Objects
		Account accountType = new Account();
		Account account1 = new Account();
		User user1 = new User();
		
		String userFirstName = null;
		String userLastName = null;
		String userName = null;
		String userPass = null;
//		registerNamePass(account1, user1, userFirstName, userLastName,
//				userName, userPass);
//		
//		String myLogin = userLogIn(user1, userName, userPass);
//		
//		String register = registerNamePass(user1, userFirstName, userLastName,
//				userName, userPass);

		System.out.println("How can we help you today?");
		System.out.println();
		System.out.println("1 - Apply for a RevBank account");
		System.out.println("2 - Register username and pass");
		System.out.println("3 - Log in");
		System.out.println("4 - Checking Account");
		System.out.println("5 - Savings Account");

		Scanner sc = new Scanner(System.in);

		int select = sc.nextInt();

		while (select != 1 || select != 2 || select != 3) {

			switch (select) {
			case 1:
				applyForAccount(accountType, user1);
				break;

			case 2:
				registerNamePass(user1, user1.getFirstName(), user1.getLastName(), user1.getUsername(),
						user1.getPassword());
				break;

			case 3:
				userLogIn(user1, user1.getUsername(), user1.getPassword());
				break;

			case 4:
				checkingAccount(accountType, user1, account1);
				break;

			case 5:
				savingsAccount(accountType, user1, account1);
				break;

			default:
				System.out.println("Invalid input.  Try again.");
				select = sc.nextInt();
			}
		}
	}

	// Applying for a bank account
	public static void applyForAccount(Account accountType, User user1) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Welcome to RevBank");
		System.out.println("The application process will only take a moment...");
		System.out.println("Would you like to apply for a Checking or Savings account?");

		System.out.println();

		System.out.println("1 - Apply for Checking");
		System.out.println("2 - Apply for Savings");
		System.out.println("3 - Main Menu");

		int select = sc.nextInt();
		switch (select) {
		case 1:
			checkingAccount(accountType, user1, accountType);

		case 2:
			savingsAccount(accountType, user1, accountType);

		case 3:
			mainMenu();
		}
	}

	// Checking Account
	public static double checkingAccount(Account accountType, User user1, Account balance) {

		Scanner sc = new Scanner(System.in);

		accountType.setType(AccountType.CHECKING);

		System.out.println("*****CHECKING*****");
		System.out.println("1 - Check your balance");
		System.out.println("2 - Make a deposit");
		System.out.println("3 - Withdraw");
		System.out.println("4 - Main Menu");

		int select = sc.nextInt();

		if (select == 1) {
			System.out.println("balance");
			System.out.println("Your balance is: " + "$" + balance.getBalance());

		} else if (select == 2) {
			System.out.println("make deposit");
			System.out.println("Enter an ammount you would like to deposit____");

			double deposit = sc.nextDouble();
			while (deposit > 0) {
				balance.setBalance(deposit);

				checkingAccount(accountType, user1, balance);

			}
			System.out.println("Invalid ammount.  Please try again.");

		} else if (select == 3) {
			System.out.println("Withdraw");
			System.out.println("Enter an ammount you would like to withdraw____");

			double withdraw = sc.nextDouble();
			while (withdraw  > 0) {
				balance.setBalance(withdraw=(withdraw + withdraw)-withdraw); 
				
				checkingAccount(accountType, user1, balance);
			}
		}

		else if (select == 4) {
			mainMenu();
		}

		return checkingAccount(balance, user1, balance);
	}

	// Savings Account
	public static void savingsAccount(Account accountType, User user1, Account balance) {

		Scanner sc = new Scanner(System.in);

		accountType.setType(AccountType.SAVINGS);

		System.out.println("*****SAVINGS*****");
		System.out.println("1 - Check your balance");
		System.out.println("2 - Make a deposit");
		System.out.println("3 - Withdraw");
		System.out.println("3 - Main Menu");

		int select = sc.nextInt();

		if (select == 1) {
			System.out.println("balance");
			System.out.println("Your balance is: " + "$" + balance.getBalance());

		} else if (select == 2) {
			System.out.println("make deposit");
			System.out.println("Enter an ammount you would like to deposit____");

			double deposit = sc.nextDouble();
			while (deposit > 0) {
				balance.setBalance(deposit);

				savingsAccount(accountType, user1, balance);

			}
			System.out.println("Invalid ammount.  Please try again.");

		} else if (select == 3) {
			System.out.println("Withdraw");
			System.out.println("Enter an ammount you would like to withdraw____");

			double withdraw = sc.nextDouble();
			while (withdraw  > 0) {
				balance.setBalance(withdraw=(withdraw + withdraw)-withdraw); 
				
				savingsAccount(accountType, user1, balance);
			}
		}
		else if (select == 4) {
			mainMenu();
		}
	}

	
	// Deposit money into accounts
	public static void deposit(Account accountType, User user1, Account balance) {

		Scanner sc = new Scanner(System.in);

		System.out.println("1 - Deposit into Checking");
		System.out.println("2 - Deposit into Savings");
		System.out.println("3 - Main Menu");

		int select = sc.nextInt();

		if (select == 1) {
			System.out.println("Enter an ammount you would like to deposit____");

			double deposit = sc.nextDouble();
			while (deposit > 0) {
				double ammount = sc.nextInt();
				balance.setBalance(ammount);
				deposit(accountType, user1, balance);
			}
			System.out.println("Invalid ammount.  Please try again.");
		}

		if (select == 2) {
			System.out.println("Enter an ammount you would like to deposit____");
			double ammount = sc.nextInt();
			balance.setBalance(ammount);
			deposit(accountType, user1, balance);
		}

		if (select == 3) {
			mainMenu();
		}

	}

	// Register for a user name and pass
	public static String registerNamePass(User user1, String userFirstName, String userLastName,
			String userName, String userPass) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter your first name___");
		userFirstName = sc.nextLine();
		user1.setFirstName(userFirstName);

		System.out.println();

		System.out.println("Enter your last name___");
		userLastName = sc.nextLine();
		user1.setLastName(userLastName);
		
		System.out.println();

		System.out.println("Enter your username:___");
		userName = sc.nextLine();
		user1.setUsername(userName);

		System.out.println();

		System.out.println("Enter your password___");
		userPass = sc.nextLine();
		user1.setPassword(userPass);

		System.out.println();

		System.out.println("1 - Apply for a RevBank account");
		System.out.println("2 - Register username and pass");
		System.out.println("3 - Log in");
		System.out.println("4 - Checking Account");
		System.out.println("5 - Savings Account");

		int select = sc.nextInt();
		switch (select) {
		case 1:
			applyForAccount(null, user1);
			break;

		case 2:
			registerNamePass(user1, userFirstName, userLastName, userName, userPass);
			break;

		case 3:
			userLogIn(user1, userName, userPass);
			break;

		case 4:
			checkingAccount(null, user1, null);
			break;

		case 5:
			savingsAccount(null, user1, null);

		default:
			System.out.println("Invalid input.  Try again.");
			select = sc.nextInt();
		}
		return registerNamePass(user1, userFirstName, userLastName, userName, userPass);
	}

	// User log in
	public static String userLogIn(User user1,String userName, String userPass) {

		// return registerNamePass(user1,userFirstName,userLastName,userName,userPass);
		Scanner sc = new Scanner(System.in);

		try {
			System.out.println("Enter your username:___");
			userName = sc.nextLine();

			System.out.println();

			System.out.println("Enter your password:___");
			userPass = sc.nextLine();

			if (userName.equals(user1.getUsername()) && userPass.equals(user1.getPassword())) {
				System.out.println("You successfully logged in");
			} else {

				throw new InvalidCredentialsException();
			}

		} catch (InvalidCredentialsException e) {
			System.out.println("You are now logged in");
		}

		System.out.println();

		System.out.println("1 - Apply for a RevBank account");
		System.out.println("2 - Register username and pass");
		System.out.println("3 - Log in");

		int select = sc.nextInt();
		switch (select) {
		case 1:
			applyForAccount(null, user1);
			break;

		case 2:
			registerNamePass(user1, user1.getFirstName(), user1.getLastName(), user1.getUsername(),
					user1.getPassword());
			break;

		case 3:
			userLogIn(user1, userName, userPass);
			break;

		default:
			System.out.println("Invalid input.  Try again.");
			select = sc.nextInt();
		}

		return userLogIn(user1, userPass, userPass);
	}
}
