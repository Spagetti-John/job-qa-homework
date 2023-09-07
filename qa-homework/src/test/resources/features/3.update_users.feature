@UPDATE_USERS
Feature: Update Users feature
  Tests Update users scenarios

  @ID-4
  Scenario: ID-4: GoREST - Users - v2 - Update - name
    Given User has created a new user
    When User updates users "name" field to "testName"
    Then User is updated

  @ID-5
  Scenario: ID-5: GoREST - Users - v2 - Update - gender - invalid
    Given User has created a new user
    When User updates users "gender" field to "helicopter"
    Then User receives response with status code 422
    And Response contains following error message
      | Field Name | Value                                 |
      | field      | gender                                |
      | message    | can't be blank, can be male of female |

  @ID-6
  Scenario: ID-6: GoREST - Users - v2 - Update - non existing user
    When User updates non existing user
    Then User receives response with status code 404
    And "Resource not found" error message is present in response
