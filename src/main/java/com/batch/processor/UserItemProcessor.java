package com.batch.processor;

import com.batch.model.User;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.batch.item.ItemProcessor;

@Component
public class UserItemProcessor implements ItemProcessor<User, User> {
	
	@Override
	public User process(User user) throws Exception {
		
		//create a file to write errors in it
		File file = new File("error_records.txt");
		//put validation
		
		if(user.getLastName()==null || user.getLastName().isEmpty()) {
			
			FileWriter fileWriter = new FileWriter("error_records.txt",true);
			fileWriter.write("Contacts.csv" + " :: " + user.getUserId() + " :: " + "Last Name cannot be null\n");
			fileWriter.close();
			return null;
			//write to error file
		}else if( user.getFirstName() ==null || user.getFirstName().isEmpty()) {
			
			FileWriter fileWriter = new FileWriter("error_records.txt",true);
			fileWriter.write("Contacts.csv" + " :: " + user.getUserId() + " :: " + "First Name cannot be null\n");
			fileWriter.close();
			return null;
		
		}else if( user.getEmail() == null || user.getEmail().isEmpty()) {
			
			FileWriter fileWriter = new FileWriter("error_records.txt",true);
			fileWriter.write("Contacts.csv" + " :: " + user.getUserId() + " :: " + "Email cannot be null\n");
			fileWriter.close();
			return null;
			
		}
		else if(user.getPhone().length()!=10) {
			
			FileWriter fileWriter = new FileWriter("error_records.txt",true);
			fileWriter.write("Contacts.csv" + " :: " + user.getUserId() + " :: " + "Invalid Phone Number\n");
			fileWriter.close();
			return null;
		}else if(user.getPhone().length()==10) {
			try {
				Long phoneNumber = Long.parseLong(user.getPhone());
				return user;
			} catch (Exception e) {
				// TODO: handle exception
				FileWriter fileWriter = new FileWriter("error_records.txt",true);
				fileWriter.write("Contacts.csv" + " :: " + user.getUserId() + " :: " + "Invalid Phone Number\n");
				fileWriter.close();
				return null;
			}
			
		}
			//write to error file
		
		return user;
	}
} 

//public Customer process(final Customer item) throws Exception {
//    if (new GregorianCalendar().get(Calendar.MONTH) == item.getBirthday().get(Calendar.MONTH)) {
//        return item;
//    }
//    return null;
//}