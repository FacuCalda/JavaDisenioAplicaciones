package logica;

import java.time.LocalDateTime;
import java.util.ArrayList;
import static logica.Eventos.NUEVO_EGRESO;
import observer.Observable;

/**
 *
 * @author facun
 */
public class ControlEstadia extends Observable {

    private final ArrayList<Estadia> estadias = new ArrayList<>();
    private final ArrayList<Estadia> estadiasEgresadas = new ArrayList<>();
    private final ArrayList<Anomalia> anomalias = new ArrayList<>();

    public ArrayList<Estadia> getEstadias() {
        return estadias;
    }

    int cantidadEstadias() {
        avisar(Eventos.ESTADIAS);
        return estadias.size();

    }

    public void agregarEstadia(Estadia estadia) {

        if (estadia.getCochera().getEstaLibre()) {
            estadia.getCochera().hacerOcupada();
            estadias.add(estadia);
            avisar(Eventos.NUEVA_INGRESO);
        } else {
            procesarEstadiaAnterior(estadia.getCochera());
            estadia.getCochera().hacerOcupada();
            estadias.add(estadia);

        }

    }

    public void procesarEstadiaAnterior(Cochera cochera) {
        Estadia estadiaAnterior = buscarEstadiaPorCochera(cochera);
        if (estadiaAnterior != null) {

            estadiaAnterior.setSalida();
            estadiaAnterior.setValor(0.0);
            Anomalia a = registrarAnomalia("HOUDINI", estadiaAnterior);

            estadiaAnterior.setAnomalia(a);
        }
    }

    public Estadia buscarEstadiaPorCochera(Cochera cochera) {
        for (Estadia estadia : estadias) {

            if (estadia.getCochera().equals(cochera) && estadia.getSalida() == null) {
                return estadia;
            }
        }
        return null;
    }

    private Anomalia registrarAnomalia(String codigoError, Estadia estadia) {
        Anomalia anomalia = new Anomalia(codigoError, estadia);

        anomalias.add(anomalia);
        avisar(Eventos.NUEVA_ANOMALIA);
        return anomalia;
    }

    public int estadiasXParking(String nombre) {
        int contador = 0;
        for (Estadia e : estadias) {
            if (e.miParking() == nombre) {
                contador++;
            }
        }
        return contador;
    }

    public double MultasestadiasXParking(String nombre) {
        double contador = 0;
        for (Estadia e : estadias) {
            if (e.miParking() == nombre) {
                contador += e.getMulta();
            }
        }
        return contador;
    }

    public double calcularMontoParkings() {
        var ret = 0;
        for (Estadia e : estadiasEgresadas) {
            ret += e.getValor();
        }
        return ret;
    }

    public double calcularMontoXParkings(String nombre) {
        double ret = 0.0;
        for (Estadia e : estadiasEgresadas) {
            if (e.miParking() == nombre) {
                ret += e.getValor();
            }
        }
        return ret;
    }

    public ArrayList<Anomalia> getAnomalias() {
        return anomalias;
    }

    private void casoMistery(Cochera cochera, Vehiculo vehiculo) {
        Estadia e = new Estadia(cochera, vehiculo);
        e.setSalida();
        e.setInicio(LocalDateTime.now());
        Anomalia a = registrarAnomalia("MiSTERY", e);
        e.setAnomalia(a);

    }

    private void casoTransportador(Cochera cochera) {
        Estadia e = buscarEstadiaPorCochera(cochera);
        Anomalia a = registrarAnomalia("Transportador1", e);
        e.setAnomalia(a);
        Vehiculo v = e.getVehiculo();

    }

    public double registrarEgresoVehiculo(Vehiculo vehiculo, Cochera cochera) {

        double monto = 0.0;
        Parking p = cochera.getParking();
        Estadia estadia = buscarEstadiaActivaPorVehiculoYCochera(vehiculo, cochera);
        if (cochera.getEstaLibre()) {
            casoMistery(cochera, vehiculo);
        } else if (!cochera.getEstaLibre() && estadia != null) {
            cochera.setEstaLibre(true);
            estadia.setSalida();
            p.actualizarFactorDemanda(contarEgresosRecientes(), contarIngresosRecientes(), estadias.size());
            monto = estadia.calcularMontoEstadia();

            estadiasEgresadas.add(estadia);

            avisar(NUEVO_EGRESO);
            return monto;
        } else {
            casoTransportador(cochera);
        }
        return monto;
    }

    private int contarIngresosRecientes() {

        return (int) estadias.stream()
                .filter(e -> e.getInicio().isAfter(LocalDateTime.now().minusSeconds(10)))
                .count();
    }

    private int contarEgresosRecientes() {
        return (int) estadiasEgresadas.stream()
                .filter(e -> e.getSalida() != null && e.getSalida().isAfter(LocalDateTime.now().minusSeconds(10)))
                .count();
    }

    private Estadia buscarEstadiaActivaPorVehiculoYCochera(Vehiculo vehiculo, Cochera cochera) {
        for (Estadia estadia : estadias) {
            if (estadia.getVehiculo() == vehiculo && estadia.getCochera().equals(cochera) && estadia.getSalida() == null) {
                return estadia;
            }
        }
        return null;
    }

}
