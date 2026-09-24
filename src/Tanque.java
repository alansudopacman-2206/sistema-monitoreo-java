public class Tanque
{
    private String id;
    private double capacidadMaxima;
    private double nivelActual;
    private EstadoTanque status;

    public Tanque(String id, double capacidadMaxima, double nivelInicial) {
        this.id = id;
        this.capacidadMaxima = capacidadMaxima;

        if (nivelInicial < 0) {
            this.nivelActual = 0;
        } else if (nivelInicial > capacidadMaxima) {
            this.nivelActual = capacidadMaxima;
        } else {
            this.nivelActual = nivelInicial;
        }

        this.status = EstadoTanque.DETENIDO;
    }
    public void llenar(double cantidad) {
        if (cantidad <= 0) {
            return;
        }

        this.nivelActual = this.nivelActual + cantidad;

        if (this.nivelActual > this.capacidadMaxima) {
            this.nivelActual = this.capacidadMaxima;
        }

        this.status = EstadoTanque.LLENANDOSE;
    }

    public void vaciar(double cantidad) {
        if (cantidad <= 0) {
            return;
        }

        this.nivelActual = this.nivelActual - cantidad;

        if (this.nivelActual < 0) {
            this.nivelActual = 0;
        }

        this.status = EstadoTanque.VACIANDOSE;
    }
}
