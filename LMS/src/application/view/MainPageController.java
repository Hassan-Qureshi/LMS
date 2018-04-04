package application.view;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import DBController.StoreData;
import application.MainApp;
import application.model.Book;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class MainPageController implements Initializable {
	
	private MainApp mainApp;
	
	@FXML
    private TableView<Book> booksTable;
    @FXML
    private TableColumn<Book, String> bookTitleColumn;
    @FXML
    private TableColumn<Book, String> authorNameColumn;
    @FXML
    private TableColumn<Book, String> ISBNColumn;
//    @FXML
//    private TableColumn<Book, String> categoryColumn;
    @FXML
    private TableColumn<Book, Integer> quantityColumn;
//    @FXML
//    private TableColumn<Book, Integer> editionColumn;
	
	
	
	
//	@FXML
//	private TextField search_text;
//	@FXML
//	private Button search_button;
//	@FXML
//	private Button home_button;
//	@FXML
//	private Button logout_button;
//	@FXML
//	private MenuButton mange_books_button;
//	@FXML
//	private MenuButton transaction_button;
//	@FXML
//	private MenuButton manage_profile_button;
	
	
	
	
	/* Attributes for Book*/
	@FXML
	private TextField book_title;
	@FXML
	private TextField author_name;
	@FXML
	private TextField  ISBN;
	@FXML
	private TextField book_edition;
	@FXML
	private TextField publish_year;
	@FXML
	private TextField quantity;
	@FXML
	private TextField category;
	
	@FXML
	private TextField location;
	
	
	/*-------------------------*/
	
	
//	@FXML
//    private void initialize() {
//		book_title.setText("This is my first Text");
//		System.out.println("In initialized Method without parameters");
//    } 
	
	
	@FXML
	private void login() {
		home();
	}
	
	@FXML
	private void logout() {
		mainApp.login_logout("view/Login.fxml");
	}
	
	@FXML
	private void addBook()
	{	
			System.out.println("add book button is selected\n");
		    mainApp.showPage("view/Add_Book.fxml",0);
	}
	
	@FXML
	private void searchBook()
	{	
			System.out.println("search book button is selected\n");
		    mainApp.showPage("view/Search_Book.fxml",0);
		    
	}
	
	@FXML
	private void home()
	{	
			System.out.println("Home button is selected\n");
			if(mainApp == null) {
				System.out.println("Main App Is null");
			}
		    mainApp.showPage("view/Main_Page.fxml",1);
	}
	
	@FXML
	private void addProfile()
	{	
			System.out.println("add profile button is selected\n");
		    mainApp.AddProfileShowPage("view/Add_Members_Profile.fxml",null);
	}
	
	@FXML
	private void searchProfile()
	{	
			System.out.println("search profile button is selected\n");
		    mainApp.AddProfileShowPage("view/Search_Member.fxml",null);
	}
	
	
	@FXML
	private void issueBook()
	{
		System.out.println("Issue book button is selected\n");
	    mainApp.IssueBookshowPage("view/Issue_Book.fxml");
	}
	
	
	@FXML
	private void returnBook()
	{
		System.out.println("Return book button is selected\n");
	    mainApp.IssueBookshowPage("view/Return_Book.fxml");
	}
	
	@FXML
	private void checkFine()
	{
		System.out.println("Check Fine button is selected\n");
	    mainApp.TransectionControl("view/Check_Fine.fxml",null,-1);
	}
	
	
	
	@FXML
	public void saveBookDetails()
	{   String error="";
	    boolean valid=true;
		if(book_title.getText()==null || book_title.getText().length()==0)
	    {    error+="Book Title Cannot be Empty\n";  valid=false;}
		if(author_name.getText()==null || author_name.getText().length()==0)
	    {    error+="author_name Cannot be Empty\n"; valid=false;}
		if(ISBN.getText()==null || ISBN.getText().length()==0)
	    {    error+="ISBN Cannot be Empty\n";  valid=false;}
		else if( ISBN.getText().length()<11 || ISBN.getText().length()>11  )
		{  error+="ISBN Length is not equal to 11 digits\n";  valid=false;}
		
		
		if(publish_year.getText()==null || publish_year.getText().length()==0)
	    {    error+="publish_year Cannot be Empty\n"; valid=false;}
		
		
		if(quantity.getText()==null || quantity.getText().length()==0)
	    {    error+="quantity Cannot be Empty\n"; valid=false;}
		if(category.getText()==null || category.getText().length()==0)
	    {    error+="category Cannot be Empty\n";  valid=false;}
		if(location.getText()==null || location.getText().length()==0)
	    {    error+="location Cannot be Empty\n"; valid=false;}
		
		if( valid )
		{
			System.out.println(book_title.getText());
			System.out.println(author_name.getText());
			System.out.println(ISBN.getText());
			System.out.println(book_edition.getText());
			System.out.println(publish_year.getText());
			System.out.println(quantity.getText());
			System.out.println(category.getText());
			Book b= new Book(book_title.getText(),author_name.getText(),
					ISBN.getText(),Integer.parseInt(book_edition.getText()),
					Integer.parseInt(publish_year.getText()),Integer.parseInt(quantity.getText()),
					category.getText(),location.getText());
		
			StoreData.StoreObject(b);
			mainApp.showPage("view/After_Adding_Book.fxml",0);
		}
		else
		{
			Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Invalid Information");
            //alert.setHeaderText("No Person Selected");
            alert.setContentText(error);
            alert.showAndWait();
		}
	    
	}
	
	@FXML
	public void retrieveData()
	{
		System.out.println(book_title.getText());
		System.out.println(author_name.getText());
		System.out.println(ISBN.getText());
		System.out.println(category.getText());
		
		String query=" ";

		int append_where=0;
		if( !"".equals( book_title.getText().replaceAll(" ", "")) )
		{   
			query+="book_title='"+book_title.getText()+"'";
			append_where=1; 
		}
		if( !"".equals(author_name.getText().replaceAll(" ", ""))  )
		{   if(append_where==0)
				query+="author_name='"+author_name.getText()+"'";  
		   else
			    query+=" or author_name='"+author_name.getText()+"'";
			append_where=1;
		}
		if( !"".equals(ISBN.getText().replaceAll(" ", ""))  )
		{   if(append_where==0)
				query+="ISBN="+ISBN.getText();  
			else
				query+=" or ISBN="+ISBN.getText();  
			append_where=1;
		}
		if( !"".equals(category.getText().replaceAll(" ", ""))  )
		{   if(append_where==0)
				query+="category='" + category.getText()+"'";  
		    else 
		    	query+=" or category='" + category.getText()+"'";
			append_where=1;
		}
	    
		if(append_where==1)
		{   
			query="from Book where " + query + " and quantity>1";}
		else
		{ query="from Book where quantity>1"; }
		
		System.out.println(query);  
	   
		List<Book> books=StoreData.RetreiveBooks(query  );
		mainApp.clearRetrievedBooks();
		//mainApp.clearIssueList();
		if(books!=null && books.size()>0)
		{
			for( Book b: books )
			{  mainApp.addRetrievedBook(b); }
			
		    System.out.println("Search button is selected\n");
		    mainApp.showPage("view/Search_Results.fxml",0);
		}
		else
		{
			Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Invalid Information");
            //alert.setHeaderText("No Person Selected");
            alert.setContentText("No Results Found Please Enter Correction Information!");
            alert.showAndWait();
		}
	    
	}
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        
	    if(booksTable == null)
	    { System.out.println("null"); }
	    else
	    {   bookTitleColumn.setCellValueFactory(cellData -> cellData.getValue().getBook_titleProperty());
            authorNameColumn.setCellValueFactory(cellData -> cellData.getValue().getAuthor_nameProperty());
            ISBNColumn.setCellValueFactory(cellData -> cellData.getValue().getISBNProperty() );
//            categoryColumn.setCellValueFactory(cellData -> cellData.getValue().getCategoryProperty());
//            editionColumn.setCellValueFactory(cellData -> cellData.getValue().getBook_editionProperty().asObject());
            quantityColumn.setCellValueFactory(cellData -> cellData.getValue().getQuantityProperty().asObject());
            
            booksTable.getSelectionModel().selectedItemProperty().addListener(
            		(observable, oldValue, newValue) -> showBookDetails(newValue));
	    	booksTable.setItems(mainApp.getRetrievedBooks());  
	    	
	    }
	    
	        

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 //System.out.println("In initialized Method with parameters"+location.getPath());
		
	}
	
	
	private void showBookDetails(Book b) {
		booksTable.refresh();
		if(b != null && mainApp!=null)
			mainApp.showBookDialog(b); 

    }
	
	
	
}
