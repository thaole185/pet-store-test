package endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;
import payload.User;

public class UserEndpoint {

	public static Response createUserEndpoint(User user) {
		Response res=
				given()
					.accept("application/json")
					.contentType("application/json")
					.body(user)
				.when()
					.post(Routes.createUser);
		return res;
	}
	
	public static Response getUserEndpoint(String userName) {
		Response res=
				given()
					.accept("application/json")
					.pathParam("username", userName)
				.when()
					.get(Routes.getUser);
		return res;
				
	}
	
	public static Response updateUserEndpoint(String userName, User user) {
		Response res=
				given()
					.accept("apllication/json")
					.contentType("application/json")
					.pathParam("username", userName)
					.body(user)
				.when()
					.put(Routes.updateUser);
		return res;
					
					
	}
	
	public static Response deleteUserEndpoint(String userName) {
		Response res=
				given()
					.accept("application/json")
					.pathParam("username", userName)
				.when()
					.delete(Routes.deleteUser);
		return res;
	}
	
	public static Response userLoginEndpoint(String userName, String password) {
		Response res=
				given()
					.accept("application/json")
					.queryParam("username", userName)
					.queryParam("password", password)
				.when()
					.get(Routes.userLogin);
		return res;
	}
	
	public static Response userLogoutEndpoint() {
		Response res=
				given()
				.when()
					.get(Routes.userLogout);
		return res;
	}
	
}
