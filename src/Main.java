public class Main {
    public static void main(String[] args) {
        // Hipotéticamente se crea un tanque de Vt=1000L y Vi=250L, capacidad 1000L y 250L de nivel actuL
        Tanque tanque1 = new Tanque("TK-001-32RTK", 1000.0, 250.0);

        // Se asocia un sensor al tanque creado
        Sensor sensor1 = new Sensor("SN-01-32RTK", tanque1);

        System.out.println("INICIO");
        System.out.println("Lectura inicial: " + sensor1.realizarLectura() + " L");
        System.out.println("¿Lectura valida?: " + sensor1.esLecturaValida());
        System.out.println("\nInfo:");
        System.out.println(tanque1.getGralInfo());

        System.out.println("\nLLENANDO TANQUE (400 L)");
        tanque1.llenar(400.0);
        sensor1.realizarLectura();
        System.out.println("Ultima lectura del sensor: " + sensor1.Getlastlectura() + " L");
        System.out.println("Estado actual: " + tanque1.getStatus());

        System.out.println("\nVACIANDO (150L)");
        tanque1.vaciar(150.0);
        sensor1.realizarLectura();
        System.out.println("ultima lectura del sensor: " + sensor1.Getlastlectura() + " L");
        System.out.println("Estado actual: " + tanque1.getStatus());

        System.out.println("\nDETENIDO");
        tanque1.detener();
        System.out.println("Estado actual: " + tanque1.getStatus());
        System.out.println("\n--- Reporte Final ---");
        System.out.println(tanque1.getGralInfo());
    }
}