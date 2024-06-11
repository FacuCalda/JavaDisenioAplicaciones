package logica;

public class Empleado extends Etiqueta {

    public Empleado() {
        super("Empleado");
    }

	  @Override
    public double calcularMulta(Estadia estadia) {
        double duracionEnUT = estadia.calcularDuracionEnSegundos();
        double montoMulta = (double)duracionEnUT / 10; // $1 por cada 10 unidades de tiempo (UT) de estadía
        return montoMulta;
    }

}
