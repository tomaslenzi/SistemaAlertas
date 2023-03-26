package lenzi.tom.servicio;

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
        SistemaAlertas sistema = new SistemaAlertas();
        Usuario usuario = new Usuario(1);

        sistema.registrarUsuario(usuario);

        assertEquals(1, sistema.getUsuarios().size());
        assertEquals(usuario, sistema.getUsuarios().get(0));
    }

    @Test
    public void registrarTemaTest(){
        SistemaAlertas sistema = new SistemaAlertas();
        Tema tema = new Tema("t1", "tema test");
        sistema.registrarTema(tema);

        assertEquals(1, sistema.getTemas().size());
        assertEquals(tema, sistema.getTemas().get(0));


    }

    @Test
    public void enviarAlertaTemaTodosUsuariosTest(){
        SistemaAlertas sa = new SistemaAlertas();
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

        SistemaAlertas sa = new SistemaAlertas();
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
        SistemaAlertas sa = new SistemaAlertas();
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

        SistemaAlertas sa = new SistemaAlertas();

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

    @Test
    public void ordenarAlertasTest(){
        Alerta a1 = new Alerta(TipoAlerta.URGENTE, "U1");
        Alerta a2 = new Alerta(TipoAlerta.INFORMATIVA,"I1");
        Alerta a3 = new Alerta(TipoAlerta.URGENTE, "U2");
        Alerta a4 = new Alerta(TipoAlerta.INFORMATIVA,"I2");

        List<Alerta> alertas = new ArrayList<>();
        alertas.add(a1);
        alertas.add(a2);
        alertas.add(a3);
        alertas.add(a4);

        SistemaAlertas sistema = new SistemaAlertas();
        List<Alerta> alertasOrdenadas = sistema.ordenarAlertas(alertas);

        assertEquals(4, alertasOrdenadas.size());
        assertEquals(a3, alertasOrdenadas.get(0));
        assertEquals(a1, alertasOrdenadas.get(1));
        assertEquals(a2, alertasOrdenadas.get(2));
        assertEquals(a4, alertasOrdenadas.get(3));

    }

}
