package org.janis.qa.homework.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.janis.qa.homework.helpers.TestCaseContext;
import org.janis.qa.homework.model.response.UserResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.janis.qa.homework.helpers.TestCaseContext.USERS_CLIENT;

public class GetUsersSteps {
    @When("User requests user list")
    public static void userRequestsUserList() {
        TestCaseContext.setLastTestResponse(USERS_CLIENT.getUsers());
    }
}
