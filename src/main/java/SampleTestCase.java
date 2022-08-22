//import controllers.UserController;

import junit.framework.Assert;
import models.User;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class SampleTestCase {

    @Test
    public void testUserInfoResponse() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String userName = "userkat167";
        //post
        HttpEntity<String> entity = new HttpEntity<>("{\"username\":\"" + userName + "\"}", headers);
        ResponseEntity<String> answerPost = restTemplate.postForEntity("https://petstore.swagger.io/v2/user", entity, String.class);
        System.out.println(answerPost);

        Assert.assertEquals("200", answerPost.getStatusCode().toString());

        String userId = answerPost.getBody().split("message\":\"")[1].split("\"")[0];
        //get
        User updateUser = restTemplate.getForObject("https://petstore.swagger.io/v2/user/" + userName, User.class);
        Assert.assertEquals(userId, updateUser.getId().toString());
        Assert.assertEquals(userName, updateUser.getUsername());
        //put
        entity = new HttpEntity<>("{\"userStatus\":1}", headers);
        ResponseEntity<String> answerPut = restTemplate.exchange("https://petstore.swagger.io/v2/user/" + userName, HttpMethod.PUT, entity, String.class);
        System.out.println(answerPut);
        Assert.assertEquals("200", answerPut.getStatusCode().toString());

        updateUser = restTemplate.getForObject("https://petstore.swagger.io/v2/user/" + userName, User.class);

        Assert.assertEquals(userName, updateUser.getUsername());
        Assert.assertEquals("1", updateUser.getUserStatus().toString());
        //delete
        restTemplate.delete("https://petstore.swagger.io/v2/user/" + userName);
    }
}
