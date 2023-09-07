package org.janis.qa.homework.exceptions;

import org.janis.qa.homework.constants.RegularExpressions;

public class TestCaseIDTagNotFoundException extends RuntimeException {
    public TestCaseIDTagNotFoundException() {
        super("Test case does not have ID tag that matches the expression: " + RegularExpressions.TEST_ID_TAG_REGEX);
    }
}
