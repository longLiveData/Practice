/*-------------------------------------------------------------
// AUTHOR: Zeyang Wu
// FILENAME: Lab4
// SPECIFICATION: switch operation.
// FOR: CSE 110- Lab #4
// TIME SPENT: three hours
//-----------------------------------------------------------*/

// Import Scanner class
import java.util.Scanner;

public class Lab4 {    
    
    public static void main(String[] args) {     
        
        Scanner sc = new Scanner(System.in);
        
        // Declare some variables you need
        String op = "";
        String m;
        
        do {            
            // Display the menu            
            displayMenu();

            // Ask the user for one option  
            op = sc.nextLine();
            
            switch (op) {                
                // Define four cases for different options. Don't forget "break".                
                case "1":
                    // ask for m
                    System.out.println("Enter a number:");
                    m = sc.nextLine();
                    int sumEnd = Integer.parseInt(m);
                    // compute the sum of 1 to m
                    int sum = 0;
                    for (int i = 1; i <= sumEnd; i++){
                        sum += i;
                    }
                    // print
                    System.out.println("The sum of 1 to " + sumEnd + " is " + sum);
                    break;
                case "2":
                    // ask for m
                    System.out.println("Enter a number:");
                    m = sc.nextLine();
                    int factorialEnd = Integer.parseInt(m);
                    // compute m!
                    int factorial = 1;
                    for (int i = 2; i <= factorialEnd; i++){
                        factorial *= i;
                    }
                    // print
                    System.out.println("The factorial of 1 to " + factorialEnd + " is " + factorial);
                    break;
                case "3":
                    // ask for m
                    System.out.println("Enter a number:");
                    m = sc.nextLine();
                    // compute the left most digit of m
                    char leftMost = m.charAt(0);
                    // print
                    System.out.println("The leftmost digit of " + m + " is " + leftMost);
                    break;
                case "4":
                    // print bye
                    System.out.println("Bye");
                    break;
                default:
                    break;
            }        
            System.out.println();
        } while (! op.equals("4"));
    }    
    
    /**     
     * Print the menu     
     */    
    private static void displayMenu() {        
        System.out.println("Please choose one option from the following menu:");        
        System.out.println("1) Calculate the sum of integers from 1 to m");        
        System.out.println("2) Calculate the factorial of a given number");        
        System.out.println("3) Display the leftmost digit of a given number");        
        System.out.println("4) Quit");    
    }
}