package logica;

public class CuentaCorriente {

	private final Integer numeroCuenta;

        private Double saldo;

    public CuentaCorriente(int numeroCuenta, Double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }

    public Integer getNumeroCuenta() {
        return numeroCuenta;
    }

 

    public Double getSaldo() {
        return saldo;
    }

    private void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    
    public void restarMonto(Double monto){
        Double valor= saldo-monto;
        setSaldo(valor);
    }    

    void validar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String toString() {
        return "CuentaCorriente{" + "numeroCuenta=" + numeroCuenta + ", saldo=" + saldo + '}';
    }

}
