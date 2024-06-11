package logica;

public abstract class Etiqueta {

	private final String nombre;

    public Etiqueta(String nombre) {
        this.nombre = nombre;
    }

	 public abstract double calcularMulta(Estadia estadia);
         public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Etiqueta{" + "nombre=" + nombre + '}';
    }

}
