package com.bdd.test.resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification req;
	PrintStream log = null;

	public RequestSpecification requestSpecification(String prameterName, String parameterValue) throws IOException {
		if (log == null) {
			log = new PrintStream(new FileOutputStream("logging.txt"));
		}

		req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam(prameterName, parameterValue)
				.addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return req;
	}

	public RequestSpecification requestSpecification(String prameterName, int parameterValue) throws IOException {
		if (log == null) {
			log = new PrintStream(new FileOutputStream("logging.txt"));
		}

		req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam(prameterName, parameterValue)
				.addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return req;
	}

	public Response getAPIResponse(RequestSpecification requestSpec, String apiMethod, String apiResource) {
		Response response = null;
		if (apiMethod.equalsIgnoreCase("GET"))
			response = requestSpec.when().get(apiResource);
		return response;
	}

	public static String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("./src/test/java/com/bdd/test/resources/global.properties");
		prop.load(fis);
		return prop.getProperty(key);

	}

	public String getJsonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}
}
