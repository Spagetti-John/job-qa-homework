# Janis 1ob-qa-homework

## Project

This repository contains E2E tests for API described in [here}(https://gorest.co.in/).

Project code is structured into directories
 - clients: Contains classes related to making REST calls to API under test.
 - config: Contains classes related to project conviguration.
 - constants: Contains classes with constants used in other parts of the code. 
 - exceptions: Contains custom exception classes.
 - helpers: Contains helper classes that are reused accoress the project to reduce copy pasted code.
 - model: Contains models for data structures used in tests. These are made with convertor desing patter.
 - stepdefinitions: Contains classes related to definng steps that are present in feature files

There is also reasorces folder that contains:
 - cucumber feature files writen gherkin
 - test data csv files
 - configuration (properties) fiels

## Running the tests

 To run the tests the following items are needed:
  - Installed Java JDK 17
  - Installed Apache maven
  - Access token to the API

Tests can be run with the following command after navigating to qa_homework directory:

`mvn clean test -Dtoken={accessToken}`

Where `{accessToken}` is replaced with the access token for the API

Optional `-Denv={env}` flag can be added to the command to specify which config (.properties) file should be used for tests. `{env}` should be replaced with the name (without the extention) of the config file. The default value is "prod".

After tests have run generated report will be available in target/cucumber-reports/CucumberReport.html file. This file can be opened with any modern web browser.
