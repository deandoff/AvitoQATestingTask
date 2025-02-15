import io.restassured.RestAssured;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

public class ItemCreateTests {

    private JSONObject createRequestBody(Object sellerID, Object name, Object price) {
        JSONObject requestBody = new JSONObject();
        if (sellerID != null) requestBody.put("sellerID", sellerID);
        if (name != null) requestBody.put("name", name);
        if (price != null) requestBody.put("price", price);
        return requestBody;
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-internship.avito.com/api/1";
    }

    @Test
    public void testCreateItemWithAllCorrectFieldsReturnsCode200() {
        JSONObject requestBody = createRequestBody(112292, "Correct Item Name", 1000);

        RestAssured.
                given().
                    header("Content-Type", "application/json").
                    header("Accept", "application/json").
                    body(requestBody.toJSONString()).
                when().
                    post("/item").
                then().
                    statusCode(200).
                    body(containsString("status")).
                    log().all();
    }

    @Test
    public void testCreateItemWithAllCorrectFieldsWithMinValuesReturnsCode200() {
        JSONObject requestBody = createRequestBody(111111, "A", 1);

        RestAssured.
                given().
                    header("Content-Type", "application/json").
                    header("Accept", "application/json").
                    body(requestBody.toJSONString()).
                when().
                    post("/item").
                then().
                    statusCode(200).
                    body(containsString("status")).
                    log().all();
    }

    @Test
    public void testCreateItemWithAllCorrectFieldsWithMaxValuesReturnsCode200() {
        JSONObject requestBody = createRequestBody(999999, "Test item name with some max values", 2147483647);

        RestAssured.
                given().
                    header("Content-Type", "application/json").
                    header("Accept", "application/json").
                    body(requestBody.toJSONString()).
                when().
                    post("/item").
                then().
                    statusCode(200).
                    body(containsString("status")).
                    log().all();
    }

    @Test
    @Ignore("BUG: API-001 - Missing required fields validation")
    public void testCreateItemWithoutSellerIDShouldReturnCode400ButReturnsCode200() {
        JSONObject requestBody = new JSONObject();

        requestBody.put("name", "Test Item");
        requestBody.put("price", 1000);

        RestAssured.
                given().
                    header("Content-Type", "application/json").
                    header("Accept", "application/json").
                    body(requestBody.toJSONString()).
                when().
                    post("/item").
                then().
                    statusCode(400).
                    body(containsString("status")).
                    body(containsString("result")).
                    body(containsString("message")).
                    body(containsString("messages")).
                    log().all();
    }

    @Test
    @Ignore("BUG: API-001 - Missing required fields validation")
    public void testCreateItemWithoutNameShouldReturnCode400ButReturnsCode200() {
        JSONObject requestBody = new JSONObject();

        requestBody.put("sellerID", 771771);
        requestBody.put("price", 1000);

        RestAssured.
                given().
                    header("Content-Type", "application/json").
                    header("Accept", "application/json").
                    body(requestBody.toJSONString()).
                when().
                    post("/item").
                then().
                    statusCode(400).
                    body(containsString("status")).
                    body(containsString("result")).
                    body(containsString("message")).
                    body(containsString("messages")).
                    log().all();
    }

    @Test
    @Ignore("BUG: API-001 - Missing required fields validation")
    public void testCreateItemWithoutPriceShouldReturnCode400ButReturnsCode200() {
        JSONObject requestBody = new JSONObject();

        requestBody.put("sellerID", 771771);
        requestBody.put("name", "Test Item");

        RestAssured.
                given().
                    header("Content-Type", "application/json").
                    header("Accept", "application/json").
                    body(requestBody.toJSONString()).
                when().
                    post("/item").
                then().
                    statusCode(400).
                    body(containsString("status")).
                    body(containsString("result")).
                    body(containsString("message")).
                    body(containsString("messages")).
                    log().all();
    }

    @Test
    @Ignore("BUG: API-001 - Missing required fields validation")
    public void testCreateItemWithAllFieldsEmptyShouldReturnCode400ButReturnsCode200() {
        JSONObject requestBody = new JSONObject();

        RestAssured.
                given().
                    header("Content-Type", "application/json").
                    header("Accept", "application/json").
                    body(requestBody.toJSONString()).
                when().
                    post("/item").
                then().
                    statusCode(400).
                    body(containsString("status")).
                    body(containsString("result")).
                    body(containsString("message")).
                    body(containsString("messages")).
                    log().all();
    }

    @Test
    @Ignore ("BUG: API-002 - Negative field value validation")
    public void testCreateItemWithNegativeSellerIDShouldReturnCode400ButReturnsCode200() {
        JSONObject requestBody = createRequestBody(-1, "Test Item", 1000);

        RestAssured.
                given().
                    header("Content-Type", "application/json").
                    header("Accept", "application/json").
                    body(requestBody.toJSONString()).
                when().
                    post("/item").
                then().
                    statusCode(400).
                    body(containsString("status")).
                    body(containsString("result")).
                    body(containsString("message")).
                    body(containsString("messages")).
                    log().all();
    }

    @Test
    @Ignore ("BUG: API-002 - Negative field value validation")
    public void testCreateItemWithNegativePriceShouldReturnCode400ButReturnsCode200() {
        JSONObject requestBody = createRequestBody(771771, "Test Item", -1);

        RestAssured.
                given().
                    header("Content-Type", "application/json").
                    header("Accept", "application/json").
                    body(requestBody.toJSONString()).
                when().
                    post("/item").
                then().
                    statusCode(400).
                    body(containsString("status")).
                    body(containsString("result")).
                    body(containsString("message")).
                    body(containsString("messages")).
                    log().all();
    }

    @Test
    public void testCreateItemWithWrongSellerIDFieldValueReturnsCode400() {
        JSONObject requestBody = createRequestBody("wrong", "Test Item", 1000);

        RestAssured.
                given().
                    header("Content-Type", "application/json").
                    header("Accept", "application/json").
                    body(requestBody.toJSONString()).
                when().
                    post("/item").
                then().
                    statusCode(400).
                    body(containsString("status")).
                    body(containsString("result")).
                    body(containsString("message")).
                    body(containsString("messages")).
                    log().all();
    }

    @Test
    public void testCreateItemWithWrongNameFieldValueReturnsCode400() {
        JSONObject requestBody = createRequestBody(771771, 1, 1000);

        RestAssured.
                given().
                    header("Content-Type", "application/json").
                    header("Accept", "application/json").
                body(requestBody.toJSONString()).
                when().
                    post("/item").
                then().
                    body(containsString("status")).
                    body(containsString("result")).
                    body(containsString("message")).
                    body(containsString("messages")).
                    statusCode(400).
                    log().all();
    }

    @Test
    public void testCreateItemWithWrongPriceFieldValueReturnsCode400() {
        JSONObject requestBody = createRequestBody(771771, "Test Item", "wrong");

        RestAssured.
                given().
                    header("Content-Type", "application/json").
                    header("Accept", "application/json").
                    body(requestBody.toJSONString()).
                when().
                    post("/item").
                then().
                    body(containsString("status")).
                    body(containsString("result")).
                    body(containsString("message")).
                    body(containsString("messages")).
                    statusCode(400).
                    log().all();
    }
}
