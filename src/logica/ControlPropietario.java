/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.ArrayList;
import observer.Observable;
import observer.Observador;

/**
 *
 * @author facun
 */
public class ControlPropietario  {
    private final ArrayList<Propietario> propietarios = new ArrayList<>();

    public ControlPropietario() {
        
    }
    
    public void agregar(Propietario tipo){
         propietarios.add(tipo);
     }

    ArrayList<Propietario> getPropietarios() {
        return propietarios;
    }

   public void RestarMonto(Vehiculo v,double monto){
   Propietario p=propietarios.stream()
                .filter(propietario -> propietario.getVehiculos().contains(v))
                .findFirst()
                .orElse(null);
   p.getCuenta().restarMonto(monto);
   
   }
}
