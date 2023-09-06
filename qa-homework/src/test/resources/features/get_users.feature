@GET_USERS
Feature: Get Users feature
  Tests get users scenarios

  @ID-1
  Scenario: ID-1: GoREST - Users - v2 - get users
    Given User has created 2 users
    When User requests user list
    Then User receives user list containing created users