import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Jugador")
public class Jugador implements Serializable {
    @Id
    @Column(name = "id", unique = true)
    private int id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "apellidos", nullable = false)
    private String apellidos;
    @Column(name = "sexo", nullable = false)
    private String sexo;
    @Column(name = "edad", nullable = false)
    private int edad;
    
    //Id
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    //Nombre
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    //Apellidos
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    //Sexo
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    //Edad
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    @Override
    public String toString() {
        return id + ", " + nombre + ", " + apellidos + ", " + sexo + ", " + edad;
    }
}