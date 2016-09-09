package org.nkxkel001.thesis.ehealth.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.nkxkel001.thesis.ehealth.DatabaseConnect;
import org.nkxkel001.thesis.ehealth.DatabaseQuery;
import org.nkxkel001.thesis.ehealth.User;
import org.nkxkel001.thesis.ehealth.UserDAO;

public class UserService {
	
	
	private UserDAO userdao;
	public UserService (){
		
		userdao = new UserDAO();
		
	}
	
	
	public List<User> getAllUsers(){
		//Retrieve all users from database
		//name, surname , username and date registered
		//create map to build sql statement
		
		//HashMap hm = new HashMap(); //contains elements for sql statement. first key is table then rest column_name :value
		ArrayList<String> querylist = new ArrayList<String>();
		querylist.add("UserTable");
		querylist.add("UserName");
		querylist.add("Name");
		querylist.add("Surname");
		querylist.add("DateRegistered");
		
		//return null;
		//return databaseQuery.Connect("SELECT UserName, Name, Surname, DateRegistered FROM UserTable", querylist);
		List <User> list = new ArrayList<>();
		list = userdao.QueryDatabase("SELECT UserName, Name, Surname, DateRegistered FROM UserTable", querylist);
		return list;				
	
	}
	
	public User GetUser(String username){
		
		return userdao.GetUser(username);
		
	}
	
	public int GetUserID(String username){
		
		return userdao.GetUserID(username);
	}
	
	public String addUser(User newuser){
		//authenticate user
		//check if user already exists
		int id=0;
		String res ="";
		if(userExists(newuser)){
			res ="User already exists";
			//ask to update
		}
		else{
			
			//add user and return table userid
		 id = userdao.InsertUser(newuser);
		 res = "Created user with id - "+Integer.toString(id);
		}
		//allow login maybe or start receiving data
		
		return res;
	}
	
	
	public User UpdateUser(User user){
			
		return userdao.UpdateUser(user);
		
	}
	
	public String DeleteUser(String username){
		String success="";
		userdao.DeleteUser(username);
		//confirm deletion
		if(userExists(username)){
			success = "Failed to delete User";
		}
		else{
			success = "User Successfully deleted ";
		}
		return success;
	}
	
	
	public boolean userExists(User user){
		if (userdao.GetUser(user.getUserName())!= null){
			
			return true;
		}
		
		else
			return false;
	}
	
	public boolean userExists(String username){
		if (userdao.GetUser(username)!= null){
			
			return true;
		}
		
		else
			return false;
	}
	
	
	
	
	
	
	
}
