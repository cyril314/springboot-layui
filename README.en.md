The provided project appears to be a comprehensive Java-based web application focused on administrative functionalities and user management. It utilizes Spring Boot, MyBatis, and Shiro for framework support, and integrates with a database to manage entities such as users, roles, permissions, and various business data.

The project includes:
- **Spring Boot**: For building standalone, production-grade applications.
- **Apache Shiro**: For authentication and authorization.
- **MyBatis**: As the persistence layer framework.
- **LayUI**: For frontend UI components.
- **Ehcache**: For caching.
- **Logback**: For logging.

### Key Features:
- **User Management**: Creation of users, assignment of roles, password management, and status modification.
- **Role and Permission Management**: Definition of roles and assignment of permissions to them.
- **Audit Logging**: Tracking of operations performed within the system.
- **Monitoring**: Monitoring of system metrics such as memory, CPU, and thread usage.
- **CRUD Operations**: Implemented across multiple entities including exams, papers, subjects, and more.

### Technologies Used:
- Java 8+
- Spring Boot
- MyBatis
- Apache Shiro
- LayUI (Frontend framework)
- MySQL (Likely, based on usage of JdbcTemplate)
- Ehcache
- Logback

### Project Structure:
- **Controllers**: Handle HTTP requests and responses.
- **Services**: Business logic layer.
- **DAOs**: Data Access Objects for database interactions.
- **Entities**: Model classes representing database tables.
- **Configuration**: Spring configuration files, including security and MVC setup.
- **Utilities**: Helper classes for common tasks such as JSON parsing, file handling, etc.

### Usage:
To run this application, ensure that:
1. Java and Maven are installed.
2. A database is set up and configured in `application.yml`.
3. Dependencies are correctly resolved via Maven.

Start the application using:
```bash
mvn spring-boot:run
```

For more detailed instructions or contributions, refer to the project's documentation or `README.md` if available in the root directory.