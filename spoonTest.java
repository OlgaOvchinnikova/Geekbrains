package RestApiGB;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

public class SpoonTest extends AbstractTest {
    // {{baseUrl}}/recipes/complexSearch?query=wine&cuisine=american&apiKey=<API Key>

    @Test
    void getTestTest() {

        given()
                .when()
                .get(getBaseUrl() + "recipes/complexSearch?query=wine&cuisine=american&apiKey={apiKey}",
                        getApiKey())
                .then()
                .statusCode(200);
    }

    @Test
    void getResponseTest1() {
        Response response = (Response) given()
                .queryParam("apiKey", getApiKey())
                .queryParam("query", "burger")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch");

        assertThat(response.getHeader("Content-Encoding"), equalTo("gzip"));
        assertThat(response.contentType(), equalTo("application/json"));
    }

    @Test
    void getErrorTest() {
        given()
                .queryParam("apiKeyError", getApiKeyError())
                .queryParam("wine&cuisine", "american")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then().statusCode(401);
    }
    //{{baseUrl}}/recipes/complexSearch?query=pasta&cuisine=italian
    // {{baseUrl}}/recipes/complexSearch?query=burger


    //{{baseUrl}}/recipes/complexSearch?query=pasta&cuisine=italian
    @Test
    void getResponseTest2() {
        given()
                .queryParam("querry", "pasta")
                .queryParam("cuisine", "italian")
                .queryParam("apiKey", getApiKey())
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .assertThat()
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON);

    }

    //{{baseUrl}}/recipes/cuisine
    @Test
    void postTest1() {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Pan Seared Fresh Maine Diver Scallops Creamy Avocado Champagne Grape Salad Teriyaki Cabernet Butter Sauce")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .statusCode(200);
        //pm.expect(pm.response.text()).to.include("Asian");
    }

    @Test
    void postTest2() {
        String cuisine = given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Pan Seared Fresh Maine Diver Scallops Creamy Avocado Champagne Grape Salad Teriyaki Cabernet Butter Sauce")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then().extract()
                .jsonPath()
                .get("cuisine")
                .toString();
        assertThat(cuisine, equalTo("Japanese"));
    }

    @Test
    void postTest3() {
        String cuisines = given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Pan Seared Fresh Maine Diver Scallops Creamy Avocado Champagne Grape Salad Teriyaki Cabernet Butter Sauce")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then().extract()
                .jsonPath()
                .get("cuisines")
                .toString();
        //   System.out.println(cuisines);
        assertThat(cuisines, equalTo("[Japanese, Asian]"));
    }

    @Test
    void postTest4() {
        String confidence = given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Pan Seared Fresh Maine Diver Scallops Creamy Avocado Champagne Grape Salad Teriyaki Cabernet Butter Sauce")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then().extract()
                .jsonPath()
                .get("confidence")
                .toString();
        //   System.out.println(cuisines);
        assertThat(confidence, equalTo("0.85"));
    }

    @Test
    void postTest5() {
        String cuisines = given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "burger")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then().extract()
                .jsonPath()
                .get("cuisines")
                .toString();
        //   System.out.println(cuisines);
        assertThat(cuisines, equalTo("[American]"));
    }

    @Test
    void addMealTest1() {
        String id = given()
                .queryParam("hash", "003bf3dca6dde5e7f1ad523006cf8a5ac66e7ef6")
                .queryParam("apiKey", getApiKey())
                .body("{\n"
                        + " \"date\": 1684891179,\n"
                        + " \"slot\": 1,\n"
                        + " \"position\": 0,\n"
                        + " \"type\": \"INGREDIENTS\",\n"
                        + " \"value\": {\n"
                        + " \"ingredients\": [\n"
                        + " {\n"
                        + " \"name\": \"1 banana\"\n"
                        + " }\n"
                        + " ]\n"
                        + " }\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/lavr/items")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("id")
                .toString();
        System.out.println(id);
        assertThat(id ,not(0));
    }
    @Test
    void addMealTest2() {
        String status = given()
                .queryParam("hash", "003bf3dca6dde5e7f1ad523006cf8a5ac66e7ef6")
                .queryParam("apiKey", getApiKey())
                .body("{\n"
                        + " \"date\": 1684891179,\n"
                        + " \"slot\": 1,\n"
                        + " \"position\": 0,\n"
                        + " \"type\": \"INGREDIENTS\",\n"
                        + " \"value\": {\n"
                        + " \"ingredients\": [\n"
                        + " {\n"
                        + " \"name\": \"1 banana\"\n"
                        + " }\n"
                        + " ]\n"
                        + " }\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/lavr/items")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("status")
                .toString();
    //    System.out.println(status);
        assertThat(status, equalTo("success"));
    }

    @Test
    void addMealTest3() {

        given()
                .queryParam("hash", getHashSpoon())
                .queryParam("apiKey", getApiKey())
                .body("{\n"
                        + " \"date\": 1684891179,\n"
                        + " \"slot\": 1,\n"
                        + " \"position\": 0,\n"
                        + " \"type\": \"INGREDIENTS\",\n"
                        + " \"value\": {\n"
                        + " \"ingredients\": [\n"
                        + " {\n"
                        + " \"name\": \"1 banana\"\n"
                        + " }\n"
                        + " ]\n"
                        + " }\n"
                        + "}")
                .when()
                .post("https://api.spoonacular.com/mealplanner/"+ getSpoonName() +"/items")
                .then()
                .assertThat()
                //.cookie("cookieName", "cookieValue")
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .statusLine(containsString("OK"))
                .header("Connection", "keep-alive")
                .header("Content-Length", Integer::parseInt, lessThan(3000))
                .contentType(ContentType.JSON)
                //  .body(equalTo("something"))
                .time(lessThan(2000L));
    }

}
