package application.model;

public class Person {
	private int id;
	private String name;
	private String cnic;
	private String phone;
	private String address;
	private String email;
	private String type;
	private int booksLimit;
	private int fine;
	
	public Person()
	{
		this(null,null,null,null,null,null,0,0);
	}
	
	public Person(String text, String text2, String text3, String text4, String text5, String text6, int i, int j) {
		// TODO Auto-generated constructor stub
		name=text;
		cnic=text2;
		phone=text3;
		address=text4;
		email=text5;
		type=text6;
		booksLimit=i;
		fine=j;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCnic() {
		return cnic;
	}
	public void setCnic(String cnic) {
		this.cnic = cnic;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public int getFine() {
		return fine;
	}
	public void setFine(int fine) {
		this.fine = fine;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBooksLimit() {
		return booksLimit;
	}

	public void setBooksLimit(int booksLimit) {
		this.booksLimit = booksLimit;
	}
	
}
