package endpoints;

public class Routes {

	static String baseUrl="https://petstore.swagger.io/v2";
	
	public static String createUser=baseUrl+"/user";
	public static String getUser=baseUrl+"/user/{username}";
	public static String updateUser=baseUrl+"/user/{username}";
	public static String deleteUser=baseUrl+"/user/{username}";
	public static String userLogin=baseUrl+"/user/login";
	public static String userLogout=baseUrl+"/user/logout";
	
}
