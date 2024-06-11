/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package iu;

import java.util.ArrayList;
import logica.Parking;

/**
 *
 * @author AMD A10 Laptop
 */
public interface VistaCartelera {
    String GetNombre();
    void cargarTablaPrecios(Parking p);
    void cargarListaCocheras(ArrayList<Long> cantidad);
}
