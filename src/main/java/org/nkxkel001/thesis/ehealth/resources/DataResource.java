package org.nkxkel001.thesis.ehealth.resources;

import java.util.List;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.nkxkel001.thesis.ehealth.Data;
import org.nkxkel001.thesis.ehealth.User;
import org.nkxkel001.thesis.ehealth.services.DataService;

@Path("/data")

public class DataResource {
	
	DataService dataService = new DataService();

	/**
	 * @param args
	 */
	//testing purpose...deny all in proper implementation
	@DenyAll
	@GET
	@Produces (MediaType.APPLICATION_JSON)
	public List<Data> getAllUserData(){
		//String name = "blabla";
		//param maybe username
		List<Data> allData =  dataService.getAllData();
		
		return allData;
						
	}
	@RolesAllowed({"USER","DOCTOR"})
	@GET
	@Path("/{username}")
	@Produces (MediaType.APPLICATION_JSON)
	public List<Data>getUserData(@PathParam("username")String username){
		List<Data> userData =  dataService.getUserData(username);
		return  userData;
	}
	
	@RolesAllowed({"USER"})
	@POST
	//@Path("/{username}")
	@Consumes (MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	//upload data. called from app.. app username same as api username
	//return status "abnormal / normal"
	public String uploadData(Data newData){
				
		return dataService.addData(newData);
	}
	
	@RolesAllowed({"USER"})
	@DELETE
	@Path("/{username}/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteData(@PathParam("username")String username,@PathParam("id")int id){
				
		return dataService.DeleteUser(id);
	}
	
	
	
	
	
	
	

}
