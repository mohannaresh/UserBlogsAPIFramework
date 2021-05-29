package com.bdd.test.stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.bdd.test.pojo.AddUserInformation;
import com.bdd.test.pojo.Comments;
import com.bdd.test.pojo.UserPosts;
import com.bdd.test.resources.APIResources;
import com.bdd.test.resources.TestDataBuild;
import com.bdd.test.resources.Utils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefinitions extends Utils {
	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	Response response;
	TestDataBuild testData = new TestDataBuild();
	AddUserInformation userInformation= new AddUserInformation();
	List<UserPosts> userPosts;
	List<Comments> comments;
	List<List<Comments>> commentsList = new ArrayList<List<Comments>>();
	String emailIdRegex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";

	@Given("user calls {string} with {string} http request with {string}")
	public void user_calls_with_http_request_with(String resource, String apiMethod, String userName)
			throws IOException {
		APIResources resourceAPI = APIResources.valueOf(resource);
		userInformation.setUserName(userName);
		requestSpec = given().spec(requestSpecification("username", userName));
		responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		response = getAPIResponse(requestSpec, apiMethod, resourceAPI.getResource());

//		System.out.println(response.getBody().asString());
		userInformation = testData.addUserInformation(response);
//		System.out.println(userInformation.getId());
	}

	@When("user calls {string} with {string} http request to get post ids")
	public void user_calls_with_http_request_to_get_post_ids(String resource, String apiMethod) throws IOException {
		APIResources resourceAPI = APIResources.valueOf(resource);
		requestSpec = given().spec(requestSpecification("userId", userInformation.getId()));
		responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		response = getAPIResponse(requestSpec, apiMethod, resourceAPI.getResource());
//		userPosts=testData.addUserPostsInformation(response);

		userPosts = Arrays.asList(response.jsonPath().getObject("$", UserPosts[].class));
//		System.out.println(userPosts.get(0).getId());
//		System.out.println(userPosts.size());
//		System.out.println(response.getBody().asString());
	}

	@When("user calls {string} with {string} http request to get comments")
	public void user_calls_with_http_request_to_get_comments(String resource, String apiMethod) throws IOException {
		APIResources resourceAPI = APIResources.valueOf(resource);

		Stream<UserPosts> userPostsStream = userPosts.stream();
		userPostsStream.forEach(userPost -> {
//			System.out.println(userPost.getId());
			try {
				requestSpec = given().spec(requestSpecification("postId", userPost.getId()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
			response = getAPIResponse(requestSpec, apiMethod, resourceAPI.getResource());
			comments = Arrays.asList(response.jsonPath().getObject("$", Comments[].class));

			commentsList.add(comments);

		});
	}

	@Then("the api call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("validate that email id of each post are in proper format")
	public void validate_that_email_id_of_each_post_are_in_proper_format() {
		commentsList.forEach(comment -> {
			Stream<Comments> userCommentsStream = comment.stream();
			userCommentsStream.forEach(eachUserComment -> {
				if(eachUserComment.getEmail().matches(emailIdRegex)) {
					System.out.println("InputUserName::::" +userInformation.getUserName() + "\t Userid::::" + userInformation.getId() + "\tUserPostId::::"
							+ eachUserComment.getPostId() + "\tUserPostEmailId::::" + eachUserComment.getEmail() +"\t ::::valid email address");
				}else {
					System.out.println("InputUserName::::" +userInformation.getUserName() + "\t Userid::::" + userInformation.getId() + "\tUserPostId::::"
							+ eachUserComment.getPostId() + "\tUserPostEmailId::::" + eachUserComment.getEmail() +"\t ::::not a valid email address");
				}
				
			});
		});

	}

}
