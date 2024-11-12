# Hospital Management System (HMS)

**HMS** is an application aimed at automating the management of hospital operations, including patient management, appointment scheduling, staff management, and billing. The system is expected to facilitate efficient management of hospital resources, enhance patient care, and streamline administrative processes.

## Prerequisites

Ensure you have the following installed on your system:

- **JDK 22** or higher: [Download here](https://www.oracle.com/sg/java/technologies/downloads/)
- **Apache Maven**: [Download here](https://maven.apache.org/download.cgi)

Make sure your environment is set up correctly by verifying the installation of Java and Maven. You can check this by running the following commands:

```
java --version
mvn --version
```

If both commands return version details, your setup is correct and you're ready to proceed. Otherwise, you may need to configure your PATH environment variables to include the locations of your Java and Maven installations.

## Getting Started

### 1. Clone the Repository

Navigate to your desired directory and clone this repository with `git clone`. Then navigate into the project directory.

```
git clone https://github.com/brandonleehs/SC2002-HMS.git
cd SC2002-HMS/
```

### 2. Build the Project

Clean the previous build artifacts (if any) and package the project into a JAR file using Maven. The generated build files will be located in the `target/` directory.

```
mvn clean package
```

### 3. Run the Application

After successfully building the project, execute the JAR file to run the Hospital Management System.

```
java -jar ./target/hms-0.0.1-SNAPSHOT.jar
```

### Alternative: Running from Source

If you prefer to compile and run the application directly from the source code, navigate to the `src/main/java/hms/` directory. Directly compile and run the `App.java` file.

## Project Structure

The project follows a standard Maven structure:

- `src/main/java/`: Contains the main source code for the application.
- `src/test/java/`: Holds unit/integration/system tests for the project.
- `target/`: This directory is generated during the build process and houses all output of the build.

Our project uses the Model-View-Controller (MVC) design pattern for maintainability and extensibility. Other design patterns our project uses include the Data Access Object (DAO), Repository and Service pattern, also commonly used in Spring applications.

For any additional information or troubleshooting, please consult the project documentation or open an issue in the repository.
