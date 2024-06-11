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
public class ControlEtiqueta {
    private final ArrayList<Estadia> etiquetas = new ArrayList<>();

    public ArrayList<Estadia> getEtiquetas() {
        return etiquetas;
    }
    public void agregar(Estadia estadia){
        etiquetas.add(estadia);
    }
}
