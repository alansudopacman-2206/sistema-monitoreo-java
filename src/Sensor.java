public class Sensor {
    private String idsensor;
    private double lastlectura;
    Tanque tanqueAsociado;

    public Sensor(String id, Tanque tanqueAsociado)
    {
        this.idsensor = id;
        this.tanqueAsociado = tanqueAsociado;
        this.lastlectura=0.0;

    }
    public double realizarLectura()
    {
        if(this.tanqueAsociado != null) {
            this.lastlectura = tanqueAsociado.getNivelActual();
        }
        return this.lastlectura;
    }
    public boolean esLecturaValida()
    {
       if(this.tanqueAsociado == null)
       {
           return false;
       }
       return this.lastlectura >= 0 && this.lastlectura <= this.tanqueAsociado.getCapacidadMaxima();
    }
    public String GetID()
    {
        return this.idsensor;
    }
    public double Getlastlectura()
    {
        return this.lastlectura;
    }
    public Tanque GeTtanqueAsociado()
    {
        return this.tanqueAsociado;
    }
}
// el enum no funcionó
/*
public enum EstadoTanque
{
    VACIANDOSE, LLENANDOSE, DETENIDO
}*/