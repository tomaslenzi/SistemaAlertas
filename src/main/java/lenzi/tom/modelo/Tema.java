package lenzi.tom.modelo;

import java.util.ArrayList;
import java.util.List;

public class Tema {

    private String nombre;
    private String descripcion;

    private List<Alerta> alertasAsociadas;

    public Tema(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.alertasAsociadas = new ArrayList<>();

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void addAlertasAsociadas(Alerta a) {
        this.alertasAsociadas.add(a);
    }

    public List<Alerta> getAlertasAsociadas() {
        List<Alerta> copia = new ArrayList<>();
        for (Alerta a : alertasAsociadas) {
            copia.add(a);
        }
        return copia;
    }

}
