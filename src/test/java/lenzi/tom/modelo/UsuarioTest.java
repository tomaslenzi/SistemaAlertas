package lenzi.tom.modelo;

import org.junit.Test;
import lenzi.tom.modelo.Alerta;
import lenzi.tom.modelo.Tema;
import lenzi.tom.modelo.Usuario;
import lenzi.tom.modelo.TipoAlerta;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UsuarioTest {

    @Test
    public void agregarTemadeInteresTest(){
        Usuario u1 = new Usuario(1);
        Tema t1 = new Tema("t1","tema1");
        Tema t2 = new Tema("t2","tema2");

        u1.agregarTemaDeInteres(t1);
        u1.agregarTemaDeInteres(t2);

        assertTrue(u1.getTemasDeInteres().contains(t1));
        assertTrue(u1.getTemasDeInteres().contains(t2));


    }

    @Test
    public void recibirAlertaTest(){

        Usuario u1 = new Usuario(1);

        Alerta a1 = new Alerta(TipoAlerta.URGENTE,"U1");
        Alerta a2 = new Alerta(TipoAlerta.INFORMATIVA,"I1");

        u1.recibirAlerta(a1);
        u1.recibirAlerta(a2);

        assertTrue(u1.getAlertas().contains(a1));
        assertTrue(u1.getAlertas().contains(a2));

    }

    public void eliminarAlertaTest() {
        Usuario u1 = new Usuario(1);
        Alerta a1 = new Alerta(TipoAlerta.URGENTE,"U1");
        Alerta a2 = new Alerta(TipoAlerta.INFORMATIVA,"I1");

        u1.recibirAlerta(a1);
        u1.recibirAlerta(a2);

        u1.eliminarAlerta(a1);

        assertFalse(u1.getAlertas().contains(a1));
        assertTrue(u1.getAlertas().contains(a2));
    }

    @Test
    public void marcarAlertaComoLeidaTest(){
        Usuario u1 = new Usuario(1);
        Alerta a1 = new Alerta(TipoAlerta.URGENTE,"U1");
        Alerta a2 = new Alerta(TipoAlerta.INFORMATIVA,"I1");


        u1.recibirAlerta(a1);
        u1.recibirAlerta(a2);

        u1.marcarAlertaComoLeida(a1);

        assertTrue(a1.isEstaLeida());
        assertFalse(a2.isEstaLeida());

    }
}
