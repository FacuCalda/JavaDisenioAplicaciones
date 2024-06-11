package logica;

import java.time.LocalDateTime;

public class Anomalia {
 private static int nextId = 1;
 private final int id;
 private final String CodigoError;
 private final Estadia Estadia;
 private   LocalDateTime inicio;

    public Anomalia(String CodigoError, Estadia Estadia) {
         this.id = nextId++; 
        this.CodigoError = CodigoError;
        this.Estadia = Estadia;
        inicio = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getCodigoError() {
        return CodigoError;
    }

    public Estadia getEstadia() {
        return Estadia;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Anomalia other = (Anomalia) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Anomalia{" + "CodigoError=" + CodigoError + ", inicio=" + inicio +"id"+  id
                +'}';
    }
    
 
}
