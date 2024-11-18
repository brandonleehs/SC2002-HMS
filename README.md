# Hospital Management System (HMS)

**HMS** is an application aimed at automating the management of hospital operations, including patient management, appointment scheduling, staff management, and billing. The system is expected to facilitate efficient management of hospital resources, enhance patient care, and streamline administrative processes.

## Contributors

| Name                            | Email                 |                                                                   GitHub Profile                                                                    |
| ------------------------------- | --------------------- | :-------------------------------------------------------------------------------------------------------------------------------------------------: |
| Lee Heng Sheng, Brandon         | BLEE075@e.ntu.edu.sg  | [![GitHub](https://img.shields.io/badge/brandonleehs-%23121011.svg?style=flat-square&logo=github&logoColor=white)](https://github.com/brandonleehs) |
| Heng Ziyang                     | HENG0274@e.ntu.edu.sg |    [![GitHub](https://img.shields.io/badge/Zycrannny-%23121011.svg?style=flat-square&logo=github&logoColor=white)](https://github.com/Zycrannny)    |
| Keagan Kong Kai Yi (Kang Kaiyi) | KEAG0002@e.ntu.edu.sg |       [![GitHub](https://img.shields.io/badge/kekgan-%23121011.svg?style=flat-square&logo=github&logoColor=white)](https://github.com/kekgan)       |
| Lim Zhen Rong                   | ZLIM139@e.ntu.edu.sg  |   [![GitHub](https://img.shields.io/badge/alvinlimzr-%23121011.svg?style=flat-square&logo=github&logoColor=white)](https://github.com/alvinlimzr)   |

## Prerequisites

Ensure you have the following installed on your system:

- **JDK 22** or higher: [Download here](https://www.oracle.com/sg/java/technologies/downloads/)
- **Apache Maven (Optional, but recommended)**: [Download here](https://maven.apache.org/download.cgi)

Make sure your environment is set up correctly by verifying the installation of Java and Maven. You can check this by running the following commands:

```
java --version
mvn --version
```

If both commands return version details, your setup is correct and you're ready to proceed. Otherwise, you may need to configure your PATH environment variables to include the locations of your Java and Maven installations.

## Usage Options:

We provide multiple convenient ways to run this project:

### Pre-compiled JAR

Clone the repository and run the pre-compiled JAR file located in the `bin/` directory:

```
git clone https://github.com/brandonleehs/SC2002-HMS.git
cd SC2002-HMS/
java -jar ./bin/hms-1.0.jar
```

### Build with Maven

**1. Clone the Repository**

Navigate to your desired directory and clone this repository with `git clone`. Then navigate into the project directory.

```
git clone https://github.com/brandonleehs/SC2002-HMS.git
cd SC2002-HMS/
```

**2. Build the Project**

Clean the previous build artifacts (if any) and package the project into a JAR file using Maven. The generated build files will be located in the `target/` directory.

```
mvn clean package
```

**3. Run the Application**

After successfully building the project, execute the JAR file to run the Hospital Management System.

```
java -jar ./target/hms-1.0.jar
```

### Running from Source

**1. Clone the Repository**

If you prefer to compile and run the application directly from the source code, clone the repository and navigate to the project directory.

```
git clone https://github.com/brandonleehs/SC2002-HMS.git
cd SC2002-HMS/
```

**2. Create a list of all java files in the project, then run the application.**

Windows:

```
dir /s /B *.java > sources.txt
java -cp "./lib/*" "@sources.txt"
```

Linux/MacOS:

```
find -name "*.java" > sources.txt
java -cp "./lib/*" @sources.txt
```

## Project Structure

The project follows a standard Maven structure:

- `src/main/java/`: Contains the main source code for the application.
- `src/test/java/`: Holds unit tests for the project.
- `target/`: This directory is generated during the build process and houses all output of the build.

Our project uses the Entity-Control-Boundary (ECB) design pattern for maintainability and extensibility.

- **Entity** objects, such as patient, hold our business logic of our application.
- **Boundary** objects act as an interface between the system and the users. It handles input and output operations and is responsible for what the user views.
- **Control** objects coordinate the flow of data between the Entity and Boundary,

This separation allows us to achieve strong decoupling, which improves modularity, testability and scalability of the application.

Other design patterns our project uses include the Repository pattern, which enforces a clean, uniform interface for performing CRUD operations on stored objects. We also made use of generics for better code reusability and type safety.

## Features

1. Data Persistence
2. New Patient Registration
3. Password Encryption
4. Password Reset
5. Input Validation
6. Unit Testing

## JavaDoc

The JavaDoc is deployed on [github pages](https://brandonleehs.github.io/SC2002-HMS/).

## TroubleShooting

The application's state is persisted in CSV files located in src/main/resources. If you encounter serialization issues or have modified the CSV files, resulting in errors, you can reset the application's state to its initial configuration. To do so, copy the content from the corresponding `[Patient/Medicine/Staff]_List-initial.csv` file and replace the current `[Patient/Medicine/Staff]_List.csv` file with it. If you have any other CSV files, such as `[Appointment/AppointmentOutcomeRecord]_List.csv`, ensure you delete them as well. This will restore the application to its default state.

For any additional information or troubleshooting, please consult the project documentation or open an issue in the repository.
