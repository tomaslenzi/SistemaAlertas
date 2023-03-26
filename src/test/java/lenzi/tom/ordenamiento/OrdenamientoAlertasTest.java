package lenzi.tom.ordenamiento;

import lenzi.tom.modelo.Alerta;
import lenzi.tom.modelo.TipoAlerta;
import lenzi.tom.servicio.SistemaAlertas;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrdenamientoAlertasTest {

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

        Ordenamiento ordenamientoAlertas = new OrdenamientoAlertas();


        assertEquals(4, ordenamientoAlertas.ordenarAlertas(alertas).size());
        assertEquals(a3, ordenamientoAlertas.ordenarAlertas(alertas).get(0));
        assertEquals(a1, ordenamientoAlertas.ordenarAlertas(alertas).get(1));
        assertEquals(a2, ordenamientoAlertas.ordenarAlertas(alertas).get(2));
        assertEquals(a4, ordenamientoAlertas.ordenarAlertas(alertas).get(3));

    }
}
