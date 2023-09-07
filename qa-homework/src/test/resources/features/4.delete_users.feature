@DELETE_USERS
Feature: Delete Users feature
  Tests get users scenarios

  @ID-7
  Scenario: ID-7: GoREST - Users - v2 - Delete
    Given User has created a new user
    When User deletes created user
    Then User is deleted

  @ID-8
  Scenario: ID-8: GoREST - Users - v2 - Delete - non existing user
    When User deletes non existent user
    Then User receives response with status code 404
    And "Resource not found" error message is present in response
