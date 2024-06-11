package logica;

public class Electrico extends Etiqueta {

    public Electrico() {
        super("Electrico");
    }

    @Override
	public double calcularMulta(Estadia estadia) {
                  return 0.5; 
    }
}
