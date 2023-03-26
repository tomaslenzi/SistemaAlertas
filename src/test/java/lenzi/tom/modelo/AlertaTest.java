package lenzi.tom.modelo;

import org.junit.Test;
import lenzi.tom.modelo.Alerta;
import lenzi.tom.modelo.Tema;
import lenzi.tom.modelo.TipoAlerta;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class AlertaTest {

    @Test
    public void addTemaAsociadoTest(){
        Alerta a1 = new Alerta(TipoAlerta.INFORMATIVA,"I1");
        Tema t1 = new Tema("t1","tema1");

        a1.addTemaAsociado(t1);

        assertTrue(a1.getTemasAsociados().contains(t1));
    }

    @Test
    public void setFechaYhoraExpiracionTest() {
        Alerta a1 = new Alerta(TipoAlerta.URGENTE, "Alerta de prueba");
        LocalDateTime fechaYhoraExpiracion = LocalDateTime.now().plusDays(1000);
        a1.setFechaYhoraExpiracion(fechaYhoraExpiracion);
        assertEquals(fechaYhoraExpiracion, a1.getFechaYhoraExpiracion());
    }

    @Test
    public void estaExpiradaTest() {
        Alerta a1 = new Alerta(TipoAlerta.URGENTE, "Alerta de prueba");

        LocalDateTime fechaYhoraExpiracion = LocalDateTime.now().plusDays(1000);
        a1.setFechaYhoraExpiracion(fechaYhoraExpiracion);
        assertFalse(a1.estaExpirada());

        LocalDateTime fechaYhoraExpiracionVencida = LocalDateTime.now().minusDays(10);
        a1.setFechaYhoraExpiracion(fechaYhoraExpiracionVencida);
        assertTrue(a1.estaExpirada());
    }

    @Test
    public void setEstaLeidaTest() {
        Alerta a1 = new Alerta(TipoAlerta.URGENTE, "Alerta de prueba");
        assertFalse(a1.isEstaLeida());
        a1.setEstaLeida();
        assertTrue(a1.isEstaLeida());
    }

    @Test
    public void getTemasAsociadosTest() {
        Alerta a1 = new Alerta(TipoAlerta.URGENTE, "Alerta de prueba");
        Tema t1 = new Tema("t1","tema 1");
        Tema t2 = new Tema("t2","tema 2");
        a1.addTemaAsociado(t1);
        a1.addTemaAsociado(t2);
        List<Tema> temasAsociados = a1.getTemasAsociados();
        assertEquals(2, temasAsociados.size());
        assertTrue(temasAsociados.contains(t1));
        assertTrue(temasAsociados.contains(t2));
    }
}
