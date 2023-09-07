package org.janis.qa.homework.stepdefinitions;

import io.cucumber.java.en.When;
import org.janis.qa.homework.helpers.TestCaseContext;

import static org.janis.qa.homework.helpers.TestCaseContext.USERS_CLIENT;

public class GetUsersSteps {
    @When("User requests user list")
    public static void userRequestsUserList() {
        TestCaseContext.setLastTestResponse(USERS_CLIENT.getUsers());
    }
}
