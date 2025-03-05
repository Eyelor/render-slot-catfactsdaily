<p align="center"><h1 align="center">RENDER-SLOT-CATFACTSDAILY</h1></p>
<p align="center">
	<em>Your daily dose of purrfect facts.
</em>
</p>
<p align="center">
	<!-- local repository, no metadata badges. --></p>
<p align="center">Built with the tools and technologies:</p>
<p align="center">
	<img src="https://img.shields.io/badge/Docker-2496ED.svg?style=default&logo=Docker&logoColor=white" alt="Docker">
	<img src="https://img.shields.io/badge/Java-%23ED8B00.svg?style=default&logo=openjdk&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/SpringBoot-72B545.svg?style=default&logo=springboot&logoColor=white" alt="SpringBoot">
</p>
<br>

##  Table of Contents

- [ Overview](#overview)
- [ Features](#features)
- [ Project Structure](#project-structure)
  - [ Project Index](#project-index)
- [ Getting Started](#getting-started)
  - [ Prerequisites](#prerequisites)
  - [ Installation](#installation)
  - [ Usage](#usage)
  - [ Testing](#testing)
- [ License](#license)
- [ Acknowledgments](#acknowledgments)

---

##  Overview

CatFactsDaily is a secure, user-friendly web application where users can store and share their favorite cat facts.  It features user authentication, data persistence, and a clean REST API, all packaged for easy deployment using Docker.  Perfect for cat lovers who want a simple, reliable way to manage their feline-themed wisdom.

API documentation on [catfactsdaily.onrender.com/swagger-ui/index.html](https://catfactsdaily.onrender.com/swagger-ui/index.html)

Uptime and status on [stats.uptimerobot.com/cxdTXPQLeA](https://stats.uptimerobot.com/cxdTXPQLeA)

---

##  Features

|      | Feature         | Summary       |
| :--- | :---:           | :---          |
| ⚙️  | **Architecture**  | <ul><li>Uses a `<Spring Boot>` framework for building a RESTful API.</li><li>Employs a multi-stage `<Docker>` build process for efficient image creation (see `Dockerfile`).</li><li>Implements `<Spring Security>` with JWT (JSON Web Tokens) for authentication and authorization (see `src/main/java/com/example/catfactsdaily/config/SecurityConfig.java`).</li><li>Utilizes `<JPA>` (Java Persistence API) and a relational database for data persistence (see `CatFactRepository`, `UserRepository`).</li></ul> |
| 🔩 | **Code Quality**  | <ul><li>Code is primarily written in `<Java>`.</li><li>Uses Data Transfer Objects (DTOs) for data encapsulation and separation of concerns (see `CatFactDTO`, `UserDTO`).</li><li>Includes unit tests for at least the `CatFactsController` (see `src/test/java/com/example/catfactsdaily/controller/CatFactsControllerTest.java`).</li><li>Code structure represents a layered architecture (controllers, services, repositories).</li></ul> |
| 📄 | **Documentation** | <ul><li>Primary language is `<Java>`, with limited additional documentation.</li><li>Includes `<Swagger>`/OpenAPI configuration for API documentation (see `OpenApiConfig.java`).</li><li>Installation and usage instructions are provided using `<Docker>` (see `Dockerfile`).</li></ul> |
| 🔌 | **Integrations**  | <ul><li>Integrates with `<Docker>` for containerization and deployment.</li><li>Uses `<Maven>` (version 3.8.5) for build management (see `mvnw`, `mvnw.cmd`).</li><li>Leverages `<Jackson>` for JSON serialization/deserialization (see `JacksonConfig.java`).</li><li>Uses `<BCrypt>` for password hashing (implied by `ApplicationConfiguration.java`).</li></ul> |
| 🧩 | **Modularity**    | <ul><li>The codebase is organized into distinct packages by functionality (controllers, services, repositories, etc.).</li><li>Uses DTOs to decouple data transfer between layers.</li><li>Dependencies are managed using `<Maven>`, promoting modularity.</li></ul> |

---

##  Project Structure

```sh
└── render-slot-catfactsdaily/
    ├── Dockerfile
    ├── mvnw
    ├── mvnw.cmd
    ├── pom.xml
    ├── README.md
    └── src
        ├── main
        └── test
```


###  Project Index
<details open>
	<summary><b><code>RENDER-SLOT-CATFACTSDAILY/</code></b></summary>
	<details> <!-- __root__ Submodule -->
		<summary><b>__root__</b></summary>
		<blockquote>
			<table>
			<tr>
				<td><b><a href='./Dockerfile'>Dockerfile</a></b></td>
				<td>- The Dockerfile automates the building and deployment of a Java Spring Boot application<br>- It leverages a multi-stage build process: first compiling the application using Maven, running tests, and packaging it; then, deploying the resulting JAR into a minimal runtime environment<br>- The final image exposes port 8080 for the application's HTTP service.</td>
			</tr>
			<tr>
				<td><b><a href='./mvnw'>mvnw</a></b></td>
				<td>- The mvnw script bootstraps Apache Maven, a build automation tool<br>- It downloads and installs a Maven distribution if one is not already present, verifying its integrity<br>- The script then executes Maven commands, providing a consistent build environment across different systems and ensuring the correct Maven version is used for the project<br>- This simplifies project setup and dependency management.</td>
			</tr>
			<tr>
				<td><b><a href='./mvnw.cmd'>mvnw.cmd</a></b></td>
				<td>- mvnw.cmd acts as a wrapper script for Apache Maven, automating its download and setup if not already present<br>- It leverages PowerShell to download the correct Maven distribution based on configuration, verifies its integrity, and sets up the necessary environment variables, enabling execution of Maven commands via mvnw<br>- This streamlines the build process by ensuring a consistent Maven version across different environments.</td>
			</tr>
			</table>
		</blockquote>
	</details>
	<details> <!-- src Submodule -->
		<summary><b>src</b></summary>
		<blockquote>
			<details>
				<summary><b>main</b></summary>
				<blockquote>
					<details>
						<summary><b>java</b></summary>
						<blockquote>
							<details>
								<summary><b>com</b></summary>
								<blockquote>
									<details>
										<summary><b>example</b></summary>
										<blockquote>
											<details>
												<summary><b>catfactsdaily</b></summary>
												<blockquote>
													<table>
													<tr>
														<td><b><a href='./src\main\java\com\example\catfactsdaily\CatfactsdailyApplication.java'>CatfactsdailyApplication.java</a></b></td>
														<td>- CatfactsdailyApplication serves as the main application entry point for the Spring Boot application<br>- It enables scheduled tasks, initiating the application's core functionality<br>- The class bootstraps the Spring context, allowing other components to function, ultimately delivering daily cat facts.</td>
													</tr>
													</table>
													<details>
														<summary><b>config</b></summary>
														<blockquote>
															<table>
															<tr>
																<td><b><a href='./src\main\java\com\example\catfactsdaily\config\ApplicationConfiguration.java'>ApplicationConfiguration.java</a></b></td>
																<td>- ApplicationConfiguration configures Spring Security for the CatFactsDaily application<br>- It defines beans for user details service, password encoding (using BCrypt), authentication manager, and authentication provider<br>- These beans integrate user data from the UserRepository to enable secure user authentication within the application's architecture.</td>
															</tr>
															<tr>
																<td><b><a href='./src\main\java\com\example\catfactsdaily\config\JacksonConfig.java'>JacksonConfig.java</a></b></td>
																<td>- JacksonConfig customizes Jackson's ObjectMapper for the CatFactsDaily application<br>- It configures the ObjectMapper to handle Java 8 date and time objects,  exclude null values from JSON output, and format dates in a specific pattern<br>- This ensures consistent and optimized JSON serialization and deserialization throughout the application, improving data handling efficiency.</td>
															</tr>
															<tr>
																<td><b><a href='./src\main\java\com\example\catfactsdaily\config\JwtAuthenticationFilter.java'>JwtAuthenticationFilter.java</a></b></td>
																<td>- JwtAuthenticationFilter intercepts incoming requests, validating JSON Web Tokens (JWTs) present in Authorization headers<br>- It extracts user information from verified tokens, authenticating the user and populating Spring Security's context<br>- Upon successful authentication, the filter allows the request to proceed; otherwise, it handles exceptions gracefully<br>- This ensures secure access to protected resources within the CatFactsDaily application.</td>
															</tr>
															<tr>
																<td><b><a href='./src\main\java\com\example\catfactsdaily\config\OpenApiConfig.java'>OpenApiConfig.java</a></b></td>
																<td>- OpenApiConfig configures the Spring Boot application's OpenAPI (Swagger) documentation<br>- It defines the API title, version, and security scheme using JWT bearer tokens<br>- This enables automatic generation of interactive API documentation, improving developer experience and facilitating API consumption by external clients<br>- The configuration enhances the CatFactsDaily application's discoverability and usability.</td>
															</tr>
															<tr>
																<td><b><a href='./src\main\java\com\example\catfactsdaily\config\SecurityConfig.java'>SecurityConfig.java</a></b></td>
																<td>- SecurityConfig secures the CatFactsDaily application<br>- It configures Spring Security, enabling JWT authentication and authorization<br>- Specifically, it defines security rules, allowing access to specific endpoints like Swagger and authentication routes while requiring authentication for others<br>- CORS configuration is also established to handle cross-origin requests<br>- The configuration uses a stateless session management approach for enhanced security.</td>
															</tr>
															</table>
														</blockquote>
													</details>
													<details>
														<summary><b>controller</b></summary>
														<blockquote>
															<table>
															<tr>
																<td><b><a href='./src\main\java\com\example\catfactsdaily\controller\AuthenticationController.java'>AuthenticationController.java</a></b></td>
																<td>- AuthenticationController manages user registration and login within the CatFactsDaily application<br>- It receives user credentials, interacts with an authentication service to verify them, and generates JSON Web Tokens (JWTs) for successful logins<br>- The controller returns user information and JWTs to the client, enabling secure access to application resources.</td>
															</tr>
															<tr>
																<td><b><a href='./src\main\java\com\example\catfactsdaily\controller\CatFactsController.java'>CatFactsController.java</a></b></td>
																<td>- CatFactsController manages user interactions with cat facts<br>- It provides REST endpoints for adding, retrieving, updating, and deleting cat facts, secured using JWT authentication<br>- The controller interacts with a CatFactsService for data persistence and a JwtService for authentication, ensuring only authorized users can modify their data<br>- Responses are appropriately formatted and include logging for security and debugging.</td>
															</tr>
															<tr>
																<td><b><a href='./src\main\java\com\example\catfactsdaily\controller\LoggedInController.java'>LoggedInController.java</a></b></td>
																<td>- LoggedInController manages user logout within the CatFactsDaily application<br>- It utilizes a JWT service to verify user identity and authorization before invalidating the provided token<br>- This ensures only authenticated users can log out and prevents unauthorized access<br>- The controller returns appropriate HTTP responses indicating success or unauthorized attempts.</td>
															</tr>
															</table>
														</blockquote>
													</details>
													<details>
														<summary><b>dto</b></summary>
														<blockquote>
															<table>
															<tr>
																<td><b><a href='./src\main\java\com\example\catfactsdaily\dto\CatFactDTO.java'>CatFactDTO.java</a></b></td>
																<td>- CatFactDTO defines a data transfer object within the CatFactsDaily application<br>- It encapsulates cat fact data, specifically a single string representing the fact itself, for efficient data exchange between different application layers<br>- This facilitates clean separation of concerns and improves code maintainability within the broader project architecture.</td>
															</tr>
															<tr>
																<td><b><a href='./src\main\java\com\example\catfactsdaily\dto\UserDTO.java'>UserDTO.java</a></b></td>
																<td>- UserDTO defines a data transfer object for user information within the CatFactsDaily application<br>- It encapsulates user name and password, facilitating data exchange between different layers of the application, likely serving as a model for user registration or login processes<br>- This promotes clean separation of concerns and improves code maintainability.</td>
															</tr>
															</table>
														</blockquote>
													</details>
													<details>
														<summary><b>entity</b></summary>
														<blockquote>
															<table>
															<tr>
																<td><b><a href='./src\main\java\com\example\catfactsdaily\entity\CatFact.java'>CatFact.java</a></b></td>
																<td>- CatFact defines the data structure for storing cat facts within the CatFactsDaily application<br>- It represents a single cat fact entry, including its unique identifier, user association, the fact itself, timestamps for creation and modification, and an active status flag<br>- This entity facilitates database persistence and interaction with the application's core functionality.</td>
															</tr>
															<tr>
																<td><b><a href='./src\main\java\com\example\catfactsdaily\entity\TokenBlacklisted.java'>TokenBlacklisted.java</a></b></td>
																<td>- TokenBlacklisted.java defines an entity for managing blacklisted authentication tokens within the CatFactsDaily application<br>- It stores blacklisted tokens, their expiration dates, and ensures uniqueness for efficient revocation<br>- This entity contributes to the application's security by preventing unauthorized access using invalidated tokens, enhancing overall system robustness.</td>
															</tr>
															<tr>
																<td><b><a href='./src\main\java\com\example\catfactsdaily\entity\User.java'>User.java</a></b></td>
																<td>- User.java defines the User entity for the CatFactsDaily application<br>- It represents a user within the system,  managing user authentication and authorization details<br>- The entity includes a unique username, password, and implements Spring Security's UserDetails interface for integration with the application's security framework<br>- This enables user management and access control features.</td>
															</tr>
															</table>
														</blockquote>
													</details>
													<details>
														<summary><b>repository</b></summary>
														<blockquote>
															<table>
															<tr>
																<td><b><a href='./src\main\java\com\example\catfactsdaily\repository\CatFactRepository.java'>CatFactRepository.java</a></b></td>
																<td>- CatFactRepository manages persistent storage of cat facts within the CatFactsDaily application<br>- It leverages Spring Data JPA to provide data access functionality, specifically enabling retrieval of cat facts based on user ID<br>- This repository facilitates database interactions, simplifying data management for the application's core functionality.</td>
															</tr>
															<tr>
																<td><b><a href='./src\main\java\com\example\catfactsdaily\repository\TokenBlacklistedRepository.java'>TokenBlacklistedRepository.java</a></b></td>
																<td>- TokenBlacklistedRepository manages blacklisted authentication tokens within the CatFactsDaily application<br>- It provides database access for storing and retrieving blacklisted tokens, enabling efficient checking of token validity and automated removal of expired tokens<br>- This ensures application security by preventing unauthorized access.</td>
															</tr>
															<tr>
																<td><b><a href='./src\main\java\com\example\catfactsdaily\repository\UserRepository.java'>UserRepository.java</a></b></td>
																<td>- UserRepository manages persistent storage of User entities within the CatFactsDaily application<br>- Leveraging Spring Data JPA, it provides database interaction, enabling retrieval of users by name and standard CRUD operations<br>- This repository component facilitates user authentication and data management within the broader application architecture.</td>
															</tr>
															</table>
														</blockquote>
													</details>
													<details>
														<summary><b>response</b></summary>
														<blockquote>
															<table>
															<tr>
																<td><b><a href='./src\main\java\com\example\catfactsdaily\response\CatFactResponse.java'>CatFactResponse.java</a></b></td>
																<td>- CatFactResponse defines a data structure representing a cat fact within the CatFactsDaily application<br>- It encapsulates essential attributes: a unique identifier, the cat fact itself, and timestamps indicating creation and modification times<br>- This class facilitates the consistent handling and transmission of cat fact data throughout the application's response mechanisms.</td>
															</tr>
															<tr>
																<td><b><a href='./src\main\java\com\example\catfactsdaily\response\LoginResponse.java'>LoginResponse.java</a></b></td>
																<td>- LoginResponse defines a data structure for successful user logins within the CatFactsDaily application<br>- It encapsulates user information, specifically the user's name, a security token, and the token's expiration time<br>- This class facilitates the communication of authentication results from the application's backend to the client, enabling secure session management.</td>
															</tr>
															<tr>
																<td><b><a href='./src\main\java\com\example\catfactsdaily\response\SignupResponse.java'>SignupResponse.java</a></b></td>
																<td>- SignupResponse defines a data structure for user registration responses within the CatFactsDaily application<br>- It encapsulates user name information, facilitating communication between the application's signup functionality and other components<br>- This class plays a role in returning user details after successful registration, contributing to the overall user management system.</td>
															</tr>
															</table>
														</blockquote>
													</details>
													<details>
														<summary><b>service</b></summary>
														<blockquote>
															<table>
															<tr>
																<td><b><a href='./src\main\java\com\example\catfactsdaily\service\AuthenticationService.java'>AuthenticationService.java</a></b></td>
																<td>- AuthenticationService handles user registration and login within the CatFactsDaily application<br>- It uses Spring Security's AuthenticationManager for authentication and encrypts passwords using a PasswordEncoder<br>- The service interacts with a UserRepository to persist and retrieve user data, ensuring data integrity through transactional management<br>- It facilitates secure user access to the application.</td>
															</tr>
															<tr>
																<td><b><a href='./src\main\java\com\example\catfactsdaily\service\CatFactsService.java'>CatFactsService.java</a></b></td>
																<td>- CatFactsService manages user cat facts within the CatFactsDaily application<br>- It provides functionalities for adding, retrieving, updating, and deleting user-specific cat facts, ensuring data integrity through transactional control<br>- The service interacts with a repository to persist data and uses DTOs and response objects for data transfer and presentation<br>- Unauthorized access is prevented through user ID verification.</td>
															</tr>
															<tr>
																<td><b><a href='./src\main\java\com\example\catfactsdaily\service\JwtService.java'>JwtService.java</a></b></td>
																<td>- JwtService manages JSON Web Tokens (JWTs) for authentication within the CatFactsDaily application<br>- It generates, validates, and invalidates JWTs, extracting user information and checking for token expiration or blacklisting<br>- The service interacts with a token blacklist repository to ensure security<br>- Integration with Spring Security is evident through UserDetails and UsernamePasswordAuthenticationToken usage.</td>
															</tr>
															<tr>
																<td><b><a href='./src\main\java\com\example\catfactsdaily\service\TokenCleanupService.java'>TokenCleanupService.java</a></b></td>
																<td>- TokenCleanupService periodically removes expired blacklisted tokens from the CatFactsDaily application's database<br>- Operating as a scheduled Spring service, it identifies and deletes entries with past expiration dates, maintaining database integrity and efficiency by removing obsolete data<br>- The service uses a transactional approach to ensure data consistency.</td>
															</tr>
															</table>
														</blockquote>
													</details>
												</blockquote>
											</details>
										</blockquote>
									</details>
								</blockquote>
							</details>
						</blockquote>
					</details>
				</blockquote>
			</details>
			<details>
				<summary><b>test</b></summary>
				<blockquote>
					<details>
						<summary><b>java</b></summary>
						<blockquote>
							<details>
								<summary><b>com</b></summary>
								<blockquote>
									<details>
										<summary><b>example</b></summary>
										<blockquote>
											<details>
												<summary><b>catfactsdaily</b></summary>
												<blockquote>
													<table>
													<tr>
														<td><b><a href='./src\test\java\com\example\catfactsdaily\CatfactsdailyApplicationTests.java'>CatfactsdailyApplicationTests.java</a></b></td>
														<td>- CatfactsdailyApplicationTests provides a Spring Boot test suite for the Catfactsdaily application<br>- The file's role within the project is to ensure the application's core functionality works correctly, contributing to overall application quality and stability.</td>
													</tr>
													</table>
													<details>
														<summary><b>controller</b></summary>
														<blockquote>
															<table>
															<tr>
																<td><b><a href='./src\test\java\com\example\catfactsdaily\controller\CatFactsControllerTest.java'>CatFactsControllerTest.java</a></b></td>
																<td>- Unit tests verify the `CatFactsController`<br>- These tests cover various scenarios for creating, reading, updating, and deleting cat facts,  including authorization checks and handling of edge cases like  nonexistent records<br>- The tests utilize mock objects to isolate the controller's logic and ensure its proper interaction with the `CatFactsService` and `JwtService` within the application's architecture.</td>
															</tr>
															</table>
														</blockquote>
													</details>
												</blockquote>
											</details>
										</blockquote>
									</details>
								</blockquote>
							</details>
						</blockquote>
					</details>
				</blockquote>
			</details>
		</blockquote>
	</details>
</details>

---
##  Getting Started

###  Prerequisites

Before getting started with render-slot-catfactsdaily, ensure your runtime environment meets the following requirements:

- **Programming Language:** Java
- **Container Runtime:** Docker


###  Installation

Install render-slot-catfactsdaily using the following method:

1. Clone the render-slot-catfactsdaily repository:
```sh
git clone https://github.com/Eyelor/render-slot-catfactsdaily
```

2. Navigate to the project directory:
```sh
cd render-slot-catfactsdaily
```

3. Install the project dependencies:

**Using `docker`**

```sh
docker build -t CatFactsDaily .
```


###  Usage
Run render-slot-catfactsdaily using the following command:

**Using `docker`**

```sh
docker run -p 8080:8080 CatFactsDaily
```


###  Testing
Run the test suite using the following command:
```sh
mvn clean test
```
This command runs before the build process in the dockerfile, but you can do this too in your IDE. For example in the IntelliJ IDEA if you have access to mvn in your terminal.

---

##  License

This project is protected under the Apache 2.0 License. For more details, refer to the [LICENSE](./LICENSE) file.

---

##  Acknowledgments

Authors:

<a href="https://github.com/Eyelor">
  <img src="https://github.com/Eyelor.png" width="64" height="64" />
</a>
<a href="https://github.com/Minlok3">
  <img src="https://github.com/Minlok3.png" width="64" height="64" />
</a>
<a href="https://github.com/HPL21">
  <img src="https://github.com/HPL21.png" width="64" height="64" />
</a>

---
