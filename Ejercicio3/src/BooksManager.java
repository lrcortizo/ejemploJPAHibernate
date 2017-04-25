
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
 
/**
 * This program demonstrates using Hibernate framework to manage
 * a one-to-one mapping with foreign key using annotations.
 * @author www.codejava.net
 *
 */
public class BooksManager {
	
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("Book");
 
    public static void main(String[] args) {
        // loads configuration and mappings
    	EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try{
	        transaction = manager.getTransaction();
	        transaction.begin(); 
	        // creates a Book entity
	        Book newBook = new Book();
	        newBook.setTitle("Hibernate Made Easy");
	        newBook.setDescription("Simplified Data Persistence with Hibernate and JPA");
	        newBook.setPublishedDate(new Date());
	         
	        newBook.setAuthor(new Author("Cameron Wallace McKenzie", "Cameron@gmail.com"));
	         
	        // persists the book entity
	        manager.persist(newBook);
	        manager.flush();
	        Long bookId = (Long) newBook.getId();
	         
	        // gets the book entity back
	        Book book = (Book) manager.find(Book.class, bookId);
	        System.out.println("Book's Title: " + book.getTitle());
	        System.out.println("Book's Description: " + book.getTitle());
	         
	        Author author = book.getAuthor();
	        System.out.println("Author's Name: " + author.getName());
	        System.out.println("Author's Email: " + author.getEmail());
	         
	        transaction.commit();
        }catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }
        ENTITY_MANAGER_FACTORY.close();
    }
}