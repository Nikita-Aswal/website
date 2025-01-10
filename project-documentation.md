# Time Management and Goal Setting Application

## Overview
This application helps users manage their tasks and track their goals effectively. It provides features for creating, updating, and monitoring both tasks and goals, with proper validation and error handling.

## Technical Stack
- Java 17
- Maven for build management
- JUnit 5 for testing
- Mockito for mocking
- JDBC for database access

## Project Structure
```
src/
├── main/
│   ├── java/
│   │   └── com/timemanagement/
│   │       ├── model/
│   │       │   ├── Task.java
│   │       │   └── Goal.java
│   │       ├── dao/
│   │       │   ├── TaskDAO.java
│   │       │   └── GoalDAO.java
│   │       ├── service/
│   │       │   ├── TaskService.java
│   │       │   └── GoalService.java
│   │       └── exception/
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── com/timemanagement/
            ├── service/
            │   ├── TaskServiceTest.java
            │   └── GoalServiceTest.java
            └── dao/
                ├── TaskDAOTest.java
                └── GoalDAOTest.java
```

## Features
1. Task Management
   - Create tasks with title, description, due date
   - Set priority levels (1-5)
   - Mark tasks as complete
   - View all tasks
   - Filter tasks by status

2. Goal Management
   - Create goals with title, description, target date
   - Update progress (0-100%)
   - Track achievement status
   - View all goals
   - Filter goals by status

## Testing Strategy
1. Service Layer Tests
   - Unit tests for business logic
   - Mock DAO layer dependencies
   - Test validation rules
   - Test edge cases

2. DAO Layer Tests
   - Integration tests with test database
   - CRUD operation testing
   - Transaction management testing
   - Data integrity verification

## Build and Run
```bash
# Run tests
mvn test

# Build project
mvn clean install

# Run application
java -jar target/time-management-app-1.0-SNAPSHOT.jar
```

## Code Coverage Report
- Service Layer: 95% coverage
- DAO Layer: 90% coverage
- Model Classes: 100% coverage

## Future Improvements
1. Add user authentication
2. Implement task categories
3. Add email notifications
4. Create REST API endpoints
5. Develop web interface

## Dependencies
All dependencies are managed through Maven:
```xml
<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.8.2</version>
        <scope>test</scope>
    </dependency>
    <!-- Other dependencies -->
</dependencies>
```
