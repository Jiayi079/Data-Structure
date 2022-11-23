package edu.csc220.recursion;

import java.util.ArrayList;

public class LongestFinder {
    /** Returns the longest String in the list words. If there is a tie, any of the Strings can be returned. */
    public String findLongest(ArrayList<String> words) {
        // TODO: Implement this.

        // if ArrayList only has one elements
        if (words.size() == 1) {
            return words.get(0);
        }

        // if the second element's length is longer then the first one, remove the first one
        if (words.get(1).length() < words.get(0).length()) {
            words.remove(1);
        } else { // else remove the second one
            words.remove(0);
        }

        // find the longest element by using recursion
        return findLongest(words);
    }

    public static void main(String[] args) {
        LongestFinder longestFinder = new LongestFinder();
        ArrayList<String> words = new ArrayList<>();
        words.add("apple");
        words.add("banana");
        words.add("cat");
        words.add("dog");
        words.add("elephant");
        words.add("fish");
        words.add("gin");

        // Expected to print out "Longest: elephant".
        System.out.println("Longest: " + longestFinder.findLongest(words));
    }
}
