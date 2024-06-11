/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import iu.VistaTablaPrecios;
import logica.Eventos;
import logica.Fachada;
import logica.Parking;
import logica.TipoVehiculo;
import observer.Observable;
import observer.Observador;

/**
 *
 * @author facun
 */
public class ControlVistaTablaPrecios implements Observador{
    private final VistaTablaPrecios vista;
    private final Fachada modelo;

    public ControlVistaTablaPrecios(VistaTablaPrecios vista) {
       this.vista = vista;
        this.modelo = Fachada.getInstancia();
        modelo.agregar(this);
         
        CargarPrecios();
       
    }
    public void CargarPrecios(){
        modelo.GetParking(vista.GetNombre());
      

       vista.cargarTabla(modelo.GetParking(vista.GetNombre()));
   
    }
    public void ObtenerTarifa(String tarifanombre,Double nuevovalor){
      Parking p=  modelo.GetParking(vista.GetNombre());
      if(tarifanombre!=null){TipoVehiculo t= p.getTarifa().obtenerClave(tarifanombre);
       modelo.actualizarTarifa(p,t,nuevovalor);
      }
       
      
    }

    public double obtenerPromedioTarifa(String nombre) {
      return  modelo.obtenerPromedioTarifas(nombre);
    }

    @Override
    public void actualizar(Observable origen, Object evento) {
     if(Eventos.NUEVA_TARIFA.equals(evento)){
            CargarPrecios();
        }
    }
    
}
