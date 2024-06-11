package logica;

import exceptions.CocheraException;
import exceptions.EstadiaException;
import java.time.Duration;
import java.time.LocalDateTime;

public class Estadia {

    private LocalDateTime inicio;
    private LocalDateTime salida = null;
    private Double valor;
    private Anomalia anomalia;
    private final Cochera cochera;
    private final Vehiculo vehiculo;
    private boolean valorCalculado = false;
    private Double multa = 0.0;

    public Double getMulta() {
        return multa;
    }

    public double calcularMontoEstadia() {
        if (valorCalculado) {
            return valor;
        }
        double duracion = calcularDuracionEnSegundos();
        TipoVehiculo t = vehiculo.getTipo();
        Double precioTipo = cochera.getParking().getTarifa().obtenerPrecioTipoVehiculo(t);
        double factorDemanda = cochera.getParking().getFactorDemanda();
        //HACER LAS MULTAS aparte
        double monto = precioTipo * duracion * factorDemanda;

        valor = calcularMulta(monto);

        valorCalculado = true;
     
        return valor;

    }

    public double calcularMulta(double monto) {
        double valormulta = 0.0;
        if (vehiculo.Discapacitado() == cochera.Discapacitado()
                && vehiculo.Electrico() == cochera.Electrico()
                && vehiculo.Empleado() == cochera.Empleado()) {
            return monto;
        } else {
            if (!vehiculo.Discapacitado() && cochera.Discapacitado()) {
                double totalMultaDiscapacitado = cochera.getEtiquetas().stream()
                        .filter(etiqueta -> "Discapacitado".equals(etiqueta.getNombre()))
                        .mapToDouble(etiqueta -> etiqueta.calcularMulta(this))
                        .sum();
   
                valormulta += totalMultaDiscapacitado;

            }
            if (!vehiculo.Empleado() && cochera.Empleado()) {
                double totalMultaEmpleado = cochera.getEtiquetas().stream()
                        .filter(etiqueta -> "Empleado".equals(etiqueta.getNombre()))
                        .mapToDouble(etiqueta -> etiqueta.calcularMulta(this))
                        .sum();
                valormulta += totalMultaEmpleado;

            }
            if (!vehiculo.Electrico() && cochera.Electrico()) {
                double totalMultaEletrico = cochera.getEtiquetas().stream()
                        .filter(etiqueta -> "Electrico".equals(etiqueta.getNombre()))
                        .mapToDouble(etiqueta -> etiqueta.calcularMulta(this))
                        .sum();
                valormulta += monto * totalMultaEletrico;

                return valormulta;
            }

        }
        multa = valormulta;

        return (monto) + valormulta;
    }

    public double calcularDuracionEnSegundos() {
        LocalDateTime inicio = this.inicio;
        LocalDateTime salida = this.salida != null ? this.salida : LocalDateTime.now();

        Duration duration = Duration.between(inicio, salida);
        double duracionTotalSegundos = duration.toMillis() / 1000.0; // Duración total en segundos

        return duracionTotalSegundos;
    }

    public Estadia(Cochera cochera, Vehiculo vehiculo) {
        // this.valor = valor; 
        this.vehiculo = vehiculo;
        this.cochera = cochera;
        this.inicio = LocalDateTime.now();
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDateTime getSalida() {
        return salida;
    }

    public void setAnomalia(Anomalia anomalia) {
        this.anomalia = anomalia;
    }

    public Anomalia getAnomalia() {
        return anomalia;
    }

    public Cochera getCochera() {
        return cochera;
    }

    public void setSalida() {
        salida = LocalDateTime.now();
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public boolean isValorCalculado() {
        return valorCalculado;
    }

    public void setValorCalculado(boolean valorCalculado) {
        this.valorCalculado = valorCalculado;
    }

    public String miParking() {
        return cochera.getParking().getNombre();
    }

    public Double getValor() {
        return valor;
    }

    public void validar() throws CocheraException, EstadiaException {
        validarNumeros();
        validarCochera();
    }

    public void validarNumeros() throws EstadiaException {
        if (valor < 0) {
            throw new EstadiaException("los numeros deben ser validos");
        }

    }

    public void validarCochera() throws CocheraException {
        if (this.cochera == null) {
            throw new CocheraException("La cochera no es valida");
        }

        cochera.validar();

    }

    @Override
    public String toString() {
        return "Estadia{" + "inicio=" + inicio + ", salida=" + salida + ", valor=" + valor + ", anomalia=" + anomalia + ", cochera=" + cochera + ", vehiculo=" + vehiculo + ", valorCalculado=" + valorCalculado + '}';
    }

}
