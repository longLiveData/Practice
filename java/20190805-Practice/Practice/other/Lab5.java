/*-------------------------------------------------------------
// AUTHOR: Zeyang Wu
// FILENAME: Lab5
// SPECIFICATION: switch operation.
// FOR: CSE 110- Lab #5
// TIME SPENT: two hours
//-----------------------------------------------------------*/

import java.util.Scanner;

public class Lab5 {
    public static void main(String[] args) {
        final char SIDE_SYMB = '-';
        final char MID_SYMB = '*';
        Scanner scanner = new Scanner(System.in);
        String inputStr = "";
        char choice = ' ';
        int numSymbols = -1, sideWidth = -1, midWidth = -1;

        do {
            displayMenu();
            inputStr = scanner.nextLine();
            if (inputStr.length() > 0) {
                choice = inputStr.charAt(0);
            }

            switch (choice) {
            case 'r':
                System.out.println("Width of the sides?");
                sideWidth = scanner.nextInt();
                System.out.println("Width of the middle?");
                midWidth = scanner.nextInt();
                scanner.nextLine();  // Flush junk newline symbols

                System.out.println();
                System.out.println(buildRow(SIDE_SYMB, sideWidth, MID_SYMB, midWidth));

                break;
            case 'p':
                System.out.println("Number of symbols on the lowest layer?");
                numSymbols = scanner.nextInt(); 
                scanner.nextLine();  // Flush junk newline symbols

                System.out.println();
                System.out.println(buildPyramid(SIDE_SYMB, MID_SYMB, numSymbols));

                break;
            case 'd':
                System.out.println("Number of symbols on the middle layer?");
                numSymbols = scanner.nextInt();
                scanner.nextLine();  // Flush junk newline symbols

                System.out.println();
                System.out.println(buildDiamond('*', ' ', numSymbols));

                break;
            case 'q':
                System.out.println("Bye");
                break;
                
            default:
                System.out.println("Please choose a valid option from the menu.");
                break;
            }
            System.out.println();
        } while (choice != 'q');
        
        scanner.close();
    }
    
    
    /**
     * Build a row of symbols (pattern) with the given parameters. 
     *
     * For example, -----*****----- can be built by the parameters
     *
     * sideWidth = 5, midWidth = 5, sideSymb = '-', midSymb = '*'
     * 
     * @param sideSymb  A char to be repeated on both sides
     * @param sideWidth Number of symbols on each side
     * @param midSymb   A char to be repeated in the middle
     * @param midWidth  Number of symbols in the middle
     * @return          A String of a row of the designed pattern
     */
    private static String buildRow(
        char sideSymb, int sideWidth, char midSymb, int midWidth) {

        String result = "";

        // YOUR CODE HERE
        
        // Make one side
        // -->
        String side = "";
        for (int i=0; i<sideWidth; i++) {
            side += sideSymb;
        }
        // Make the middle part
        // -->
        String middle = "";
        for (int i=0; i<midWidth; i++) {
            middle += midSymb;
        }
        // Combine side + middle + side, save into "result"
        // -->
        result = side + middle + side;

        return result;
    }  // End of buildRow

    /**
     * Build a pyramid pattern with the given parameters.
     *
     * For example, the following pattern
     *
     *  -----*-----
     *  ----***----
     *  ---*****---
     *  --*******--
     *  -*********-
     *  ***********
     *
     * can be built by sideSymb = '-', midSymb = '*', numSymbols = 11
     *
     * When ptnHeight is not an odd integer, replace it by the closest
     * even integer below. For example, if numSymbols is 10, use 9 instead.
     * 
     * When ptnHeight is 0, return an empty String.
     * 
     * @param  sideSymb   A char to be repeated on both sides
     * @param  midSymb    A char to be repeated in the middle
     * @param  numSymbols The number of symbols on the lowest layer
     * @return            A String of the pyramid pattern.
     */
    private static String buildPyramid(
        char sideSymb, char midSymb, int numSymbols) {

        String result = "";
        int sideWidth = -1, midWidth = -1;

        // YOUR CODE HERE
        
        // If numSymbols is 0, return an empty string
        // -->
        if (numSymbols == 0) {
            return result;
        }
        
        // If numSymbols is not an odd number, find the
        // odd number less than numSymbols and replace it
        // -->
        if (numSymbols % 2 == 0) {
            System.out.println("The input is not an odd number. Use the closest odd number below " + numSymbols);
            numSymbols = numSymbols - 1;
        }
        
        // Make a loop to iterate the pyramid's levels
        for (int i=1; i <= numSymbols; i += 2) {
            // Compute the number of middle symbols
            // -->
            int midNum = i;
            // Compute the number of symbols on one side
            // -->
            int sideNum = (numSymbols - i) / 2;
            
            // Use the "buildRow" method to make a row, then
            // add the row to the variable "result".
            // You may need to add a linebreak char "\n".
            // -->
            result += buildRow(sideSymb, sideNum, midSymb, midNum) + "\n";
        }

        return result;
    }

    /**
     * Build a diamond pattern. The parameters are the same 
     * as {@link #buildPyramid(char, char, int)}.
     * 
     * @param  sideSymb  A char to be repeated on both sides
     * @param  midSymb   A char to be repeated in the middle
     * @param  numSymbols The height of a pyramid
     * @return           A String of the inverted diamond pattern.
     */
    private static String buildDiamond(
        char sideSymb, char midSymb, int numSymbols) {

        String result = "";
        
        // If numSymbols is 0, return an empty string
        // -->
        if (numSymbols == 0) {
            return result;
        }
        
        // If numSymbols is not an odd number, find the
        // odd number less than numSymbols and replace it
        // -->
        if (numSymbols % 2 == 0) {
            System.out.println("The input is not an odd number. Use the closest odd number below " + numSymbols);
            numSymbols = numSymbols - 1;
        }
        
        // Make a loop to iterate the pyramid's levels
        for (int i=1; i <= numSymbols; i += 2) {
            // Compute the number of middle symbols
            // -->
            int midNum = i;
            // Compute the number of symbols on one side
            // -->
            int sideNum = (numSymbols - i) / 2;
            
            // Use the "buildRow" method to make a row, then
            // add the row to the variable "result".
            // You may need to add a linebreak char "\n".
            // -->
            result += buildRow(sideSymb, sideNum, midSymb, midNum) + "\n";
        }
        
        for (int i=numSymbols-2; i >= 1; i -= 2) {
            // Compute the number of middle symbols
            // -->
            int midNum = i;
            // Compute the number of symbols on one side
            // -->
            int sideNum = (numSymbols - i) / 2;
            
            // Use the "buildRow" method to make a row, then
            // add the row to the variable "result".
            // You may need to add a linebreak char "\n".
            // -->
            result += buildRow(sideSymb, sideNum, midSymb, midNum) + "\n";
        }
        
        
        return result;
    }
    
    
    /**
     * Display the menu
     */
    private static void displayMenu() {
        System.out.println("Please choose one pattern from the list:");
        System.out.println("r) Row");
        System.out.println("p) Pyramid");
        System.out.println("d) Shallow diamond");
        System.out.println("q) Quit");
    }  // End of displayMenu

}  // End of Lab5
