package lenzi.tom.modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id;
    private List<Tema> temasDeInteres;
    private List<Alerta> alertas;

    public Usuario(int id) {
        this.id = id;
        this.temasDeInteres = new ArrayList<>();
        this.alertas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void agregarTemaDeInteres(Tema t) {
        temasDeInteres.add(t);
    }

    public void recibirAlerta(Alerta a) {
        alertas.add(a);
    }

    public List<Tema> getTemasDeInteres() {
        List<Tema> copia = new ArrayList<>();
        for (Tema t : temasDeInteres) {
            copia.add(t);
        }
        return copia;
    }

    public List<Alerta> getAlertas() {
        List<Alerta> copia = new ArrayList<>();
        for (Alerta a : alertas) {
            copia.add(a);
        }
        return copia;
    }

    public void eliminarAlerta(Alerta a) {
        alertas.remove(a);
    }

    public void marcarAlertaComoLeida(Alerta a) {
        if (alertas.contains(a)) {
            a.setEstaLeida();
        }
    }

}
