## REST API для управления пользователями и их подписками на сервисы

## Используемые технологии:
* Java 17
* Spring Boot 3
* Maven
* Lombok
* Mapstruct
* Hibernate
* PostgreSQL
* Flyway
* Swagger
* Docker
* Redis
* AOP
* Spring Cloud Config

### Функционал
1. API для управления пользователями
- Создание пользователя.
- Получение информации о пользователе.
- Обновление данных пользователя.
- Удаление пользователя.
2. API для подписок
- Добавление подписки пользователю.
- Получение списка подписок пользователя.
- Удаление подписки.
- Подписки представляют собой подписки на цифровые сервисы, такие как
  YouTube Premium, VK Музыка, Яндекс.Плюс, Netflix и другие стриминговые
  платформы.
3. Интеграция с базой данных
- Использовать PostgreSQL.
- Таблицы: users, subscriptions.
4. Логирование
- Логирование через SLF4J.\
Логирование осуществляется в классе ErrorHandlingControllerAdvice и классах сущностей с использованием аннотаций @PrePersist, @PostPersist, @PreRemove, @PostRemove.\
Также реализовано логирование посредством AOP.\
Класс LoggingAspect предназначен для логирования аргументов методов, помеченных кастомной аннотацией @LogMethodArgs
5. Docker
- Создан Dockerfile для развертывания сервиса.
- Разработан docker-compose.yml, который позволяет локально запускать проект
  вместе с базой данных и Redis.

### API
#### Эндпоинты:
- POST /users - создать пользователя
- GET /users/{id} - получить информацию о пользователе
- PUT /users/{id} - обновить пользователя
- DELETE /users/{id} - удалить пользователя
- POST /users/{id}/subscriptions - добавить подписку
- GET /users/{id}/subscriptions - получить подписки пользователя
- DELETE /users/{id}/subscriptions/{sub_id} - удалить подписку
- GET /subscriptions/top - получить ТОП-3 популярных подписок

### Open api:
Интерфейс swagger доступен по адресу: http://localhost:8080/swagger-ui/index.html

### Запуск:
Приложение использует порт 8080.\
Postgres и Redis запускаются из докера.\
Запуск приложения:

```Bash
mvn clean package
docker-compose up
```