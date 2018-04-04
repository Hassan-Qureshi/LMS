package application;

	
import java.io.IOException;

import application.model.Book;
import application.model.Person;
import application.view.BookDialogController;
import application.view.IssueBookController;
import application.view.MainPageController;
import application.view.MemberProfileController;
import application.view.TransectionController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainApp extends Application {
	
	private ObservableList<Book> retrievedBooks = FXCollections.observableArrayList();
	
	private ObservableList<Book> issueList = FXCollections.observableArrayList();
	private Person person;
	
    private Stage primaryStage;
    private AnchorPane Main_Page;

    public MainApp(){

    }
    
    
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Library Management System");
//        showPage("view/Main_Page.fxml",1);
        login_logout("view/Login.fxml");
    }
    
    public void login_logout(String page) {
    	try {
            FXMLLoader Main_Page_loader = new FXMLLoader();
        	Main_Page_loader.setLocation(MainApp.class.getResource(page));
            Main_Page=(AnchorPane) Main_Page_loader.load();
            MainPageController controller = Main_Page_loader.getController();
            controller.setMainApp(this);
            Scene scene = new Scene(Main_Page);
        	primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            }catch(Exception e) {
            	
            }
    }
    
    
    public Object commonCodeShowPage(String page) {
    	System.out.println("common show page");
    	try {
    		
    	FXMLLoader Main_Page_loader = new FXMLLoader();
    	Main_Page_loader.setLocation(MainApp.class.getResource("view/Main_Page.fxml"));
        Main_Page=(AnchorPane) Main_Page_loader.load();
        MainPageController controller = Main_Page_loader.getController();
        controller.setMainApp(this);
        Scene scene = new Scene(Main_Page);
    	primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource(page));
        AnchorPane pageOverview=(AnchorPane) loader.load();
        pageOverview.setTranslateX(250);
        pageOverview.setTranslateY(70);
        Main_Page.getChildren().add(pageOverview);
        
        return loader.getController();
        
    	} catch (IOException e) {
            e.printStackTrace();
        	System.out.println("error while loading fxml");
        }
		return null;
    }
    
    /**
     * Shows the person overview inside the root layout.
     */
    public void showPage(String page,int root) {
    	System.out.println("show page");
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(page));
            AnchorPane pageOverview=(AnchorPane) loader.load();
            
            if(root==1) {
            	Scene scene; 
            	Main_Page = pageOverview;
            	scene = new Scene(Main_Page);
            	primaryStage.setScene(scene);
                primaryStage.setResizable(false);
                primaryStage.show();
                
                MainPageController controller = loader.getController();
                controller.setMainApp(this);
            }
            else {
            	MainPageController controller=(MainPageController) commonCodeShowPage(page);
            	 
                controller.setMainApp(this);

            }
            
            
            
             
         
        } catch (IOException e) {
            e.printStackTrace();
        	System.out.println("error while loading fxml");
        }
    }

    public void IssueBookshowPage(String page) {
        IssueBookController controller = ( IssueBookController) commonCodeShowPage(page);
		controller.setMainApp(this);
    }
    
    public void AddProfileShowPage(String page, Person p) {
        MemberProfileController controller =( MemberProfileController ) commonCodeShowPage(page);
		controller.setMainApp(this);
		if( p!=null)
		{ controller.setMember(p); }
    }
    
    public void TransectionControl(String page,Person p,int fine)
    {
    	TransectionController controller =(TransectionController) commonCodeShowPage(page);
		controller.setMainApp(this);
		
		if(p!=null && fine >=0)
		{   System.out.println("in mainApp ="+person); 
			controller.setPersonAndFine(person,fine);
		}
    }
    
    public boolean showBookDialog(Book b) {
        BookDialogController controller = (BookDialogController) commonCodeShowPage("view/Selected_Book.fxml");
		controller.setMainApp(this);
		controller.setBook(b);
		
		return true;
    }
    
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

	public ObservableList<Book> getRetrievedBooks() {
		return retrievedBooks;
	}

	public void setRetrievedBooks(ObservableList<Book> books) {
		this.retrievedBooks = books;
	}
	public void addRetrievedBook(Book book) {
		this.retrievedBooks.add( book ) ;
		
	}
	



	public void clearRetrievedBooks() {
		// TODO Auto-generated method stub
		this.retrievedBooks.clear();
	}

	public void clearIssueList() {
		// TODO Auto-generated method stub
		this.issueList.clear();
	}


	public ObservableList<Book> getIssueList() {
		return issueList;
	}



	public void setIssueList(ObservableList<Book> issueList) {
		this.issueList = issueList;
	}
    public void addBookInIssueList(Book b)
    {	
	   issueList.add(new Book(b));
	   System.out.println("addingb book in list"+b.getAuthor_name());
    }



	public Person getPerson() {
		return person;
	}
	
	
	
	public void setPerson(Person person) {
		this.person = person;
	}
}
