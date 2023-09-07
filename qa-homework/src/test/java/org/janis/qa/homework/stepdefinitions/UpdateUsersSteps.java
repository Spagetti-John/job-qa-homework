package org.janis.qa.homework.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.janis.qa.homework.config.Config;
import org.janis.qa.homework.helpers.TestCaseContext;
import org.janis.qa.homework.model.response.UserResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.janis.qa.homework.helpers.ObjectHelper.invokeSetterMethod;
import static org.janis.qa.homework.helpers.TestCaseContext.USERS_CLIENT;
import static org.janis.qa.homework.helpers.TestCaseContext.getLastTestResponse;
import static org.janis.qa.homework.helpers.csv.UserCsvHelper.getRandomUsersFromCsv;
import static org.janis.qa.homework.stepdefinitions.CommonSteps.userReceivesUserListContainingCreatedUsers;
import static org.janis.qa.homework.stepdefinitions.GetUsersSteps.userRequestsUserList;

public class UpdateUsersSteps {
    @When("User updates users {string} field to {string}")
    public void userUpdatesUsersFieldTo(String fieldName, String value) {
        var userToUpdate = TestCaseContext.getFirstCreatedTestUser();

        invokeSetterMethod(fieldName, userToUpdate, value, String.class);

        TestCaseContext.setLastTestResponse(
                USERS_CLIENT.updateUser(userToUpdate.getId(), userToUpdate.convertToPayload()));
    }

    @Then("User is updated")
    public void userIsUpdated() {
        var updatedUser = getLastTestResponse().statusCode(HttpStatus.SC_OK).extract().as(UserResponse.class);

        assertThat(updatedUser).usingRecursiveComparison().isEqualTo(TestCaseContext.getFirstCreatedTestUser());

        TestCaseContext.updateFirstCreatedTestUser(updatedUser);

        userRequestsUserList();
        userReceivesUserListContainingCreatedUsers();
    }

    @When("User updates non existing user")
    public void userUpdatesNonExistingUser() {
        var payload = getRandomUsersFromCsv(1).get(0).convertToPayload();

        TestCaseContext.setLastTestResponse(USERS_CLIENT.updateUser(Config.getInstance().getUsedUserId(), payload));
    }
}
