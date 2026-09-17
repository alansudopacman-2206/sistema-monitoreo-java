public class main {
        public static void main(String[] args)
        {
            System.out.println("Sistema de monitoreo");
        }
}

enum EstadoTanque{
    llenado, vaciando, detenido;
}
public final class Tanque {
    private String Id;
    private double capacidadMaxima;
    private double nivelActual;
    private EstadoTanque estado;
    private Tanque() {
    }

    static Tanque createTanque() {
        return new Tanque();
    }
}

class SensorNivel {
    private String id;
    private double lastLEcture;
    private Tanque tanqueAsociado;

}