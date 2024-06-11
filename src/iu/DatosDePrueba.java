package iu;

import exceptions.CocheraException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import logica.Carga;
import logica.Cochera;
import logica.CuentaCorriente;
import logica.Discapacitado;
import logica.Electrico;
import logica.Empleado;
import logica.Estadia;
import logica.Etiqueta;

import logica.Fachada;
import logica.Motocicleta;
import logica.Parking;
import logica.Pasajero;
import logica.Propietario;
import logica.Standard;
import logica.Tarifario;
import logica.TipoVehiculo;
import logica.Vehiculo;
import simuladortransito.Estacionable;
import simuladortransito.Sensor;
import simuladortransito.Transitable;

/**
 *
 * @author facun
 */
public class DatosDePrueba implements Sensor {

    public static void cargar() throws Exception {

        Fachada fachada = Fachada.getInstancia();

        TipoVehiculo tvm = new Motocicleta();
        fachada.agregar(tvm);
        TipoVehiculo tvc = new Carga();
        fachada.agregar(tvc);
        TipoVehiculo tvp = new Pasajero();
        fachada.agregar(tvp);
        TipoVehiculo tvs = new Standard();
        fachada.agregar(tvs);

        HashMap<TipoVehiculo, Double> precioTipoVehiculo = new HashMap<>();
        precioTipoVehiculo.put(tvm, 0.05);
        precioTipoVehiculo.put(tvc, 0.1);
        precioTipoVehiculo.put(tvp, 0.1);
        precioTipoVehiculo.put(tvs, 0.1);
        Tarifario tarifa = new Tarifario(precioTipoVehiculo);

        HashMap<TipoVehiculo, Double> precioTipoVehiculo2 = new HashMap<>();
        precioTipoVehiculo2.put(tvm, 0.05);
        precioTipoVehiculo2.put(tvc, 0.1);
        precioTipoVehiculo2.put(tvp, 0.1);
        precioTipoVehiculo2.put(tvs, 0.1);
        Tarifario tarifa2 = new Tarifario(precioTipoVehiculo2);
        try {
            Parking parking1 = new Parking("Parking Central", "Av. Principal 123", tarifa);
            Parking parking2 = new Parking("Parking Norte", "Calle Secundaria 456", tarifa2);
            fachada.agregar(parking2);
            fachada.agregar(parking1);

            Set<String> codigosUtilizado = new HashSet<>();

            agregarCocherasConEtiquetas(parking1, 50, codigosUtilizado, fachada);
            agregarCocherasConEtiquetas(parking2, 70, codigosUtilizado, fachada);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //////////////////////////////
        Set<String> patentesGeneradas = new HashSet<>();
        Random random1 = new Random();

        // Crear 50 propietarios
        for (int i = 1; i <= 50; i++) {
            Propietario propietario = new Propietario("cedula" + i, "nombre" + i, new CuentaCorriente(1 + i, 10.0 + i));

            // Crear vehiculos x proppietario
            int numVehiculos = random1.nextInt(2) + 2;
            for (int j = 0; j < numVehiculos; j++) {
                TipoVehiculo tipoVehiculo = null;
                int tipoIndex = random1.nextInt(4); // 

                switch (tipoIndex) {
                    case 0 ->
                        tipoVehiculo = new Motocicleta();
                    case 1 ->
                        tipoVehiculo = new Carga();
                    case 2 ->
                        tipoVehiculo = new Pasajero();
                    case 3 ->
                        tipoVehiculo = new Standard();
                    // Puedo agregar más tipos aca despues

                }

                String patente;
                while (true) {
                    // Generar número de patente aleatorio como string
                    patente = String.valueOf(random1.nextInt(900000) + 100000); // Genera un número de 6 dígitos

                    // Verificar si la patente ya ha sido generada
                    if (!patentesGeneradas.contains(patente)) {
                        // Patente no repetida, agregar al conjunto
                        patentesGeneradas.add(patente);
                        break; // Rompe el bucle para evitar continuar generando más patentes
                    }
                }

                Vehiculo vehiculo = new Vehiculo(patente, tipoVehiculo);

                // Verificar si este vehículo debe tener etiqueta (25% de probabilidad)
                if (random1.nextInt(100) < 25) {
                    // Suponiendo que tienes una lista de etiquetas disponibles
                    Etiqueta[] etiquetasDisponibles = {new Discapacitado(), new Electrico(), new Empleado()};
                    // Elegir aleatoriamente una etiqueta y asignarla al vehículo
                    int indiceEtiqueta = random1.nextInt(etiquetasDisponibles.length);
                    Etiqueta etiqueta = etiquetasDisponibles[indiceEtiqueta];
                    vehiculo.agregar(etiqueta);
                }

                propietario.agregarVehiculo(vehiculo);
                fachada.agregar(vehiculo);
            }
            fachada.agregar(propietario);
        }

    }

    public static ArrayList<Vehiculo> cargarAuto() {
        Fachada fachada = Fachada.getInstancia();
        return fachada.vehiculos();
    }

    public static ArrayList<Cochera> cargarCochera() {
        Fachada fachada = Fachada.getInstancia();
        return fachada.cocheras();
    }

    @Override
    public void ingreso(Transitable transitable, Estacionable estacionable) {
        Fachada fachada = Fachada.getInstancia();
        Cochera c = (Cochera) estacionable;
        Vehiculo v = (Vehiculo) transitable;
        Estadia e = new Estadia(c, v);
        fachada.agregar(e);

    }

    @Override
    public void egreso(Transitable transitable, Estacionable estacionable) {
        Fachada fachada = Fachada.getInstancia();
        Cochera c = (Cochera) estacionable;
        Vehiculo v = (Vehiculo) transitable;
        fachada.egresoVehiculo(v, c);

    }

    public static void agregarCocherasConEtiquetas(Parking parking, int totalCocheras, Set<String> codigosUtilizados, Fachada fachada) throws CocheraException {
        Random random = new Random();
        Etiqueta ed = new Discapacitado();
        Etiqueta ee = new Electrico();
        Etiqueta eempleado = new Empleado();
        int numCocherasConEtiqueta = (int) (totalCocheras * 0.25); // 25% de las cocheras tendrán etiquetas

        for (int i = 1; i <= totalCocheras; i++) {
            String codigo;
            do {
                codigo = String.valueOf((int) (Math.random() * 1000) + 1); 
            } while (codigosUtilizados.contains(codigo));
            codigosUtilizados.add(codigo);

            Cochera cochera = new Cochera(codigo, true, parking);
            parking.agregarCochera(cochera);

            // Verificar si esta cochera debe tener etiqueta
            if (numCocherasConEtiqueta > 0) {
                // Reducir el contador de cocheras con etiquetas
                numCocherasConEtiqueta--;

                
                int etiquetaAleatoria = random.nextInt(3);
                switch (etiquetaAleatoria) {
                    case 0 ->
                        cochera.agregar(ed);
                    case 1 ->
                        cochera.agregar(ee);
                    case 2 ->
                        cochera.agregar(eempleado);
                    default -> {
                    }
                }
            }
            fachada.agregar(cochera);
        }
    }
}
