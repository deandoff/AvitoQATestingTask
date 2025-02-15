import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class GetSellersItemsByIDTests {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-internship.avito.com/api/1";
    }

    @Test
    public void getSellersItemsByValidIDReturnsCode200() {
        RestAssured.given().get("/{sellerID}/item", 771771).
                then().
                    assertThat().statusCode(200).
                    body(containsString("createdAt")).
                    body(containsString("id")).
                    body(containsString("name")).
                    body(containsString("price")).
                    body(containsString("sellerId")).
                    body(containsString("statistics")).
                    log().all();
    }

    @Test
    public void getSellersEmptyItemsListByIDReturnsCode200() {
        RestAssured.given().get("/{sellerID}/item", 999111)
                .then().
                    assertThat().statusCode(200).
                    body("size()", equalTo(0)).
                    log().all();
    }

    @Test
    public void getSellersItemsByInvalidIDReturnsCode400() {
        RestAssured.given().get("/{sellerID}/item", "invalidID").
                then().
                    assertThat().statusCode(400).
                    body(containsString("result")).
                    body(containsString("message")).
                    body(containsString("messages")).
                    body(containsString("status")).
                    log().all();
    }

}
