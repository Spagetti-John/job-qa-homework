package org.janis.qa.homework.helpers;

import io.restassured.response.ValidatableResponse;
import lombok.Data;
import org.janis.qa.homework.clients.UsersClient;
import org.janis.qa.homework.config.Config;
import org.janis.qa.homework.model.response.UserResponse;

import java.util.ArrayList;
import java.util.List;

@Data
public class TestCaseContext {
    private static ThreadLocal<TestCaseContext> CONTEXT = new ThreadLocal<>();
    private static Config CONFIG = Config.getInstance();

    public static UsersClient USERS_CLIENT;

    public List<UserResponse> createdUsers;

    public ValidatableResponse lastResponse;
    public String testCaseID;

    private TestCaseContext(String testCaseID) {
        createdUsers = new ArrayList<>();
        this.testCaseID = testCaseID;
    }

    public static TestCaseContext get() {
        return CONTEXT.get();
    }

    public static void init(String testCaseID) {
        CONTEXT.set(new TestCaseContext(testCaseID));
        USERS_CLIENT = new UsersClient(CONFIG.getBaseUrl());
    }

    public static List<UserResponse> getCreatedTestUsers() {
        return get().getCreatedUsers();
    }

    public static UserResponse getFirstCreatedTestUser() {
        return getCreatedTestUsers().get(0);
    }

    public static void updateFirstCreatedTestUser(UserResponse userResponse) {
        get().getCreatedUsers().set(0, userResponse);
    }

    public static void removeFirstCreatedTestUser() {
        get().getCreatedUsers().remove(0);
    }

    public static void addCreatedTestUser(UserResponse userResponse) {
        get().getCreatedUsers().add(userResponse);
    }

    public static void setLastTestResponse(ValidatableResponse response) {
        get().setLastResponse(response);
    }

    public static ValidatableResponse getLastTestResponse() {
        return get().getLastResponse();
    }

    public static String getTestTestCaseID() {
        return get().getTestCaseID();
    }
}
