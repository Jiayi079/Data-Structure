package edu.csc220.trees;

import java.util.ArrayList;

public class CountingBST {
    /** Returns the number of occurrences of element in the counting binary search tree starting at the given root. */
    public int getCount(Node root, String element) {

        // declare a Node element to present the root
        Node curr = root;

        // run the loop until the curr (root) is null
        while (curr != null) {

            // check if the element is equal the curr by using compare the alphabet
            if (element.compareTo(curr.getElement()) == 0) {
                // if equal, which means we find the element we need,
                // then return this element's count
                return curr.getCount();
            }
            // check if the element is smaller than the curr
            else if (element.compareTo(curr.getElement()) < 0) {
                // if it is true, which means the element will be curr's left child node
                curr = curr.getLeft();
            } else { // else, it is bigger than the curr
                // if it is true, which means the element will be curr's right child node
                curr = curr.getRight();
            }
        }

        return 0;
    }

    /**
     * Inserts the element into the counting binary search tree starting at the given root. If there is already a node
     * in the tree containing newElement, its count should be incremented.
     */
    public Node insert(Node root, String newElement) {

        // declare two Nodes to present the current node, which is the root
        // and the prev node, which is the parent node for the curr
        Node prev = null;
        Node curr = root;

        // run the loop until curr is null
        while (curr != null) {
            // check if the newElement is equal to the curr element
            // by using compare the alphabet
            if (newElement.compareTo(curr.getElement()) == 0) {
                // if it is equal, which means the tree does already contain a node with the element,
                //
                curr.incrementCount();
                // return the root since we already finish the insert
                return root;
            } // check if the newElement is smaller then the curr element
            else if (newElement.compareTo(curr.getElement()) < 0) {
                // if it is true, which means the position for the newElement should become it's left child
                // we need set the curr node become the prev node
                // and the curr's left child node become curr node
                prev = curr;
                curr = curr.getLeft();
            } else {
                // else, which means the position for the newElement should become it's right child
                // set the curr node become prev node
                // and the curr's right child node become curr node
                prev = curr;
                curr = curr.getRight();
            }
        }

        // declare one newNode with the newElement whcih we want to insert
        Node newNode = new Node (newElement);

        // check if the prev is equal null, which means curr is null as well
        if (prev == null) {
            // set the newNode become the root since it is an empty root
            root = newNode;
        } // check if the newElement is smaller than the prev node,
        // which means the newElement should become the prev's left child
        else if (newElement.compareTo(prev.getElement()) < 0) {
            // set prev's left child become newNode
            prev.setLeft(newNode);
        } else {
            // otherwise the newNode should become prev's right child
            prev.setRight(newNode);
        }


        return root;
    }

    /**
     * Returns true if every node in the counting binary search tree starting at the given root has a count of 0, and
     * false otherwise.
     */
    public boolean isTreeDead(Node root) {

        // declare curr node become the root of the tree
        Node curr = root;

        // if root already is null, the tree is dead
        if (curr == null) {
            // return true, which means tree is dead
            return true;
        }

        // check curr's child node if curr is not null by using the while loop
        while (curr != null) {
            // check if curr's count is zero
            // or check if curr's left child is zero
            // or check if curr's right child is zero
            // using recursive to check child node's left and right child until curr is null,
            // which means already check all of the node in the tree
            if (curr.getCount() == 0 ||
                    isTreeDead(curr.getLeft()) ||
                    isTreeDead(curr.getRight())) {
                // as long as one's count is equal 0, return true
                return true;
            }
        }

        // otherwise return false
        return false;
    }

    /**
     * Removes the specified element from the counting binary search tree starting at the given root. Elements should be
     * removed by decrementing the count stored in their corresponding node. If doing so results in that node being the
     * root of a dead tree, that entire subtree should be removed entirely from the original tree.
     */
    public Node remove(Node root, String element) {

        // declare curr and prev node
        Node curr = root;
        Node prev = null;

        // run the loop until curr is null
        while (curr != null) {
            // check if the element compare to the curr's element is zero
            if (element.compareTo(curr.getElement()) == 0) {
                // if it is true, decrement the curr's count by 1
                curr.decrementCount();
                // since we already find the element we want to remove
                // so break the loop
                break;
            } // check if the element is smaller than the curr's element
            else if (element.compareTo(curr.getElement()) < 0) {
                // if it is, the element we want to remove should become curr's left side
                // set the curr node become prev node
                // and curr's left child node become curr node
                prev = curr;
                curr = curr.getLeft();
            } // else, the element is larger than the curr's element
            else {
                // set the curr node become prev nod
                // and the curr's right child node become curr node
                prev = curr;
                curr = curr.getRight();
            }
        }

        // check if the tree is dead and
        // the prev and curr should not be null at the same time
        if (isTreeDead(root)&& prev != null  && curr != null) {
            // if it is true
            // we need to find the curr node is the left or right child node for the prev node and delete it
            if (curr.getElement().compareTo(prev.getElement()) < 0) {
                // if it is the left child, set the prev's left child become null to delete it
                prev.setLeft(null);
            } else {
                // else, set the prev's right child become the null to delete it
                prev.setRight(null);
            }
        }

        return root;
    }

    public void run() {
        System.out.println("Printouts for insert:");

        // First, we'll set up the tree with individual words with no duplicates.
        Node root = new Node("mango");
        root = insert(root, "falafel");
        root = insert(root, "venison");
        root = insert(root, "kale");
        root = insert(root, "turkey");
        root = insert(root, "zucchini");
        root = insert(root, "cheese");
        root = insert(root, "pierogi");
        root = insert(root, "baklava");
        root = insert(root, "udon");
        root = insert(root, "ramen");

        // The first printout should list the above words in alphabetical order.
        print(root);

        // Next, we'll add duplicates to the tree.
        root = insert(root, "turkey");
        root = insert(root, "udon");
        root = insert(root, "turkey");
        root = insert(root, "pierogi");
        root = insert(root, "turkey");

        // The second printout should still be in alphabetical order, but with two copies of each of "udon" and
        // "pierogi", and four copies of "turkey".
        print(root);

        System.out.println();
        System.out.println("Printouts for getCount:");

        // Check some specific counts. "baklava" should have 1, "turkey" should have 4, and "pastrami" should have 0.
        System.out.println("baklava: " + getCount(root, "baklava"));
        System.out.println("turkey: " + getCount(root, "turkey"));
        System.out.println("pastrami: " + getCount(root, "pastrami"));

        System.out.println();
        System.out.println("Printouts for remove and isTreeDead:");

        // Finally, we'll start removing Strings from the tree.
        root = remove(root, "cheese");
        root = remove(root, "baklava");
        root = remove(root, "kale");
        root = remove(root, "mango");
        root = remove(root, "udon");
        root = remove(root, "falafel");




        // The expected printout here is:
        // [mango: 0, pierogi: 2, ramen: 1, turkey: 4, udon: 1, venison: 1, zucchini: 1]
        print(root);
    }

    /**
     * Prints out the contents of the tree whose root is the provided node. This method will attempt to print the
     * elements in alphabetical order, and will include the count of each word as well.
     */
    private void print(Node root) {
        ArrayList<NodeInfo> elements = new ArrayList<>();
        addToList(root, elements);
        System.out.println(elements);
    }

    /** A helper method that print uses to collect all of the node information from the tree into a list. */
    private void addToList(Node root, ArrayList<NodeInfo> elements) {
        if (root != null) {
            addToList(root.getLeft(), elements);
            elements.add(new NodeInfo(root.getElement(), root.getCount()));
            addToList(root.getRight(), elements);
        }
    }

    /**
     * This class is only used in printing out a tree's contents, used by the print method above. You can completely
     * ignore this.
     */
    private static class NodeInfo {
        private final String element;
        private final int count;

        NodeInfo(String element, int count) {
            this.element = element;
            this.count = count;
        }

        @Override
        public String toString() {
            return String.format("%s: %d", element, count);
        }
    }

    public static void main(String[] args) {
        CountingBST bst = new CountingBST();
        bst.run();
    }
}
