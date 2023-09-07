package org.janis.qa.homework.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.http.HttpStatus;
import org.janis.qa.homework.helpers.TestCaseContext;
import org.janis.qa.homework.model.response.UserResponse;

import static org.janis.qa.homework.helpers.TestCaseContext.USERS_CLIENT;

public class Hooks {

    @Before
    public void before() {
        TestCaseContext.init();

        //In case some users have been left in a bad state
        cleanUp();
    }

    @After
    public void cleanUp() {
        TestCaseContext.getCreatedTestUsers().
                forEach(userResponse -> USERS_CLIENT.
                        deleteUser(userResponse.getId()).
                            statusCode(HttpStatus.SC_NO_CONTENT));

        USERS_CLIENT.
                getUsers("100").
                    statusCode(HttpStatus.SC_OK).
                        extract().
                            jsonPath().getList(".", UserResponse.class).
                            stream().
                                filter(userResponse -> userResponse.getEmail().endsWith("janisQAHomework2023.lv")).
                                forEach(userResponse -> USERS_CLIENT.
                                        deleteUser(userResponse.getId()).
                                            statusCode(HttpStatus.SC_NO_CONTENT));
    }
}
