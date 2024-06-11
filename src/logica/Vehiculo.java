package logica;

import exceptions.VehiculoException;
import java.util.ArrayList;
import simuladortransito.Transitable;

public class Vehiculo implements Transitable {

    private String patente;
    private TipoVehiculo tipo;
    private final ArrayList<Etiqueta> etiquetas;
    private Propietario propietario;

    public Vehiculo(String patente, TipoVehiculo tipo) {
        this.patente = patente;
        this.tipo = tipo;
        etiquetas = new ArrayList<>();
    }

    public ArrayList<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void agregar(Etiqueta etiqueta) {
        etiquetas.add(etiqueta);
    }

    @Override
    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public TipoVehiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVehiculo tipo) {
        this.tipo = tipo;
    }

    public void validar() throws VehiculoException, Exception {
        tipo.validar(this);
        // propietario.validar();
        if (patente == null || patente.isEmpty()) {
            throw new VehiculoException("patente invalida");
        }
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "patente=" + patente + ", tipo=" + tipo.toString() + ", etiquetas=" + etiquetas.toString() + '}';
    }

    public boolean Discapacitado() {
        boolean contieneDiscapacitado = etiquetas.stream()
                .anyMatch(etiqueta -> "Discapacitado".equals(etiqueta.getNombre()));
        return contieneDiscapacitado;
    }

    @Override
    public boolean esDiscapacitado() {
        boolean contieneDiscapacitado = etiquetas.stream()
                .anyMatch(etiqueta -> "Discapacitado".equals(etiqueta.getNombre()));
        return contieneDiscapacitado;
    }

    public boolean Electrico() {
        boolean contieneDiscapacitado = etiquetas.stream()
                .anyMatch(etiqueta -> "Electrico".equals(etiqueta.getNombre()));
        return contieneDiscapacitado;
    }

    @Override
    public boolean esElectrico() {
        boolean contieneDiscapacitado = etiquetas.stream()
                .anyMatch(etiqueta -> "Electrico".equals(etiqueta.getNombre()));
        return contieneDiscapacitado;
    }

    public boolean Empleado() {
        boolean contieneDiscapacitado = etiquetas.stream()
                .anyMatch(etiqueta -> "Empleado".equals(etiqueta.getNombre()));
        return contieneDiscapacitado;
    }

    @Override
    public boolean esEmpleado() {
        boolean contieneDiscapacitado = etiquetas.stream()
                .anyMatch(etiqueta -> "Empleado".equals(etiqueta.getNombre()));
        return contieneDiscapacitado;
    }


        
        
        

}
