package com.banking.system;
import java.util.*;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;

import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.*;
public class MiniStatement extends Transactions  {
       public static Scanner sc = new Scanner(System.in);
	public void getAllInfo()  throws ClassNotFoundException, SQLException, IOException {
	
		long accountNum =0;
		System.out.println("\n\t\t******ENTER ACCOUNT NUMBER********");
		try {
			accountNum=sc.nextLong();
			
		}catch(Exception e) {
			System.out.println("\n\t\t*******PLEASE ENTER CORRECTLY********");
		}
		super.checkAccountNumber(accountNum);
		displayInfo(accountNum);
		
			
	}
	
	public String getName(long accountNum) throws ClassNotFoundException, SQLException {
		 String query = "SELECT * from bank where AccountNumber='"+accountNum+"'  ";
			Connection con=DataBaseConnection.connectToDatabase();
			Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		String name =rs.getString("FirstName")+" "+rs.getString("MiddleName")+" "+rs.getString("LastName");
		
		return name;
	}
	
	public long getBalance(long accountNum) throws ClassNotFoundException, SQLException {
		 String query = "SELECT * from bank where AccountNumber='"+accountNum+"'  ";
			Connection con=DataBaseConnection.connectToDatabase();
			Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
	 long bal =rs.getLong("Balance");
		
		return bal;
		
	}
	
	public void displayInfo(long accountNum) throws ClassNotFoundException, SQLException, IOException {
		
		 String sql= "SELECT * from transactions where AccountNumber='"+accountNum+"'  ";
			Connection con=DataBaseConnection.connectToDatabase();
			Statement st=con.createStatement();
			

			ResultSet rs1=st.executeQuery(sql);
			
	
			
			System.out.println("\t\t*************TRANSACTIONS INFORMATION********************");
			System.out.println("\t\tAmount\tDescription\tDate\tAccount Number");
			String dest = "C:\\Users\\Prabhav\\Desktop\\BankStatement.pdf";       
		      PdfWriter writer = new PdfWriter(dest);        
		      
		         
		      PdfDocument pdf = new PdfDocument(writer);              
		      
		       
		      Document document = new Document(pdf);              
		          
		      String imFile = "C:\\Users\\Prabhav\\Desktop\\logo.png";       
		      ImageData data = ImageDataFactory.create(imFile);              
		    
		        
		      Image image = new Image(data);                        
		      image.setFixedPosition(210, 650);
		        
		      document.add(image);      
		      PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD); 
		  
		      Text text1 = new Text("\n\n\n\n\n\n\n\n\nBANK ACCOUNT STATEMENT");
		      text1.setFont(font);
		      Paragraph paragraph1 = new Paragraph();
		      paragraph1.add(text1);
		      document.add(paragraph1);
		      Text text2 = new Text("ACCOUNT HOLDER'S NAME: "+getName(accountNum));
		      text2.setFont(font);
		  
		      Paragraph paragraph2 = new Paragraph();
		      paragraph2.add(text2);
		      document.add(paragraph2);
		      
		    
		      
		      Text text4 = new Text("ACCOUNT Number: "+accountNum);
		      text4.setFont(font);
		      
		      Paragraph paragraph5 = new Paragraph();
		      paragraph5.add(text4);
		      document.add(paragraph5); 
		      
	          Paragraph paragraph3=new Paragraph("\n\n       			AMOUNT					DESCRIPTION					DATE AND TIME");
	          paragraph3.setFont(font);
		      document.add(paragraph3);
		      
		      Paragraph paragraph4 = null;
			if(rs1.next()) {
			do{
				System.out.println("\t\t"+rs1.getLong(1)+"\t"+rs1.getString(2)+"\t"+rs1.getString(3)+"\t\t"+rs1.getLong(4));
				 paragraph4=new Paragraph("\n						"+rs1.getLong(1)+"						"+rs1.getString(2)+"							"+rs1.getString(3)		);
					document.add(paragraph4);
				}while(rs1.next());
			}else {
				System.out.println("\t\t*************RESULT NOT FOUND**************");
			}
			  Text text=new Text("\n		TOTAL BALANCE:  "+getBalance(accountNum));
		      text.setFont(font);
		      
		      Paragraph paragraph = new Paragraph();
		      paragraph.add(text);
		      document.add(paragraph);
			document.close();
			con.close();
//       	sendEmail();
	}
	
	public void sendEmail() {
		 final String username = "prabhavlamichhane77@gmail.com";
			final String password = "@@##$$Delete123@@##$$";
			String fromEmail = "prabhavlamichhane77@gmail.com";
			String toEmail = "prabhavlamichhane84@gmail.com";
			
			Properties properties = new Properties();
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");
			
			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username,password);
				}
			});
			MimeMessage msg = new MimeMessage(session);
			try {
				msg.setFrom(new InternetAddress(fromEmail));
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
				msg.setSubject(" BANK ATM PROJECT");
				msg.setText("BANK MINISTATEMENT");
			Multipart emailContent = new MimeMultipart();

				MimeBodyPart textBodyPart = new MimeBodyPart();
				textBodyPart.setText("My text");
				
						MimeBodyPart pdfAttachment = new MimeBodyPart();
			pdfAttachment.attachFile("C:\\Users\\Prabhav\\Desktop\\BankStatement.pdf");
				
	      emailContent.addBodyPart(textBodyPart);
		emailContent.addBodyPart(pdfAttachment);
				
				
				msg.setContent(emailContent);
				
				Transport.send(msg);
				System.out.println("\t\tEMAIL SENT");
				

			} catch (Exception e)
			{
				e.printStackTrace();
			
		}

	}
	
	
}
