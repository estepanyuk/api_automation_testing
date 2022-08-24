import junit.framework.Assert;
import models.User;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class ApiTestCase {

    @Test
    public void testUserInfoResponse() {

        //Step 1 'Create new user'/'Создаем нового пользователя'
        Long currentTimeMillis = System.currentTimeMillis();

        User referenceUser = new User(
                currentTimeMillis,
                "user" + currentTimeMillis,
                "testFirstName1",
                "testLastName1",
                "testEmail1@test.ru",
                "testPassword1",
                "1234567890",
                0
        );

        PostUser(referenceUser);

        //Step 2 'Get and check created user'/'Получить и проверить созданного пользователя'
        ResponseEntity<String> answerGetCreatedUser = GetUser(referenceUser.getUsername(), "200");
        User createdUser = new User(answerGetCreatedUser.getBody());

        Assert.assertEquals(referenceUser.getId(), createdUser.getId());
        Assert.assertEquals(referenceUser.getUsername(), createdUser.getUsername());
        Assert.assertEquals(referenceUser.getFirstName(), createdUser.getFirstName());
        Assert.assertEquals(referenceUser.getLastName(), createdUser.getLastName());
        Assert.assertEquals(referenceUser.getEmail(), createdUser.getEmail());
        Assert.assertEquals(referenceUser.getPassword(), createdUser.getPassword());
        Assert.assertEquals(referenceUser.getPhone(), createdUser.getPhone());
        Assert.assertEquals(referenceUser.getUserStatus(), createdUser.getUserStatus());

        //Step 3 'Update created user'/'Обновить созданного пользователя'
        referenceUser.setFirstName("testFirstName2");
        referenceUser.setLastName("testLastName2");
        referenceUser.setEmail("testEmail2@test.ru");
        referenceUser.setPassword("testPassword2");
        referenceUser.setPhone("0987654321");
        referenceUser.setUserStatus(1);

        PutUser(referenceUser);

        //Step 4 'Get and check updated user'/'Получаем и проверяем обновленного пользователь'
        ResponseEntity<String> answerGetUpdatedUser = GetUser(referenceUser.getUsername(), "200");
        User updatedUser = new User(answerGetUpdatedUser.getBody());

        Assert.assertEquals(referenceUser.getId(), updatedUser.getId());
        Assert.assertEquals(referenceUser.getUsername(), updatedUser.getUsername());
        Assert.assertEquals(referenceUser.getFirstName(), updatedUser.getFirstName());
        Assert.assertEquals(referenceUser.getLastName(), updatedUser.getLastName());
        Assert.assertEquals(referenceUser.getEmail(), updatedUser.getEmail());
        Assert.assertEquals(referenceUser.getPassword(), updatedUser.getPassword());
        Assert.assertEquals(referenceUser.getPhone(), updatedUser.getPhone());
        Assert.assertEquals(referenceUser.getUserStatus(), updatedUser.getUserStatus());

        //Step 5 'Delete user'/'Удаляем пользователя'
        DeleteUser(referenceUser.getUsername());

        //Step 6 'Try get deleted user'/'Пробуем получить удаленного пользователя'
        GetUser(referenceUser.getUsername(), "404");
    }

    public void PostUser(User user) {
        RequestUser(HttpMethod.POST, "https://petstore.swagger.io/v2/user", user.toString(), "200");
    }

    public ResponseEntity<String> GetUser(String userName, String statusCode) {
        return RequestUser(HttpMethod.GET, "https://petstore.swagger.io/v2/user/" + userName, "{}", statusCode);
    }

    public void PutUser(User user) {
        RequestUser(HttpMethod.PUT, "https://petstore.swagger.io/v2/user/" + user.getUsername(), user.toString(), "200");
    }

    public void DeleteUser(String userName) {
        RequestUser(HttpMethod.DELETE, "https://petstore.swagger.io/v2/user/" + userName, "{}", "200");
    }

    public ResponseEntity<String> RequestUser(HttpMethod httpMethod, String url, String body, String statusCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> answer;
        try {
            answer = restTemplate.exchange(url, httpMethod, entity, String.class);
        }
        catch (HttpClientErrorException e) {
            answer = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Assert.assertEquals(statusCode, answer.getStatusCode().toString());

        return answer;
    }
}