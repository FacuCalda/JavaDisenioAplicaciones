package logica;

import exceptions.VehiculoException;
import java.util.ArrayList;

public class Propietario {

	private String cedula;
	private String nombre;
        private ArrayList<Vehiculo> vehiculos;
        private CuentaCorriente cuenta;

    public Propietario(String cedula, String nombre, CuentaCorriente cuenta) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.vehiculos = new ArrayList();
        this.cuenta = cuenta;
    }
     public void agregarVehiculo(Vehiculo v)throws VehiculoException,Exception{
         v.validar();
         vehiculos.add(v);
     }      

    public CuentaCorriente getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaCorriente cuenta) {
        this.cuenta = cuenta;
    }
           

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    void validar() throws Exception{
        validarStrings();
        cuenta.validar();
    }
    private void validarStrings()throws Exception{
     if(nombre==null||nombre.isEmpty()){
         throw new Exception ("nombre invalido");
     }
     if(cedula==null||cedula.isEmpty()) throw new Exception ("cedula invalido");
    }

    @Override
    public String toString() {
        return "Propietario{" + "cedula=" + cedula + ", nombre=" + nombre + ", vehiculos=" + vehiculos.toString() + ", cuenta=" + cuenta.toString() + '}';
    }
           
           

}
