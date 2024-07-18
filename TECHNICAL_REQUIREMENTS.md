# Техническое задание

## Проект: Платформа онлайн-курсов

### Введение

Целью данного проекта является создание платформы для онлайн-курсов, которая связывает преподавателей и учеников. Платформа позволяет преподавателям создавать и управлять курсами.

### Бизнес-модель

#### Сущности

1. **Преподаватель (Trainer)**
    - **Идентификатор (ID)**: уникальный идентификатор преподавателя.
    - **Имя (Name)**: ФИО преподавателя.
    - **Список курсов (Courses)**: список курсов, созданных преподавателем.

2. **Курс (Course)**
    - **Идентификатор (ID)**: уникальный идентификатор курса.
    - **Название (Title)**: название курса.
    - **Описание (Description)**: описание курса.
    - **Преподаватель (Trainer)**: ссылка на преподавателя, который создал курс.

### Требования к системе

#### Технологические требования

- **Язык программирования**: Java 21+
- **Фреймворк**: Spring Boot 3.x
- **Система сборки**: Maven 3.x
- **База данных**: PostgreSQL 15.x
- **Инструменты разработки**: IntelliJ IDEA, Eclipse

### Структура базы данных

#### Таблица: trainer

| Имя поля    | Тип данных     | Обязательное | Уникальность | Ограничения    | Описание                             	|
|-------------|----------------|--------------|--------------|----------------|-----------------------------------------|
| id          | INT            | YES          | YES          | AUTO_INCREMENT | Уникальный идентификатор преподавателя. |
| name        | VARCHAR(255)   | YES          | NO           |                | ФИО преподавателя.                   	|

#### Таблица: course

| Имя поля    | Тип данных     | Обязательное | Уникальность | Ограничения    | Описание                             	|
|-------------|----------------|--------------|--------------|----------------|-----------------------------------------|
| id          | INT            | YES          | YES          | AUTO_INCREMENT | Уникальный идентификатор курса.      	|
| title       | VARCHAR(255)   | YES          | NO           |                | Название курса.                      	|
| description | TEXT           | NO           | NO           |                | Описание курса.                      	|
| trainer_id  | INT            | YES          | NO           | FOREIGN KEY    | Идентификатор преподавателя курса.		|

### Функциональные требования

- Возможность регистрации преподавателя с уникальным идентификатором и именем.
- Возможность получения информации о преподавателе.
- Возможность обновления информации о преподавателе.
- Возможность удаления преподавателя.
- Возможность создания курса с уникальным идентификатором, названием, описанием и ссылкой на преподавателя.
- Возможность обновления информации о курсе.
- Возможность получения информации о курсе.
- Возможность получения списка всех курсов преподавателя.
- Возможность обновления информации о курсе.
- Возможность удаления курса.

### Интерфейсные требования

1. **Пользовательский интерфейс**
    - Веб-интерфейс для преподавателей, позволяющий регистрироваться, создавать и управлять курсами.

2. **API**
    - RESTful API для взаимодействия с данными о преподавателях и курсах, включающий следующие конечные точки:
        - POST /api/v1/trainers: создание преподавателя.
        - GET /api/v1/trainers: получение списка преподавателей.
        - GET /api/v1/trainers/{id}: получение информации о преподавателе.
        - PUT /api/v1/trainers/{id}: обновление информации о преподавателе.
        - DELETE /api/v1/trainers/{id}: удаление преподавателя.
        - POST /api/v1/courses: создание курса.
        - PUT /api/v1/courses/{id}: обновление информации о курсе.
        - GET /api/v1/courses: получение списка всех курсов.
        - GET /api/v1/courses/{id}: получение информации о курсе.
        - DELETE /api/v1/courses/{id}: удаление курса.
        - GET /api/v1/trainers/{id}/courses: получение списка курсов конкретного преподавателя.

3. **Возможные коды ошибок**
    - 400 Bad Request: Неверный запрос (например, отсутствуют обязательные поля).
    - 404 Not Found: Данные не найдены, например преподаватель или курс с указанным ID не найден.
    - 500 Internal Server Error: Ошибка сервера.

4. **Примеры запросов и ответов**

    * Создание преподавателя (POST /api/v1/trainers)
        - Запрос (json):
          ```json
          {
            "name": "Иванов Иван Иванович"
          }
          ```
        - Ответ (json):
          ```json
          {
            "id": 1,
            "name": "Иванов Иван Иванович"
          }
          ```

    * Обновление информации о преподавателе (PUT /api/v1/trainers/{id})
        - Запрос (json):
          ```json
          {
            "name": "Иванов Иван Владимирович"
          }
          ```
        - Ответ (json):
          ```json
          {
            "id": 1,
            "name": "Иванов Иван Владимирович"
          }
          ```

    * Создание курса (POST /api/v1/courses)
        - Запрос (json):
          ```json
          {
            "title": "Основы программирования",
            "description": "Курс по основам программирования.",
            "trainer_id": 1
          }
          ```
        - Ответ (json):
          ```json
          {
            "id": 1,
            "title": "Основы программирования",
            "description": "Курс по основам программирования.",
            "trainer_id": 1
          }
          ```

    * Обновление информации о курсе (PUT /api/v1/courses/{id})
        - Запрос (json):
          ```json
          {
            "title": "Продвинутый курс программирования",
            "description": "Углубленный курс по программированию.",
            "trainer_id": 1
          }
          ```
        - Ответ (json):
          ```json
          {
            "id": 1,
            "title": "Продвинутый курс программирования",
            "description": "Углубленный курс по программированию.",
            "trainer_id": 1
          }
          ```

    * Удаление курса (DELETE /api/v1/courses/{id})
        - Запрос (json):
          ```json
          {
            "id": 1,
            "title": "Основы программирования",
            "description": "Курс по основам программирования.",
            "trainer_id": 1
          }
          ```