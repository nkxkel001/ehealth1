package org.nkxkel001.thesis.ehealth;

//this class is used to connect to the database. to perform queries on the database
//

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseConnect {
	
	// JDBC driver name and database URL
		public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		public static final String DB_URL = "jdbc:mysql://messengerdb.ckie6u2t8xbh.us-west-2.rds.amazonaws.com:3306/ebdb";
		//public static final String DB_URL = "jdbc:mysql://localhost/ehealth";
		
	// Database credentials
	   //public static final String USER = "root";
	   public static final String USER ="messengerDB";
	   public static final String PASS = "061193kayy";
	   
   static Connection conn = null;
   
  
   public DatabaseConnect(){
							
			}
   public static Connection getConnection()
   {
      /* if (conn != null) {return conn;}
       
       else*/ return getConnection(DB_URL, USER, PASS);
   }
   
   private static Connection getConnection(String db_name,String user_name,String password)
   {
	   
	   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		      
	   }
	   catch(Exception e)
       {
           e.printStackTrace();
       }

       return conn;        
	   
   }
   
} 
   
		   
		    
		    
		   
		  