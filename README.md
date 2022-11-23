# Data-Structure

# Description
 - using various data structures provided by Java, and the implementation details supporting them.
 - applying the right data structures to different scenarios.
 - writing and understing recursion and recursive backtracking.
 - analyzing the performance of an algorithm, working with various searching and sorting algorithms.
 
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
