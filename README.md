#  Spring Boot Starter Kit

A production-ready **Spring Boot Starter Project** with best practices, authentication, logging, and developer-friendly setup.  
Built in public over **90 days** and updated daily!  

## Progress Log
- **Day 1:** Project scaffolded with a simple `HelloController`. 
- **Day 2:** Added `UserController` with `/users` endpoint returning mock data. 
- **Day 3:** Integrated PostgreSQL + Spring Data JPA. `/users` now supports create & list from DB.
- **Day 4:** Integrated PostgreSQL + Spring Data JPA. `/users` now supports create & list from DB with Global exception handling.
- **Day 5:** Completed full CRUD for `UserEntity` and added `@ControllerAdvice`-based global exception handling.
- **Day 6:** Added pagination, sorting, and custom query endpoints (`/api/users/paged`, `/api/users/search`) for scalable User listing.
- **Day 7:**  Added DTOs: `UserRequest` and `UserResponse`, Enforced validation using `spring-boot-starter-validation` (@NotBlank, @Email, @Size), Updated 		`UserController` to accept `@Valid` requests and return `UserResponse` and Improved global error handling: returns a map of field errors for validation failures and structured ApiError for other exceptions.
- **Day 8:** Setup Swagger/OpenAPI docs.
- **Day 9:** Enhancing API documentation with annotations and better descriptions.
- **Day 10:**  Dockerizing Spring boot starter-kit.
- **Day 11:**  JWT Authentication (Login + Token Generation). We’ve officially entered **Phase 2 — Authentication & Security**.  
				Today we implemented **JWT-based login** with Spring Security and PostgreSQL.
- **Day 12:**  Role-Based Authorization (ADMIN / USER)
- **Day 13:** Method-Level Authorization (@PreAuthorize / @PostAuthorize)

			- Enabled `@EnableMethodSecurity(prePostEnabled = true)` in `SecurityConfig`.
			- Added fine-grained method-level access:
			- `@PreAuthorize("hasRole('ADMIN')")` for admin-only endpoints.
			- `@PreAuthorize("hasAnyRole('USER','ADMIN')")` for user endpoints.
			- Introduced `@PostAuthorize` to validate access *after* execution — great for per-user data filtering.

			This makes the Starter Kit more secure and production-ready.
- **Day 14:** Refresh Tokens + Expiry Handling

			- Added short-lived Access Tokens (15 mins)
			- Added long-lived Refresh Tokens (7 days)
			- New endpoint: `/api/auth/refresh` to renew access token
			- Improves UX — users stay logged in without re-entering credentials

			Key: Access token handles frequent calls, refresh token handles session continuity.




Follow my journey on [X](https://x.com/patilvishi) for daily updates!  

---

##  Getting Started

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

### ? What’s New
- Added `/api/v1/auth/login` endpoint.
- Integrated Spring Security + BCrypt password hashing.
- Configured JWT secret + expiry in `application.yml`.

### Test Flow User based

Register users with different roles:

POST /api/auth/signup
{
  "username": "vish_admin",
  "email": "admin@example.com",
  "password": "admin123",
  "role": "ROLE_ADMIN"
}

POST /api/auth/signup
{
  "username": "vish_user",
  "email": "user@example.com",
  "password": "user123",
  "role": "ROLE_USER"
}


Login both users -> get tokens.

Try:

GET /api/admin/dashboard ? Works only for ADMIN.

GET /api/user/profile ? Works for both ADMIN & USER.
