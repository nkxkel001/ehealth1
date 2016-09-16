package org.nkxkel001.thesis.ehealth.resources;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.nkxkel001.thesis.ehealth.User;
import org.nkxkel001.thesis.ehealth.services.UserService;


@Path("/users")


public class UserResource {
	
	UserService userService = new UserService();
	
	// GET permission: only admin can retrieve list of all users
	//@RolesAllowed("ADMIN")
	//@PermitAll
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	//only admin
	public List<User> getUsers(){
		//String name = "blabla";
		List<User> listusers =  userService.getAllUsers();
		
		return listusers;
		
	}
	
	
	//public access ? called with details of new user for sign up from app
	//called from google after authentication
	//return user id to be used in url to post data while logged in
	@POST
	@Consumes (MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addUser(User newUser){
				
		return userService.addUser(newUser);
	}
	
	@GET
	@Path("/{username}")
	@Produces (MediaType.APPLICATION_JSON)
	public User getprofile(@PathParam("username")String username){
		
		return userService.GetUser(username);
		//return "hello its def json";
		
	}
	
	@PUT
	@Path("/{username}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces (MediaType.APPLICATION_JSON)
	public User Updateprofile(@PathParam("username")String username, User user){
		user.setUserName(username);
		return userService.UpdateUser(user);
			
	}
	
	@DELETE
	@Path("/{username}")
	public String DeleteUser(@PathParam("username")String username){
			
		return userService.DeleteUser(username);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
