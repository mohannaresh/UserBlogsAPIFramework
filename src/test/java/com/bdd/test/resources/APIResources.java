package com.bdd.test.resources;

public enum APIResources {

	GetUserNameAPI("/users"),
	GetPostsAPI("/posts"),
	GetCommentsAPI("/comments");

	private String resource;

	APIResources(String resource) {
		this.resource = resource;
	}

	public String getResource() {
		return resource;
	}

}
