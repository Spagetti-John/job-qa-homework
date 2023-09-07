package org.janis.qa.homework.helpers;

import org.apache.http.HttpStatus;
import org.janis.qa.homework.constants.FieldNames;
import org.janis.qa.homework.model.response.UserResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.janis.qa.homework.helpers.TestCaseContext.USERS_CLIENT;
import static org.janis.qa.homework.helpers.csv.UserCsvHelper.getRandomUsersFromCsv;

public class UserHelper {

    public static void createUsers(int count) {
        getRandomUsersFromCsv(count).forEach((userCsv) -> {
            var payload = userCsv.convertToPayload();
            var response = USERS_CLIENT.createUser(payload);
            var createdUser = response.statusCode(HttpStatus.SC_CREATED).extract().as(UserResponse.class);

            assertThat(createdUser.getId()).isGreaterThan(0);
            assertThat(createdUser).
                    usingRecursiveComparison().
                        ignoringFields(FieldNames.ID).
                        isEqualTo(userCsv.convertToResponse());

            TestCaseContext.addCreatedTestUser(createdUser);
        });
    }

    public static List<UserResponse> getUsersListFromLastResponse() {
        return TestCaseContext.getLastTestResponse().
                statusCode(HttpStatus.SC_OK).
                extract().
                    jsonPath().getList(".", UserResponse.class);
    }
}
