package org.nkxkel001.thesis.ehealth;


public class Security {
	
	private DatabaseQuery databaseQuery;
	
	public Security(){

		this.databaseQuery = new DatabaseQuery();
		
	}
	
	
	public int InsertCredentials(String username, String password){
		String sql = "INSERT INTO CredentialsTable (UserName, Password) VALUES ('"+username+"','"+password+"')";
		int res = databaseQuery.Insert(sql);
		return res;
			
	}
	
	public void UpdatePassword(String username, String password){
		String sql = "UPDATE CredentialsTable SET Password ='"+password+"' WHERE UserName ='"+username+"'";
		databaseQuery.UpdateOrDelete(sql);
	}
	
	public String getPassword(String username){
		
		String sql ="SELECT Password FROM CredentialsTable WHERE UserName = '"+username +"'";
		String res = databaseQuery.SelectString(sql,"Password");
		
		return res;
		
	}
	/**************************************************************************/
	/*****ROLES METHODS*******/
	
	public int InsertRole(String username, String role){
		String sql = "INSERT INTO RolesTable (UserName, Role) VALUES ('"+username+"','"+role+"')";
		int res = databaseQuery.Insert(sql);
		return res;
			
	}
	
public String getRole(String username){
		
		String sql ="SELECT Role FROM RolesTable WHERE UserName = '"+username +"'";
		String res = databaseQuery.SelectString(sql,"Role");
		
		return res;
		
	}
public boolean matchCredentials(String username, String password){
	boolean match = false;
	if(getPassword(username).equals(password)){
		match = true;
	}
	return match;

 }
	
	

}
