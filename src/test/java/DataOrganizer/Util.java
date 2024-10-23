package DataOrganizer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Util {

	public static RequestSpecification req;

	public RequestSpecification genericRequestSpec() throws IOException {

		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.text"));
			req = new RequestSpecBuilder().setBaseUri(getProperties("baseurl"))
					// .setBaseUri("https://rahulshettyacademy.com")
					.addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).
					setContentType(ContentType.JSON)
					.build();
			return req;
		} else {
			return req;
		}
	}

	public ResponseSpecification genericResponseSpec() {
		ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
				.build();
		return res;
	}

	public String getProperties(String key) throws IOException {
		Properties p = new Properties();
		FileInputStream fp = new FileInputStream(
				"/Users/sai/eclipse-workspace/APIRestCucumber/src/test/java/DataOrganizer/global.properties");
		p.load(fp);
		String str = p.getProperty(key).toString();
		fp.close();
		return str;
	}
	
	public String getValue(Response response, String key) {
		String str=response.toString();
		JsonPath js=new JsonPath(str);
		String value=js.get(key);
		
		return value;
	}

}
