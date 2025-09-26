# 🚀 Spring Boot Starter Kit

A production-ready **Spring Boot Starter Project** with best practices, authentication, logging, and developer-friendly setup.  
Built in public over **90 days** and updated daily!  

## 📅 Progress Log
- **Day 1:** Project scaffolded with a simple `HelloController`. 
- **Day 2:** Added `UserController` with `/users` endpoint returning mock data. 
- **Day 3:** Integrated PostgreSQL + Spring Data JPA. `/users` now supports create & list from DB.
- **Day 4:** Integrated PostgreSQL + Spring Data JPA. `/users` now supports create & list, update, delete from DB with Global exception handling.


## 🎯 Roadmap
- ✅ Basic setup
- ✅ REST endpoints  
- ✅ Database (Postgres) 
- ⏳ JWT Authentication  
- ⏳ Docker support  
- ⏳ Logging & Monitoring  

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
