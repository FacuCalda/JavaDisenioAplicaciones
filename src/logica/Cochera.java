package logica;

import exceptions.CocheraException;
import java.util.ArrayList;
import simuladortransito.Estacionable;

public class Cochera implements Estacionable{

	private final String codigo;
	private Boolean estaLibre;
        private final Parking parking;
        private final ArrayList<Etiqueta> etiquetas;
        

    public Cochera(String codigo, Boolean estaLibre,Parking parking) {
        this.codigo = codigo;
        this.estaLibre = estaLibre;
        
        etiquetas = new ArrayList<>();
            this.parking = parking;
    }

    public ArrayList<Etiqueta> getEtiquetas() {
        return etiquetas;
    }
       public void agregar(Etiqueta etiqueta){
           etiquetas.add(etiqueta);
       }         
 
  public void validar()throws CocheraException{
      if(parking==null)throw new CocheraException("parking no es valido");
      if(codigo==null||codigo.isEmpty())throw new CocheraException("codigo cochera no es valido");
  }

    public void setEstaLibre(Boolean estaLibre) {
        this.estaLibre = estaLibre;
    }

    public Parking getParking() {
        return parking;
    }
        
    

        @Override
    public String getCodigo() {
        return codigo;
    }

    public Boolean getEstaLibre() {
        return estaLibre;
    }
    
    public void hacerOcupada(){
     estaLibre=false;
    }
    public void hacerLibre(){
     estaLibre=true;
    }
    public boolean Discapacitado() {
    boolean contieneDiscapacitado = etiquetas.stream()       
           .anyMatch(etiqueta -> "Discapacitado".equals(etiqueta.getNombre()));
    return contieneDiscapacitado;
    }
    @Override
    public boolean esDiscapacitado() {
    boolean contieneDiscapacitado = etiquetas.stream()       
           .anyMatch(etiqueta -> "Discapacitado".equals(etiqueta.getNombre()));
    return contieneDiscapacitado;
    }
 public boolean Electrico() {
          boolean contieneDiscapacitado = etiquetas.stream()       
           .anyMatch(etiqueta -> "Electrico".equals(etiqueta.getNombre()));
    return contieneDiscapacitado;
    }

    @Override
    public boolean esElectrico() {
          boolean contieneDiscapacitado = etiquetas.stream()       
           .anyMatch(etiqueta -> "Electrico".equals(etiqueta.getNombre()));
    return contieneDiscapacitado;
    }
       public boolean Empleado() {
  boolean contieneDiscapacitado = etiquetas.stream()        
           .anyMatch(etiqueta -> "Empleado".equals(etiqueta.getNombre()));
    return contieneDiscapacitado;
    }

    @Override
    public boolean esEmpleado() {
  boolean contieneDiscapacitado = etiquetas.stream()        
           .anyMatch(etiqueta -> "Empleado".equals(etiqueta.getNombre()));
    return contieneDiscapacitado;
    }

    @Override
    public String toString() {
        return "Cochera{" + "codigo=" + codigo + ", estaLibre=" + estaLibre + ", parking=" + parking + ", etiquetas=" + etiquetas + '}';
    }
        

    
}
