/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import iu.VistaCartelera;
import java.util.ArrayList;
import logica.Cochera;
import logica.Eventos;
import logica.Fachada;
import logica.Parking;
import observer.Observable;
import observer.Observador;

/**
 *
 * @author AMD A10 Laptop
 */
public class ControlCartelera implements Observador {
 private final VistaCartelera vista;
    private final Fachada modelo;
    
     public ControlCartelera(VistaCartelera vista) {
       this.vista = vista;
        this.modelo = Fachada.getInstancia();
        modelo.agregar(this);
         
        Cargar();
        cargarTipos();
       
    }
     public void cargarTipos(){
       modelo.GetParking(vista.GetNombre());
       vista.cargarTablaPrecios(modelo.GetParking(vista.GetNombre()));
     
     }
        public void Cargar(){
        modelo.GetParking(vista.GetNombre());
    Parking p=  modelo.GetParking(vista.GetNombre());
        long cocherasDiscapacitados = p.getCocheras().stream()
        .filter(Cochera::esDiscapacitado)
        .count();
        
        long cocherasElectricas= p.getCocheras().stream()
        .filter(Cochera::esElectrico)
        .count();
        
        long cocherasEmpl= p.getCocheras().stream()
        .filter(Cochera::esEmpleado)
        .count();
        ArrayList<Long> cantidad=new ArrayList<>();
        cantidad.add(cocherasDiscapacitados);
        cantidad.add(cocherasElectricas);
        cantidad.add(cocherasEmpl);
        
       vista.cargarListaCocheras(cantidad);
   
    }
    @Override
    public void actualizar(Observable origen, Object evento) {
        if(Eventos.NUEVA_TARIFA.equals(evento)){
            cargarTipos();
        }
    }
    
}
