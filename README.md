# **Bookmap Test Task**
This repository is a test project for **Bookmap**. 
The project includes automated tasks to generate test reports and deploy them to GitHub Pages for easy access.
## **Features**
- **Automated Tests** using Java and Gradle.
- **Test Report** generation with ExtentReports.
- **Continuous Integration (CI)** with GitHub Actions.
- **Static Deployment** of test reports to GitHub Pages.

## **Getting Started**
### **Prerequisites**
1. **Java 17**:
    - Ensure Java Development Kit (JDK) 17 or higher is installed.
    - Verify installation with:
``` bash
     java -version
```
### **Git**:
    - Make sure Git is installed to clone the repository.

### **Setup**
1. Download the zip.

2. Build the project:
``` bash
   ./gradlew build
```

3. Run tests:
``` bash
   ./gradlew clean test
```
4. Check the generated **ExtentReport**:
    - The report will be located in the `test-output` directory:
``` 
     test-output/SparkReport.html
```
## **GitHub Actions Workflow**
The project includes a GitHub Actions CI/CD pipeline to automate the following:
1. **Run Tests**:
    - On each push to the `main` branch or via manual trigger.

2. **Generate ExtentReport**:
    - Automatically produce a report for the test results.

3. **Deploy to GitHub Pages**:
    - Publishes the ExtentReport to a publicly accessible URL.

### **Workflow Triggers**
- **Push to `main`**:
    - Automatically triggers the workflow to run tests and deploy reports.

- **Manual Execution**:
    - Go to the **Actions** tab in your GitHub repository to trigger the workflow manually.

### **Accessing the Report**
Once the deployment is complete, the ExtentReport will be available at:
``` 
https://natrix2020.github.io/BookmapTestTask/
```

## **GitHub Workflow Overview**
The workflow file is located at `.github/workflows/deploy-extent-report.yml`.
#### Key Steps:
1. **Build and Run Tests**:
    - Sets up JDK 17.
    - Executes the Gradle test task and generates an **ExtentReport**.

2. **Prepare and Upload Report**:
    - Copies the ExtentReport from the `test-output` directory to a deployable location.

3. **Deploy to GitHub Pages**:
    - Publishes the ExtentReport using `actions/deploy-pages` to the `gh-pages` branch.

## **How to Trigger the Workflow Manually**
1. Go to the **Actions** tab in your GitHub repository.
2. Select the workflow titled `Run Tests and Deploy ExtentReport`.
3. Click on the **Run workflow** button in the top right corner.
