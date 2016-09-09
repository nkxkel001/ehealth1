package org.nkxkel001.thesis.ehealth;

import java.util.List;

import org.nkxkel001.thesis.ehealth.services.UserService;

public class test {

	/**
	 * @param args
	 */
	static UserService userService = new UserService();
	static DatabaseQuery databaseQuery= new DatabaseQuery();
	static UserDAO userdao = new UserDAO();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String sql ="SELECT * FROM UserTable WHERE Name = 'Keletsoyy'";
		List<User> listusers =  userService.getAllUsers();
			for( User a: listusers){
			
			System.out.println(a.getFirstName());
			
				}
		//databaseQuery.SelectAll(sql);
		
		//User user = userdao.GetUser("desire@gmail.com");
		//user.setFirstName("Desire");
		//userdao.UpdateUser(user);
		//user.setUserName("desire@gmail.com");
		//userdao.InsertUser(user);

	}

}
