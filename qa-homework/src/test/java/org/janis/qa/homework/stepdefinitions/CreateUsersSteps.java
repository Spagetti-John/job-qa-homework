package org.janis.qa.homework.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.janis.qa.homework.helpers.TestCaseContext;

import static org.janis.qa.homework.helpers.TestCaseContext.USERS_CLIENT;
import static org.janis.qa.homework.helpers.UserHelper.createUsers;
import static org.janis.qa.homework.helpers.csv.UserCsvHelper.getRandomUsersFromCsv;
import static org.janis.qa.homework.stepdefinitions.CommonSteps.userReceivesUserListContainingCreatedUsers;
import static org.janis.qa.homework.stepdefinitions.GetUsersSteps.userRequestsUserList;

public class CreateUsersSteps {

    @Given("User has created {int} users")
    public void userHasCreatedUsers(int userCount) {
        createUsers(userCount);
    }

    @Given("User has created a new user")
    @When("User creates new user")
    public void userCreatesNewUser() {
        createUsers(1);
    }

    @Then("User is created")
    public void userIsCreated() {
        userRequestsUserList();
        userReceivesUserListContainingCreatedUsers();
    }

    @When("User creates new user with {string} email address")
    public void userCreatesNewUserWithInvalidEmailAddress(String email) {
        var payload = getRandomUsersFromCsv(1).get(0).convertToPayload();
        payload.setEmail(email);

        TestCaseContext.setLastTestResponse(USERS_CLIENT.createUser(payload));
    }
}
