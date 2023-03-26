package lenzi.tom.servicio;

import lenzi.tom.modelo.Alerta;
import lenzi.tom.modelo.Tema;
import lenzi.tom.modelo.TipoAlerta;
import lenzi.tom.modelo.Usuario;
import lenzi.tom.ordenamiento.Ordenamiento;

import java.util.*;

public class SistemaAlertas {

    private final Map<Integer, Usuario> usuarios;
    private final List<Tema> temas;

    private Ordenamiento ordenamientoAlertas;
    public SistemaAlertas(Ordenamiento ordenamiento) {
        this.temas = new ArrayList<>();
        this.usuarios = new HashMap<>();
        this.ordenamientoAlertas = ordenamiento;
    }

    public void registrarUsuario(Usuario u) {
        usuarios.put(u.getId(), u);
    }

    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios.values());
    }

    public void registrarTema(Tema t) {
        temas.add(t);
    }

    public List<Tema> getTemas() {
        List<Tema> copia = new ArrayList<>();
        for (Tema t : temas) {
            copia.add(t);
        }
        return copia;
    }

    public void enviarAlertaTemaTodosUsuarios(Alerta a) {
        if (!a.estaExpirada()) {
            for (Usuario u : usuarios.values()) {
                if (u.getTemasDeInteres().containsAll(a.getTemasAsociados()))
                    u.recibirAlerta(a);
            }
        }

    }

    public void enviarAlertaTemaUsuarioEspecifico(Alerta a, int id) {
        if (!a.estaExpirada()) {
            Usuario u = usuarios.get(id);
            if ((u.getId() == id) && (u.getTemasDeInteres().containsAll(a.getTemasAsociados()))) {
                u.recibirAlerta(a);

            }
        }

    }

    public List<Alerta> obtenerAlertasNoLeidas(Usuario u) {
        List<Alerta> alertasNoLeidas = new ArrayList<>();
        for (Alerta a : u.getAlertas()) {
            if ((!a.estaExpirada()) && (!a.isEstaLeida())) {
                alertasNoLeidas.add(a);
            }
        }


        return ordenamientoAlertas.ordenarAlertas(alertasNoLeidas);
    }

    public List<Alerta> obtenerAlertasPorTema(Tema t) {
        List<Alerta> alertasPorTema = new ArrayList<>();

        for (Alerta a : t.getAlertasAsociadas()) {
            if (!a.estaExpirada()) {
                alertasPorTema.add(a);
            }

        }

        return ordenamientoAlertas.ordenarAlertas(alertasPorTema);
    }

}
