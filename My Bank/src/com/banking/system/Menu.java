package com.banking.system;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.*;

public class Menu {

	static Scanner sc = new Scanner(System.in);
	public static void menuOptions() throws ClassNotFoundException, SQLException, IOException {
		char ch;
		int choice = 0;
		do {
			System.out.println("\t\t\t      MENU");
			System.out.println("\t\t__________________________________");
			System.out.println("\n\t\t******* 1.)  GO TO BANK *******");
			System.out.println("\t\t******* 2.)  GO TO ATM  *******");
			System.out.println("\t\t******* 3.)    EXIT     *******");
			System.out.println("\t\t__________________________________");
			System.out.println("\n\t\t***********  ENTER CHOICE ******");
			try {
			 choice = sc.nextInt();
			}catch(Exception e) {
				System.out.println("****\t\tPlease choose option correctly****");
			}
			
			switch(choice) {
			   
			case 1: 
//				GO  TO BANK		
				bankOptions();
				break;
			
			case 2: 
//					GO TO ATM
				atmOptions();
				break;
				
			case 3:  System.exit(0);           			// EXIT FROM PROGRAM 
				
			default: System.out.println("\t\tINVALID INPUT!\nPLEASE TRY AGAIN");                //INVALID USER INPUT
		
			
				}	
			System.out.println("\t\t****** Do you want to continue[Y/N]? ******");
			   ch= sc.next().charAt(0);
			   
			}while(ch=='y' && ch=='Y');
		 

	}

	

public static void bankOptions() throws ClassNotFoundException, SQLException, IOException{
	char ch;
	int choice = 0;
	Transactions ts = new Transactions();
	MiniStatement ms=new MiniStatement();
   	do {
   		System.out.println("\t\t__________________________________");
	System.out.println("\n\t\t******* 1.)CREATE NEW ACCOUNT ****");
	System.out.println("\t\t******* 2.)DEPOSIT AMOUNT     ****");
	System.out.println("\t\t******* 3.)WITHDRAW AMOUNT    ****");
	System.out.println("\t\t******* 4.)SEE MINISATEMENT   ****");
	System.out.println("\t\t******* 5.)  EXIT BANK        ****");
	System.out.println("\t\t__________________________________");
	  System.out.println("\n\t\t**********  ENTER CHOICE  ********");
	   try {
	  choice=sc.nextInt();
	   } catch(Exception e) {
		   System.out.println("\t\t****Please choose option correctly****");
	   }
	  switch(choice) {
	        
	   case 1: 
//		   Create a bank account
		   CreateAccount create = new CreateAccount();
		     
		 break;
	   
	   case 2 :
//		 Deposit amount to bank
        
         
         ts.deposit();
	      break;
	   
	   case 3:
//		   Withdraw amount from bank
		 ts.withDraw();
		  
	   break;
	   
	   case 4:
//			Get Ministatement from bank
	        ms.getAllInfo();
	        
	        break;
		
	   case 5:    menuOptions(); break;           //  EXIT TO PREVIOUS MENU 
		
		default: System.out.println("\t\tINVALID INPUT!\nPLEASE TRY AGAIN");
	
		
			}	
	  System.out.println("\t\t****** EXIT BANK [Y/N]? ******");
	   ch= sc.next().charAt(0);
		   
		}while(ch!='y' && ch!='Y');
	 
}

public static void atmOptions() throws ClassNotFoundException, SQLException, IOException {
	char ch;
	int choice=0;
	ATM atm = new ATM();
    do {
   	 System.out.println("\t\t__________________________________");
   	 System.out.println("\n\t\t******* 1.)WITHDRAW CASH ATM *****"); 
   	 System.out.println("\t\t******* 2.)   CHANGE PIN     *****");
   	 System.out.println("\t\t******* 3.)   EXIT ATM       *****");
   	 System.out.println("\t\t__________________________________");
   	   System.out.println("\n\t\t****** ENTER CHOICE  ******");
   
   	     try {
   	   choice = sc.nextInt();
   	     }catch(Exception e) {
   	    	System.out.println("****\t\tPlease choose option correctly****");
   	     }
   	   switch (choice) {
   	 
   	   case 1: atm.withDrawAtm(); break;
   	   
   	   case 2:atm.changePin(); break;
   	   
   		case 3:  menuOptions();  break ;      //Returns to customer options 
			
   		default: System.out.println("\t\tINVALID INPUT!\nPLEASE TRY AGAIN");;
   	
   		
   			}	
   		System.out.println("****** \t\tEXIT ATM [Y/N]? ******");
   		   ch= sc.next().charAt(0);
   		   
   		}while(ch!='y' && ch!='Y');
}

public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
	Menu.menuOptions();
	 
	 
}
}

