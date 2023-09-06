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
    public void userRequestsUserList() {
        TestCaseContext.setLastTestResponse(USERS_CLIENT.getUsers());
    }

    @Then("User receives user list containing created users")
    public void userReceivesUserListContainingCreatedUsers() {
        var returnedUsers = TestCaseContext.getLastTestResponse().
                statusCode(HttpStatus.SC_OK).
                    extract().
                        jsonPath().getList(".", UserResponse.class);

        assertThat(returnedUsers).containsAll(TestCaseContext.getCreatedTestUsers());
    }
}
