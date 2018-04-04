package application.view;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import DBController.StoreData;
import application.MainApp;
import application.model.Book;
import application.model.Person;
import application.model.Transection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class IssueBookController implements Initializable {
	private MainApp mainApp;
	
	@FXML
    private TableView<Book> IssuebooksTable;
    @FXML
    private TableColumn<Book, String> bookTitleColumn;
    @FXML
    private TableColumn<Book, String> authorNameColumn;
    
    @FXML
    private TextField personid;
    @FXML
    private TextField ISBN;
    
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        
	    if(IssuebooksTable == null)
	    { System.out.println("null"); }
	    else
	    {   bookTitleColumn.setCellValueFactory(cellData -> cellData.getValue().getBook_titleProperty());
            authorNameColumn.setCellValueFactory(cellData -> cellData.getValue().getAuthor_nameProperty());
            
            IssuebooksTable.getSelectionModel().selectedItemProperty().addListener(
            		(observable, oldValue, newValue) -> showBookDetails(newValue));
            IssuebooksTable.setItems(mainApp.getIssueList());  }
	    
    }
    
    private void showBookDetails(Book b) {
		//System.out.println(b.getBook_title());
		//mainApp.showBookDialog(b); 

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private void addMoreBooks()
	{
		System.out.println("search button is selected\n");
	    mainApp.showPage("view/Search_Book.fxml",0);
	}

	@FXML
	private void home()
	{	
			System.out.println("Home button is selected\n");
		    mainApp.showPage("view/Main_Page.fxml",1);
	}
	
	
	@FXML
	private void clearIssuedBooks()
	{
		mainApp.clearIssueList();
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
	private  void returnBookInDB()
	{
		if( this.personid.getText()!=null &&  !"".equals( this.personid.getText().replaceAll(" ", "")) && 
		     this.ISBN.getText()!=null  && !"".equals( this.ISBN.getText().replaceAll(" ", "")) )
		{
			String query="from Book where ISBN="+this.ISBN.getText();
			System.out.println(query);
			List<Book> b=(List<Book>) StoreData.RetreiveBooks(query);
			
			query="from Person where id="+ personid.getText();
			System.out.println(query);
			List<Person> p=(List<Person>) StoreData.RetreivePerson(query);
			
			Person person = null;
			Book book = null;
			boolean ISBN_OK=false;
			boolean PersonID_OK=false;
			if(  b!=null  && b.size()>0 )
			{   book=b.get(0);
				ISBN_OK=true;
			}
			if(p!=null  && p.size()>0)
			{   person=p.get(0);
				PersonID_OK=true;
			}
			if( PersonID_OK && ISBN_OK )
			{   query="from Transection where personid="+ personid.getText() + " and bookid="+book.getId()
														+ " and status=1";
				List<Transection> t=(List<Transection>) StoreData.RetreiveTransections(query);
				if( t!=null && t.size()>0 )
				{
					Transection transection=t.get(0);
					transection.setStatus(false);
					transection.setEdate(new Date());
					book.setQuantity(book.getQuantity()+1);
					person.setBooksLimit(person.getBooksLimit()+1);
					
					
					long diff = transection.getEdate().getTime() - transection.getSdate().getTime();
				    int days=(int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
					System.out.println("days  "+ days); 
					if(days>=15)
					{
						person.setFine(person.getFine()+ (days-15)*5);
						System.out.println("You are charged Fine RS="+ (days-15)*5); 
					}
					
					StoreData.StoreObject(book);
					StoreData.StoreObject(person);
					StoreData.StoreObject(transection);
					
					mainApp.setPerson(person);
					mainApp.TransectionControl("view/After_Return_Book.fxml", person, (days-15)*5);
				}
				else
				{
					Alert alert = new Alert(AlertType.WARNING);
			        alert.initOwner(mainApp.getPrimaryStage());
			        alert.setTitle("Invalid Information");
			        //alert.setHeaderText("No Person Selected");
			        alert.setContentText("No Transection Active Found With Given Information!");
			        alert.showAndWait();
				}
			}
			else
			{
				Alert alert = new Alert(AlertType.WARNING);
		        alert.initOwner(mainApp.getPrimaryStage());
		        alert.setTitle("Invalid Information");
		        //alert.setHeaderText("No Person Selected");
		        if(!PersonID_OK && !ISBN_OK)
		        alert.setContentText("ISBN Is Not Valid!\nPersonID IS Not Valid!");
		        else if( !ISBN_OK )
			        alert.setContentText("ISBN Is Not Valid!");
		        else if(!PersonID_OK)
			        alert.setContentText("PersonID IS Not Valid!");
		        alert.showAndWait();
			}
		}
		else
		{
			Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(mainApp.getPrimaryStage());
	        alert.setTitle("Invalid Information");
	        //alert.setHeaderText("No Person Selected");
	        alert.setContentText("Please Fill The Input Fields");
	        alert.showAndWait();
		}
	}
	
	
	@FXML
	private void saveIssuedBooks()
	{   if(mainApp.getIssueList().size()==0)
	    {
			Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(mainApp.getPrimaryStage());
	        alert.setTitle("Empty List");
	        //alert.setHeaderText("No Person Selected");
	        alert.setContentText("Please Add At Least One Book\nTo Proceed Transection" );
	        alert.showAndWait();
	        return;
	    }
		
		
		if( this.personid.getText()!=null &&  !"".equals( this.personid.getText().replaceAll(" ", ""))  )
		{
			
			String query="from Person where id="+ personid.getText();
			System.out.println(query);
			List<Person> p=(List<Person>) StoreData.RetreivePerson(query);
			Person person;
			if(  p!=null  && p.size()>0)
			{	person=p.get(0); 
				System.out.println("Person Found");
				if ( person.getBooksLimit()>=mainApp.getIssueList().size()  )
				{	
					
					for( Book b:mainApp.getIssueList() )
					{
						System.out.println("book id"+b.getBook_title());
						Transection t=new Transection("Issue",b.getId(),person.getId(),true);
						StoreData.StoreObject(t);
						b.setQuantity(b.getQuantity()-1);
						StoreData.StoreObject(b);
					}
					person.setBooksLimit(person.getBooksLimit()-mainApp.getIssueList().size());
					StoreData.StoreObject(person);
					Alert alert = new Alert(AlertType.WARNING);
		            alert.initOwner(mainApp.getPrimaryStage());
		            alert.setTitle("Transection Completed");
		            alert.setHeaderText("Operation Successful");
		            alert.setContentText( "Issue Transection Completed" );
		            alert.showAndWait();
		            this.home();
				}
				else
				{
					Alert alert = new Alert(AlertType.WARNING);
		            alert.initOwner(mainApp.getPrimaryStage());
		            alert.setTitle("Limit Exceeded");
		            //alert.setHeaderText("No Person Selected");
		            alert.setContentText("Sorry! You Exceeded Limit\nYour remaining Issue Book Limit is "+person.getBooksLimit());
		            alert.showAndWait();
				}
				
				
				
			  //mainApp.setRetrievedPerson(person);
			  //mainApp.AddProfileShowPage("view/Search_Member_Result.fxml",person);
			}
			else
			{
				Alert alert = new Alert(AlertType.WARNING);
	            alert.initOwner(mainApp.getPrimaryStage());
	            alert.setTitle("Invalid Information");
	            //alert.setHeaderText("No Person Selected");
	            alert.setContentText("No Results Found For This ID Please Try Again!");
	            alert.showAndWait();
			}
			
			
		}
		else
		{
			Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Invalid Information");
            //alert.setHeaderText("No Person Selected");
            alert.setContentText("Please Enter the Borrower's ID");
            alert.showAndWait();
		}
		
	}
}
