package org.nkxkel001.thesis.ehealth;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import javax.xml.bind.annotation.XmlRootElement;

import org.nkxkel001.thesis.ehealth.services.UserService;
@XmlRootElement // help do conversion to XML
public class Data {

	/**
	 * @param args
	 */
	
	private int id;
	private String dateMonitored;
	//private int [] values;
	private String healthStatus;
	private String username;
	private String device;
	private String dateUploaded;
	private String values;
	
	
	public Data(){
		//this.valuesString= Arrays.toString(this.values);
	}
	
		
	public Data(int id, String dateMonitored, String healthStatus,
			String username, String device, String dateUploaded,
			String valuesString) {
		super();
		this.id = id;
		this.dateMonitored = dateMonitored;
		this.healthStatus = healthStatus;
		this.username = username;
		this.device = device;
		this.dateUploaded = dateUploaded;
		this.values = valuesString;
	}


	public int [] getArray() {
		// TODO Auto-generated method stub
		//this.valuesString= Arrays.toString(this.values);
		String strip = values.substring(1, values.indexOf("]"));
		String strip2 = strip.replaceAll("\\s","");
		String[] vals = strip2.split(","); 
	    int[] ints = new int[vals.length];
	    for (int i=0; i < vals.length; i++) {
	    	try{
	        ints[i] = Integer.parseInt(vals[i]);
	    	}
	    	catch(NumberFormatException ex){
	    		
	    	}
	    }
	  
	    //this.values = ints;
	   	return ints;
	}
	
	public void setValues2(String values){
		String strip = values.substring(1, values.indexOf("]"));
		String strip2 = strip.replaceAll("\\s","");
		String[] vals = strip2.split(","); 
	    int[] ints = new int[vals.length];
	    for (int i=0; i < vals.length; i++) {
	    	try{
	        ints[i] = Integer.parseInt(vals[i]);
	    	}
	    	catch(NumberFormatException ex){
	    		
	    	}
	    }
	  
	  //  this.values = ints;
	    this.values= values;
	}


	public HashMap<String, String> GetSetAttributes(Data data){
		HashMap <String,String> setAttributes = new HashMap <String,String>();
		try{
		if(!(data.username.equals(null))){
			UserService userService = new UserService();
			int userID = userService.GetUserID(username);
			setAttributes.put("UserID", Integer.toString(userID));
		}	
		if(!(data.dateMonitored.equals(null))){
			setAttributes.put("DateMonitored", data.dateMonitored);
		}
		
		if(!(data.device.equals(null))){
			setAttributes.put("Device", data.device);
		}
		if(!(data.healthStatus.equals(null))){
			setAttributes.put("State", data.healthStatus);
		}
		if(!(data.values.equals(null))){
			setAttributes.put("DataValues", data.values);
		}
		if(!(data.dateUploaded.equals(null))){
			setAttributes.put("DateUploaded", data.dateUploaded);
		}
		
		}
		catch (NullPointerException e)  {
			
		}
						
		return setAttributes;
	

	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDateMonitored() {
		return dateMonitored;
	}


	public void setDateMonitored(String dateMonitored) {
		this.dateMonitored = dateMonitored;
	}


	public String getHealthStatus() {
		return healthStatus;
	}


	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getDevice() {
		return device;
	}


	public void setDevice(String device) {
		this.device = device;
	}


	public String getDateUploaded() {
		return dateUploaded;
	}


	public void setDateUploaded(String dateUploaded) {
		this.dateUploaded = dateUploaded;
	}


	public String getValues() {
		return values;
	}


	public void setValues(String values) {
		this.values = values;
	}
	
	
	
	

	

}
