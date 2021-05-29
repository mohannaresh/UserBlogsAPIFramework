package com.bdd.test.resources;

import com.bdd.test.pojo.UserInformation;
import com.bdd.test.pojo.UserPosts;

import io.restassured.response.Response;

public class TestDataBuild extends Utils {

	public UserInformation addUserInformation(Response response) {
		UserInformation addUserInfo = new UserInformation();
		addUserInfo.setId(Integer.parseInt(getJsonPath(response, "id")));
		addUserInfo.setName(getJsonPath(response, "username"));
		addUserInfo.setEmail(getJsonPath(response, "email"));
		return addUserInfo;
	}

	public UserPosts addUserPostsInformation(Response response) {

		UserPosts userPosts = new UserPosts();
		userPosts.setId(Integer.parseInt(getJsonPath(response, "id")));
		userPosts.setUserId(Integer.parseInt(getJsonPath(response, "userId")));
		userPosts.setBody(getJsonPath(response, "body"));
		userPosts.setTitle(getJsonPath(response, "title"));
		return userPosts;
	}
}
