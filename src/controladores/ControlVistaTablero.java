
package controladores;

import iu.VistaTablero;
import java.util.ArrayList;
import logica.Anomalia;
import logica.Eventos;
import logica.Fachada;
import observer.Observable;
import observer.Observador;

/**
 *
 * @author facun
 */
public final class ControlVistaTablero implements Observador {
    private final VistaTablero vista;
    private final Fachada modelo;
    private ArrayList<Anomalia> mostradas=new ArrayList();

    public ControlVistaTablero(VistaTablero vista) {
        this.vista = vista;
        this.modelo = Fachada.getInstancia();
        modelo.agregar(this);
        facturaciontotal();
        llenarTabla(modelo.cargarTabla());
        actualizarAnomalias();
    }
    public void facturaciontotal(){
       var total= modelo.facturacionParking();
       vista.totalFacturadoParking(total);
    }
    public void cantidadEstadias(){
        var cantidad= modelo.GetCantidadEstadias();
        vista.totalEstadias(cantidad);
    }
    public void llenarTabla(ArrayList<Object[]> data2 ){
         cantidadEstadias();
            facturaciontotal();
         for (Object[] entry : data2) {
            var nombre = (String) entry[0];
            var estadiass = modelo.CantidadestadiasXParking(nombre);
            var monto = modelo.facturacionXParking(nombre);
            var multa = modelo.MultasestadiasXParking(nombre);

            entry[5] = estadiass;
            entry[7] = monto;
            entry[6] = multa;
        }
        vista.llenarTabla(data2);
    }
    public void actualizarAnomalias(){
        ArrayList<Anomalia> nuevasAnomalias =modelo.GetAnomalias();
         ArrayList<Anomalia> aMostrar =new ArrayList();
        for (Anomalia anomalia : nuevasAnomalias){
            if(!mostradas.contains(anomalia.getId())){
                
                mostradas.add(anomalia);
                aMostrar.add(anomalia);
            }
        }
        vista.cargarAnomalias(aMostrar);
    }
            

    @Override
    public void actualizar(Observable origen, Object evento) {
        switch ((Eventos) evento) {
            case NUEVA_ANOMALIA -> {
                if(vista.presionado()){
                actualizarAnomalias();
                }
               llenarTabla(modelo.cargarTabla());}
            case NUEVA_INGRESO, NUEVO_EGRESO -> {
                llenarTabla(modelo.cargarTabla());
                 }
            default -> {
                 }
        }         
    }
}
