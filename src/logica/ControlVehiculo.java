/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.ArrayList;

/**
 *
 * @author Solec
 */
public class ControlVehiculo {
     private final ArrayList<TipoVehiculo> tipovehiculos = new ArrayList<>();
     private final ArrayList<Vehiculo> vehiculos = new ArrayList<>();
     
     public void agregar(TipoVehiculo tipo){
         tipovehiculos.add(tipo);
     }
      public void agregarVehiculo(Vehiculo tipo){
         vehiculos.add(tipo);
     }
      public ArrayList<Vehiculo> GetVehiculos(){
          return vehiculos;
      }

 
}
