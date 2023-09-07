@CREATE_USERS
Feature: Create Users feature
  Tests Create users scenarios

  @ID-2
  Scenario: ID-2: GoREST - Users - v2 - Create
    When User creates new user
    Then User is created

  @ID-3
  Scenario: ID-3: GoREST - Users - v2 - Create - invalid - email
    When User creates new user with "invalidEmail@" email address
    Then User receives response with status code 422
    And Response contains following error message
      | Field Name | Value      |
      | field      | email      |
      | message    | is invalid |
