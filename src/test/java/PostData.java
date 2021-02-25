import baseclass.BaseClass;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.core.IsEqual.equalTo;

public class PostData extends BaseClass {
    @Test
    void postUser() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "test@test.com");
        jsonObject.put("name", "Ivan");
        jsonObject.put("phone", "33333");
        jsonObject.put("userName", "IvanPetrov");
        jsonObject.put("website", "newwebsite.com");
        httpRequest.header("content-Type", "application/json");
        httpRequest.body(jsonObject.toJSONString());
        response = httpRequest.request(Method.POST, properties.getProperty("postData"));
        response.then().log().all()
                .statusCode(201).body("userName", equalTo("IvanPetrov"));
        // int statusCode = response.getStatusCode();
        // Assert.assertEquals(201,statusCode);
    }

    @Test
    void postUser2() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "test@test.com");
        jsonObject.put("name", "Ivan");
        jsonObject.put("phone", "33333");
        jsonObject.put("userName", "IvanPetrov");
        jsonObject.put("website", "newwebsite.com");
        httpRequest.header("content-Type", "application/json");
        httpRequest.body(jsonObject.toJSONString());
        response = httpRequest.request(Method.POST, properties.getProperty("postData"));
        response.then().log().all()
                .statusCode(400)
                .body("exception", equalTo("DublicateUserNameException"))
                .body("message", equalTo("User with current username allready exists. Username: IvanPetrov"));


    }

    @Test
    void postUser3() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "test@test.com");
        jsonObject.put("name", "Vasily");
        jsonObject.put("phone", "4444");
        jsonObject.put("userName", "");
        jsonObject.put("website", "newwebsite.com");
        httpRequest.header("content-Type", "application/json");
        httpRequest.body(jsonObject.toJSONString());
        response = httpRequest.request(Method.POST, properties.getProperty("postData"));
        response.then().log().all()
                .statusCode(400)
                .body("exception", equalTo("InvalidUserDataException"))
                .body("message", equalTo("Incorrect user. Please validate if all mandatory data is filled."));
        //int statusCode = response.getStatusCode();
        // Assert.assertEquals(400,statusCode);

    }

    @Test
    void postUser4() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "test@test.com");
        jsonObject.put("name", "Ivan");
        jsonObject.put("phone", "1111111");
        jsonObject.put("userName", "IvanIvanov");
        jsonObject.put("website", "newwebsite.com");
        httpRequest.header("content-Type", "application/json");
        httpRequest.body(jsonObject.toJSONString());
        response = httpRequest.request(Method.POST, properties.getProperty("postData"));
        int statusCode = response.getStatusCode();
        Assert.assertEquals(201, statusCode);
    }
}
