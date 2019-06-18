package com.banking.system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class ATM extends Transactions {
	public Scanner sc = new Scanner(System.in);
	public int count=0;
    public void withDrawAtm() throws ClassNotFoundException, SQLException {
    
    	System.out.println("\t\t**********ENTER YOUR PIN**************");
    	int pin = sc.nextInt();
    	if(checkPin(pin) && checkIncorrect())
    	super.withDraw();
    	else { 
    		System.out.println("\t\t***********INCORRECT PIN************");
    		count++;
    	}
    	
    }
    
   public void changePin() throws ClassNotFoundException, SQLException {
	   
	     System.out.println("\t\t******ENTER YOUR OLD PIN*******");
	       int pin= sc.nextInt(); 
	       
	
		
		if(checkPin(pin)) {
			System.out.println("\t\t********ENTER NEW PIN********");
			int newPin = sc.nextInt();
			
			
			String query="UPDATE bank SET Pin=? where Pin= ?";
			Connection con=DataBaseConnection.connectToDatabase();
			PreparedStatement st=con.prepareStatement(query);
			st.setLong(1,newPin);
			st.setLong(2, pin);
			st.executeUpdate();
			st.close();
			con.close();
		}
		
		
   }
   
   public boolean checkPin(int pin) throws ClassNotFoundException, SQLException {
	   System.out.println("\t\t********ENTER ACCOUNT NUMBER***************");
	   long accNo=sc.nextLong();
	   String query="SELECT * from bank  where AccountNumber="+accNo+"";
		Connection connection=DataBaseConnection.connectToDatabase();
		Statement st=connection.createStatement();
		ResultSet rs=st.executeQuery(query);
		
		rs.next();
		int pinFromDatabase= rs.getInt("Pin");
		
		if(pin == pinFromDatabase)
		return true;   return false;
   }
   
   public boolean checkIncorrect() {
	   if(count>3) {
		   System.out.println("**********PLEASE VISIT BANK*******************");
	   return false;
	   }
	return true;
   }

}
