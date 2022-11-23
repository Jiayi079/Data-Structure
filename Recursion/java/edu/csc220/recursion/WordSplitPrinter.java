package edu.csc220.recursion;

import java.io.*;
import java.util.*;

public class WordSplitPrinter {
    /**
     * Finds and prints out every possible way to split the input String into individual, valid English words (including
     * if input is itself a single valid word). You must call printWordSplit below to actually print out the results.
     */
    public void findWordSplits(String input, TreeSet<String> allWords) {
        // TODO: Implement this.

        ArrayList<String> words = new ArrayList<>();
        // using helper method which has ArrayList parameter to save the words we find
        findWordSplitsHelper(words, input, allWords);

    }

    public void findWordSplitsHelper (ArrayList<String> words, String input, TreeSet<String> allWords) {
        if (!input.isEmpty()) {
            // for loop to get each separate characters
            for (int i = 0; i < input.length(); i++) {

                String splitWordSoFar = input.substring(0, i + 1);

                // check if the separate characters is a words
                if (allWords.contains(splitWordSoFar)) {

                    // create one new ArrayList to avoid duplicate in the original Arraylist
                    // each iterate use words Arraylist to keep the words that already find
                    // put newWords ArrayList into the recursion to get new words
                    ArrayList<String> newWords = new ArrayList<>();

                    // add each words into the newWords to avoid the words disappear
                    for (int j = 0; j < words.size(); j++) {
                        newWords.add(words.get(j));
                    }

                    // also need add the word we find
                    newWords.add(splitWordSoFar);

                    String inputSoFar = input.substring(i + 1);

                    // find the remaining words by using recursion
                    findWordSplitsHelper(newWords, inputSoFar, allWords);
                }
            }
        } else {
            // if input is empty, which means already find all of the split words
            printWordSplit(words);
        }
    }

    /** Prints out a word split, i.e. one particular arrangement of words. This is implemented for you! */
    private void printWordSplit(ArrayList<String> words) {
        if (words.isEmpty()) {
            System.out.println("(empty word list)");
        } else {
            System.out.print(words.get(0));
            for (int i = 1; i < words.size(); i++) {
                System.out.print(" " + words.get(i));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TreeSet<String> dictionary = readWords();
        WordSplitPrinter wordSplitPrinter = new WordSplitPrinter();

        // Expected to print out:
        // i saw a bus
        // i saw ab us
        // is aw a bus
        // is aw ab us
        wordSplitPrinter.findWordSplits("isawabus", dictionary);
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
