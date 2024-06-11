/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package parking;

import iu.DatosDePrueba;
import iu.InterfazGrafica;
import java.util.ArrayList;
import logica.Cochera;
import logica.Vehiculo;
import simuladorIU.SimuladorIU;
import simuladortransito.ConfiguracionException;
import simuladortransito.Estacionable;
import simuladortransito.FlujoEgreso;
import simuladortransito.FlujoIngreso;
import simuladortransito.Periodo;
import simuladortransito.SimuladorTransito;
import simuladortransito.Transitable;

/**
 *
 * @author AMD A10 Laptop
 */
public class Parking {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        // TODO code application logic here
        new InterfazGrafica().setVisible(true);
        DatosDePrueba.cargar();
        iniciar();
       
        
    }
      public static void iniciar() throws Exception{
       
        SimuladorTransito simulador = SimuladorTransito.getInstancia();
        //Cargo vehiculos y cocheras
        ArrayList<Cochera> cochera = DatosDePrueba.cargarCochera();
        
        ArrayList<Estacionable> estacionables = new ArrayList<>(cochera);

        ArrayList<Vehiculo> vehiculo = DatosDePrueba.cargarAuto();
             
        ArrayList<Transitable> transitables = new ArrayList<>(vehiculo);
        DatosDePrueba d= new DatosDePrueba();
        simulador.addEstacionables(estacionables);
        simulador.addTransitables(transitables);
new SimuladorIU(null, false,d,estacionables,transitables).setVisible(true);
       try {
           

simulador.programar(new FlujoEgreso("E1", new Periodo(6, 5), 10)); // 20 egresos
simulador.programar(new FlujoIngreso("I1", new Periodo(0, 5), 10)); // 10 ingresos

         //INICIO SIMULACION
           simulador.iniciar(new DatosDePrueba());
            simulador.reanudarLogger();
        } catch (ConfiguracionException ex) {
            ex.printStackTrace();
       }
        
    }
}
