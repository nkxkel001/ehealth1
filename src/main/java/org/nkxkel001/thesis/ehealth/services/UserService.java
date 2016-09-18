package org.nkxkel001.thesis.ehealth.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import org.nkxkel001.thesis.ehealth.DatabaseConnect;
import org.nkxkel001.thesis.ehealth.DatabaseQuery;
import org.nkxkel001.thesis.ehealth.Security;
import org.nkxkel001.thesis.ehealth.User;
import org.nkxkel001.thesis.ehealth.UserDAO;
import org.glassfish.jersey.internal.util.Base64;
public class UserService {
	
	
	private UserDAO userdao;
	private Security security;
	private final String role = "USER";
	public UserService (){
		
		userdao = new UserDAO();
		security = new Security();
		
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
	
	public String addUser(User newuser, String auth){
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
		 addCredentials(auth);
		 
		 
		 //res = "Created user with id - "+Integer.toString(id);
		 res = "success";
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
	
	public void addCredentials(String auth){
		    // Header is in the format "Basic 5tyc0uiDat4"
	        // We need to extract data before decoding it back to original string
		    System.out.println("auth: "+auth);
	        String[] authParts = auth.split("\\s+");
	        String authInfo = authParts[1];
	        // Decode the data back to original string
	        String usernameAndPassword = new String(Base64.decode(authInfo.getBytes()));;
	        final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();
            System.out.println(username);
            System.out.println(password);
              
		security.InsertCredentials(username, password);
		addRole(username);
	}
	public void addRole(String username){
		security.InsertRole(username, role);
	}

	public String [] decodeAuth(String auth){
		String [] usercred = new String[2];
		System.out.println("auth: "+auth);
        String[] authParts = auth.split("\\s+");
        String authInfo = authParts[1];
        // Decode the data back to original string
        String usernameAndPassword = new String(Base64.decode(authInfo.getBytes()));;
        final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
        final String username = tokenizer.nextToken();
        final String password = tokenizer.nextToken();
        System.out.println(username);
        System.out.println(password);
        usercred [0] = username;
        usercred [1] = password;
		
		return usercred;	
	}

	public String checkUser(String authString) {
		String [] usercred = decodeAuth( authString);
		String username = usercred [0];
        String password = usercred [1];
        String result="";
	
		if (userdao.GetUser(username)!= null){
			if(security.matchCredentials(username, password)){
				result = "success";
			}
			else
				result = "error";
		}
		else
			result = "no user";
		
		// TODO Auto-generated method stub
		return result;
	
	
	
	
	
	
	}	
	
}
