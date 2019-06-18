package com.banking.system;

import java.util.*;

import java.sql.*;
import java.text.SimpleDateFormat;


public class CreateAccount {
	
 String firstName,middleName,lastName,mobileNumber,address,email,dateAndTime;
	
	long balance;
	
	int pin;
	
	static Scanner sc = new Scanner(System.in);
	 
	CreateAccount() throws ClassNotFoundException, SQLException{
		this.balance =0;
		this.askFirstName();
		this.askMiddleName();
	    this.askLastName();
		this.askMobileNumber();
		this.askAddress();
	    this.askEmail();
	    this.pin =1234;
	   
	    
	    this.dateAndTime= new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	    this.storeAllInfo();
	    this.provideInfo();
	   }
	
	CreateAccount(long balance){
		this.balance+=balance;
	}
	
	private void askFirstName() {
		System.out.println("\t\t************ENTER FIRST NAME***************");
		String firstName  = sc.nextLine();
		if (isStringOnlyAlphabet(firstName))
		         this.firstName = firstName;
			else  {
				System.out.println("\t\t*******PLEASE TYPE CORRECTLY**********");
                      askFirstName();			
				}
		}
	private void askMiddleName() {
		System.out.println("\t\t************ENTER MIDDLE NAME [OPTIONAL]***************");
		String middleName  = sc.nextLine();
		if (isStringForOptional(middleName))
		         this.middleName =middleName;
		else {
				System.out.println("\t\t*******INVALID NAME**********");
				askMiddleName();
				}
		}
	private void askLastName() {
		System.out.println("\t\t************ENTER LAST NAME***************");
		String lastName  = sc.nextLine();
		if (isStringOnlyAlphabet(lastName))
		         this.lastName = lastName;
			else {
				System.out.println("\t\t*******PLEASE TYPE CORRECTLY**********");
				askLastName();
				}
		}
	private void askMobileNumber() {
		System.out.println("\t\t************ENTER MOBILE NUMBER***************");
		String mobileNumber  = sc.nextLine();
		if (isStringOnlyNumber(mobileNumber))
		         this.mobileNumber = mobileNumber;
			else {
				System.out.println("\t\t*******INVALID MOBILE NUMBER**********");
		        askMobileNumber();
			}
		}
	private void askAddress() {
		System.out.println("\t\t************ENTER ADDRESS***************");
		String address  = sc.nextLine();
		if (isStringOnlyAlphabet(address)) 
			this.address = address;
		
			else  {
				System.out.println("\t\t*******PLEASE TYPE CORRECTLY**********");
				askAddress();
			}
		}
	private void askEmail() {
		System.out.println("\t\t************ENTER EMAIL***************");
		String email  = sc.nextLine();
		if (isCorrectEmail(email)) {
		         this.email = email;
		        
		}
		else {
			System.out.println("\t\t*******INVALID NAME**********");
			askEmail();
		}
		}
	
	private boolean isStringOnlyAlphabet(String str) { 
	    return ((!str.equals("")) 
	            && (str != null) 
	            && (str.matches("^[a-zA-Z]*$"))); 
	} 
	
	private boolean isStringForOptional(String str) {
		return ( (str.matches("^[a-zA-Z]*$")));
		
	}
	
	private  boolean isStringOnlyNumber(String str) 
	{ 
	    return ((!str.equals("")) 
	            && (str != null) 
	            && (str.matches("[0-9]+") && str.length() == 10)); 
	} 
	
	private  boolean isCorrectEmail(String str) {
		return ((!str.equals("")) 
				&& (str !=null));
	}
	

	
	

	private void storeAllInfo() throws ClassNotFoundException, SQLException {
		
		String sql ="INSERT INTO bank(FirstName,MiddleName,LastName,MobileNumber,EmailAddress,Address,Pin,Balance,DateAndTime)"
				+ "VALUES('"+this.firstName+"','"+this.middleName+"','"+this.lastName+"','"+this.mobileNumber+"','"+this.email+"','"+this.address
			
			+"','"+this.pin+"','"+this.balance+"','"+this.dateAndTime+"')";
	
		Connection con= DataBaseConnection.connectToDatabase();
		Statement st=con.createStatement();
		st.executeUpdate(sql);
		System.out.println("\t\t*******ACCOUNT CREATION SUCCESSFUL**********");
		st.close();
		con.close();
		
	}
	

	 
	 public void provideInfo() throws ClassNotFoundException, SQLException {
		  
		 String query = "SELECT * from bank where FirstName='"+this.firstName+"'  ";
			Connection con=DataBaseConnection.connectToDatabase();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			rs.next();
			String name =rs.getString("FirstName")+" "+rs.getString("MiddleName")+" "+rs.getString("LastName");
			long accNo=rs.getInt("AccountNumber");
			String dateAndTime = rs.getString("DateAndTime");
			long bal=rs.getLong("Balance");
			System.out.println("\n\t\tFULL NAME: "+name+"\n\t\tACCOUNT NUMBER: "+accNo+"\n\t\tACCOUNT CREATION DATE: "+dateAndTime
					            + "\n\t\tBALANCE:_"+bal);
			
}
}
