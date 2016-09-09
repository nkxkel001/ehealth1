package org.nkxkel001.thesis.ehealth;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseQuery {
	private Connection connect;
	
	public DatabaseQuery(){
		
	 this.connect = DatabaseConnect.getConnection();
		

		
	}
	

	/**
	 * @param args
	 */
	 public ArrayList<HashMap <String, String>> Connect(String sqlStatement, List<String> columns) {
		   
		   Statement stmt = null;
		   ArrayList<String> output = new ArrayList<String>();
		   
		   ArrayList<HashMap <String, String>> queryResults = new ArrayList<HashMap <String, String>>();
		   int numberOfColumns = columns.size();
		   
		   try{
		      //STEP 4: Execute a query
			   
		      System.out.println("Creating statement...");
		      stmt = connect.createStatement();
		      String sql;
		      sql = sqlStatement;
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      //get required columns
		   //  Object[] columnsArray = (String[]) columns.toArray();
		      String out = "";

		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		    	  HashMap <String,String> resultSet = new HashMap <String,String>();
		    	  for(int i = 1; i<numberOfColumns; i++){
		    		  
		    		  resultSet.put(columns.get(i), rs.getString(columns.get(i)));
		    		  
		    		  System.out.println(rs.getString(columns.get(i))); 
		    		}
		    	  queryResults.add(resultSet);
		    	  
		    	  //output.add(out.substring(1));
		    	  //queryResults.add(resultSet);
		    	  //out = "";
		        		        		         
		      }
		      
		      
		      //STEP 6: Clean-up environment
		      rs.close();
		     // stmt.close();
		     // connect.close();
		      //return proflist;
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }/*finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(connect!=null)
		            connect.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }*///end try
		   System.out.println("Goodbye!");
		   return queryResults;
		}//end main

	   
	 public ArrayList<HashMap <String, String>> SelectAll (String sqlStatement) {
		   
		   Statement stmt = null;
		   ArrayList<HashMap <String, String>> queryResults = new ArrayList<HashMap <String, String>>();
		     
		   try{
		      System.out.println("Creating statement for SelectAll...");
		      stmt = connect.createStatement();
		      String sql;
		      sql = sqlStatement;
		      ResultSet rs = stmt.executeQuery(sql);
		      ResultSetMetaData rsmd = rs.getMetaData();
		      int numberOfColumns = rsmd.getColumnCount();
		      //String name = rsmd.getColumnName(1);
		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		    	  HashMap <String,String> resultSet = new HashMap <String,String>();
		    	  for(int i = 1; i<=numberOfColumns; i++){
		    		  
		    		  resultSet.put(rsmd.getColumnName(i), rs.getString(rsmd.getColumnName(i)));
		    		  
		    		  System.out.println(rs.getString(rsmd.getColumnName(i)));
		    		}
		    	  queryResults.add(resultSet);
		    	  
		    	  //output.add(out.substring(1));
		    	  //queryResults.add(resultSet);
		    	  //out = "";
		        		        		         
		      }
		      System.out.println(queryResults.size());
		      
		      
		      //STEP 6: Clean-up environment
		      rs.close();
		     // stmt.close();
		     // connect.close();
		      //return proflist;
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      /*try{
		         if(connect!=null)
		            connect.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }*///end finally try
		   }//end try
		   System.out.println("Goodbye!");
		   return queryResults;
		}//end main


	 public int SelectID(String sql){
		 int ID=0;
		 Statement stmt = null;
		  try{
		      System.out.println("Creating statement for SelectID...");
		      stmt = connect.createStatement();
		      ResultSet rs = stmt.executeQuery(sql);
		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		    	  ID = rs.getInt(1);
		        		        		         
		      }
		       
		      //STEP 6: Clean-up environment
		      rs.close();
		     // stmt.close();
		     // connect.close();
		      //return proflist;
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      /*try{
		         if(connect!=null)
		            connect.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }*///end finally try
		   }//end try
		   System.out.println("Goodbye!");
					 
		 return ID;
		 
	  }
	 
	 public int Insert (String sql) {
		   
		   Statement stmt = null;
		   int generatedKey = 0;     
		   try{
			   
			   PreparedStatement ps = connect.prepareStatement(sql,
				        Statement.RETURN_GENERATED_KEYS);
				 
				ps.execute();
				 
				ResultSet rs = ps.getGeneratedKeys();
				
				if (rs.next()) {
				    generatedKey = rs.getInt(1);
				    
				}
				 
				System.out.println("Inserted record's ID: " + generatedKey);
	
		      rs.close();
		      }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		    
		   }//end try
		   System.out.println("Goodbye!");
		   return generatedKey;
		}//end main
	 
	 
	 public void UpdateOrDelete (String sql) {
		   
		   Statement stmt = null;
		   try{
			   
			   System.out.println("Creating statement for Update / Delete...");
			   stmt = connect.createStatement();
			   stmt.executeUpdate(sql); 
			   System.out.println("Record Updated / Deleted");
		      stmt.close();
		      }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		    
		   }//end try
		   System.out.println("Goodbye!");
		  
		}//end main
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}



