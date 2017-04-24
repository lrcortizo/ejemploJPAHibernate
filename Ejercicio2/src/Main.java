
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    // Create an EntityManagerFactory when you start the application.
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("Trabajador");

    public static void main(String[] args) {
        riseSalary(20);
        ENTITY_MANAGER_FACTORY.close();
    }
   
    public static void riseSalary(int p){
    	// Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        List<Integer> trabajadores = null;
        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();
            trabajadores = manager.createQuery("SELECT id FROM Trabajador t",
            		Integer.class).getResultList();
            // Get the Student object
            for(int i = 0;i < trabajadores.size()-1;i++){
            	Trabajador trabajador = manager.find(Trabajador.class, trabajadores.get(i));
                // Change the values
                trabajador.setSueldo(trabajador.getSueldo()+trabajador.getSueldo()*p/100);
                // Update the student
                manager.persist(trabajador);
            }
            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
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
    }
}
