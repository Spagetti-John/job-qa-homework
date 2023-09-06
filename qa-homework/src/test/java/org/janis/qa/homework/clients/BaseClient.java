package org.janis.qa.homework.clients;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

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
                    oauth2("7301cc99802ee91cbb376e4dfec016b7c00ab319194c53482f0daec97b3ffe11");
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
}
