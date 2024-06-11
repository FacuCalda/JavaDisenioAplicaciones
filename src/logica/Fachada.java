/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.ArrayList;
import java.util.Collection;
import observer.Observable;
import observer.Observador;

/**
 *
 * @author facun
 */
public class Fachada extends Observable implements Observador {

    private static final Fachada instancia = new Fachada();

    public static Fachada getInstancia() {
        return instancia;
    }
    private final ControlParking controlParking = new ControlParking();
    private final ControlEstadia controlEstadia = new ControlEstadia();
    private final ControlVehiculo controlVehiculo = new ControlVehiculo();
    private final ControlPropietario controlPropietario = new ControlPropietario();

    public Fachada() {
        controlParking.agregar(this);
        controlEstadia.agregar(this);
    }

    public void agregar(Parking usuario) {
        controlParking.agregarParking(usuario);
    }

    public void agregar(Estadia estadia) {
        controlEstadia.agregarEstadia(estadia);
    }

    public void agregar(TipoVehiculo tipo) {
        controlVehiculo.agregar(tipo);
    }

    public void agregar(Propietario tipo) {
        controlPropietario.agregar(tipo);
    }

    public void agregar(Vehiculo tipo) {
        controlVehiculo.agregarVehiculo(tipo);
    }

    public void agregar(Cochera c) {
        controlParking.agregarCochera(c);
    }

    public Collection getPropietarios() {
        return controlPropietario.getPropietarios();
    }

    public double facturacionParking() {
        return controlEstadia.calcularMontoParkings();
    }

    public double facturacionXParking(String nombre) {
        return controlEstadia.calcularMontoXParkings(nombre);
    }

    public int CantidadestadiasXParking(String nombre) {
        return controlEstadia.estadiasXParking(nombre);
    }

    public double MultasestadiasXParking(String nombre) {
        return controlEstadia.MultasestadiasXParking(nombre);
    }

    public ArrayList<Vehiculo> vehiculos() {
        return controlVehiculo.GetVehiculos();
    }

    public ArrayList<Cochera> cocheras() {
        return controlParking.GetCocheras();
    }

    public void egresoVehiculo(Vehiculo vehiculo, Cochera cochera) {
        double m = controlEstadia.registrarEgresoVehiculo(vehiculo, cochera);
        restarMonto(vehiculo, m);
    }

    public int GetCantidadEstadias() {
        return controlEstadia.cantidadEstadias();
    }

    public void restarMonto(Vehiculo v, double monto) {
        controlPropietario.RestarMonto(v, monto);
    }

    public ArrayList<Object[]> cargarTabla() {
        return controlParking.tablaParkings();
    }

    @Override
    public void actualizar(Observable origen, Object evento) {
        avisar(evento);
    }

    public ArrayList<Anomalia> GetAnomalias() {
        return controlEstadia.getAnomalias();
    }

    public Parking GetParking(String nombre) {
        return controlParking.getParkingNombre(nombre);
    }

    public void actualizarTarifa(Parking p, TipoVehiculo t, Double nuevovalor) {
        controlParking.actualizarTarifa(p, t, nuevovalor);
    }

    public double obtenerPromedioTarifas(String nombreTipoVehiculo) {
        return controlParking.obtenerPromedioTarifa(nombreTipoVehiculo);

    }
}
