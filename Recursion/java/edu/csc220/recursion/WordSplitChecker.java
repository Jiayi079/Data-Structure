package edu.csc220.recursion;

import java.io.*;
import java.util.*;

public class WordSplitChecker {
    /**
     * Returns true if there is at least one way to split input into a sequence of valid English words (words that
     * appear in the allWords set), and false otherwise.
     *
     * For this assignment, you can define your own private helper method, but you cannot change this method's signature
     * (i.e. the name, the parameters, or the fact that it returns a boolean).
     */
    public boolean hasWordSplit(String input, TreeSet<String> allWords) {
        // TODO: Implement this.
        if (input.isEmpty()) {
            return true;
        } else {
            // if input is not empty, check each words by using for loop
            for (int i = 0; i < input.length(); i++) {
                String splitWords = input.substring(0, i + 1);
                if (allWords.contains(splitWords)) {
                    // check next words by using recursion
                    // if hasWordsSplit recursion return ture,
                    // that's mean already find one way to split input into valid words
                    if (hasWordSplit(input.substring(i + 1), allWords)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }



    public static void main(String[] args) {
        TreeSet<String> allWords = readWords();
        WordSplitChecker wordSplitChecker = new WordSplitChecker();

        // All of the following should print out true.
        System.out.println("goat: " + wordSplitChecker.hasWordSplit("goat", allWords));
        System.out.println("car: " + wordSplitChecker.hasWordSplit("car", allWords));
        System.out.println("isawabus: " + wordSplitChecker.hasWordSplit("isawabus", allWords));

        // All of the following should print out false.
        System.out.println("xvix: " + wordSplitChecker.hasWordSplit("xvix", allWords));
        System.out.println("m: " + wordSplitChecker.hasWordSplit("m", allWords));
    }

    // Reads the "words.txt" file and returns the words in a TreeSet<String>. This is completely implemented for you!
    private static TreeSet<String> readWords() {
        TreeSet<String> allWords = new TreeSet<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("words.txt"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                allWords.add(line.toLowerCase());
            }
            bufferedReader.close();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return allWords;
    }
}
