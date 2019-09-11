

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import RequestModels.GoRest;
import org.junit.Assert;
import io.restassured.response.Response;
import org.testng.annotations.*;
import utils.ExcelReader;
import utils.RequestMethods;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import utils.LogUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class DemoTest extends TestSetup {

	Gson gson = new Gson();
	GoRest fromJson;
	Response response;

	/*@Test
	public void abc() {

		RestAssured.baseURI = "https://gorest.co.in/public-api";
		RequestSpecification request = RestAssured.given();
		Response response = request.header("Authorization", "Bearer  A-y-MYhwR7cEMXpHIpalW11rtOtc2FAPuJjA").get(RestAssured.baseURI+"/users");
		System.out.println(response.body().asString());

		*//*Gson gson = new Gson();
		ExampleApp e = gson.fromJson(response.body().asString(), ExampleApp.class);
		System.out.println("+++++++++++++++"+e);

		response.then().assertThat().body(matchesJsonSchemaInClasspath("schema.json"));
		System.out.println(gson.toJson(e).toString());*//*

		//ExampleApp e = response.body().as(ExampleApp.class);
		//System.out.println(">>>>>>>>>>>>>>>>>>" + e.getTotal());

	}*/

	@Test(dataProvider="data-provider")
	public void getDataFromApi(HashMap<String,String> data,Method method) {

		try {
			// Start the test using the ExtentTest class object.
			extentTest = extent.startTest(method.getName(), "Verify WebSite Title");
			LogUtil.info("Submit GET request for Api");
			response = RequestMethods.getResponse("users");
			RequestSpecification request = RestAssured.given();
			GoRest fromJson = gson.fromJson(response.getBody().asString(), GoRest.class);
			int statusCode = response.getStatusCode();
			LogUtil.debug("Verify status code to be 200");
			Assert.assertTrue(statusCode == 200);
			LogUtil.error("Status code is not 200");

			//Validate if JSON schema is as expected
			//response.then().assertThat().body(matchesJsonSchemaInClasspath("schema.json"));

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}


	@DataProvider(name = "data-provider")
	public static Object[][] getData(Method method) throws IOException {
		return ExcelReader.getTestData(method.getName());
	}


}
