package org.janis.qa.homework.exceptions;

public class TooFewEntriesInListException extends RuntimeException {

    public TooFewEntriesInListException() {
        super("There are too few entries in list to perform this operation");
    }
}
