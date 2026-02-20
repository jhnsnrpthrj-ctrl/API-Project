package com.omrbranch.report;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

import com.omrbranch.utility.BaseClass;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

/**
 * Generates JVM HTML reports using Masterthought Cucumber Reporting.
 */
public class Reporting extends BaseClass {

  /**
   * Generates a detailed JVM report from the JSON file configured in
   * Config.properties.
   *
   * @param jsonFileReport - JSON report file path (optional, can pass null).
   */
  public static void generateJvmReport(String jsonFileReport) {

    // Read paths and metadata from Config.properties
    String defaultJson = getProjectPath() + getPropertyFileValue("jsonFilePath");
    if (jsonFileReport == null || jsonFileReport.isEmpty()) {
      jsonFileReport = defaultJson;
    }

    String jvmPath = getProjectPath() + getPropertyFileValue("jvmFilePath");
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    File reportOutputDir = new File(jvmPath + "\\JVM_Report_" + timeStamp);

    String projectName = getPropertyFileValue("projectName");
    String author = getPropertyFileValue("reportAuthor");

    // Create Configuration Object
    Configuration config = new Configuration(reportOutputDir, projectName);
    config.addClassifications("Project", projectName);
    config.addClassifications("Author", author);
    config.addClassifications("Browser", getPropertyFileValue("browserType"));
    config.addClassifications("Platform", System.getProperty("os.name"));
    config.addClassifications("Environment", "QA");
    config.addClassifications("Execution Time", timeStamp);

    // Build Report
    List<String> jsonFiles = new ArrayList<>();
    jsonFiles.add(jsonFileReport);

    ReportBuilder builder = new ReportBuilder(jsonFiles, config);
    builder.generateReports();

    // Console Confirmation
    System.out.println("------------------------------------------------------------");
    System.out.println("JVM Report Generated Successfully!");
    System.out.println("Location : " + reportOutputDir.getAbsolutePath());
    System.out.println("Project  : " + projectName);
    System.out.println("Author   : " + author);
    System.out.println("Time     : " + timeStamp);
    System.out.println("------------------------------------------------------------");
  }
}
