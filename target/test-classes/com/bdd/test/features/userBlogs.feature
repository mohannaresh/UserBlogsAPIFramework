Feature: This feature is about user blogs posts API operations

  @EndToEndTest
  Scenario Outline: Validate displayed email id  is proper format present in the comment section of each blog post created by a specific user
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
