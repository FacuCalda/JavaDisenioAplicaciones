/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.ArrayList;
import java.util.Map;
import observer.Observable;

/**
 *
 * @author facun
 */
public class ControlParking extends Observable {

    private final ArrayList<Parking> parkings = new ArrayList<>();
    private final ArrayList<Cochera> cocheras = new ArrayList<>();
    private final ArrayList<Anomalia> anomalias = new ArrayList<>();

    public ArrayList<Parking> getParkings() {
        return parkings;
    }

    public ArrayList<Object[]> tablaParkings() {
        ArrayList<Object[]> data = new ArrayList<>();
        for (Parking p : parkings) {
            var nombre = p.getNombre();

            var ocu = p.contarCocherasOcupadas();
            var libre = p.contarCocherasLibres();
            var estadoTipo = p.getEstado();
            var estado = p.getFactorDemanda();

            data.add(new Object[]{nombre, ocu, libre, estadoTipo, estado, null, null, null});
        }

        return data;
    }

    public void actualizarTarifa(Parking p, TipoVehiculo t, Double nueva) {
        p.getTarifa().establecerPrecio(t, nueva);
        avisar(Eventos.NUEVA_TARIFA);

    }

    public double obtenerPromedioTarifa(String nombreTipoVehiculo) {
        double sumaTarifas = 0.0;
        int contador = 0;

        for (Parking parking : parkings) {
            for (Map.Entry<TipoVehiculo, Double> entry : parking.getTarifa().getPrecioTipoVehiculo().entrySet()) {
                TipoVehiculo t = parking.getTarifa().obtenerClave(nombreTipoVehiculo);

                if (entry.getKey().equals(t)) {
                    sumaTarifas += entry.getValue();
                    contador++;
                }
            }
        }

        if (contador == 0) {
            throw new IllegalArgumentException("No se encontraron tarifas para el tipo de vehículo especificado.");
        }

        return sumaTarifas / contador;
    }

    public void agregarParking(Parking parking) {
        parkings.add(parking);
    }

    public void agregarCochera(Cochera c) {
        cocheras.add(c);
    }

    public ArrayList<Anomalia> getAnomalias() {
        return anomalias;
    }

    Cochera getCochera(Integer codigo) {

        return cocheras.stream().filter(t -> t.getCodigo().equals(codigo)).findFirst().get();
    }

    ArrayList<Cochera> GetCocheras() {
        return cocheras;
    }

    public Parking getParkingNombre(String nombre) {

        return parkings.stream()
                .filter(parking -> parking.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

}
