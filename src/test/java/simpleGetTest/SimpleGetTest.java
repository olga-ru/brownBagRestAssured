package simpleGetTest;

import baseclass.BaseClass;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleGetTest extends BaseClass {

    @Test
    void requestToServer() {
        response = httpRequest.request(Method.GET, properties.getProperty("getAllUser"));
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    void responseUser() {
        response = httpRequest.request(Method.GET, properties.getProperty("getSingleUser"));
        JsonPath jsonPath = response.jsonPath();
        String validateName = jsonPath.get("name").toString();
        Assert.assertEquals(validateName.contains("Ivan"), true);
        String validateUserName = jsonPath.get("userName").toString();
        Assert.assertEquals(validateUserName.contains("IvanPetrov"), true);
        String validatePhone = jsonPath.get("phone").toString();
        Assert.assertEquals(validatePhone.contains("33333"), true);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    void responseUser2() {
        response = httpRequest.request(Method.GET, properties.getProperty("getSingleUser"));
        JsonPath jsonPath = response.jsonPath();
        String validateUserName = jsonPath.get("userName").toString();
        Assert.assertEquals(validateUserName.contains("PetrPetrov"), false);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    void responseUser3() {
        {
            response = httpRequest.request(Method.GET, properties.getProperty("getSingleUser3"));
            int statusCode = response.getStatusCode();
            Assert.assertEquals(statusCode, 404);
        }

    }
}
