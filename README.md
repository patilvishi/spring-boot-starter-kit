# 🚀 Spring Boot Starter Kit

A production-ready **Spring Boot Starter Project** with best practices, authentication, logging, and developer-friendly setup.  
Built in public over **90 days** and updated daily!  

## 📅 Progress Log
- **Day 1:** Project scaffolded with a simple `HelloController`. 
- **Day 2:** Added `UserController` with `/users` endpoint returning mock data. 
- **Day 3:** Integrated PostgreSQL + Spring Data JPA. `/users` now supports create & list from DB.
- **Day 4:** Integrated PostgreSQL + Spring Data JPA. `/users` now supports create & list from DB with Global exception handling.
- **Day 5:** Completed full CRUD for `UserEntity` and added `@ControllerAdvice`-based global exception handling.
- **Day 6:** Added pagination, sorting, and custom query endpoints (`/api/users/paged`, `/api/users/search`) for scalable User listing.
- **Day 7:**  Added DTOs: `UserRequest` and `UserResponse`, Enforced validation using `spring-boot-starter-validation` (@NotBlank, @Email, @Size), Updated 		`UserController` to accept `@Valid` requests and return `UserResponse` and Improved global error handling: returns a map of field errors for validation failures and structured ApiError for other exceptions.
- **Day 8:** Setup Swagger/OpenAPI docs.
- **Day 9:** Enhancing API documentation with annotations and better descriptions.





Follow my journey on [X](https://x.com/patilvishi) for daily updates!  

---

## 🚀 Getting Started

```bash
git clone https://github.com/patilvishi/spring-boot-starter-kit.git
cd spring-boot-starter-kit
./mvnw spring-boot:run   # If using Maven

## Create a user:

	POST http://localhost:8080/api/users
	Body:
	{
		"name": "Alice",
		"email": "alice@example.com"
	}


## List users:

	GET http://localhost:8080/api/users


## Get single user:

	GET http://localhost:8080/api/users/{id}


## Delete:

	DELETE http://localhost:8080/api/users/{id}
