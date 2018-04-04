package application.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {
	
	private int id;
	private StringProperty book_title;
	private StringProperty author_name;
	private StringProperty  ISBN;
	private IntegerProperty book_edition;
	private IntegerProperty publish_year;
	private IntegerProperty quantity;
	private StringProperty category;
	private StringProperty location;
	
	public Book()
	{
		this(null, null,null,0,0,0,null,null);
	}
	public Book(Book b)
	{
		
		id=b.getId();
		book_title=b.getBook_titleProperty();
		author_name=b.getAuthor_nameProperty();
		ISBN=b.getISBNProperty();
		book_edition=b.getBook_editionProperty();
		publish_year=b.getPublish_yearProperty();
		quantity=b.getQuantityProperty();
		category=b.getCategoryProperty();
		this.location=b.getLocationProperty();
		
	}
	public Book(String  book_title,String author_name,String ISBN,int book_edition,int publish_year,int quantity,String category,String location)
	{
		this.book_title=new SimpleStringProperty(book_title);
		this.author_name=new SimpleStringProperty(author_name);
		this.ISBN=new SimpleStringProperty(ISBN);
		this.book_edition=new SimpleIntegerProperty(book_edition);
		this.publish_year=new SimpleIntegerProperty(publish_year);
		this.quantity=new SimpleIntegerProperty(quantity);
		this.category=new SimpleStringProperty(category);
		this.location=new SimpleStringProperty(location);
		
	}
	public String getBook_title() {
		return book_title.get();
	}
	public String getAuthor_name() {
		return author_name.get();
	}
	public String getISBN() {
		return ISBN.get();
	}
	public int getBook_edition() {
		return book_edition.get();
	}
	public int getPublish_year() {
		return publish_year.get();
	}
	public int getQuantity() {
		return quantity.get();
	}
	
	public String getCategory() {
		return category.get();
	}
	

	public StringProperty getBook_titleProperty() {
		return book_title;
	}
	public StringProperty getAuthor_nameProperty() {
		return author_name;
	}
	public StringProperty getISBNProperty() {
		return ISBN;
	}
	public IntegerProperty getBook_editionProperty() {
		return book_edition;
	}
	public IntegerProperty getPublish_yearProperty() {
		return publish_year;
	}
	public IntegerProperty getQuantityProperty() {
		return quantity;
	}
	public StringProperty getCategoryProperty() {
		return category;
	}

	public void setBook_titleProperty(StringProperty book_title) {
		this.book_title = book_title;
	}
	public void setAuthor_nameProperty(StringProperty author_name) {
		this.author_name = author_name;
	}
	public void setISBNProperty(StringProperty iSBN) {
		ISBN = iSBN;
	}
	public void setBook_editionProperty(IntegerProperty book_edition) {
		this.book_edition = book_edition;
	}
	public void setPublish_yearProperty(IntegerProperty publish_year) {
		this.publish_year = publish_year;
	}
	public void setQuantityProperty(IntegerProperty quantity) {
		this.quantity = quantity;
	}
	public void setCategoryProperty(StringProperty category) {
		this.category = category;
	}
	
	public void setBook_title(String book_title) {
		this.book_title = new SimpleStringProperty(book_title);
	}
	public void setAuthor_name(String author_name) {
		this.author_name = new SimpleStringProperty(author_name);
	}
	public void setISBN(String iSBN) {
		ISBN =new SimpleStringProperty(iSBN);
	}
	public void setBook_edition(int book_edition) {
		this.book_edition =new SimpleIntegerProperty( book_edition );
	}
	public void setPublish_year(int publish_year) {
		this.publish_year =new SimpleIntegerProperty( publish_year );
	}
	public void setQuantity(int quantity) {
		this.quantity =new SimpleIntegerProperty( quantity );
	}
	public void setCategory(String category) {
		this.category = new SimpleStringProperty(category);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public StringProperty getLocationProperty() {
		return location;
	}
	public void setLocationProperty(StringProperty location) {
		this.location = location;
	}
	
	public String getLocation() {
		return location.get();
	}
	public void setLocation(String location) {
		this.location = new SimpleStringProperty(location);
	}
	
	
}
