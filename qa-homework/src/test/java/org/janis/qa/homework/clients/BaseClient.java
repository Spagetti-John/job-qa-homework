package org.janis.qa.homework.clients;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.janis.qa.homework.constants.PropertyNames;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseClient {
    protected final String baseURL;

    public BaseClient(String baseURL) {
        this.baseURL = baseURL;
    }

    protected RequestSpecification getRequestSpecification() {
        return
            given().
                log().all().
                baseUri(this.baseURL).
                auth().
                    oauth2(System.getProperty(PropertyNames.TOKEN));
    }

    protected ValidatableResponse get(String uri) {
        return get(uri, new HashMap<>());
    }

    protected ValidatableResponse get(String uri, Map<String, String> urlParams) {
        return
            getRequestSpecification().
                formParams(urlParams).
            when().
                get(uri).
            then().
                log().all();
    }

    protected ValidatableResponse postJson(Object payload, String uri) {
        return
            getRequestSpecification().
                contentType(ContentType.JSON).
                body(payload).
            when().
                post(uri).
            then().
                log().all();
    }

    protected ValidatableResponse putJson(Object payload, String uri) {
        return
            getRequestSpecification().
                contentType(ContentType.JSON).
                body(payload).
            when().
                put(uri).
            then().
                log().all();
    }
}
