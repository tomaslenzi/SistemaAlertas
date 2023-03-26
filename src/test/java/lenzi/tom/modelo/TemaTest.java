package lenzi.tom.modelo;

import org.junit.Assert;
import org.junit.Test;
import lenzi.tom.modelo.Alerta;
import lenzi.tom.modelo.Tema;
import lenzi.tom.modelo.TipoAlerta;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TemaTest {

    @Test
    public void addAlertasAsociadasTest(){
        Tema t = new Tema("Tema 1", "Tema 1");
        Alerta a1 = new Alerta(TipoAlerta.URGENTE, "U1");
        Alerta a2 = new Alerta(TipoAlerta.INFORMATIVA, "I1");
        t.addAlertasAsociadas(a1);
        t.addAlertasAsociadas(a2);
        List<Alerta> alertasAsociadas = t.getAlertasAsociadas();
        assertEquals(2, alertasAsociadas.size());
        assertTrue(alertasAsociadas.contains(a1));
        assertTrue(alertasAsociadas.contains(a2));
    }

    @Test
    public void getAlertasAsociadasTest() {
        Tema t = new Tema("t1", "Tema 1");
        Alerta a1 = new Alerta(TipoAlerta.URGENTE, "Alerta 1");
        Alerta a2 = new Alerta(TipoAlerta.INFORMATIVA, "Alerta 2");
        t.addAlertasAsociadas(a1);
        t.addAlertasAsociadas(a2);
        List<Alerta> alertasAsociadas = t.getAlertasAsociadas();
        Assert.assertEquals(2, alertasAsociadas.size());
        Assert.assertTrue(alertasAsociadas.contains(a1));
        Assert.assertTrue(alertasAsociadas.contains(a2));
    }

    @Test
    public void getNombreTemaTest() {
        Tema t = new Tema("Tema", "Tema 1");
        assertEquals("Tema", t.getNombre());
    }
    @Test
    public void setNombreTest() {
        Tema t = new Tema("Tema", "Tema 1");
        assertEquals("Tema", t.getNombre());
    }

    @Test
    public void getDescripcionTest() {
        Tema t = new Tema("Tema 1", "Descripción del tema 1");
        assertEquals("Descripción del tema 1", t.getDescripcion());
    }

    @Test
    public void setDescripcionTest() {
        Tema t = new Tema("Tema 1", "Descripción del tema 1");
        t.setDescripcion("Nueva descripción");
        assertEquals("Nueva descripción", t.getDescripcion());
    }
}
