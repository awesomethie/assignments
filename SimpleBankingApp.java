/*Sample Run:
Welcome to Mobile Banking App!
Please provide your username:
tc0169
Please provide your password:
12345
The username and password don’t match! 
Please provide your username:
TC0169
Please provide your password:
12345
How can we help you today? Please pick one of the following options:
1. Transfer Money
2. Request Money
3. Open a Savings Account
4. Close an Account
5. Log Off
1
What is the amount you would like to transfer?
€60
What is the account holder’s name that the money will be transferred?
Jessica
€60.0 is transferred to Jessica. You have €440.0 in your personal account.
Is there anything else we can help you with today? Please pick one of the following 
options:
1. Transfer Money
2. Request Money
3. Open a Savings Account
4. Close an Account
5. Log Off
5
You logged off.*/


import java.util.Scanner; 

public class SimpleBankingApp {

	public static void main(String[] args) {
		
		// Add 5 customers info
		Customer customer1 = new Customer("Thy", "Cao", "TC0169", "12345", 500);
		Customer customer2 = new Customer("Yennifer", "Nguyen", "JT0129", "67890", 1000);
		Customer customer3 = new Customer ("Jennie", "Kim", "JK0109", "54321", 9000);
		Customer customer4 = new Customer ("Mike", "Nguyen", "MN1110", "09876", 20000);
		Customer customer5 = new Customer ("Luna", "Pham", "LP0809", "08090", 9000000);
		
		// Create arrays for 5 customers
		Customer [] customers = {customer1, customer2, customer3, customer4, customer5};
		
		boolean isAuthenticated = false; // flag to know whether user id & password are authenticated
		Customer authenticatedCustomer = null; // flag to mark whether customer is authenticated
		boolean isRunning = true; // flag to know whether user is still using the application
		boolean isFirstTime = true; // flag to check whether this selection is the 1st time
		
		
		Scanner input = new Scanner (System.in);
		
		while (!isAuthenticated) {
			System.out.println("Welcome to Mobile Banking App!" + "\n" + "Please provide your username: ");
			String userId = input.nextLine();
			System.out.println("Please provide your password: ");
			String password = input.nextLine();
			
			authenticatedCustomer = authenticate(customers, userId, password);
			
			 if(authenticatedCustomer != null) {
			    isAuthenticated = true;
				System.out.println("Login successfully!");
				break;
			} else {
				System.out.println("The username and password don’t match!");
			}
			
		
		} // where while loop for log in ends
		
		while (isRunning) {
			showMenu(isFirstTime); // Display menu based on whether it's the first time
		    isFirstTime = false; 
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    // Logic for transferring money
                    transferMoney(authenticatedCustomer, input);
                    break;
                case 2:
                    // Logic for requesting money
                	requestMoney(authenticatedCustomer, input);
                    break;
                case 3:
                    // Logic for opening a new savings account
                	openSavingsAccount(authenticatedCustomer);
                    break;
                case 4:
                    // Logic for closing an account
                	closeAccount(authenticatedCustomer, input);
                    break;
                case 5:
                    System.out.println("You logged off.");
                    isRunning = false; // Exit the loop and program
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } //where while loop for menu selection ends
        
		input.close();

	} // where main method ends
	
	// Create a method to authenticate user
	public static Customer authenticate(Customer[] customers, String userId, String password) {
	    for (Customer customer : customers) { // Corrected loop syntax
	        if (customer.getUserId().equals(userId) && customer.getPassword().equals(password)) {
	            return customer; // Correct place to return true
	        }
	    }
	    return null; 
	    
	} // where myAuthenticate() ends
	
	 // Create a method to show menu for user
	 public static void showMenu(boolean isFirstTime) {
		 
		 if (isFirstTime) {
		        System.out.println("\nHow can we help you today? Please pick one of the following options:\n");
		    } else {
		        System.out.println("\nIs there anything else we can help you with today? Please pick one of the following options:\n");
		    }
	 
	        System.out.println("1. Transfer Money");
	        System.out.println("2. Request Money");
	        System.out.println("3. Open a Savings Account");
	        System.out.println("4. Close an Account");
	        System.out.println("5. Log Off");
	    } // where showMenu() ends
	 
	 // Create a method to validate amount of money 
	 public static boolean isValidAmount(double amount) {
		    return amount > 0;
		}
	 
	 // 1. Method to transfer money to a different account
	 public static void transferMoney(Customer customer, Scanner input) {
		 
		 System.out.print("What is the amount you would like to transfer?"  + "\n" + "€");
		 double amount = input.nextDouble();
		 input.nextLine();
		 
		 if (!isValidAmount(amount)) {
		        System.out.println("Invalid amount. The amount must be greater than €0.");
		        return;
		    }
		 
		 System.out.println("What is the account holder’s name that the money will be transferred?");
	        String recipientName = input.nextLine();
	        
	        if (customer.getAccountBalance() >= amount) {
	            // Perform the transfer
	            customer.setAccountBalance(customer.getAccountBalance() - amount);
	            System.out.println("€" + amount + " is transferred to " + recipientName + ". You have €" + customer.getAccountBalance() + " in your personal account.");
	        } else {
	            // Insufficient funds
	            System.out.println("Insufficient funds in your account. Transfer cannot be done.");
	        } 
	 } // where transferMoney() ends 
	 
	 // 2. Method to request money from a different account
	 public static void requestMoney(Customer customer, Scanner input) {
		 
		System.out.print("What is the amount you would like to request?" + "\n€");
		double amount = input.nextDouble();
		input.nextLine();
		

	    if (!isValidAmount(amount)) {
	        System.out.println("Invalid amount. The amount must be greater than €0.");
	        return;
	    }
		
		System.out.println("What is the account holder's name that you would like to request from?");
		String requesteeName = input.nextLine();
		
		System.out.println("€" + amount + " is requested from "+ requesteeName +" successfully.");
	
	 } // where requestMoney() ends
	 
	 // 3. Method to open a saving account
	 public static void openSavingsAccount(Customer customer) {
		 double openingBalanceRequirement = 500.0;
		 double openingFee = 10.0;

	        if (customer.hasSavingsAccount()) {
	            System.out.println("You already have a savings account.");
	            return;
	        }

	        if (customer.getAccountBalance() >= openingBalanceRequirement) {
	            // Customer has enough money to open a savings account for free
	            customer.setHasSavingsAccount(true);
	            System.out.println("Your savings account has been opened and is ready to use.");
	        } else if (customer.getAccountBalance() >= openingFee) {
	            // Deduct the opening fee and open the account
	            customer.setAccountBalance(customer.getAccountBalance() - openingFee);
	            customer.setHasSavingsAccount(true);
	            System.out.println("Your savings account has been opened with a €10 fee. Remaining balance: €" + customer.getAccountBalance());
	        } else {
	            // Not enough money to open an account
	            System.out.println("Insufficient funds. Your savings account cannot be opened.");
	        }
	    } // where openSavingAccount() ends
	 
	 // 4. Method to close an account
	 public static void closeAccount(Customer customer, Scanner input) {
	        System.out.println("Which account would you like to close? \n 1. Personal account\n 2. Savings account");
	        int choice = input.nextInt();
	        input.nextLine(); // Consume the newline left-over

	        switch (choice) {
	            case 1:
	            	// For personal account
	                customer.setAccountBalance(0);
	                System.out.println("Your personal account has been closed.");
	                break;
	            case 2:
	                // For the savings account
	                customer.setHasSavingsAccount(false);
	                System.out.println("Your savings account has been closed.");
	                break;
	            default:
	                System.out.println("Invalid option. No account closed.");
	                break;
	        }
	 } // where closeAccount() ends

} // where public class ends

class Customer {
	// Declare variables
	String firstName, lastName, userId, password;
	double accountBalance;
	boolean hasSavingsAccount = false;
	
	// Define constructors
	public Customer(String firstName, String lastName, String userId, String password, double accountBalance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
        this.password = password;
        this.accountBalance = accountBalance;
        
    }
	
	// Define methods
	public String getUserId() {
		return userId;
	}
	
	public String getPassword() {
		return password;
	}
	
	public double getAccountBalance() {
        return accountBalance;
    }
	
	public double setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
        return accountBalance;
    }
	
	public boolean hasSavingsAccount() {
        return hasSavingsAccount;
    }

    public void setHasSavingsAccount(boolean hasSavingsAccount) {
        this.hasSavingsAccount = hasSavingsAccount;
    }

	
} // where class Customer ends
