# Course Manager

Course Manager is a web application that allows trainers to create and manage their courses.

## Project Description

The goal of this project is to create a platform for online courses that connects trainers and students. The platform allows trainers to create and manage courses.

## Technologies Used

- Java 21
- Spring Boot 3.2.7
- Maven 3.9.1
- PostgreSQL 15
- IntelliJ IDEA / Eclipse

## Getting Started

### Prerequisites

- Java 21
- Maven 3.x
- PostgreSQL 15.x

## Installation

1. Clone the repository:


            git clone https://github.com/ASaurytskaya/Course_Manager

2. Navigate to the project directory:

            cd course_manager

3. Configure PostgreSQL
- Create a new database.

4. Set up environment variables:
      
- Set your database connection properties.

            export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:your_db_port/your_db_name 
            export SPRING_DATASOURCE_USERNAME=your_username 
            export SPRING_DATASOURCE_PASSWORD=your_password
  
- Set server port.

            export SERVER_PORT=your_port

5. Build the project:

            mvn clean install

6. Run the application:

            mvn spring-boot:run


### API

The RESTful API allows for interaction with trainer and course data, including the following endpoints:
#### Trainer Endpoints
- **Create Trainer**
  - `POST /course_manager/api/v1/trainers`
  - Request Body:
    ```json
    {
      "name": "John Doe"
    }
    ```
  - Response:
    ```json
    {
      "id": 1,
      "name": "John Doe"
    }
    ```

- **Get All Trainers**
  - `GET /course_manager/api/v1/trainers`
  - Response:
    ```json
    [
      {
        "id": 1,
        "name": "John Doe"
      },
      {
        "id": 2,
        "name": "Jane Smith"
      }
    ]
    ```

- **Get Trainer By ID**
  - `GET /course_manager/api/v1/trainers/{id}`
  - Response:
    ```json
    {
      "id": 1,
      "name": "John Doe"
    }
    ```

- **Update Trainer**
  - `PUT /course_manager/api/v1/trainers/{id}`
  - Request Body:
    ```json
    {
      "name": "Johnathan Doe"
    }
    ```
  - Response:
    ```json
    {
      "id": 1,
      "name": "Johnathan Doe"
    }
    ```

- **Delete Trainer**
  - `DELETE /course_manager/api/v1/trainers/{id}`

#### Course Endpoints
- **Create Course**
  - `POST /course_manager/api/v1/courses`
  - Request Body:
    ```json
    {
      "title": "Programming Basics",
      "description": "Introduction to programming.",
      "trainerId": 1
    }
    ```
  - Response:
    ```json
    {
      "id": 1,
      "title": "Programming Basics",
      "description": "Introduction to programming.",
      "trainerId": 1
    }
    ```

- **Get All Courses**
  - `GET /course_manager/api/v1/courses`
  - Response:
    ```json
    [
      {
        "id": 1,
        "title": "Programming Basics",
        "description": "Introduction to programming.",
        "trainerId": 1
      }
    ]
    ```

- **Get Course By ID**
  - `GET /course_manager/api/v1/courses/{id}`
  - Response:
    ```json
    {
      "id": 1,
      "title": "Programming Basics",
      "description": "Introduction to programming.",
      "trainerId": 1
    }
    ```

- **Update Course**
  - `PUT /course_manager/api/v1/courses/{id}`
  - Request Body:
    ```json
    {
      "title": "Advanced Programming",
      "description": "Advanced concepts in programming.",
      "trainerId": 1
    }
    ```
  - Response:
    ```json
    {
      "id": 1,
      "title": "Advanced Programming",
      "description": "Advanced concepts in programming.",
      "trainerId": 1
    }
    ```

- **Delete Course**
  - `DELETE /course_manager/api/v1/courses/{id}`

- **Get Courses By Trainer ID**
  - `GET /course_manager/api/v1/trainers/{id}/courses`
  - Response:
    ```json
    [
      {
        "id": 1,
        "title": "Programming Basics",
        "description": "Introduction to programming.",
        "trainerId": 1
      }
    ]
    ```

### Error Handling

The API provides structured error responses for various exceptions. Below are the main exceptions and their corresponding HTTP status codes:

1. BadRequestException
   * Status Code: 400 BAD REQUEST
   * Description: Thrown for invalid requests, such as missing required fields.
2. TrainerNotFoundException
   * Status Code: 404 NOT FOUND
   * Description: Thrown when a trainer with the specified ID is not found.
3. CourseNotFoundException
   * Status Code: 404 NOT FOUND
   * Description: Thrown when a course with the specified ID is not found.
4. Global Exception
   * Status Code: 500 INTERNAL SERVER ERROR
   * Description: Catches any unhandled exceptions and returns a generic error message.

Example Error Response
```json
    {
      "error_type": "error",
      "message": "No trainer found with id 123"
    }
```