package com.bdd.test.resources;

import com.bdd.test.pojo.AddUserInformation;
import com.bdd.test.pojo.UserPosts;

import io.restassured.response.Response;

public class TestDataBuild extends Utils {

	public AddUserInformation addUserInformation(Response response) {
		AddUserInformation addUserInfo = new AddUserInformation();
		String id = getJsonPath(response, "id").replaceAll("\\[", "").replaceAll("\\]", "");
		addUserInfo.setId(Integer.parseInt(id));
		addUserInfo.setUserName(getJsonPath(response, "username"));
		addUserInfo.setEmailId(getJsonPath(response, "email"));
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
