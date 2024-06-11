package logica;

public class Discapacitado extends Etiqueta {

    public Discapacitado() {
        super("Discapacitado");
    }

	 @Override
    public double calcularMulta(Estadia estadia) {
        return 250; 
    }
}
