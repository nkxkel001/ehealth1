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
	private int [] values;
	private String healthStatus;
	private String username;
	private String device;
	private String dateUploaded;
	private String valuesString;
	
	
	public Data(){
		this.valuesString= Arrays.toString(this.values);
	}
	
	public Data( String dateMonitored, int[] values,String username, String device) {
		this.dateMonitored = new Date().toString();
		this.values = values;
		this.username = username;
		this.device = device;
		this.valuesString= Arrays.toString(values);
		
		}
	
	
	
	public Data(int id, String dateMonitored, int[] values,
			String healthStatus, String username, String device,
			String dateUploaded) {
		this.id = id;
		this.dateMonitored = dateMonitored;
		this.values = values;
		this.healthStatus = healthStatus;
		this.username = username;
		this.device = device;
		this.dateUploaded = dateUploaded;
		this.valuesString= Arrays.toString(values);
		
	}
	
	




	public int getId() {
		return id;
	}




	public String getDateMonitored() {
		return dateMonitored;
	}




	public int[] getValues() {
		return values;
	}
	
	public String getStringValues(){
		return valuesString;
		
	}
	
	

	public String getHealthStatus() {
		return healthStatus;
	}




	public String getUsername() {
		return username;
	}




	public String getDevice() {
		return device;
	}




	public String getDateUploaded() {
		return dateUploaded;
	}




	public void setId(int id) {
		this.id = id;
	}




	public void setDateMonitored(String dateMonitored) {
		this.dateMonitored = dateMonitored;
	}




	public void setValues(int[] values) {
		this.values = values;
	}
	
	public void setValues() {
		// TODO Auto-generated method stub
		this.valuesString= Arrays.toString(this.values);
		
	}
	
	public void setValues(String values){
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
	  
	    this.values = ints;
	    this.valuesString= values;
		
	}




	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public void setDevice(String device) {
		this.device = device;
	}




	public void setDateUploaded(String dateUploaded) {
		this.dateUploaded = dateUploaded;
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
			setAttributes.put("DataValues", data.valuesString);
		}
		if(!(data.dateUploaded.equals(null))){
			setAttributes.put("DateUploaded", data.dateUploaded);
		}
		
		}
		catch (NullPointerException e)  {
			
		}
						
		return setAttributes;
		
		
		
		

	}

	

}
