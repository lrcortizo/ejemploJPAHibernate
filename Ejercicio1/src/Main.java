import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    // Create an EntityManagerFactory when you start the application.
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("Jugador");

    public static void main(String[] args) {
        // Imprimir todos los jugadores
        List<Jugador> equipo = readAll();
        if (equipo != null) {
        	System.out.println("Datos de todos los jugadores del equipo:");
            for (Jugador jugador : equipo) {
                System.out.println(jugador);
            }
        }
        ENTITY_MANAGER_FACTORY.close();
    }

    public static void create(int id, String nombre, String apellidos, String sexo, int edad) {
        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();
            // Create a new Student object
            Jugador jugador = new Jugador();
            jugador.setId(id);
            jugador.setNombre(nombre);
            jugador.setApellidos(apellidos);
            jugador.setSexo(sexo);
            jugador.setEdad(edad);
            // Save the student object
            manager.persist(jugador);
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

    public static List<Jugador> readAll() {
        List<Jugador> equipo = null;
        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();
            // Get a List of Students
            equipo = manager.createQuery("SELECT j FROM Jugador j",
                    Jugador.class).getResultList();
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
        return equipo;
    }

    public static void delete(int id) {
        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get the Student object
            Jugador jugador = manager.find(Jugador.class, id);
            // Delete the student
            manager.remove(jugador);
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

    public static void upate(int id, String nombre, String apellidos, String sexo, int edad) {
        // Create an EntityManager
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();
            // Get the Student object
            Jugador jugador = manager.find(Jugador.class, id);
            // Change the values
            jugador.setNombre(nombre);
            jugador.setApellidos(apellidos);
            jugador.setSexo(sexo);
            jugador.setEdad(edad);
            // Update the student
            manager.persist(jugador);
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
