
# Respond.io Automation Framework

## Introduction
This repository contains the test automation framework for the Respond.io platform. The framework automates critical workflows such as the Login Flow and Workspace User Flow, ensuring that the system's core functionalities are tested efficiently.

## Framework Structure
The framework is built using the following technologies:
- **Selenium WebDriver**: For interacting with the web application.
- **TestNG**: For test management and execution.
- **ExtentReports**: For generating comprehensive test reports.
- **Java**: As the primary programming language.
- **Page Object Model (POM)**: For organizing web elements in classes to enhance reusability.

## Automated Test Cases
1. **Valid Login Test**: Verify successful login using valid credentials.
2. **Invalid Login Test**: Ensure the system shows appropriate error messages for invalid credentials.
3. **Empty Fields Validation**: Check that the system provides validation messages when email/password fields are empty.
4. **Add Workspace User Test**: Verify the addition of new workspace users.

## Reporting
The framework uses **ExtentReports** and **Extent Spark Reporter** for generating HTML reports. These reports provide detailed test execution summaries, including pass/fail status, screenshots, and logs for each test case.

## Test Data Management
Test data is currently hardcoded in the initial stages of development. Future enhancements will include external data sources such as Excel and CSV files to drive tests dynamically. Sensitive data like passwords will be handled using environment variables.

## Prerequisites
- Java 1.8+
- Maven (for dependency management)
- TestNG
- Selenium WebDriver
- ExtentReports

## Setup Instructions
1. Clone the repository:
   ```
   git clone https://github.com/your-repo-name/respondio-automation.git
   ```
2. Install dependencies using Maven:
   ```
   mvn install
   ```
3. Configure environment variables for sensitive data:
   - Set your `USERNAME`, `PASSWORD`, and other environment variables.
4. Run the tests:
   ```
   mvn test
   ```

## Test Execution
To execute the test suite, run:
```
mvn clean test
```

## Reports
After test execution, reports can be found in the `/reports` directory, providing a detailed overview of the test results.

## Future Enhancements
- Dynamic test data management using external sources.
- Cross-browser testing setup.
- CI/CD pipeline integration using Jenkins or GitLab CI.

## Contribution Guidelines
1. Fork the repository.
2. Create a new branch for your feature/bug fix.
3. Commit your changes and submit a pull request.

## License
This project is licensed under the MIT License.
