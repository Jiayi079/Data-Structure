package edu.csc220.linkedlists;

import java.util.*;

public class Program {

    /**
     * insertInSortedOrder assumes that the singly linked list starting at head is already arranged in increasing sorted
     * order, alphabetically. It modifies the list by inserting a new node containing newElement in the correct place so
     * that the list remains in sorted order. The method should return the head node of the modified list.
     *
     * See the assignment handout for more details and tips on how to approach the problem.
     */


    public SingleNode insertInSortedOrder(SingleNode head, String newElement) {
        // TODO: Implement.

        // declare the head of the Node
        SingleNode curr = head;

        // search each node by using while loop
        while (curr != null) {

            // check the current element is "less than" newElement or not
            if (curr.getElement().compareTo(newElement) < 0) {
                // if it is, newElement should be after the current element
                // then, check if the the current element's next one is "greater than" the newElement or not
                // need to check the current element's next one isn't null first
                if (curr.getNext() != null) {
                    if (curr.getNext().getElement().compareTo(newElement) > 0 ) {
                        // if it is true, that's mean the newElement should after the current element
                        // and before the current element's next one
                        break;
                    }
                }
            } else {
                // if the newElement isn't "greater than" the current node,
                // get the next node and run the loop again
                curr = curr.getNext();
            }
        }

        // if current element already equals null, return head to stop the method to avoid next codes run
        if (curr == null) {
            return head;
        }

        // declare the newElement become the SingleNode
        SingleNode newNode = new SingleNode(newElement);

        // newElement should appear before the current node
        newNode.setNext(curr.getNext());
        curr.setNext(newNode);

        return head;
    }

    /**
     * removeAllWithLength should modify the singly linked list starting at head by removing all Strings whose length
     * is equal to the length parameter. The method should return the head node of the modified list.
     *
     * See the assignment handout for more details and tips on how to approach the problem.
     */
    public SingleNode removeAllWithLength(SingleNode head, int length) {
        // TODO: Implement.

        // Declare the previous element and the current element
        SingleNode prev = null;
        SingleNode curr = head;


        // search each node by using while loop
        while (curr != null) {

            // check if current element is equal the require length
            if (curr.getElement().length() == length) {

                // check if the current element is the first element in the linked list
                if (prev == null) {
                    // skip the first element, and make the second element become head
                    // update the new head to current one and run the loop again
                    head = curr.getNext();
                    curr = head;
                } else {
                    // if the current element isn't the first element
                    // update the previous elements and the current element to remove this element
                    prev.setNext(curr.getNext());
                    curr = curr.getNext();
                }
            } else {
                // if current element doesn't meet the length requirement.
                // skip to the next element and run the loop again
                prev = curr;
                curr = curr.getNext();
            }
        }

        return head;
    }

    /**
     * insertInSortedOrder assumes that the doubly linked list starting at head is already arranged in increasing sorted
     * order, alphabetically. It modifies the list by inserting a new node containing newElement in the correct place so
     * that the list remains in sorted order.
     *
     * See the assignment handout for more details and tips on how to approach the problem.
     */
    public DoubleNode insertInSortedOrder(DoubleNode head, String newElement) {
        // TODO: Implement.

        // Declare the previous element and the current element
        DoubleNode prev = null;
        DoubleNode curr = head;

        // start searching each element in the linked list by using while loop
        while  (curr != null) {

            // check if the current element is "less than" the newElement
            if (curr.getElement().compareTo(newElement) < 0) {
                // check if the next element is "greater than" the newElement
                if (curr.getNext().getElement().compareTo(newElement) > 0) {
                    // break the while loop
                    break;
                }
            } else {
                prev = curr;
                curr = curr.getNext();
            }
        }

        // check if current element already equals to null
        // return head to avoid next code run
        if (curr == null){
            return head;
        }

        // declare the newElement become DoubleNode and add to the liked list
        DoubleNode newNode = new DoubleNode(newElement);

        // newElement should be insert after the current element
        // and before the next element
        curr.getNext().setPrev(newNode);
        newNode.setNext(curr.getNext());
        curr.setNext(newNode);
        newNode.setPrev(curr);


        return head;
    }

    /**
     * removeAllWithLength should modify the doubly linked list starting at head by removing all Strings whose length
     * is equal to the length parameter. The method should return the head node of the modified list.
     *
     * See the assignment handout for more details and tips on how to approach the problem.
     */
    public DoubleNode removeAllWithLength(DoubleNode head, int length) {
        // TODO: Implement.

        // Declare previous and current elements
        DoubleNode prev = null;
        DoubleNode curr = head;

        // starting searching each elements in the linked list by using while loop
        while (curr != null) {

            // check if the current element meet the length requirement
            if (curr.getElement().length() == length) {

                // check if it is the first element in the linked list
                if (prev == null) {
                    // if it is, just skip the first elements
                    head = curr.getNext();
                    // update the curr elements become the second element
                    curr = head;
                }

                // check if the current element is the last element in the linked list
                if (curr.getNext() == null) {
                    // if it is, set the previous element's next become null
                    // to skip the last elements
                    prev.setNext(null);
                    // update the current element become null to skip it
                    curr = null;
                } else {

                    // if the current element is not the last one in the linked list
                    prev.setNext(curr.getNext());
                    curr = curr.getNext();
                    curr.setPrev(prev);
                }
            } else {
                //if the current element do not meet the length requirement
                // update the previous and the current elements and check next
                prev = curr;
                curr = curr.getNext();
            }
        }

        return head;
    }


    public static void main(String[] args) {
        Program program = new Program();

        SingleNode ll1 = createSinglyLinkedList("Alice", "Bob", "Carlos");
        ll1 = program.insertInSortedOrder(ll1, "Amelia");
        verify(ll1, "Alice", "Amelia", "Bob", "Carlos");

        SingleNode ll2 = createSinglyLinkedList("salad", "carrot", "apple", "celery", "crouton", "banana");
        ll2 = program.removeAllWithLength(ll2, 6);
        verify(ll2, "salad", "apple", "crouton");

        DoubleNode ll3 = createDoublyLinkedList("Alice", "Bob", "Carlos");
        ll3 = program.insertInSortedOrder(ll3, "Amelia");
        verify(ll3, "Alice", "Amelia", "Bob", "Carlos");

        DoubleNode ll4 = createDoublyLinkedList("salad", "carrot", "apple", "celery", "crouton", "banana");
        ll4 = program.removeAllWithLength(ll4, 6);
        verify(ll4, "salad", "apple", "crouton");

        System.out.println("Everything works!");
    }

    private static SingleNode createSinglyLinkedList(String... elements) {
        SingleNode head = null;
        for (int i = elements.length - 1; i >= 0; i--) {
            SingleNode node = new SingleNode(elements[i]);
            node.setNext(head);
            head = node;
        }
        return head;
    }

    private static DoubleNode createDoublyLinkedList(String... elements) {
        DoubleNode head = null;
        for (int i = elements.length - 1; i >= 0; i--) {
            DoubleNode node = new DoubleNode(elements[i]);
            if (head != null) {
                node.setNext(head);
                head.setPrev(node);
            }
            head = node;
        }
        return head;
    }

    private static void verify(SingleNode head, String... expected) {
        HashSet<SingleNode> nodesSeen = new HashSet<>();
        ArrayList<String> elements = new ArrayList<>();
        while (head != null) {
            if (nodesSeen.contains(head)) {
                throw new RuntimeException(
                        String.format(
                                "Found a cycle in the linked list! We've already seen '%s' before.",
                                head.getElement()));
            }
            nodesSeen.add(head);
            elements.add(head.getElement());
            head = head.getNext();
        }

        List<String> expectedElements = Arrays.asList(expected);
        if (!expectedElements.equals(elements)) {
            throw new RuntimeException(
                    String.format("\nExpected: %s\nActual:   %s", expectedElements, elements));
        }
    }

    private static void verify(DoubleNode head, String... expected) {
        HashSet<DoubleNode> nodesSeen = new HashSet<>();
        ArrayList<String> elements = new ArrayList<>();

        DoubleNode tail = null;
        while (head != null) {
            if (nodesSeen.contains(head)) {
                throw new RuntimeException(
                        String.format(
                                "Found a cycle in the linked list (forward direction)! We've already seen '%s' before.",
                                head.getElement()));
            }
            nodesSeen.add(head);
            elements.add(head.getElement());
            tail = head;
            head = head.getNext();
        }

        nodesSeen.clear();
        ArrayList<String> reverseElements = new ArrayList<>();
        while (tail != null) {
            if (nodesSeen.contains(tail)) {
                throw new RuntimeException(
                        String.format(
                                "Found a cycle in the linked list (reverse direction)! We've already seen '%s' before.",
                                tail.getElement()));
            }
            nodesSeen.add(tail);
            reverseElements.add(tail.getElement());
            tail = tail.getPrev();
        }

        List<String> expectedElements = Arrays.asList(expected);
        if (!expectedElements.equals(elements)) {
            throw new RuntimeException(
                    String.format("\nExpected: %s\nActual:   %s", expectedElements, elements));
        }

        Collections.reverse(expectedElements);
        if (!expectedElements.equals(reverseElements)) {
            throw new RuntimeException(
                    String.format(
                            "\nExpected (reversed): %s\nActual (reversed):   %s", expectedElements, reverseElements));
        }
    }
}
