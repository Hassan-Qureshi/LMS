package DBController;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import application.model.Book;
import application.model.Person;
import application.model.Transection;
public class StoreData {  
	
//public static void main(String[] args) {  
//	StoreObject();
//    RetreiveValues();
//    DeleteValues();
//
//      
//}  
public static Object StoreObject(Object obj){

	 SessionFactory factory=HibernateUtil.getSessionFactory();
//	  session = HibernateUtil.getSessionFactory().getCurrentSession();
	  Session session=factory.openSession();  
  
    //creating transaction object  
    Transaction t=session.beginTransaction();  
          
    //session.persist(obj);//persisting the object  
    session.saveOrUpdate(obj);
    
    t.commit();//transaction is committed  
    if ( session.isOpen() ) session.close();
     
    System.out.println("successfully saved"); 
    return obj;
}
public static void DeleteValues(){
	// SessionFactory factory=HibernateUtil.getSessionFactory();
//	  session = HibernateUtil.getSessionFactory().getCurrentSession();
	 // Session session=factory.openSession();  
 
   //creating transaction object  
 
	    //Transaction t=session.beginTransaction();  
	    
	    //Query query = session.createQuery("from Employee");
		// query.list() returns objects, cast to List<Location>
//		List<Employee> list = (List<Employee>)query.list( );
//		
//
//		for(Employee l : list ) 
//		session.delete(l);
//		t.commit();
//		if ( session.isOpen() ) session.close();

	    System.out.println("successfully Deleted");
 }


public static void deleteBook(Book b)
{		SessionFactory factory=HibernateUtil.getSessionFactory();
		Session session=factory.openSession();
		Transaction t=session.beginTransaction();
		Book db=(Book) session.get(Book.class, b.getId());
		session.delete(db);
		t.commit();
		if ( session.isOpen() ) session.close();
		System.out.println("successfully Deleted");
}


public static void deletePerson(Person p)
{		SessionFactory factory=HibernateUtil.getSessionFactory();
		Session session=factory.openSession();
		Transaction t=session.beginTransaction();
		Person db=(Person) session.get(Person.class, p.getId());
		session.delete(db);
		t.commit();
		if ( session.isOpen() ) session.close();
		System.out.println("successfully Deleted");
}


@SuppressWarnings("unchecked")
public static List<Book> RetreiveBooks(String q){
	 SessionFactory factory=HibernateUtil.getSessionFactory();
//	  session = HibernateUtil.getSessionFactory().getCurrentSession();
	  Session session=factory.openSession();  

  //creating transaction object  

	    Transaction t=session.beginTransaction();  
	    
	    Query query = session.createQuery(q);
   	    //query.list() returns objects, cast to List<Location>
		List<Book> list = (List<Book>)query.list( );
		t.commit();
//
//		for(Employee l : list ) System.out.printf("%d %s %s\n", 
//			l.getId(), l.getFirstName(), l.getLastName() );
//
//		
		if ( session.isOpen() ) session.close();

	    System.out.println("successfully Retrived");
	    return list;
}



public static List<Person> RetreivePerson(String q){
	 SessionFactory factory=HibernateUtil.getSessionFactory();
//	  session = HibernateUtil.getSessionFactory().getCurrentSession();
	  Session session=factory.openSession();  

 //creating transaction object  

	    Transaction t=session.beginTransaction();  
	    
	    Query query = session.createQuery(q);
  	    //query.list() returns objects, cast to List<Location>
		@SuppressWarnings("unchecked")
		List<Person> list = (List<Person>)query.list( );
		t.commit();
//
//		for(Employee l : list ) System.out.printf("%d %s %s\n", 
//			l.getId(), l.getFirstName(), l.getLastName() );
//
//		
		if ( session.isOpen() ) session.close();

	    System.out.println("successfully Retrived");
	    return list;
}

public static List<Transection> RetreiveTransections(String q)
{
	SessionFactory factory=HibernateUtil.getSessionFactory();
//	  session = HibernateUtil.getSessionFactory().getCurrentSession();
	  Session session=factory.openSession();  

//creating transaction object  

	    Transaction t=session.beginTransaction();  
	    
	    Query query = session.createQuery(q);
	    //query.list() returns objects, cast to List<Location>
		@SuppressWarnings("unchecked")
		List<Transection> list = (List<Transection>)query.list( );
		t.commit();
//
//		for(Employee l : list ) System.out.printf("%d %s %s\n", 
//			l.getId(), l.getFirstName(), l.getLastName() );
//
//		
		if ( session.isOpen() ) session.close();

	    System.out.println("successfully Retrived");
	    return list;
}


}  