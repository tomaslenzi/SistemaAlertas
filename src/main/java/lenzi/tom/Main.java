package lenzi.tom;

import lenzi.tom.modelo.Alerta;
import lenzi.tom.servicio.SistemaAlertas;
import lenzi.tom.modelo.Tema;
import lenzi.tom.modelo.Usuario;
import lenzi.tom.modelo.TipoAlerta;

public class Main {
    public static void main(String[] args) {
        SistemaAlertas s1 = new SistemaAlertas();

        Tema t1 = new Tema("t1", "tema1");
        Tema t2 = new Tema("t2", "tema2");
//I1,I2,U1,I3,U2,I4 se ordenarán de la siguiente forma --> U2,U1,I1,I2,I3,I4

        Alerta a1 = new Alerta(TipoAlerta.INFORMATIVA, "I1");
        Alerta a2 = new Alerta(TipoAlerta.INFORMATIVA, "I2");
        Alerta a3 = new Alerta(TipoAlerta.URGENTE, "U1");
        Alerta a4 = new Alerta(TipoAlerta.INFORMATIVA, "I3");
        Alerta a5 = new Alerta(TipoAlerta.URGENTE, "U2");
        Alerta a6 = new Alerta(TipoAlerta.INFORMATIVA, "I4");

        Usuario u1 = new Usuario(1);

        s1.registrarTema(t1);
        s1.registrarTema(t2);
        s1.registrarUsuario(u1);

        a1.addTemaAsociado(t1);
        a2.addTemaAsociado(t1);
        a3.addTemaAsociado(t1);
        a4.addTemaAsociado(t1);
        a5.addTemaAsociado(t1);
        a6.addTemaAsociado(t1);


        u1.agregarTemaDeInteres(t1);
        u1.agregarTemaDeInteres(t2);

        s1.enviarAlertaTemaUsuarioEspecifico(a1, 1);
        s1.enviarAlertaTemaUsuarioEspecifico(a2, 1);
        s1.enviarAlertaTemaUsuarioEspecifico(a3, 1);
        s1.enviarAlertaTemaUsuarioEspecifico(a4, 1);
        s1.enviarAlertaTemaUsuarioEspecifico(a5, 1);
        s1.enviarAlertaTemaUsuarioEspecifico(a6, 1);

        s1.enviarAlertaTemaTodosUsuarios(a1);

        u1.marcarAlertaComoLeida(a1);
        u1.marcarAlertaComoLeida(a5);



        System.out.println("alertas no leidas: " + s1.obtenerAlertasNoLeidas(u1));
        System.out.println("alertas por tema: " + s1.obtenerAlertasPorTema(t1));



    }
}