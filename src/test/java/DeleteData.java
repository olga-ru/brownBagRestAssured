import baseclass.BaseClass;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.core.IsEqual.equalTo;

public class DeleteData extends BaseClass {

    @Test
    void DeleteUser() {
        response = httpRequest.request(Method.DELETE, properties.getProperty("deleteData"));
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 204);
    }

    @Test
    void DeleteUserNegative() {
        response = httpRequest.request(Method.DELETE, properties.getProperty("deleteDataNegative"));
        response.then().log().all()
                .statusCode(404)
                .body("exception", equalTo("UserNotFoundException"))
                .body("message", equalTo("User not found. User name: SemenSemenov"));

        //int statusCode = response.getStatusCode();
        // Assert.assertEquals(statusCode,404);
    }
}