package com.bdd.test.cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "json:target/cucumber.json"}, features = "src/test/java/com/bdd/test/features", glue = {
				"com.bdd.test.stepDefinitions" },tags = "@EndToEndTest")
public class TestRunner {

}
