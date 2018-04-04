package application.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import DBController.StoreData;
import application.MainApp;
import application.model.Person;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class TransectionController implements Initializable{
	private MainApp mainApp;
	private Person person;
	private int fine;
	
	@FXML 
	private Label current_fine;
	@FXML 
	private Label previous_fine;
	@FXML
	private Label total_fine;
    @FXML
    private TextField personid;
	
	
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
	private void payFine()
	{
		person.setFine(0);
		StoreData.StoreObject(person);
		Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Fine Payment");
        alert.setHeaderText("Fine Payment");
        alert.setContentText("Fine Successfully Paid!");
        alert.showAndWait();
        this.home();
	}
	
	
	@FXML
	private void checkFine()
	{
		if(personid!=null && !"".equals( this.personid.getText().replaceAll(" ", "")) )
		{
			String query="from Person where id="+ personid.getText();
			System.out.println(query);
			List<Person> p=(List<Person>) StoreData.RetreivePerson(query);
			
			if(p!=null  && p.size()>0)
			{   
				mainApp.setPerson( p.get(0) );
				mainApp.TransectionControl("view/Check_Fine_Result.fxml", p.get(0), p.get(0).getFine());
			}
			else
			{
				Alert alert = new Alert(AlertType.WARNING);
		        alert.initOwner(mainApp.getPrimaryStage());
		        alert.setTitle("Invalid Information");
		        //alert.setHeaderText("No Person Selected");
		        alert.setContentText("PersonID IS Not Valid!");
		        alert.showAndWait();
			}
			
		}
		else
		{
			Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(mainApp.getPrimaryStage());
	        alert.setTitle("Invalid Information");
	        //alert.setHeaderText("Fine Payment");
	        alert.setContentText("Borrower ID Cannot Be Empty!");
	        alert.showAndWait();
		}
	
	}
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	public void setMainApp(MainApp mainApp) {
		// TODO Auto-generated method stub
		this.mainApp=mainApp;
	}

	public void setPersonAndFine(Person person, int fine) {
		// TODO Auto-generated method stub
		System.out.println("in set Person And Fine ="+person); 
		//if( person!=null)
			this.person=person;
			this.fine=fine;
			if(this.current_fine!=null)
			this.current_fine.setText(  "RS."+((Integer)fine).toString() );
			if(this.previous_fine!=null)
			this.previous_fine.setText( "RS."+((Integer)(person.getFine()-fine) ).toString() );
			if(this.total_fine!=null)
			this.total_fine.setText(  ( "RS."+(Integer)person.getFine() ).toString() ); 
		
	} 

}
