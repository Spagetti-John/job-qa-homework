package org.janis.qa.homework.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.janis.qa.homework.config.Config;
import org.janis.qa.homework.helpers.TestCaseContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.janis.qa.homework.helpers.TestCaseContext.USERS_CLIENT;
import static org.janis.qa.homework.helpers.UserHelper.getUsersListFromLastResponse;
import static org.janis.qa.homework.stepdefinitions.GetUsersSteps.userRequestsUserList;

public class DeleteUsersSteps {
    @When("User deletes created user")
    public void userDeletesCreatedUser() {
        var userToDelete = TestCaseContext.getFirstCreatedTestUser();

        USERS_CLIENT.deleteUser(userToDelete.getId()).statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Then("User is deleted")
    public void userIsDeleted() {
        var createdUser = TestCaseContext.getFirstCreatedTestUser();
        userRequestsUserList();

        assertThat(getUsersListFromLastResponse()).doesNotContain(createdUser);

        TestCaseContext.removeFirstCreatedTestUser();
    }

    @When("User deletes non existent user")
    public void userDeletesNonExistentUser() {
        TestCaseContext.setLastTestResponse(USERS_CLIENT.deleteUser(Config.getInstance().getUsedUserId()));
    }
}
