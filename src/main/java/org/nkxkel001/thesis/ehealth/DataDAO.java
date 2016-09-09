package org.nkxkel001.thesis.ehealth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nkxkel001.thesis.ehealth.services.UserService;

public class DataDAO {

	/**
	 * @param args
	 */
	private DatabaseQuery databaseQuery;
	public DataDAO(){
		
		this.databaseQuery = new DatabaseQuery();
	} 
	
	public Data CreateData(HashMap<String, String>  values){
		String attribute;
		Data createdData = new Data();
		for (Map.Entry<String, String> entry : values.entrySet()) {
			attribute = entry.getKey();
			System.out.print(attribute);
			System.out.print("--");
			System.out.println(entry.getValue());
			
			if (attribute.equals("DataID")){
				try{
				int id = Integer.parseInt(entry.getValue());
				createdData.setId(id);
				}
				catch (NumberFormatException ex){}
     		}
			else if (attribute.equals("DateMonitored")){
				createdData.setDateMonitored(entry.getValue());
							
			}
			else if (attribute.equals("State")){
				createdData.setHealthStatus(entry.getValue());
							
			}
			else if (attribute.equals("DataValues")){
				createdData.setValues(entry.getValue());
							
			}
			else if (attribute.equals("DateUploaded")){
				createdData.setDateUploaded(entry.getValue());
							
			}
			else if (attribute.equals("Device")){
				createdData.setDevice(entry.getValue());
							
			}
			/*else if (attribute.equals("UserID")){
				try{
				int id2 = Integer.parseInt(entry.getValue());
				createdData.setId(id2);
				}
				catch (NumberFormatException ex){}
     		}*/
								
			}
							
		return createdData;
	
	}
	
	
	public List <Data> CreateDatas(ArrayList<HashMap <String, String>> queryResults){
		ArrayList<Data> Data = new ArrayList<Data>();
		for(HashMap <String,String> map : queryResults){
			
			Data.add(CreateData(map));
		
		}
		return Data;
			
		
	}
	
	
	
	

	public int InsertData(Data newData) {
		// TODO Auto-generated method stub
		HashMap <String, String> setAtrributes = newData.GetSetAttributes(newData);
		String column ="";
		String values ="";
		String sql = "INSERT INTO DataTable (";
		for (Map.Entry<String, String> entry : setAtrributes.entrySet()) {
			if(entry.getKey().equals("UserID")){
				column += (","+entry.getKey());
				values += (","+entry.getValue()+"");
			}
			else{
			column += (","+entry.getKey());
			values += (",'"+entry.getValue()+"'");
			}
		}
		sql = sql + column.substring(1)+") VALUES ("+values.substring(1)+")";
		System.out.println(sql);
		
		int res = databaseQuery.Insert(sql);
			
		return res;
	}
	
	
	public List <Data> GetUserData (String username){
		List<Data> userdata = null;
		UserService userService = new UserService();
		int userID = userService.GetUserID(username);
		String sql ="SELECT * FROM DataTable WHERE UserID = "+userID;
		ArrayList<HashMap <String, String>> results = databaseQuery.SelectAll(sql);
		userdata = CreateDatas(results);
		return userdata;
	}

	public List<Data> GetAllData() {
		List<Data> alldata = null;
		String sql ="SELECT * FROM DataTable";
		ArrayList<HashMap <String, String>> results = databaseQuery.SelectAll(sql);
		alldata = CreateDatas(results);
		return alldata;
	}
	
	
	
	

}
