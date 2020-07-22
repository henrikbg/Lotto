/*
    Henrik Berg, 9/11/18
    Lotto.java

    Precondition for selection sort algorithm: an array (unsorted or sorted)

    Selection sort algorithm:
    - Receive an array
    - Create a for loop to go through all the indexes in the array
        - Initialize a 'best' variable and set it to the index number in the array
        - Create a nested for loop checking the number in front of k
            - Check if the next number is less than the current k index set the current index to the next number
        - Swap the best and k array index values

    Postcondition for selection sort algorithm: sorted array

    Precondition for bubble sort algorithm: an array (unsorted or sorted)

    Bubble sort algorithm:
    - Receive an array
    - Set a 'done' variable to false (to start while loop)
    - Create a while loop to keep sorting until array is sorted (done is true)
        - Set done variable to true (assuming it's sorted at the beginning)
        - Compare each pair of adjacent items
        - Swap items if they are in the wrong order (small after large)
        - If a swap occurs, set 'done' to false
    - Will continue to loop until array is sorted and will have a final looping to check everything is sorted
    
    Postcondition for bubble sort algorithm: sorted array and done = true

    Precondition for binary search algorithm: array must be in sorted order
    
    Binary search algorithm:
    - Receive an array and user guess
    - Call the sorting method to sort the array from smallest to largest
    - Initialize the left variable (or bound) to 0
    - Initialize the right variable (or bound) to the length of the array subtracted by 1
    - Create a for loop to check that the left bound is less or equal to the right bound (to avoid overlapping)
        - Initialize the middle variable to be the number in the middle of the array
        - Check if the user input is equal to the middle number
            - If not, check if it's less than the middle and reassign the right bound to middle subtracted by 1
            - If not less than the middle, assign the left variable to the middle added by 1
    - Return -1 if the number is not found in the array

    Postcondition for binary search algorithm: middle (actual number, not index) or -1 (to indicate false, not found in range)
*/

package lotto;

import java.util.Scanner;

public class Lotto {

    public static void main(String[] args) {
        
        // Initializations
        int[] randomNumberList = new int[100];
        int userInput, balance = 0, arrayResult;
        boolean win = false;
        final int LOWER_BOUND = 100, UPPER_BOUND = 999;
        String stringInput;
        
        // Creating scanner
        Scanner inputReader = new Scanner(System.in);
        
        // Do while loop to keep on repeating until the user exits
        do {
            
            // Ask user if they want to enter the program
            System.out.print("Welcome to the lotto! Enter 'yes' to buy a ticket for $5 or 'exit' to exit: ");
            stringInput = inputReader.nextLine();
            
            // Check if they entered the program
            if(stringInput.equals("yes")) {
                
                // For loop to generate random numbers
                for(int i = 0; i < randomNumberList.length; i++)
                    randomNumberList[i] = randomNumberGenerator(LOWER_BOUND, UPPER_BOUND);
            
                // Apply the entry fee
                balance = balance - 5;
            
                // Ask user for random number input
                System.out.print("Enter a random number between 100 and 999: ");
                userInput = inputReader.nextInt();
            
                // Boolean to check if user input is found in array
                arrayResult = binarySearch(randomNumberList, userInput);
                
                if(arrayResult != -1)
                    win = true;

                // If statement to check if they won or not
                if(win == true) {
                    // Add $100 for winning
                    balance = balance + 100;
                    
                    System.out.println("Congratulations your number matched! You won 100 dollars.");
                } else
                    System.out.println("Unfortunately, your number did not match.");
            
            } else if (stringInput.equals("exit"))
                outputBalance(balance);
            
            System.out.print("");
            stringInput = inputReader.nextLine();

        } while(!stringInput.equals("yes"));
        
    }
    
    // Method to output balance and quit program
    public static void outputBalance(int balance) {
        // Output final balance and postcondition
        System.out.println("Thank you for using my program! Your final balance is: " + balance + " dollars.");
        System.exit(0);
        
    }
    
    public static boolean linearSearch(int[] userArray, int userInput) {
        
        for(int i = 0; i < userArray.length; i++) {
            
            if( (int) userArray[i] == userInput) {
                return true;
            }
        }
        return false;
    }
    
    // Searching through the array method
    public static int binarySearch(int[] userArray, int userInput) {
        
        // Calls method that sorts given array
        // selectionSort(userArray);
        
        selectionSort(userArray);
        
        // Initializing the left and right bounds for the search
        int left = 0, right = userArray.length - 1;
        
        // Loop to check that right never is less than left
        while(left <= right) {
            
            // Finding the middle of the numbers in the array
            int middle = (left + right) / 2;
            
            if(userInput == userArray[middle])
                return middle;
            else if(userInput < userArray[middle])
                right = middle - 1;
            else
                left = middle + 1;
            
        }
        
        return -1;
    }
    
    // Selection sort method
    public static void selectionSort(int[] userArray) {
        
        for(int k = 0; k < userArray.length - 1; k++) {
            
            // Finds the best number and replaces original
            int best = k;
            
            for(int q = k + 1; q < userArray.length; q++)
                if(userArray[q] < userArray[best])
                    best = q;
            
            swap(userArray, k, best);
        }
        
    }
    
    // Bubble sort method
    public static void bubbleSort(int[] userArray) {
        
        // False to initiate while loop
        boolean done = false;
        
        while(!done) {
            
            // Set to true (reset)
            done = true;
            
            for(int k = 0; k < userArray.length - 1; k++) {
                
                // Sorting by swapping adjacent items
                if(userArray[k + 1] < userArray[k]) {
                    
                    swap(userArray, k, k+1);
                    
                    done = false;
                }
                      
            }
            
        }
        
    }

    // Swapping numbers in array using temporary variable
    public static void swap(int[] userArray, int i, int j) {
        
        int temporary = userArray[i];
        
        userArray[i] = userArray[j];
        
        userArray[j] = temporary;
    }
    
    // Random number generator method
    public static int randomNumberGenerator(int min, int max)
    {
        int range = (max - min) + 1;   
        
        return (int)(Math.random() * range) + min;
    }
    
}
