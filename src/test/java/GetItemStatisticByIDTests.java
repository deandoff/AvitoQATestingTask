import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.containsString;

public class GetItemStatisticByIDTests {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-internship.avito.com/api/1";
    }

    @Test
    public void testGetItemStatisticByIDReturnsCode200() {
        RestAssured.given().get("/statistic/{id}", "df3ff6f1-75a2-4d77-b0dc-744ed5fc175a").
                then().
                    assertThat().statusCode(200).
                    body(containsString("contacts")).
                    body(containsString("likes")).
                    body(containsString("viewCount")).
                    log().all();
    }

    @Test
    public void testGetStatisticOfNotExistingItemReturnsCode404() {
        RestAssured.given().get("/statistic/{id}", "df3ff6f1-75a2-4d77-b1dd-744ed5fc175a").
                then().assertThat().statusCode(404).
                    body(containsString("result")).
                    body(containsString("message")).
                    body(containsString("messages")).
                    body(containsString("status")).
                    log().all();
    }

    @Test
    public void testGetItemStatisticByInvalidIDReturnsCode400() {
        RestAssured.given().get("/statistic/{id}", "invalid_id").
                then().assertThat().statusCode(400).
                    body(containsString("result")).
                    body(containsString("message")).
                    body(containsString("messages")).
                    body(containsString("status")).
                    log().all();
    }

    @Test
    public void testGetItemStatisticByNumberIDReturnsCode400() {
        RestAssured.given().get("/statistic/{id}", 100).
                then().assertThat().statusCode(400).
                    body(containsString("result")).
                    body(containsString("message")).
                    body(containsString("messages")).
                    body(containsString("status")).
                    log().all();
    }
}
