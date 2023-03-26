package lenzi.tom.ordenamiento;

import lenzi.tom.modelo.Alerta;
import lenzi.tom.modelo.TipoAlerta;

import java.util.LinkedList;
import java.util.List;

public class OrdenamientoAlertas implements Ordenamiento{
    @Override
    public List<Alerta> ordenarAlertas(List<Alerta> alertas) {
        LinkedList<Alerta> alertasOrdenadas = new LinkedList<>();

        for (Alerta a : alertas) {
            if (a.getTipo() == TipoAlerta.URGENTE) {
                alertasOrdenadas.addFirst(a);
            } else {
                alertasOrdenadas.addLast(a);
            }
        }

        return alertasOrdenadas;
    }
}
