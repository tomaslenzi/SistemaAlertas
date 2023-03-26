package lenzi.tom.servicio;

import lenzi.tom.ordenamiento.Ordenamiento;
import lenzi.tom.ordenamiento.OrdenamientoAlertas;
import org.junit.Test;
import lenzi.tom.modelo.Alerta;
import lenzi.tom.servicio.SistemaAlertas;
import lenzi.tom.modelo.Tema;
import lenzi.tom.modelo.Usuario;
import lenzi.tom.modelo.TipoAlerta;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SistemaAlertasTest {

    @Test
    public void registrarUsuarioTest(){
        Ordenamiento OrdenamientoAlertas = new OrdenamientoAlertas();
        SistemaAlertas sa = new SistemaAlertas(OrdenamientoAlertas);
        Usuario usuario = new Usuario(1);

        sa.registrarUsuario(usuario);

        assertEquals(1, sa.getUsuarios().size());
        assertEquals(usuario, sa.getUsuarios().get(0));
    }

    @Test
    public void registrarTemaTest(){
        Ordenamiento OrdenamientoAlertas = new OrdenamientoAlertas();
        SistemaAlertas sistema = new SistemaAlertas(OrdenamientoAlertas);
        Tema tema = new Tema("t1", "tema test");
        sistema.registrarTema(tema);

        assertEquals(1, sistema.getTemas().size());
        assertEquals(tema, sistema.getTemas().get(0));


    }

    @Test
    public void enviarAlertaTemaTodosUsuariosTest(){
        Ordenamiento OrdenamientoAlertas = new OrdenamientoAlertas();
        SistemaAlertas sa = new SistemaAlertas(OrdenamientoAlertas);
        Usuario u1 = new Usuario(1);
        Usuario u2 = new Usuario(2);
        Tema t1 = new Tema("t1","tema test 1");
        Tema t2 = new Tema("t2", "tema test 2");
        Alerta a1 = new Alerta(TipoAlerta.INFORMATIVA,"I1");
        Alerta a2 = new Alerta(TipoAlerta.URGENTE, "U2");

        a1.addTemaAsociado(t1);
        a2.addTemaAsociado(t2);

        sa.registrarUsuario(u1);
        sa.registrarUsuario(u2);
        u1.agregarTemaDeInteres(t1);
        u1.agregarTemaDeInteres(t2);
        u2.agregarTemaDeInteres(t2);

        sa.enviarAlertaTemaTodosUsuarios(a1);
        sa.enviarAlertaTemaTodosUsuarios(a2);

        assertTrue(u1.getAlertas().contains(a1));
        assertTrue(u1.getAlertas().contains(a2));
        assertTrue(u2.getAlertas().contains(a2));
        assertFalse(u2.getAlertas().contains(a1));
    }

    @Test
    public void enviarAlertaTemaUsuarioEspecificoTest(){
        Ordenamiento OrdenamientoAlertas = new OrdenamientoAlertas();
        SistemaAlertas sa = new SistemaAlertas(OrdenamientoAlertas);
        Usuario u1 = new Usuario(1);
        Usuario u2 = new Usuario(2);

        Tema t1 = new Tema("t1","tema test 1");
        Tema t2 = new Tema("t2", "tema test 2");
        Alerta a1 = new Alerta(TipoAlerta.INFORMATIVA,"I1");
        Alerta a2 = new Alerta(TipoAlerta.URGENTE, "U2");

        a1.addTemaAsociado(t1);
        a2.addTemaAsociado(t2);

        sa.registrarUsuario(u1);
        sa.registrarUsuario(u2);
        u1.agregarTemaDeInteres(t1);
        u1.agregarTemaDeInteres(t2);
        u2.agregarTemaDeInteres(t2);

        sa.enviarAlertaTemaUsuarioEspecifico(a1,1);
        assertEquals(1,u1.getAlertas().size());
        assertEquals(0,u2.getAlertas().size());

        sa.enviarAlertaTemaUsuarioEspecifico(a2,2);
        assertEquals(1,u1.getAlertas().size());
        assertEquals(1,u2.getAlertas().size());

    }

    @Test
    public void obtenerAlertasNoLeidasUsuarioTest(){
        Ordenamiento OrdenamientoAlertas = new OrdenamientoAlertas();
        SistemaAlertas sa = new SistemaAlertas(OrdenamientoAlertas);
        Usuario u1 = new Usuario(1);

        Tema t1 = new Tema("t1","tema test 1");
        Tema t2 = new Tema("t2", "tema test 2");
        Alerta a1 = new Alerta(TipoAlerta.INFORMATIVA,"I1");
        Alerta a2 = new Alerta(TipoAlerta.URGENTE, "U2");

        a1.addTemaAsociado(t1);
        a2.addTemaAsociado(t2);

        sa.registrarUsuario(u1);

        u1.agregarTemaDeInteres(t1);
        u1.agregarTemaDeInteres(t2);


        u1.recibirAlerta(a1);
        u1.recibirAlerta(a2);

        List<Alerta> alertasNoLeidas = sa.obtenerAlertasNoLeidas(u1);

        assertEquals(2, alertasNoLeidas.size());

        assertEquals(a1, alertasNoLeidas.get(1));
        assertEquals(a2, alertasNoLeidas.get(0));
    }

    @Test
    public void obtenerAlertasPorTemaTest(){
        Ordenamiento OrdenamientoAlertas = new OrdenamientoAlertas();
        SistemaAlertas sa = new SistemaAlertas(OrdenamientoAlertas);

        Tema t1 = new Tema("t1", "tema 1");
        Tema t2 = new Tema("t1", "tema 1");

        Alerta a1 = new Alerta(TipoAlerta.URGENTE, "U2");
        Alerta a2 = new Alerta(TipoAlerta.INFORMATIVA,"I1");

        a2.setFechaYhoraExpiracion(LocalDateTime.now().minusDays(1));

        t1.addAlertasAsociadas(a1);

        List<Alerta> alertasEsperadasTema1 = new ArrayList<>();
        alertasEsperadasTema1.add(a1);

        List<Alerta> alertasObtenidasTema1 = sa.obtenerAlertasPorTema(t1);

        assertEquals(alertasEsperadasTema1, alertasObtenidasTema1);

        List<Alerta> alertasEsperadasTema2 = new ArrayList<>();

        List<Alerta> alertasObtenidasTema2 = sa.obtenerAlertasPorTema(t2);

        assertEquals(alertasEsperadasTema2, alertasObtenidasTema2);

    }



}
