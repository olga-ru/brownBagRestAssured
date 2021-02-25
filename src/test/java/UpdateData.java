import baseclass.BaseClass;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.core.IsEqual.equalTo;

public class UpdateData extends BaseClass {

    @Test
    void updateUser() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("street", "Miera");
        jsonObject.put("suite", "13");
        jsonObject.put("city", "Riga");
        jsonObject.put("zipcode", "1234");

        httpRequest.header("content-Type", "application/json");
        httpRequest.body(jsonObject.toJSONString());
        response = httpRequest.request(Method.PUT, properties.getProperty("updateRecord"));
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 204);
    }

    @Test
    void updateUserNegative2() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("street", "Valnu");
        jsonObject.put("suite", "12");
        jsonObject.put("city", "Riga");
        jsonObject.put("zipcode", "5678");

        httpRequest.header("content-Type", "application/json");
        httpRequest.body(jsonObject.toJSONString());
        response = httpRequest.request(Method.PUT, properties.getProperty("updateRecordNegative"));
        response.then().log().all()
                .statusCode(404)
                .body("exception", equalTo("UserNotFoundException"))
                .body("message", equalTo("User not found. User name: SemenSemenov"));

    }

    @Test
    void updateUserNegative3() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("street", "Valnu");
        jsonObject.put("suite", "12");
        jsonObject.put("city", "");
        jsonObject.put("zipcode", "5678");

        httpRequest.header("content-Type", "application/json");
        httpRequest.body(jsonObject.toJSONString());
        response = httpRequest.request(Method.PUT, properties.getProperty("updateRecord"));
        response.then().log().all()
                .statusCode(400)
                .body("exception", equalTo("InvalidAddressDataException"))
                .body("message", equalTo("Incorrect address. Please validate if all mandatory data is filled."));

    }

}


