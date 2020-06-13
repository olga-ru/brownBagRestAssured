package getRequestForAllEmployeeData;

import baseclass.BaseClass;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetRequestForAllEmployeeData extends BaseClass {

    @Test(priority = 1)
    void requestToServer()
    {
        response = httpRequest.request(Method.GET, properties.getProperty("getAllEmployeeData"));
    }

    @Test(priority = 2)
    void verifyStatusCode()
    {
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200);
    }
}
