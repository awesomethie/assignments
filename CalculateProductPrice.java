/*
Please provide the information about the packages below.
Weight and price of package 1: 
1.5 10
Weight and price of package 2: 
2.5 15
Package 1 has a worse unit price!
Which package would you like to order? 
2
How many packages would you like to order? 
4
The total cost of 4 packages will be 60 euros.*/

import java.util.Scanner;

public class CalculateProductPrice {

	public static void main(String[] args) {
		// Step 1: Read in the weight and price of 2 packages
		
		Scanner input = new Scanner (System.in);
		System.out.println("Please enter the weight and price of package 1: ");

		
		double weight1 = input.nextDouble();
		double price1 = input.nextDouble();
		if (weight1 <= 0 || price1 <= 0) {
            System.out.println("Inputs are invalid. All values must be positive.");
            input.close();
            return;
		}
		
		System.out.println("Next, enter the weight and price of package 2: ");
		
		double weight2 = input.nextDouble();
		double price2 = input.nextDouble();
		if (weight2 <= 0 || price2 <= 0) {
            System.out.println("Inputs are invalid. All values must be positive.");
            input.close();
            return;
		}
		
		// Step 2: Compute the unit price of each package
		
		double unitPrice1 = price1/weight1, unitPrice2 = price2/weight2;
		
		// Step 3: Display the results
		
		if (unitPrice1 > unitPrice2) {
			System.out.println("Package 1 has a worse unit price.");
		} else if (unitPrice1 < unitPrice2){
			System.out.println("Package 2 has a worse unit price.");
		} else {
            System.out.println("Both packages have the same unit price.");
        }
		
		// Step 4: Read in user's option to buy package(s)
		
		System.out.println("Which package would you like to order?");
		
		int myPackage = input.nextInt();
		 if (myPackage != 1 && myPackage != 2) {
	            System.out.println("Invalid package selection. Please try again.");
	            input.close();
	            return;
	        } 
	        	
		System.out.println("How many packages would you like to order?");
	        
		
	
		int noOfPackage = input.nextInt();
		 if (noOfPackage <= 0) {
	            System.out.println("Number of packages must be positive.");
	            input.close();
	            return;
		 }
		
		// Step 5: Compute the total price and display the results
		
		if (myPackage == 1) {
			double totalPrice1 = price1*noOfPackage;
			System.out.println("The total cost of " + noOfPackage +" packages will be " + totalPrice1 + " euros.");
		} else {
			double totalPrice2 = price2*noOfPackage;
			System.out.println("The total cost of " + noOfPackage +" packages will be " + totalPrice2 + " euros.");
		}

		input.close();		
		

	}

}
