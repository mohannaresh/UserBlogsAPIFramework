Feature: This feature is about user blogs posts API operations

  @EndToEndTest
  Scenario Outline: Search for specific username in json placeholder api
    Given user calls "GetUserNameAPI" with "GET" http request with "<userName>"
    Then the api call got success with status code 200
   	When user calls "GetPostsAPI" with "GET" http request to get post ids
    Then the api call got success with status code 200
    When user calls "GetCommentsAPI" with "GET" http request to get comments
    Then the api call got success with status code 200
    And validate that email id of each post are in proper format
    

    Examples: 
      | userName |
      |Delphine|
      |Antonette|
