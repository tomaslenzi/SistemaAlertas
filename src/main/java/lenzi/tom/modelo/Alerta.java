package lenzi.tom.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Alerta {
    private LocalDateTime fechaYhoraExpiracion;

    private String nombre;
    private boolean estaLeida;

    private List<Tema> temasAsociados;

    private TipoAlerta tipo;

    public Alerta(TipoAlerta tipo, String nombre) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.fechaYhoraExpiracion = null;
        this.estaLeida = false;
        this.temasAsociados = new ArrayList<>();
    }

    public void addTemaAsociado(Tema t) {
        temasAsociados.add(t);
        t.addAlertasAsociadas(this);

    }

    public void setFechaYhoraExpiracion(LocalDateTime fechaYhoraExpiracion) {
        this.fechaYhoraExpiracion = fechaYhoraExpiracion;
    }

    public List<Tema> getTemasAsociados() {
        return new ArrayList<>(temasAsociados);
    }

    public LocalDateTime getFechaYhoraExpiracion() {
        return fechaYhoraExpiracion;
    }

    public TipoAlerta getTipo() {
        return tipo;
    }

    public void setEstaLeida() {

        this.estaLeida = true;
    }

    public boolean estaExpirada() {
        return ((this.fechaYhoraExpiracion != null) && (this.fechaYhoraExpiracion.isBefore(LocalDateTime.now())));

    }

    public boolean isEstaLeida() {
        return estaLeida;
    }

    @Override
    public String toString() {
        return "Alerta{" +
                "nombre='" + nombre + '\'' +
                ", tipo=" + tipo +
                '}';
    }
}
