package application.view;


import DBController.StoreData;
import application.MainApp;
import application.model.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class BookDialogController {
	
	private MainApp mainApp;
	
	
	@FXML
	private TextField book_title;
	@FXML
	private TextField author_name;
	
	@FXML
	private TextField book_edition;
	@FXML
	private TextField ISBN;
	@FXML
	private TextField publish_year;
	@FXML
	private TextField quantity;
	@FXML
	private TextField category;
    @FXML
    private TextField location_in_lib;

	//private Stage dialogStage;
	private Book book;
	@FXML
	private void home()
	{	
			
			System.out.println("Home button is selected\n");
		    mainApp.showPage("view/Main_Page.fxml",1);
	}
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }

	public void setDialogStage(Stage dialogStage) {
		// TODO Auto-generated method stub
		//this.dialogStage = dialogStage;
		
	}

	@FXML
	private void addBook()
	{	
			System.out.println("add button is selected\n");
		    mainApp.showPage("view/Add_Book.fxml",0);
	}
	
	@FXML
	private void searchBook()
	{	
			System.out.println("search button is selected\n");
		    mainApp.showPage("view/Search_Book.fxml",0);
		    
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
			System.out.println("add profile button is selected\n");
		    mainApp.AddProfileShowPage("view/Search_Member.fxml",null);
	}
	
	
	
	public void setBook(Book b) {
		// TODO Auto-generated method stub
		this.book=b;
		book_title.setText( b.getBook_title() );
		author_name.setText(b.getAuthor_name());
		ISBN.setText(  (b.getISBN()).toString());
		book_edition.setText(   ((Integer)b.getBook_edition()).toString()  );
		publish_year.setText(  ((Integer)b.getPublish_year()).toString()    );
		quantity.setText(  ((Integer)b.getQuantity()).toString() );
		category.setText(  b.getCategory()  );
		location_in_lib.setText(b.getLocation());
	}

	@FXML
    private void handlecancel()
    {
		//dialogStage.close();
    }
	
	@FXML
	private void IssueBook()
	{   
		mainApp.addBookInIssueList(book);
		//dialogStage.close();
		System.out.println("Issue button is pressed"+book.getAuthor_name());
		mainApp.IssueBookshowPage("view/Issue_Book.fxml");
	}
	
	@FXML
	private void backButton()
	{
		mainApp.showPage("view/Search_Results.fxml",0);
	}
	
	
	
	@FXML
	private void deleteBook()
	{
		
		StoreData.deleteBook(book);
		
		mainApp.showPage("view/Search_Results.fxml",0);
		int index=mainApp.getRetrievedBooks().indexOf(book);
		mainApp.getRetrievedBooks().remove(index);
		
		Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Operation Response");
        alert.setHeaderText("Book Successfully deleted");
        //alert.setContentText(error);
        alert.showAndWait();
	//	dialogStage.close();
		
	}
	
	@FXML
	private void updateBook()
	{
		
		
		String error="";
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
		if(location_in_lib.getText()==null || location_in_lib.getText().length()==0)
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
					category.getText(),location_in_lib.getText());
			b.setId(book.getId());
			StoreData.StoreObject(b);
			int index=mainApp.getRetrievedBooks().indexOf(book);
			mainApp.getRetrievedBooks().set(index, b);
			
			Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Operation Response");
            alert.setHeaderText("Book Successfully Updated");
            //alert.setContentText(error);
            alert.showAndWait();
		//	dialogStage.close();
			//mainApp.showPage("view/After_Adding_Book.fxml");
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
	
	
}
