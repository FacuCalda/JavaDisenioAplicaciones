package logica;

import exceptions.VehiculoException;
import java.util.Objects;

public abstract class TipoVehiculo {

    private final String nombre;

    public TipoVehiculo(String nombre) {
        this.nombre = nombre;
    }

    public void validar(Vehiculo vehiculo) throws VehiculoException {
        if (nombre == null || nombre.isEmpty()) {
            throw new VehiculoException("tipo vehiculo invaldo");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public double MiTarifa(Tarifario tarifa) {

        return tarifa.obtenerPrecioTipoVehiculo(this);

    }

    @Override
    public String toString() {
        return nombre + ' ';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoVehiculo other = (TipoVehiculo) obj;
        return Objects.equals(this.nombre, other.nombre);
    }

}
