# Проект api_automation_testing

Написан автотесты API для сайта https://petstore.swagger.io/

В этом проекте реализован блок User из которого взяты методы:
```bash
POST /user, Create user
GET /user/getUserByName, Get user by user name
PUT /user/getUserByName, Updated user
DELETE /user/getUserByName Delete user
```

## Cтруктура проекта
```bash
.
├── README.md
├── pom.xml
├── src
│   └── main
│       └── java
│           ├── ApiTestCase.java //Класс для тестов 
│           └── models
│               └── User.java //Родительский класс
```
### Класс User.java
Класс соответсвующий модели User из свагера.
* Объявленно 8 полей;
* Описан конструктор, при помощи которого заполняются поля;
* Описан конструктор класса user, принимает json строку
* Реализовать геттеры и сеттеры для полей;
* Реализован метод toString;

### Класс ApiTestCase.java
* Реализован Метод базового запроса к классу User;</br>
public ResponseEntity<String> RequestUser(HttpMethod httpMethod, String url, String body, String statusCode);
* 4 метода запроса:</br>
public void PostUser(User user)</br>
public ResponseEntity<String> GetUser</br>
public void PutUser(User user)</br>
public void DeleteUser(String userName)</br>
* Реализован @Test содержащий все запросы:</br>
public void testUserInfoResponse() </br>
Step 1 'Create new user'/'Создаем нового пользователя'</br>
Step 2 'Get and check created user'/'Получить и проверить созданного пользователя'</br>
Step 3 'Update created user'/'Обновить созданного пользователя'</br>
Step 4 'Get and check updated user'/'Получаем и проверяем обновленного пользователь'</br>
Step 5 'Delete user'/'Удаляем пользователя'</br>
Step 6 'Try get deleted user'/'Пробуем получить удаленного пользователя'</br>

# Выполнение автоматизированных API теста
```bash
Кейс 1 "ApiTestCase":
1.Зайти в класс ApiTestCase.java
2.Найти метод public void testUserInfoResponse() 
3.Слева на панели нажать на "Run test"
```
!["API Тест"](/https://github.com/estepanyuk/api_automation_testing/blob/fa6a53c3de8e8489d46bc72aff08931fdf405e7e/Docs/APITest.png)

# Для реализации данной задачи использовались:
1. Java/Kotlin
2. Junit
3. Maven/Gradle
4. Rest Assured
5. Allure
