package com.omrbranch.runner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.omrbranch.report.Reporting;
import com.omrbranch.utility.BaseClass;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

/**
 * Main runner class for executing Cucumber BDD test scenarios.
 * This class integrates:
 *  - Cucumber feature files (.feature)
 *  - Step definition methods (glue code)
 *  - Reporting configuration
 * 
 * It uses JUnit as the test execution framework.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		tags =   "@Login",
    // Enables step execution notifications in console
    stepNotifications = true,

    // Converts generated step methods into camelCase naming convention
    snippets = SnippetType.CAMELCASE,

    // If true, validates step definitions without executing tests
    dryRun = false,

    // Publishes Cucumber report to cloud (optional)
    publish = true,

    // Ensures clean and readable console output
    monochrome = true,

    // Plugins for output formatting and reporting
    plugin = {
        "pretty",                                 // Readable console output
        "json:target\\output.json",               // JSON report for JVM report generation
        "html:target\\cucumber-report.html",      // Simple HTML report
        "rerun:target\\failed_scenarios.txt"      // Failed scenario re-run file
    },

    // Step definition package
    glue = {"com.omrbranch.stepdefinition","com.omrbranch.hooks"},

    // Path to the feature files
    features = "src\\test\\resources\\features"
)
public class TestRunnerClass2 extends BaseClass {

    /**
     * Executes after all scenarios have finished.
     * Generates a JVM report based on the Cucumber JSON output.
     */
    @AfterClass
    public static void afterClass() {
        // Dynamically gets project path for portability
        Reporting.generateJvmReport(getProjectPath() + "\\target\\output.json");
    }
}
