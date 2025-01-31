
/*
 * *** Alexa Solorzano COMP 272 001 ***
 *
 * Homework # 1 (Programming Assignment). This Java class defines some basic
 * manipulation operations on Linked-Lists and Stacks.
 *
 * Additionally, there are two algorithm analysis methods where you need
 * to return a specified number as provided in comments of each method indicating
 * the complexity of the code shown. The testing routine will be looking for a
 * specific number returned.
 */

import java.util.Stack;

public class HW1 {

    /*
     * Class LinkedList
     *
     * This class builds a singly linked list. Each node of the linked-list
     * contains a single integer values.
     *
     * Methods:
     *  - void   sortInserted(val)     - Inserts value 'val' into the linked-list in
     *                                   sorted fashion
     *  - void   removeElementsLT(val) - Removed values in the linked-list that are less
     *                                   than 'val'
     *  - void   removeElement(val)    - Removes all values in the linked list of
     *                                   value 'val'
     *  - String toString()            - coverts and returns the linked-lists as a string
     *                                   delimited by brackets []
     *
     */

    static class LinkedList {
        static class Node {
            int data;
            Node next;

            Node(int d)  {        // Constructor
                data = d;
                next = null;
            }
        }
        Node head;                // head of Linked-list


        /*
         * Method sortedInsert() - this method will insert a new node to the
         * linked list containing the value specific in teh parameter 'data'.
         * The newly inserted node will be inserted in sorted order within
         * the linked-list.
         *
         */
        public void sortedInsert ( int data ) {
            Node new_node = new Node(data); // create new node with provided data

            new_node.next = null; // To keep the list in sorted order, the new node doesn't point to another node

            // Special case for head node.
            if (this.head == null || head.data >= new_node.data ) { //if the list is empty or if the new nodes data is less the the heads
                new_node.next = head; // new node point to the current head
                head = new_node; // the new node is the first element of the list
            } else {
                // locate the node before the point of insertion
                Node current = this.head; // iterate through the pointers

                // Identify where to place the item to insert
                while (current.next != null && current.next.data < data) { //while there is a next node and it is greater than/equal to the new data
                    current = current.next; // shift current to the next node in the list
                }
                new_node.next = current.next; //insert new node between current and current.next
                current.next = new_node;
            }

            return;
        }


        /*
         * Method removeElementsLT() - this method removes all nodes that contain a
         * value that is less than the provided parameter 'ltValue'.
         *
         * The method will invoke the method removeElements for each element
         * found in the linked-list that is less than thr parameter value passed.
         */
        public void removeElementsLT ( int ltValue ) {
         /*
         * Keeping track of the previous & current node will allow me to remove the nodes from the linked list.
         * Initialize current to head
         * Initialize previous to null 
         * 
         * while(current is not null):
         *  if(current's data is LESS THAN the target value):
         *      if(previous pointer is null) (i.e., we are at the head node):
         *          set head to the current's next (this removes the head node)
         *       else:
         *          set previous next  to current's next (will ensure the linked list will skip over the current node, therefore removing it)
         *  else {
         *      set previous to current (move previous down the list when no removal happens)
         *    
         *    set current to current's next (move current forward to the next nodes)
         * 
         */

        Node current = head;
        Node previous = null; 

        // traverse the list
        while(current != null){
            if(current.data < ltValue){ //current node's data is less than the targets value
                if(previous == null){ // the current node is the head node
                    head = current.next; // remove head by setting head to the next node
            } else { // current node is not the head node
                previous.next = current.next; //skip current node, remove it
            }
        } else {
            previous = current; // move previous pointer forward if we don't remove the node
        }
            current = current.next; // move current pointer to the next node
        } 
        }


        /*
         * Method removeElement() - this method removes all nodes that contain a
         * value equal to the value the provided parameter 'value'.
         */

        public void removeElement ( int value ) {
        /*
         * Keeping track of the previous & current node will allow me to remove the nodes from the linked list.
         * Initialize current to head
         * Initialize previous to null 
         * 
         * while(current is not null):
         *  if(current's data equals the target value):
         *      if(previous pointer is null) (i.e., we are at the head node):
         *          set head to the current's next (this removes the head node)
         *       else:
         *          set previous next  to current's next (will ensure the linked list will skip over the current node, therefore removing it)
         *  else {
         *      set previous to current (move previous down the list when no removal happens)
         *    
         *    set current to current's next (move current forward to the next nodes)
         * 
         */
        Node current = head;
        Node previous = null;

        // traverse the list 
        while(current != null){
            if(current.data == value){
                // if we're removing the head node
                if(previous == null){
                    head = current.next; // move head to the next node
                } else {
                    previous.next = current.next; //skip current node
                }
            } else {
                previous = current;
            }
            current = current.next;
          }
        }


        /*
         * Method toString() - this is a helper method for printing / constructing
         * a string object from the linked-list.
         */
        public String toString () // Method to output the LinkedList as a String
        {
            String output = "[";
            Node currNode = this.head;
            while (currNode != null) {
                output += currNode.data + " ";
                currNode = currNode.next;
            }
            return output.trim() + "]";
        }

    } // End class LinkedList




    /*
     * Class Stacks
     *
     * This class utilizes the Java Common Framework Library Stack class.
     *
     * The intent of this class is to write methods which utilize the Java
     * library's Stack class. You need to utilize this library class in
     * your solution.
     *
     * Methods:
     *  - boolean isPalindrome(string)   - method returns true or false if 'string'
     *                                     is a palindrome
     *  - int     findlargestK(stack, k) - method accepts a stack and returns the
     *                                     the largest index in the stack containing
     *                                     value 'k' (stack index starts at 0)
     *
     */

    static class Stacks {

        /*
         * Method isPalindrome() - This method will return the boolean value 'true'
         * or 'false' on if the passed in parameter string is a palindrome or not.
         *
         * The routine should be case insensitive! Meaning 'RaCe cAr' is a palindrome.
         * Moreover, spaces are ignore, so both 'race car' and 'racecar' are plaindromes.
         *
         * The method should utilize the provided Stack class.
         */

         /*
          * First thing to consider in this method is that the input is a String. However, to accurately assess a potential palindrome, we have to be able to analyze every character in the input.
          * That is why the stack's type argument will be declared as character
          * My first step should be to input all the characters of the String input into the stack 
          * By inputting the characters into the stack, the string is in reverse order 
          * Then we can start comparing the first character (at index 0) with the character coming out of the stack
          * Stacks are LIFO (Last-in & First-out)
          * When we call the .pop() we will be getting the last character of the string 
          * Say our non-palindrom string is cat
          * To put it into our stack we would have to .push(object) to insert 
          * The stack will look like 
          * t
          * a
          * c
          * for(the length of the string)
          *     insert the character at index x of the string into the stack
          *
          * declare a boolean variable to track if the string is palindrome
          *
          * for(the size of the stack)
          *     if(the current character of the string is NOT the same as the recently pushed character of the stack)
          *         the boolean variable is declared false
          * 
          * since it was not found false, then we return the boolean variable which is TRUE
          */
        public static boolean isPalindrome(String input) {

            Stack<Character> stack = new Stack<>();
            input = input.toLowerCase().replaceAll("\\s+", "");
            // push all characters of the string into the stack
            for(int i = 0; i < input.length(); i++){
                stack.push(input.charAt(i));
            }
            // use boolean variable to track if the string is a palindrome 
            boolean isPalindrome = true; // assume it is a palindrom 

            for(int i = 0; i < input.length(); i++){
                if(input.charAt(i) != stack.pop()){
                    isPalindrome = false;
                }
            }
            return isPalindrome;
        }


        /*
         * Method findLargestk() - This method will return the largest index
         * position in the stack for the value specified by the parameter 'k'.
         *
         * Note that the bottom of the stack is index location 0. So it you push
         * on to the stack the values 3 4 9 4 4 7 4, in that order. And you pass the
         * value '4' for the parameter k, then the largest index position is index
         * location 6.
         *
         * The method should utilize the provided Stack class. This method should NOT
         * destroy the passed in stack, meaning when the method returns, the passed in
         * stack should be identical to when this method is passed. One trick as you
         * pop elements off the passed in stack, place them in a temp stack. Then when
         * completed, place them all back in teh original stack.
         * 
         * 
         * initialize the temp stack 
         * for(the length of the orginal stack)
         *  use push to add the int from the first stack onto the new temp stack--avoid doing it in reverse order = have to keep the order the same
         * 
         * declare int variable to hold the highest index 
         * 
         * for(the lenght of the temp stack)
         *  keep track of what index the target int is in + look for the highest index 
         *  attach the value of the highest index to the int variable 
         *  delete temp stack and make sure original stack is untouched
         * 
         *  return int variable 
         */
        public static int findLargestK(Stack<Integer> stack, int k) {

            // YOUR CODE GOES HERE
            return -1;
        }

    }  // End class Stacks



    /*******************************
     *
     * Algorithm Analysis
     *
     * Below are two methods, algorithmAnalysis1 and algorithmAnalysis2.
     * Modify the return statement to return the correct option number.
     *
     *********************************/

    public static int algorithmAnalysis1(int n, int m) {
        int a = 0, b = 0;

        for (int i=0; i < n; i++)
            a+= Math.random();

        for (int j=0; j < m; j++)
            b+= Math.random();

        /*
         * Select the correct option listed below:
         *   1. O(N * M) time, O(1) space
         *   2. O(N + M) time, O(N + M) space
         *   3. O(N + M) time, O(1) space
         *   4. O(N * M) time, O(N + M) space
         *
         * From class notes, I know that time complexity is based on counting the steps in a program 
         * All loops that grow proportionally to the input size have a linear time complexity O(n) 
         * both the for loops have 1) constant time operation (Math.random()) and a +=
         * For any loop we find the run-time of the block inside them & multiply by the # of times the program repeats the loop 
         * the loops are both  executed by n & m 
         * T(n) = n * t(a+= Math.random()) = O(n)
         * T(m) = m * t(b+= Math.random()) = O(m)
         * We get O(n+m)
         * 
         * a & b are simple integer variables that don't take increasing space as the input increases --> O(1)
         * loops don't require additional space for storing data --> O(1)
         * 
         * TODO: return the answer (which option is correct), in the return statement
        */

        // RETURN THE CORRECT OPTION NUMBER LISTED ABOVE
        return 3;
    }


    public static int algorithmAnalysis2(int n) {
        int i, j, k = 0;
        for (i = n/2; i <= n; i++)
            for ( j = 2; j <= n; j = j*2 )
                k+= n/2;

        /*
         * Select the correct option listed below:
         *   1. O(N) time
         *   2. O(N log N) time
         *   3. O(N^2) time
         *   4. O(N^2Log n) time
         *
         * TODO: return the answer (which option is correct), in the return statement
         * 
         * first for loop:
         * runs from i = n/2 to i=n
         * the total number of iterations would be 
         * n - n/2 = n/2
         * Big-O notation we drop the constants so we would be left with O(n)
         * 
         * second NESTED for loop:
         * variable j starts at 2 and in each iteration it is MULTIPLIED by 2
         * j = j*2 
         * this is a logarithmic progression 
         * loops continues while j <= n which means it'll run log2(n) times
         * Complexity is O(log n)
         * k+= n/2 is constant time operation
         * O(n) x O(log n) = O(n log n)
         */

        // RETURN THE CORRECT OPTION LISTED ABOVE
        return 2;
    }

}
