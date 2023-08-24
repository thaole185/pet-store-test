package testcases;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import endpoints.UserEndpoint;
import io.restassured.response.Response;
import payload.User;
import utilities.DataProviders;
import utilities.SharedExtentReport;

public class UserTests {

	@Test(priority=1,dataProvider="validInfo", dataProviderClass=DataProviders.class)
	public void createUser(String id,String username,String firstName,String lastName,String email,String password,String phone,String userStatus) {
		User user=setUser(id, username, firstName, lastName, email, password, phone, userStatus);
		
		Response res=UserEndpoint.createUserEndpoint(user);
		
		AssertJUnit.assertEquals(res.getHeader("content-type"), "application/json");
		AssertJUnit.assertEquals(res.jsonPath().getInt("code"), 200);
		AssertJUnit.assertEquals(res.jsonPath().getString("message"), id);
	}
	
	@Test(dependsOnMethods="createUser", dataProvider="existedUsername",dataProviderClass=DataProviders.class)
	public void getUser(User user) {
		Response res=UserEndpoint.getUserEndpoint(user.getUsername());
		AssertJUnit.assertEquals(res.getHeader("content-type"), "application/json");
		ObjectMapper mapper=new ObjectMapper();
		String body=res.getBody().asString();
		System.out.println(body);
		try {
			User resUser=mapper.readValue(body, User.class);
			AssertJUnit.assertEquals(user, resUser);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider="Empty FirstName", dataProviderClass=DataProviders.class)
	public void createUserWithEmptyFirstName(User user) {
		Response res=UserEndpoint.createUserEndpoint(user);
		if(res.statusCode()==200) {
			Assert.assertFalse(true, "User with empty first name was created");
		}
	}
	
	@Test(dataProvider="Update Username",dataProviderClass=DataProviders.class)
	public void updateUserByName(User user)
	{
		Response response=UserEndpoint.updateUserEndpoint(user.getUsername(),user);
		Assert.assertEquals(response.getStatusCode(),200);
		System.out.println(user.getUsername());
		
		Response responseAfterupdate=UserEndpoint.getUserEndpoint(user.getUsername());
		Assert.assertEquals(responseAfterupdate.getStatusCode(),200);
			
	}
	
	@Test(dependsOnMethods="createUser",dataProvider="validInfo", dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String id,String username,String firstName,String lastName,String email,String password,String phone,String userStatus)
	{
		Response response=UserEndpoint.deleteUserEndpoint(username);
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	public User setUser(String id,String username,String firstName,String lastName,String email,String password,String phone,String userStatus) {
		User user= new User();
		user.setId(Integer.parseInt(id));
		user.setUsername(username);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword(password);
		user.setPhone(phone);
		user.setUserStatus(Integer.parseInt(userStatus));
		
		return user;
	}
}
