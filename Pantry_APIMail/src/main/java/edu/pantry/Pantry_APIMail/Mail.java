package edu.pantry.Pantry_APIMail;

import java.util.Date;

public class Mail {

	String subject;
	String to;
	String from;
	Date date;
	int size;
	
	
	public Mail(String subject, String from, String to, Date date2, int size) {
		super();
		this.subject = subject;
		this.from = from;
		this.date = date2;
		this.size = size;		
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		subject = subject;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		from = from;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		date = date;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		size = size;
	}
	

}
