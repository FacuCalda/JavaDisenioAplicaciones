/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package iu;

import java.util.ArrayList;
import logica.Anomalia;

/**
 *
 * @author facun
 */
public interface VistaTablero {
    void totalFacturadoParking(Double monto);
    void totalEstadias(int monto);
     void llenarTabla(ArrayList<Object[]> data);
     void cargarAnomalias(ArrayList<Anomalia> data);
     boolean presionado();
}
