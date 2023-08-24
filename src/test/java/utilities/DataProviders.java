package utilities;

import java.io.File;
import java.io.IOException;
import org.testng.annotations.DataProvider;

import payload.User;

public class DataProviders {

	@DataProvider(name="validInfo")
	public String[][] getAllData(){
		String[][] datas=readData("Valid Info");
		return datas;
	}
	
	@DataProvider(name="Empty FirstName")
	public User[] getUserName(){
		String[][] datas=readData("Empty FirstName");
		User[] users=new User[datas.length];
		
		for(int i=0; i<datas.length;i++) {
			User user=new User();
			user.setId(Integer.parseInt(datas[i][0]));
			user.setUsername(datas[i][1]);
			user.setFirstName(datas[i][2]);
			user.setLastName(datas[i][3]);
			user.setEmail(datas[i][4]);
			user.setPassword(datas[i][5]);
			user.setPhone(datas[i][6]);
			user.setUserStatus(Integer.parseInt(datas[i][7]));
			users[i]=user;
		}
		return users;
	}
	
	@DataProvider(name="Update Username")
	public User[] updateUserName(){
		String[][] datas=readData("Update Username");
		User[] users=new User[datas.length];
		
		for(int i=0; i<datas.length;i++) {
			User user=new User();
			user.setId(Integer.parseInt(datas[i][0]));
			user.setUsername(datas[i][1]);
			user.setFirstName(datas[i][2]);
			user.setLastName(datas[i][3]);
			user.setEmail(datas[i][4]);
			user.setPassword(datas[i][5]);
			user.setPhone(datas[i][6]);
			user.setUserStatus(Integer.parseInt(datas[i][7]));
			users[i]=user;
		}
		return users;
	}
	
	public String[][] readData(String sheetName){
		String path= System.getProperty("user.dir")+File.separator+"testData"+File.separator+"Userdata.xlsx";
		XLUtility reader=new XLUtility(path);
		int row=reader.getRowCount(sheetName);
		int col=reader.getCellCount(sheetName, 1);
		
		String[][] datas= new String[row][col]; // not row-1 because, it didnt count the header
		
		for(int i=1; i<=row; i++) {
			for(int c=0; c<col;c++) {
				datas[i-1][c]=reader.getCellData(sheetName, i, c);
			}
		}
		return datas;
	}
}
