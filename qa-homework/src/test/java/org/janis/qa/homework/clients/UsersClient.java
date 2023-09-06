package org.janis.qa.homework.clients;

import io.restassured.response.ValidatableResponse;
import org.janis.qa.homework.constants.DefaultParams;
import org.janis.qa.homework.constants.FormParamNames;
import org.janis.qa.homework.model.payload.UserPayload;

import java.util.Map;

public class UsersClient extends BaseClient {

    private final String CREATE_USER_URI = "public/v2/users";
    private final String GET_USERS_URI = "public/v2/users";
    private final String DELETE_USER_URI = "public/v2/users/{id}";

    public UsersClient(String baseURL) {
        super(baseURL);
    }

    public ValidatableResponse createUser(UserPayload userPayload) {
        return super.postJson(userPayload, CREATE_USER_URI);
    }

    public ValidatableResponse getUsers(String pageSize) {
        return super.get(GET_USERS_URI, Map.of(FormParamNames.PER_PAGE, pageSize));
    }
    public ValidatableResponse getUsers() {
        return getUsers(DefaultParams.DEFAULT_USERS_PAGE_SIZE);
    }

    public ValidatableResponse deleteUser(int id) {
        return super.getRequestSpecification().
            when().
                delete(DELETE_USER_URI, id).
            then().
                log().all();
    }

}
