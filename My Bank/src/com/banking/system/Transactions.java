package com.banking.system;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
public class Transactions {
static Scanner sc = new Scanner(System.in);

    
	public void deposit() throws ClassNotFoundException, SQLException {
		long amount= 0;
		long accountNum =0;
		String status="Deposit";
		String dateAndTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		System.out.println("\n\t\t******ENTER ACCOUNT NUMBER********");
		try {
			accountNum=sc.nextLong();
			
		}catch(Exception e) {
			System.out.println("\n\t\t*******PLEASE ENTER CORRECTLY********");
		}
		
		if(checkAccountNumber(accountNum)) {
			System.out.println("\t\t************ENTER AMOUNT TO BE DEPOSITED************");
			try {
				amount=sc.nextLong();
				
			}catch(Exception e) {
				System.out.println("\t\t******PLEASE ENTER AMOUNT CORRECTLY******");
			}
			String sql ="INSERT INTO transactions(Amouont,Status,DateAndTime,AccountNumber)"
					+ "VALUES('"+amount+"','"+status+"','"+dateAndTime+"','"+accountNum+"')";
		
			String query="UPDATE bank SET Balance=Balance + ? where AccountNumber= ?";
			Connection con=DataBaseConnection.connectToDatabase();
			Statement statement=con.createStatement();
			statement.executeUpdate(sql);
	       
			
			PreparedStatement st=con.prepareStatement(query);
			st.setLong(1,amount);
			st.setLong(2, accountNum);
			st.executeUpdate();
			st.close();
			 con.close();
            System.out.println("\t\tDEPOSIT SUCCESSFUL");
			
		}else 
			System.out.println("\t\t********INCORRECT ACCOUNT NUMBER********");
				
	}
	
	public void withDraw() throws ClassNotFoundException, SQLException {
		long amount= 0;
		long accountNum =0;
		String status="Withdraw";
		String dateAndTime= new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		System.out.println("\n\t\t******ENTER ACCOUNT NUMBER********");
		try {
			accountNum=sc.nextLong();
			
		}catch(Exception e) {
			System.out.println("\n\t\t*******PLEASE ENTER CORRECTLY********");
		}
		
		if(checkAccountNumber(accountNum)  && checkBalance(accountNum)) {
			System.out.println("\t\t************ENTER AMOUNT TO BE WITHDRAWN************");
			try {
				amount=sc.nextLong();
				
			}catch(Exception e) {
				System.out.println("\t\t******PLEASE ENTER AMOUNT CORRECTLY******");
			}
			String sql ="INSERT INTO transactions(Amouont,Status,DateAndTime,AccountNumber)"
					+ "VALUES('"+amount+"','"+status+"','"+dateAndTime+"','"+accountNum+"')";
			Connection con=DataBaseConnection.connectToDatabase();
			Statement statement=con.createStatement();
			statement.executeUpdate(sql);
			
			String query="UPDATE bank SET Balance=Balance - ? where AccountNumber= ?";
			
			PreparedStatement st=con.prepareStatement(query);
			st.setLong(1,amount);
			st.setLong(2, accountNum);
			st.executeUpdate();
			st.close();
			con.close();
			
		}
	}
	
	
	
//	public void transfer() {
//		
//	}
	
	public boolean checkAccountNumber(long accountNum) throws ClassNotFoundException, SQLException {
	
		String query="SELECT AccountNumber from bank  where AccountNumber="+accountNum+"";
		Connection connection=DataBaseConnection.connectToDatabase();
		Statement st=connection.createStatement();
		ResultSet rs=st.executeQuery(query);
		
		rs.next();
		long accFromDatabase = rs.getLong("AccountNumber");
	
		if (accountNum == accFromDatabase)     return true;  return false;
		
	}
	
	public boolean checkBalance(long accountNum) throws ClassNotFoundException, SQLException {
		String query="SELECT Balance from bank  where AccountNumber="+accountNum+"";
		Connection connection=DataBaseConnection.connectToDatabase();
		Statement st=connection.createStatement();
		ResultSet rs=st.executeQuery(query);
		
		rs.next();
		long balanceFromDatabase = rs.getLong("Balance");
		
		if (balanceFromDatabase == 0) {
			System.out.println("\t\t*********INSUFFCIENT BALANCE*********");
			return false; 
		}
		return true;
		
	}
}
