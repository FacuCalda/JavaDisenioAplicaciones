package logica;

import exceptions.CocheraException;
import exceptions.ParkingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Parking {

    private String nombre;
    private String direccion;
    private ArrayList<Cochera> cocheras = new ArrayList<>();
    ;
        private Tarifario tarifa;
    private double factordemanda;
    private String estado = "estable";

    public Parking(String nombre, String direccion, Tarifario tarifa) {
        this.nombre = nombre;
        this.direccion = direccion;

        this.tarifa = tarifa;
        this.factordemanda = 1;
    }

    public double getFactorDemanda() {
        return factordemanda;
    }

    public void setFactorDemanda(double factordemanda) {
        this.factordemanda = factordemanda;
    }

    public String getEstado() {
        return estado;
    }

    public Tarifario getTarifa() {
        return tarifa;
    }

    public HashMap<TipoVehiculo, Double> getTarifaHashmap() {
        return tarifa.getPrecioTipoVehiculo();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<Cochera> getCocheras() {
        return cocheras;
    }

    public void setCocheras(ArrayList<Cochera> cocheras) {
        this.cocheras = cocheras;
    }

    public int capacidad() {
        return this.cocheras.size();
    }

    public void actualizarFactorDemanda(Integer egresosRecientes, Integer ingresosRecientes, int ocupadas) {
        int capacidad = capacidad();
        double ocupacion = (double) ocupadas / capacidad;

        if (Math.abs(ingresosRecientes - egresosRecientes) <= 0.1 * capacidad) {
            // Tendencia estable
            this.setFactorDemanda(Math.max(0.25, this.getFactorDemanda() - 0.01));
            estado = "Estable";
        } else if (ingresosRecientes - egresosRecientes > 0.1 * capacidad) {
            // Tendencia positiva
            if (ocupacion < 0.33) {
                this.setFactorDemanda(Math.min(10, this.getFactorDemanda() + 0.05));
            } else if (ocupacion < 0.66) {
                this.setFactorDemanda(Math.min(10, this.getFactorDemanda() + 0.1));
            } else {
                this.setFactorDemanda(Math.min(10, this.getFactorDemanda() + 0.15));
            }
            estado = "Positivo";
        } else if (egresosRecientes - ingresosRecientes > 0.1 * capacidad) {
            // Tendencia negativa
            if (this.getFactorDemanda() > 1) {
                this.setFactorDemanda(1);
                estado = "Negativa";
            } else {
                this.setFactorDemanda(Math.max(0.25, this.getFactorDemanda() - 0.05));
                estado = "Negativa";
            }
        }
    }

    public void validar() throws ParkingException {
        validarString();

    }

    private void validarString() throws ParkingException {
        if (nombre == null || nombre.isEmpty() || direccion == null || direccion.isEmpty()) {
            throw new ParkingException("");
        }
    }

    private void validarNoRepetidos(Cochera cochera) throws CocheraException {
        for (Cochera c : cocheras) {
            if (c.getCodigo().equals(cochera.getCodigo())) {
                throw new CocheraException("Cochera ya existe");
            }
        }
    }

    public void agregarCochera(Cochera cochera) throws CocheraException {
        cochera.validar();
        validarNoRepetidos(cochera);
        cocheras.add(cochera);
    }
    // Método para contar cocheras libres

    public int contarCocherasLibres() {
        int count = 0;
        for (Cochera cochera : cocheras) {
            if (cochera.getEstaLibre()) {
                count++;
            }
        }
        return count;
    }

    public long contarCocherasDiscapacitados() {
        return cocheras.stream()
                .filter(c -> c.esDiscapacitado() && c.getEstaLibre())
                .count();
    }

    public long contarCocherasElec() {
        return cocheras.stream()
                .filter(c -> c.esElectrico() && c.getEstaLibre())
                .count();
    }

    public long contarCocherasEmpl() {
        return cocheras.stream()
                .filter(c -> c.esEmpleado() && c.getEstaLibre())
                .count();
    }

    // Método para contar cocheras ocupadas
    public int contarCocherasOcupadas() {
        int count = 0;
        for (Cochera cochera : cocheras) {
            if (!cochera.getEstaLibre()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.nombre);
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
        final Parking other = (Parking) obj;
        return Objects.equals(this.nombre, other.nombre);
    }

    @Override
    public String toString() {
        return "Parking{" + "nombre=" + nombre + ", direccion=" + direccion + "," + tarifa + ", factordemanda=" + factordemanda + '}';
    }

}
