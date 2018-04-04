package application.model;

import java.util.Date;

public class Transection {
	private int id;
	private String type;
	private int  bookid;
	private int personid;
	private Date sdate;   //issue/reserve date
	private Date edate;   // return/reserved book is given to the reverser
	private boolean status;
	
	public Transection()
	{
		
	}
	
	public Transection(String type,int  bookid,int personid,boolean status)
	{
		this.type=type;
		this.bookid=bookid;
		this.personid=personid;
		this.sdate=new Date();   //issue/reserve date
		this.status=status;
	}
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookId) {
		this.bookid = bookId;
	}
	public int getPersonid() {
		return personid;
	}
	public void setPersonid(int personId) {
		this.personid = personId;
	}
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public Date getEdate() {
		return edate;
	}
	public void setEdate(Date edate) {
		this.edate = edate;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

}
