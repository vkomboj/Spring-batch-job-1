package com.batch.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name="Contacts_Vivek")
public class User {
	
	@Id
	@Column(name = "userid")
	private Integer userId;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "phone")
	private Long phone;
	
	@Column(name = "mailId")
	private String mailId;
	
	public User() {
		
	}
	public User(int id,String fn, String ln, long ph, String mail) {
		this.userId=id;
		this.firstName = fn;
		this.lastName = ln;
		this.phone = ph;
		this.mailId = mail;
	
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	
	 @Override
	    public String toString() {
	        final StringBuffer sb = new StringBuffer("User{");
	        sb.append("userId=").append(userId);
	        sb.append(", First Name='").append(firstName).append('\'');
	        sb.append(", Last Name='").append(lastName).append('\'');
	        sb.append(", Phone=").append(phone);
	        sb.append(", Mail Id='").append(mailId).append('\'');
	        sb.append('}');
	        return sb.toString();
	    }
}
