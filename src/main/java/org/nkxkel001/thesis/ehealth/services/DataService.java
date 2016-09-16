package org.nkxkel001.thesis.ehealth.services;

import java.util.List;

import org.nkxkel001.thesis.ehealth.Alert;
import org.nkxkel001.thesis.ehealth.Data;
import org.nkxkel001.thesis.ehealth.DataDAO;
import org.nkxkel001.thesis.ehealth.HealthCheck;

public class DataService {

	private DataDAO datadao;
	private HealthCheck healthcheck;
	private Alert alert;
	
	public DataService (){
		
		datadao = new DataDAO();
		UserService userService = new UserService();
		//healthcheck = new HealthCheck ();
		
	}
	
	//testing purposes..comment out for final
	public List<Data> getAllData() {
		// TODO Auto-generated method stub
		
		return datadao.GetAllData();
	}
	
	public List<Data> getUserData(String username) {
		// TODO Auto-generated method stub
		
		return datadao.GetUserData(username);
	}
	
	

	public String addData(Data newData) {
		// TODO Auto-generated method stub
		//check data first and set health status then upload
		//return data status
		String res="";
		healthcheck = new HealthCheck (newData);
		String status = healthcheck.CheckStatus();
		newData.setHealthStatus(status);
		//newData.setValues();
		String s = sendAlert(newData);
		//save data
		@SuppressWarnings("unused")
		int id = datadao.InsertData(newData);
	
		res = "Uploaded data. Health check result = "+status+" "+s;
		
		return res;
	}
	
	public String sendAlert(Data data){
		String res ="";
		if(data.getHealthStatus().equalsIgnoreCase("abnormal")){
			alert = new Alert(data);
			res = alert.sendOutAlert();
		}
				
		return res;
	}

}
