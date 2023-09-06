package org.janis.qa.homework.stepdefinitions;

import io.cucumber.java.en.Given;

import static org.janis.qa.homework.helpers.UserHelper.createUsers;

public class CreateUsersSteps {

    @Given("User has created {int} users")
    public void userHasCreatedUsers(int userCount) {
        createUsers(userCount);
    }
}
