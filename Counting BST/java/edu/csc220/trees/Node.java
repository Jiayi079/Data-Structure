package edu.csc220.trees;

/** Represents a node in a counting binary search tree. */
public class Node {
    private String element;
    private int count;
    private Node left;
    private Node right;

    public Node(String element) {
        this.element = element;
        this.count = 1;
    }

    public String getElement() {
        return element;
    }

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        count++;
    }

    public void decrementCount() {
        if (count > 0) {
            count--;
        }
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
