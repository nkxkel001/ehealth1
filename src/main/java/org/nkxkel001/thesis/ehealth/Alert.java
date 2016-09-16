package org.nkxkel001.thesis.ehealth;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import org.nkxkel001.thesis.ehealth.services.UserService;


public class Alert {

	/**
	 * @param args
	 */
	Data data;
	UserService userService = new UserService();
	SendMailTLS mailTLS = new SendMailTLS();
	User user;
	
	public Alert(){}
	public Alert(Data data){
		this.data = data;
	}
	
	public void setOwner(){
		String username = this.data.getUsername();
		 this.user = userService.GetUser(username);
		 
		}
	
	public List <String> retrieveEmergencyContact(){
		//get user emergency contact to send email to
		List <String> contacts = new ArrayList <String>(); 
		String contact = user.getEmergencyContact();
		String[] conts = contact.split(",");
		for(int i = 0; i<conts.length; i++){
			contacts.add(conts[i]);
		}
		String contact2 = "ostelekk@gmail.com";
		contacts.add(contact2);
		return contacts;
	}
	
	@SuppressWarnings("unused")
	public List <String> retrieveDoctorContact(){
		//get user doctor contact to send email to
		//doctor id to retrieve contact from doctor table
		int doctorid = user.getDoctorID();
		//doctorService
		List <String> contacts = new ArrayList <String>(); 
		String contact = "nkxkel001@myuct.ac.za";
		String contact2 = "ostelekk@gmail.com";
		contacts.add(contact);
		contacts.add(contact2);
		return contacts;
	}
	
	public File getDataFile(){
		 File dataCSV = new java.io.File("data.csv");

	     PrintWriter outfile;
		try {
			outfile = new PrintWriter(dataCSV);
			String val = this.data.getValues();
			String dataArray = val.substring(1, val.length()-1);
			outfile.println("Date Monitored: "+data.getDateMonitored());
			outfile.println("Patient name: "+user.toString());
		    outfile.println("Data,"+ dataArray);
		    outfile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return dataCSV;
	}
	
	
	
	public String AlertDoctor(){
		//alert doctor..send attachment of data
		File toattach = getDataFile();
		String subject = "Health status alert";
		String body = "Abnormal health status detected for "+ user.toString()+ " based on data monitored at (date and time): "+data.getDateMonitored()+".\n See attached file for data details.";		
		List <String> contacts =retrieveDoctorContact();
		String [] addresses = new String[contacts.size()];
		addresses = contacts.toArray(addresses);
		return mailTLS.sendEmailAttach(addresses, subject, body, toattach);
			
	}
	
	public String AlertEmergencyContact(){
		String subject = "Health status alert";
		String body = "Abnormal health status detected for "+ user.toString()+ " based on data monitored at (date and time): "+data.getDateMonitored();
		List <String> contacts =retrieveEmergencyContact();
		String [] addresses = new String[contacts.size()];
		addresses = contacts.toArray(addresses);
		 //simple text message
		return mailTLS.sendEmail(addresses, subject, body);
	}
	
	
	public String sendOutAlert(){
		setOwner();
		String s = AlertEmergencyContact();
		String d = AlertDoctor();
		System.out.println(s);
		System.out.println(d);
		return "All relevant parties notified";
	}

}
