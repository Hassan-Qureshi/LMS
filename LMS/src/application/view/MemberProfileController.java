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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;

public class MemberProfileController implements Initializable {
	private MainApp mainApp;
	
	private Person person;
	
	@FXML
	private TextField id;
	
	@FXML
	private TextField name;
	@FXML
	private TextField cnic;
	@FXML
	private TextField phone;
	@FXML
	private TextField address;
	@FXML
	private  TextField email;
	@FXML
	private RadioButton student;
	@FXML
	private RadioButton teacher;
	
//	@FXML
//	private String limit;
//	@FXML
//	private int fine;

	@FXML
	private Label l_id;
	
	@FXML
	private Label l_name;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void teacherSelected()
	{
		//System.out.println("Teacher Selected");
		student.setSelected(false);
	}

	@FXML
	public void studentSelected()
	{
		//System.out.println("Student Selected");
		teacher.setSelected(false);
	}
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        System.out.println("Linked with mainapp");
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
			System.out.println("add profile button is selected\n");
		    mainApp.AddProfileShowPage("view/Search_Member.fxml",null);
	}
	
	
	@FXML
	private void giveOnlineAccess()
	{
		System.out.println("give Onile access");
		
		Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
	    alert.setTitle("Online Access");
	    alert.setHeaderText("You have Successfully Granted Online Access");
	    alert.setContentText("Use this UserID and Password to Login\nUserID="+l_id.getText()+"\nPassword=abc1234\nNote:You Can Change Password Using Website");
	    alert.showAndWait();
	}
	
	
	@FXML
	private void updateProfile()
	{
		if( valid() )
		{
			person.setName(name.getText());
			person.setCnic(cnic.getText());
			person.setPhone(phone.getText());
			person.setAddress(address.getText());
			person.setEmail(email.getText());
			if(student.isSelected())
				person.setType("Student");
			else
				person.setType("Teacher");
			
			StoreData.StoreObject(person);
			
			Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(mainApp.getPrimaryStage());
	        alert.setTitle("Operaton Result");
	        alert.setHeaderText("Successful Operation");
	        alert.setContentText(person.getName() + "'s Profile is Updated");
	        alert.showAndWait();
	        this.home();
			
		}
	}
	
	@FXML
	private void deleteProfile()
	{
		StoreData.deletePerson(person);
		Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Operaton Result");
        alert.setHeaderText("Successful Operation");
        alert.setContentText(person.getName() + " is Removed From Member's List");
        alert.showAndWait();
        this.home();
		
	}
	
	@FXML
	public void saveMembersDetails()
	{  
		
		
		
		String type="";
		int    cap=0;
	    if( student.isSelected())
	    {	System.out.println("Student is seclected");
	        type+="Student";
	        cap=3;
	    }
	    else
	    {
	    	System.out.println("Teacher is seclected");
	        type+="Teacher";
	        cap=5;
	    }
		
		if( valid() )
		{
			System.out.println("in save funtion");
			System.out.println(name.getText());
			System.out.println(cnic.getText());
			System.out.println(phone.getText());
			System.out.println(address.getText());
			System.out.println(email.getText());
			//System.out.println(student);
			
			
			Person	p=new Person(name.getText(),cnic.getText(),phone.getText(),address.getText(),
					            email.getText(),type,cap,0);
			
			p=(Person)StoreData.StoreObject(p); 
			mainApp.AddProfileShowPage("view/After_Adding_Member.fxml",p);
	    }
		
	}

	private boolean valid() {
		// TODO Auto-generated method stub
		
		String error="";
	    boolean v=true;
		if(name.getText()==null || name.getText().length()==0 || name.getText().matches("\\s*") )
	    {    error+="Member's name Cannot be Empty\n";  v=false;}
		if(cnic.getText()==null || cnic.getText().length()==0  || cnic.getText().matches("\\s*") ) 
	    {    error+="CNIC Cannot be Empty\n"; v=false;}
		if(phone.getText()==null || phone.getText().length()==0  || phone.getText().matches("\\s*"))
	    {    error+="Phone Cannot be Empty\n";  v=false;}
		else if( address.getText()==null || address.getText().length()==0  || address.getText().matches("\\s*") )
		{  error+="Address cannot be Empty\n";  v=false;}
		
		if(email.getText()==null || email.getText().length()==0  || email.getText().matches("\\s*"))
	    {    error+="Email Address Cannot be Empty\n"; v=false;}
		if(!student.isSelected() && !teacher.isSelected())
		{
			error+="Select a Type Student OR Teacher\n"; v=false;
		}
		
		if(cnic.getText()!=null && cnic.getText().length()!=13)
	    {    error+="CNIC Length is not equal to 13 Digits\n"; v=false;}
		if(phone.getText()!=null && phone.getText().length()!=11)
	    {    error+="Phone Length is not equal to 11 Digits\n";  v=false;}
		//[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+
		if(email.getText()!=null && !email.getText().matches("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+"))
		{
			error+="Email Address is not valid\n";  v=false;
		}
		
		if( v )
		{ return true;}	
		else
		{
			Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Invalid Information");
            //alert.setHeaderText("No Person Selected");
            alert.setContentText(error);
            alert.showAndWait();
		}
		
		return false;
	}

	public void setMember(Person p) {
		// TODO Auto-generated method stub
		person=p;
		if(l_id!=null && l_name!=null )
		{   System.out.println("in set member function");
			l_id.setText(  ( (Integer)p.getId() ).toString() );
		    l_name.setText( p.getName() );
		    return;
		}
		if( this.name!=null )
		{
			name.setText(p.getName());
		}
		if(this.phone!=null)
		{
			phone.setText(p.getPhone());
		}
		if(this.cnic!=null)
		{
			cnic.setText(p.getCnic());
		}
		if(this.email!=null)
		{
			email.setText(p.getEmail());
		}
		if(this.address!=null)
		{
			address.setText(p.getAddress());
		}
		if( this.student!=null)
		{
			if(p.getType().equals("Student"))
			{
				student.setSelected(true);
			}
			else
			{
				teacher.setSelected(true);
			}
				
		}
		
	}

	@FXML
	private void searchProfileInDB()
	{
		
		String query="";
		int one_field_req=0;
		
		if( this.id.getText()!=null && !"".equals(this.id.getText().replaceAll(" ", "")))
		{   query+=" id="+this.id.getText();
			one_field_req=1;
		}
		
		if( this.phone.getText()!=null &&  !"".equals( this.phone.getText().replaceAll(" ", ""))  )
		{   if ( one_field_req==0 )
				query+=" phone="+this.phone.getText();
			else
				query+=" or phone="+this.phone.getText();
			one_field_req=1;
		}
		if( this.cnic.getText()!=null && !"".equals( this.cnic.getText().replaceAll(" ", "")) )
		{   if ( one_field_req==0 )
				query+=" cnic="+this.cnic.getText();
		    else
		    	query+=" or cnic="+this.cnic.getText();
			one_field_req=1;
		}
		if( this.email.getText()!=null && !"".equals(this.email.getText().replaceAll(" ", "")))
		{
			if ( one_field_req==0 )
				query+=" email='"+this.email.getText()+"'";
			else
				query+=" or email='"+this.email.getText()+"'";
			one_field_req=1;
		}
		
		if( one_field_req==0 )
		{
			Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Invalid Information");
            //alert.setHeaderText("No Person Selected");
            alert.setContentText("At Least One Field is Required!");
            alert.showAndWait();
		}
		else
		{   query="from Person where "+query;
			System.out.println(query);
			List<Person> p=(List<Person>) StoreData.RetreivePerson(query);
			if(  p!=null  && p.size()>0)
			{ person=p.get(0); 
			  mainApp.setPerson(person);
			  mainApp.AddProfileShowPage("view/Search_Member_Result.fxml",person);
			}
			else
			{
				Alert alert = new Alert(AlertType.WARNING);
	            alert.initOwner(mainApp.getPrimaryStage());
	            alert.setTitle("Invalid Information");
	            //alert.setHeaderText("No Person Selected");
	            alert.setContentText("No Results Found Please Try Again!");
	            alert.showAndWait();
			}
			
		}
	}
	
	
	
}
