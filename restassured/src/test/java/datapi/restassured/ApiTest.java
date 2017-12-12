package datapi.restassured;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
//import com.jayway.restassured.RestAssured;
//import com.jayway.restassured.http.ContentType;
//import com.jayway.restassured.path.json.JsonPath;
//import com.jayway.restassured.response.Response;
//import static com.jayway.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static io.restassured.module.jsv.JsonSchemaValidatorSettings.*;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

/**
 * Unit test for simple App.
 */
public class ApiTest {
	@Before
	public void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8888;
		RestAssured.basePath = "/operations";
	}

	@Test
	public void testJsonTestGiven() {
		given().contentType("application/json").when().
		get("/name?value=test").then().log().all();

		given().param("value", "test2").
		header("Accept", "application/xml").when().
		get("/name").then().log().body();

		given().param("value", "test3").
		header("Accept", "application/xml").
		response().contentType(ContentType.XML)
		.when().get("/name").then().log().body();

		given().param("value", "test4").
		request("CONNECT", "/name").
		then().log().body();

		given().param("value", "test5").
		request("GET", "/{id}", "name5").
		then().log().body();
	}

	@Test
	public void testJson() {
		expect().statusCode(200).
		body("id", equalTo("name"), 
				"value", equalTo("test")).log().all().
		when().
		get("/name?value=test");
	}

	@Test
	public void testJsonTest() {
		expect().statusCode(200).
		// 但是如果想用rest-assured的JsonConfig来配置返回的所有的json数值都为BigDecimal类型：
		// given().config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL))).
				body("checked", equalTo(false), 
						"tags", hasItems("home", "green"), 
						"tags", contains("home", "green"), 
						"price", equalTo(12.5f), 
						"price", is(12.5f), 
						"description.colors", equalTo("red"))
				.when().get("/getJson");
	}

	@Test
	public void getJsonTest() {
		Response response = get("/getJson");
		assertEquals(200, response.getStatusCode());
		String json = response.asString();
		JsonPath jp = new JsonPath(json);
		assertEquals(false, jp.get("checked"));
		assertEquals("A green door", jp.get("name"));
		System.out.println(jp.getList("tags").get(0));
	}

	@Test
	public void testJsonSchemaTest() {
		get("/getJson").then().and().body(matchesJsonSchemaInClasspath("products-schema.json"));
		JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder()
				.setValidationConfiguration(
						ValidationConfiguration.newBuilder().setDefaultVersion(SchemaVersion.DRAFTV4).freeze())
				.freeze();
		// JsonSchemaValidator.reset();//想要重置JsonSchemaValidator到默认设置仅仅需要调用reset方法：
		get("/getJson").then().and()
				.body(matchesJsonSchemaInClasspath("products-schema-error.json").using(jsonSchemaFactory));
	}

	@Test
	public void testTest() throws InterruptedException {
		Response response = get("/getNo/999");
		response.getBody().print();
		if (null != response) {
			Thread.sleep(1000);
			response = get("/getNo/999");
			response.getBody().print();
		}
	}

}
