package logica;

import java.util.HashMap;

public class Tarifario {

    private final HashMap<TipoVehiculo, Double> precioTipoVehiculo;

    public Tarifario() {
        // Inicializar el HashMap
        precioTipoVehiculo = new HashMap<>();
    }

    public Tarifario(HashMap<TipoVehiculo, Double> precioTipoVehiculo) {
        this.precioTipoVehiculo = precioTipoVehiculo;
    }

    public HashMap<TipoVehiculo, Double> getPrecioTipoVehiculo() {
        return precioTipoVehiculo;
    }

    public TipoVehiculo obtenerClave(String clave) {
        if (clave != null) {
            String nombreBuscado = clave.split(" ")[0]; 
            System.out.println(nombreBuscado);
            for (TipoVehiculo tipo : precioTipoVehiculo.keySet()) {
                if (tipo.getNombre().equalsIgnoreCase(nombreBuscado)) { 
                    return tipo;
                }
            }
        }
        return null;
    }

    public Double obtenerPrecioTipoVehiculo(TipoVehiculo tipoVehiculo) {

        return precioTipoVehiculo.get(tipoVehiculo);
    }

    public void establecerPrecio(TipoVehiculo tipoVehiculo, double precio) {
        precioTipoVehiculo.put(tipoVehiculo, precio);
    }

    @Override
    public String toString() {
        return precioTipoVehiculo + "";
    }

}
