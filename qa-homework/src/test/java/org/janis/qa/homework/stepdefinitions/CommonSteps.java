package org.janis.qa.homework.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.janis.qa.homework.constants.FieldNames;
import org.janis.qa.homework.helpers.TestCaseContext;
import org.janis.qa.homework.model.response.ErrorResponse;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.janis.qa.homework.helpers.UserHelper.getUsersListFromLastResponse;

public class CommonSteps {
    @Then("User receives response with status code {int}")
    public void userReceivesResponseWithStatusCode(int statusCode) {
        TestCaseContext.getLastTestResponse().statusCode(statusCode);
    }

    @And("Response contains following error message")
    public void responseContainsFollowingErrorMessage(Map<String, String> dataTable) {
        var expectedErrorMessage = ErrorResponse.builder().
                message(dataTable.get(FieldNames.MESSAGE)).
                field(dataTable.get(FieldNames.FIELD)).build();

        var returnedErrors = TestCaseContext.getLastTestResponse().
                extract().
                    jsonPath().getList(".", ErrorResponse.class);

        assertThat(returnedErrors).contains(expectedErrorMessage);
    }

    @Then("User receives user list containing created users")
    public static void userReceivesUserListContainingCreatedUsers() {
       var returnedUsers = getUsersListFromLastResponse();

        assertThat(returnedUsers).containsAll(TestCaseContext.getCreatedTestUsers());
    }

    @And("{string} error message is present in response")
    public void errorMessageIsPresentInResponse(String errorMessage) {
        var expectedErrorMessage = ErrorResponse.builder().
                message(errorMessage).
                build();

        var returnedErrors = TestCaseContext.getLastTestResponse().
                extract().
                    as(ErrorResponse.class);

        assertThat(returnedErrors).usingRecursiveComparison().isEqualTo(expectedErrorMessage);
    }


}
