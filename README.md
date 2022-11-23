# Data-Structure
# Table of Contents
 - [ Counting BST](#one)
 - [ Linked Lists](#two)
 - [ Recursion](#three)
 - [ Room Navigator](#four)






# Description
 - using various data structures provided by Java, and the implementation details supporting them.
 - applying the right data structures to different scenarios.
 - writing and understing recursion and recursive backtracking.
 - analyzing the performance of an algorithm, working with various searching and sorting algorithms.
 
<a name="one"></a>
# Counting BST
### Overview
 - learned about binary search trees to implement a Counting Binary Search Tree
 - implementing a series of methods providing  functionality for a binary search tree that not only stores elements, but how many occurrences of them there are
<br><br>
The Node class is as follows:
```
    public class Node {
        private String element;
        private int count;
        private Node left;
        private Node right;
    
        public Node(String element) {
            this.element = element;
            this.count = 1;
        }
    
        // Getters and setters (details below).
    }
```
<br><br>
Node looks similar to the one we’ve used in class, but with two differences: each node stores string elements instead of integers, and each node tracks how many occurrences of each element there are with the count variable. As you can see from the constructor, a new node is created with an initial count of one. Node defines how our Counting Binary Search Tree works. It is a data structure that follows the standard rules of a binary search tree, but is able to store multiple copies of the same element by increasing the count tracked by its node. Node defines some simple getter methods for a node’s element, its count, and its left and right child nodes.<br><br>
In addition, it defines the following two methods:
```
    public class Node {
        // ...
        public void incrementCount() {
            count++;
        }
        public void decrementCount() {
            if (count > 0) {
            count--;
        }
    }
    // ...
}
```
<br><br>
Calling incrementCount on a Node will increase its count by one, and calling decrementCount on a Node will decrease its count by one. If a node’s count is already at 0, decrementCount will leave it at 0, so it’ll never become negative.
<br><br>
CountingBST defines several methods that you will need to implement to complete the required functionality for the data structure:
```
    public Node insert(Node root, String newElement) {
        // ...
    }
    public int getCount(Node root, String element) {
        // ...
    }
    public boolean isTreeDead(Node root) {
        // ...
    }
    public Node remove(Node root, String element) {
        // ...
    }
```
<br><br>
### Printing the Tree:
I’ve provided a print method in CountingBST that you can use to print out the contents of a counting binary search tree. It will attempt to print the contents of each node in the tree in alphabetical order, which will only work if you’ve correctly implemented insert to respect the rules of binary search tree.
<br><br>
The printed line’s format will look something like:
```
[mango: 0, pierogi: 2, ramen: 1, turkey: 4]
```
<br><br>
Each node in the printout shows its string element followed by its count. All nodes are printed, including those with a count of zero. The nodes are printed in alphabetical order by element. You can call the function to verify whether your code is working as expected. The run method makes a series of calls to the four methods listed above and prints out the tree in between.
<br><br>
###insert:
insert takes two parameters: the root node of a counting binary search tree and a new element to insert into the tree.
 - If the tree does not already contain a node with the element, a new node should be created and inserted at the right location with an initial count of one.
 - If the tree does already contain a node with the element, that existing node should have its count incremented by one. No new node should be created or inserted.
<br><br>
Either way, the method is expected to return the root of the tree. Be sure to look through the material from Lecture 23 for an example of how to insert an element into a binary search tree.
<br><br>
Once completed, you should be able to run the CountingBST program and verify that the first two printed lines yield the expected results.
<br><br>
### getCount:
getCount takes two parameters: the root node of a counting binary search tree and an element to search for. It should return the number of times that element appears in the tree. If insert was implemented correctly, there will be at most one node with the specified element, with the node storing the total count. If there is no node, getCount should return zero. Once completed, you should be able to run the CountingBST program and verify that the next several printed lines yield the expected results.
<br><br>
### isTreeDead:
isTreeDead is a method that will help us improve our implementation of remove later on (see the next section for details on how). isTreeDead takes the root of a tree as its only parameter and should return true if every single node in that tree has a count of zero, and false otherwise. isTreeDead will need to be solved recursively. However, because the binary tree is a recursively defined structure, the recursive implementation for this algorithm ends up being significantly simpler than other recursive problems you’ve tackled in the past.
<br><br>
Consider a few things:
 - An empty tree is considered dead.
 - In any tree, the root must have a count of zero for the tree to be dead.
 - The root has a left child and a right child, each of which is the root of a smaller binary tree. Making a recursive isTreeDead call to each of these two trees will tell you whether the left subtree is dead and whether the right subtree is dead. Consider how you can use that information to determine if the original tree is dead.
<br><br>
You will not be able to test whether isTreeDead works until remove is implemented as well.
<br><br>
### remove:
In lecture 23, we talked about a complicated algorithm for removing elements from binary trees. For this assignment, remove is going to do something much, much simpler: it should find the node containing the element, and simply decrement its count. If that count happens to reach zero, then the element is technically not in the data structure, even if the node still sticks around. Thankfully, this allows us to skip the complicated node removal work. 
<br><br>
There is a downside to this approach. As more and more elements are removed, their nodes will still stick around in the tree as “dead” nodes containing counts of zero. Their presence still contributes to the run time of searching through the tree, as they’ll be used in comparisons. You will need to use the isTreeDead method you implemented to make an optimization that will mitigate this problem.
<br><br>
Every time you remove an element, there is a chance that its node is now the root of a dead tree. You can check that by calling isTreeDead on the node in question. If you find that that is the case, you should completely remove that node (and its children) from the original tree structure by setting its parent node’s left or right child to null. Doing so will eliminate nodes when it’s safe to do so, reducing the number of useless nodes in the original tree structure.
<br><br>
Once completed, you should be able to run the CountingBST program and verify that the last printed line yields the expected results.
<br><br>
[GO TOP](#Data-Structure)<a name="Data-Structure"></a>

<a name="two"></a>
# Linked Lists
solve four problems:
1. Inserting a new element into a singly linked list
2. Removing several elements from a singly linked list
3. Inserting a new element into a doubly linked list
4. Removing several elements from a doubly linked list
<br><br>
## Overview
In all of our demos we worked through in lecture, we used the concept of a Node class to represent each node in a linked list, whether those nodes were for singly linked lists or doubly linked lists. In this assignment, because we need to work with both, we are naming them so they can be differentiated.
<br><br>
SingleNode defines a node that can be used for a singly linked list:
```
    public class SingleNode {
        private String element;
        private SingleNode next;
      
        // Constructor, getters, setters
    }
```
DoubleNode defines a node that can be used for a doubly linked list:
```
    public class DoubleNode {
        private String element;
        private DoubleNode next;
        private DoubleNode prev;
        
        // Constructor, getters, setters
}
```
Program defines several methods that you will need to implement to complete the assignment:
```
    public SingleNode insertInSortedOrder(SingleNode head, String newElement) {
        // ...
    }
    public SingleNode removeAllWithLength(SingleNode head, int length) {
        // ...
    }
    public DoubleNode insertInSortedOrder(DoubleNode head, String newElement) {
        // ...
    }
    public DoubleNode removeAllWithLength(DoubleNode head, int length) {
        // ...
    }
```
Note that there are two versions of insertInSortedOrder and two versions of removeAllWithLength -- each must be implemented both for singly linked lists (SingleNode version) and doubly linked lists (DoubleNode version).
<br><br>
## insertInSortedOrder
Both versions of insertInSortedOrder take the head node of a linked list as well as a String representing the new element to insert into the list. The method assumes that the input linked list is already arranged in sorted alphabetical order, and it must find the correct location to insert the new element to maintain that order. Once the new element is inserted, the method should return the head of the list.
<br><br>
To determine where the new element should be inserted, you will need to use the following method defined for String objects:
```
    public int compareTo(String other) {
        // ...
    }
```
The compareTo method allows you to compare two Strings to determine which one appears “first” in alphabetical order. Assuming that firstString and secondString are both String variables, it would be used as follows:
```
    if (firstString.compareTo(secondString) < 0) {
        // firstString is “less than” secondString.
        // firstString would appear before secondString in sorted order.
    }
```
...or, alternatively:
```
    if (firstString.compareTo(secondString) > 0) {
        // firstString is “greater than” secondString.
        // secondString would appear before firstString in sorted order.
    }
```
Of course, you will need to replace firstString and secondString in the code snippets above with the actual String variables you are comparing in your implementation.
<br><br>
Now, suppose you have a list of Strings in alphabetical order:
```
    "Alice", "Bob", "Carlos"
```
If we want to insert the String "Amelia" into the list in sorted order, we can check each element in the list starting from the beginning. If we see a String that is “less than” the new element, we can’t really do anything, because we still don’t know what else follows. However, if we see a String that is “greater than” the new element, we know that our new element belongs in the spot right before. In this example, when we see "Bob", we should know to insert "Amelia" right before it.
<br><br>
I strongly recommend looking through our lecture material and demo code related from Lectures 19 and 20 for ideas on how to approach list insertion
<br><br>
## removeAllWithLength
Both versions of removeAllWithLength take the head node of a linked list as well as a number named length. The method should remove every single String in the linked list where the String’s length is equal to the length parameter, and return the head node of the resulting linked list. If no Strings in the original list have the specified length, the list should be unchanged.
<br><br>
Important note: You could implement this by using code I’ve already provided in our demo examples to remove a single String, and just repeatedly do that until no elements are removed. However, doing so will only get you partial credit, as it’s an inefficient approach.
<br><br>
In order to receive full credit for each version, you must implement removeAllWithLength using only a single loop to go through the entire list. You can accomplish this by iterating through the list, removing a node when necessary while still in the loop (rather than breaking out of it), and allowing the loop to continue until the end of the list is reached.
<br><br>
## Edge Cases
Remember to consider the various edge cases involved with all of our list operations. What happens if you’re inserting a new element at the beginning or the end of a list? What happens if you’re removing an element from the beginning or the end of a list? How do these cases differ when dealing with singly linked lists or doubly linked lists?
<br><br>
## Verification
I’ve provided code in the starter project to help with verifying your results. The Program class defines two versions of the verify method: one for singly linked lists, and one for doubly linked lists. You can call them from main to check if a linked list looks the way it’s expected:
```
    verify(ll1, "Alice", "Amelia", "Bob", "Carlos");
```
In this example, the linked list named ll1 is being checked. If it doesn’t contain the Strings "Alice", "Amelia", "Bob", "Carlos" in that specific order, verify will throw an error with what the linked list actually contains. The doubly linked list version of verify will also check that the elements in reverse match the expected order.
<br><br>
[GO TOP](#Data-Structure)<a name="Data-Structure"></a>

<a name="three"></a>
# Recursion
## Overview
Using recursion to solve three problems:
1. A problem which uses “standard” recursion
2. A problem which uses recursion exploration
3. A version of #2 which also uses recursive backtracking
<br><br>

## LongestFinder
LongestFinder is a class that defines a method: findLongest. It initially appears as follows:
<br><br>
```
    public String findLongest(ArrayList<String> words) {
        // TODO: Implement this.
        return "";
    }
```
You will be replacing the TODO and the placeholder line (return "";) with your own implementation. findLongest should return whichever string in the ArrayList<String> parameter words has the longest length. If there is a tie between words, returning any of them is acceptable. You can assume that words will never be empty.
<br><br>
As an example, the main method in the provided starter code runs the following:
```
    LongestFinder longestFinder = new LongestFinder();
    ArrayList<String> words = new ArrayList<>();
    words.add("apple");
    words.add("banana");
    words.add("cat");
    words.add("dog");
    words.add("elephant");
    words.add("fish");
    words.add("gin");
    System.out.println("Longest: " + longestFinder.findLongest(words));
```
Once you’ve implemented findLongest correctly, this should print out:
```
    Longest: elephant
```
In order to receive credit, you must implement findLongest using recursion. There is, of course, an iterative solution:
```
    String longestSoFar = "";
    
    for (String word: words) {
        if (word.length() > longestSoFar.length()) {
            longestSoFar = word;
        }
    }
    
    return longestSoFar;
```
However, as this assignment is a test of your understanding of recursion, you’ll need to figure out the recursive solution.
<br><br>

## WordSplitPrinter
In lecture, we talked about how we can use recursion exploration to print out all permutations of a given string. For example, we could use recursion on the input “goat” to print out:
```
    goat     ogat     agot     tgoa
    gota     ogta     agto     tgao
    gaot     oagt     aogt     toga
    gato     oatg     aotg     toag
    gtoa     otga     atgo     tago
    gtao     otag     atog     taog
```
Most of these aren’t actual anagrams, because they’re gibberish. We could check which are actual words (like “toga”), but that wouldn’t be enough. There are also cases like “atgo” where the full string isn’t a word, but we can break it into the separate words “at” and “go”.
<br><br>
To find all anagrams, then, we need to find the ways we can take one of these permutations and split it into separate English words. That’s where your method, findWordSplits, comes in:
```
    public void findWordSplits(String input, TreeSet<String> allWords) {
    // TODO: Implement this.
    }
```
The method takes two parameters: input and allWords.
 - input will be a string like “atgo” or “goat” that you’re trying to split into separate words. You do not need to find permutations of input, since that was part of the demo we did in class. input will be a string whose character ordering is already set.
 - allWords is a set containing the words listed in the “words.txt” file -- nearly 10,000 English words. allWords is already populated for you! You’ll need to use it when you’re splitting up the input string, since each chunk needs to be an actual word.
<br><br>
Your task is to implement findWordsSplits to find every way you can split input into valid English words, where every character in input is used exactly once. For example, if input is “goat”, we would expect these two splits to be printed:
```
    go at
    goat

```
Other arrangements, such as “g oat” or “goa t”, would not be printed because “g”, “goa”, and “t” are not considered words. The main method uses “isawabus” as input. Once findWordsSplits is correctly implemented, you should see the following output:
```
    i saw a bus
    i saw ab us
    is aw a bus
    is aw ab us
```
If the exact ordering is not the same, that’s completely fine. I’ve provided an already implemented method in the starter code called printWordSplit:
```
    public void printWordSplit(ArrayList<String> words) {
        // Already implemented for you.
    }
```
You should call printWordSplit to print out a single arrangement. For the “i saw a bus” example above, you would call printWordSplit passing in an ArrayList<String> containing ["i", "saw", "a", "bus"] to print it out.
<br><br>
## WordSplitChecker
If you were able to complete WordSplitPrinter, then congratulations! You’ve already done most of the hard work for this last step. In WordSplitChecker, you’ll find the hasWordSplit method:
```
    public boolean hasWordSplit(String input, TreeSet<String> allWords) {
        // TODO: Implement this.
        return false;
    }
```
You will be replacing the TODO and the placeholder line (return false;) with your own implementation. hasWordSplit should return true if there is at least one way to split input into valid words (including if the entire string input is a single valid word), and false otherwise. Valid words are those that appear in the allWords set.
<br><br>
Notice that the difference between hasWordSplit and findWordSplits is that hasWordSplit doesn’t need to find every possible way to split input -- it just needs to return true as soon as it finds the first valid split, or false if there are none.
<br><br>
Counter-intuitively, hasWordSplit is actually more challenging, for a few reasons:
1. hasWordSplit needs to return a value. That means every recursive call made needs to think about how to use the return value from that call.
2. In order to receive full credit, your implementation of hasWordSplit must return a result as soon as it can. hasWordSplit should return true as soon as it finds the first word split, and stop searching for any others, as it is a recursive backtracking problem.
3. In order to receive full credit, your implementation cannot use any instance or global variables. If you need to access some piece of information, it needs to be passed from method to method using parameters and return values.
<br><br>
A lot of the recursion exploration logic for hasWordSplit will be the same as what you wrote for findWordSplits. A major difference is that in order to satisfy requirement #2 above, you’ll need to add recursive backtracking logic. Think about what it means when you recursively call hasWordSplit when exploring an option, and it returns true. What about when it returns false?

<br><br>
[GO TOP](#Data-Structure)<a name="Data-Structure"></a>

<a name="four"></a>
# Room Navigator
## Diagrams
![image](https://user-images.githubusercontent.com/89435466/203494602-554d84c5-a700-4162-bfd3-11e3f372978d.png)
![image](https://user-images.githubusercontent.com/89435466/203494628-d60a71ca-8ec7-4845-8553-d0e1aa697c80.png)
![image](https://user-images.githubusercontent.com/89435466/203494645-6eba6829-d529-49c4-8dac-94ec0f997086.png)
<br><br>
## Overview
Welcome to Room Navigator! In this assignment, you’ll use the collections you’ve learned about to implement a path search algorithm that can navigate rooms in a maze. Or in our case, a maze-like house with multiple floors and an unreasonably large number of rooms.
<br><br>
(If you’ve played the board game Betrayal at House on the Hill, you might recognize the room names; this assignment was very much inspired by the game! But don’t worry if you’re not familiar with it, as this assignment has nothing to do with the actual game).
<br><br>
The Room Navigator program represents rooms with the simple Room class, which is defined for you in the Room.java file. Every Room keeps track of its name and a list of other neighboring Rooms that are connected. For example:
```
    Room room = // ...
    String roomName = room.getName();
    ArrayList<String> connectedRoomNames = room.getConnectedRooms();
```
There is a text file included in the project starter code named “layout.txt”. Inside, you’ll see a list of room names, each followed by a semicolon and then a comma-separated list of other room names. The text file is used by the Room Navigator program to construct a list of Rooms with all of their connections set up.
<br><br>
Your task is to implement doesPathExist. The method takes in a list of Rooms (constructed from the “layout.txt” file as described above), as well as the names of a start room and an end room. It should return true if, from the start room, you can repeatedly move to a connected neighboring room until you reach the end room, or false if that is not possible.
<br><br>
To accomplish this, you’ll make use of several different collections and a simple breadth-first search algorithm. See Breadth-First Search below for the detailed steps!
<br><br>
When your implementation is complete, you should be able to call doesPathExist with different starting rooms and ending rooms and find varying answers (see the main method, which has a few sample calls). Every room is reachable from every other room on the same floor, but you’ll notice there’s no way to move between floors. If you ask whether a path exists between a ground floor room and a basement room, for example, the answer should be false.
<br><br>
## Breadth-First Search At a Glance
The algorithm we’ll use to determine whether we can find a path from a start location to a destination is called breadth-first search.
1. Begin at the start location (the starting room).
2. Look at every location that is one step away. Are any of these the destination?
3. Look at every location that is one step away from the rooms in #2. Are any of these the destination?
4. Look at every location that is one step away from the rooms in #3. Are any of these the destination?
5. etc….
<br><br>
At any point in this process, if a location you’re looking at is the destination, then you’ve found a path and you’re finished! But if you continue searching and eventually exhaust all of your search options without finding the destination, you can safely say that no such path exists.
<br><br>
In the Room Navigator program, the locations that are one step away from a room are all it’s connected neighbors. We will use the getConnectedRooms() method in implementing breadth-first search! But first…
<br><br>
## Setting Up Your Collections
If you try to jump straight into implementing the breadth-first search algorithm described above, you’re going to run into a major problem fairly quickly. Suppose you have a Room object and want to know what rooms you can reach.
<br><br>
```
    Room room = // ...
    ArrayList<String> connectedRooms = room.getConnectedRooms();
```
It’s simple enough to get a list of the immediately connected rooms. But how do we find all the rooms that are connected to those rooms?
```
    for (int i = 0; i < connectedRooms.size(); i++) {
        String connectedRoomName = connectedRooms.get(i);
        // ???
    }
```
There’s a bit of a problem here. We want to ask for the list of rooms connected to connectedRoomName, but it’s just a String name. In order to find its neighbors, we need it as an actual Room object.
<br><br>
doesPathExist is provided with a list of all of the Room objects as a parameter. In theory, we could search through this list, calling getName() on each one and comparing it connectedRoomName. But that would be fairly tedious, and we can make use of the collections we’ve learned in Java to do much better!
<br><br>
Is there a collection which you can use to quickly look up the Room object associated with a specific name? (Hint: yes there is, it’s a map). The first step, then, would be to initialize this collection using the data in the list of Rooms that is passed in as a parameter.
<br><br>
The next step is to think about what collection you’re going to need in a breadth-first search.
 - Suppose you explore room A, which is connected to rooms B, C, and D. You add B, C, and D to your collection as a way of indicating that you need to explore them later.
 - You’ve finished looking at A, so you move onto the next room: B.
 - Room B is connected to E and F. We add E and F to the collection as well.
 - C and D still need to be explored, and now so do E and F. Breadth-first search dictates that we check the “closer” rooms first -- C and D, which were one step away from A. Then, we can look at E and F.
<br><br>
If you pay close attention to the order in which we need to look at rooms, the collection’s requirements fit the description of a Queue. The older elements in the collection are processed first. In the case of breadth-first search, the locations that are just one step away need to be processed before the locations that are two steps away. So we’ll be using a queue as well!
<br><br>
## Breadth-First Search In More Depth
Knowing what collections we’ll need, we can now apply the high-level description of breadth-first search to our program and define the following pseudo-code:
```
    Initialize a queue of room names.
    Add the starting room name to that queue.
    
    While the queue isn’t empty:
        Remove the room name from the queue.
        If that room name matches the end room name, return true. Done!
        Get the full room information from the room name.
        Get the list of rooms connected to this room.
        For each of those connected rooms:
            Add the connected room’s name to the queue.
    If you exhaust the queue without finding the end room, return false.
```
And that is breadth-first search!
<br><br>
## Wait… This Doesn’t Work
If you’ve correctly implemented everything we’ve described so far and try to run the program, you’ll find that it runs forever! That’s an infinite loop (and a problem). You can force the program to stop running by clicking on the red square in the top-right corner of IntelliJ.
<br><br>
When it comes to breadth-first search returning false, the program will only do so if the queue of rooms that need to be explored becomes empty. That will only happen if we stop adding new connected rooms to check
<br><br>
What happens if there’s a “cycle” of connected rooms? For example, suppose A is connected to B, B is connected to C, and C is connected to A. Or: A is connected to B and B is connected to A. In either case, the breadth-first search loop will always find more connected rooms to add to the queue, and it will never end.
<br><br>
To fix this problem, consider that if you’ve already seen room B during the search, there’s no point in looking at B a second (or third, etc.) time. No new information will be gained -- so you can just skip the room those subsequent times. We will need to use a collection to track the names of rooms that have already been visited. When a room name is removed from the queue, check if it has already been visited. If so, just skip ahead to the next iteration of the loop (you can use the continue keyword). If not, then add the room name to that collection of already-visited rooms, and carry on with processing the room.
<br><br>
[GO TOP](#Data-Structure)<a name="Data-Structure"></a>




















