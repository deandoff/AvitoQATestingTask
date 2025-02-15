import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.containsString;

public class GetItemByIDTests {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-internship.avito.com/api/1";
    }

    @Test
    public void getItemByValidIDReturnsStatus200() {
        RestAssured.given().get("/item/{id}", "df3ff6f1-75a2-4d77-b0dc-744ed5fc175a").
                then().
                    assertThat().
                    statusCode(200).
                    body(containsString("createdAt")).
                    body(containsString("id")).
                    body(containsString("name")).
                    body(containsString("price")).
                    body(containsString("sellerId")).
                    body(containsString("statistics")).
                    log().all();
    }

    @Test
    public void getItemByIdReturnsStatus404() {
        RestAssured.given().get("/item/{id}", "df3ff6f1-75a2-4d77-b1dd-734ed5fc175a").
                then().
                    assertThat().
                    statusCode(404).
                    body(containsString("result")).
                    body(containsString("message")).
                    body(containsString("messages")).
                    body(containsString("status")).
                    log().all();
    }

    @Test
    public void getItemByInvalidIDReturnsStatus400() {
        RestAssured.given().get("/item/{id}", "INVALID_ID").
                then().
                    assertThat().
                    statusCode(400).
                    body(containsString("result")).
                    body(containsString("message")).
                    body(containsString("messages")).
                    body(containsString("status")).
                    log().all();
    }

    @Test
    public void getItemByNumberIDReturnsStatus400() {
        RestAssured.given().get("/item/{id}", 100).
                then().
                    assertThat().
                    statusCode(400).
                    body(containsString("result")).
                    body(containsString("message")).
                    body(containsString("messages")).
                    body(containsString("status")).
                    log().all();
    }
}
