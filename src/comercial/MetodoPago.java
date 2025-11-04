package comercial;

import java.util.UUID;
import enums.TipoPago;

public class MetodoPago {
    private String id;
    private TipoPago tipo;
    private String titular;
    private String numeroEnmascarado;
    private boolean activo;

    public MetodoPago(TipoPago tipo, String titular, String numeroCompleto) {
        this.id = UUID.randomUUID().toString();
        this.tipo = tipo;
        this.titular = titular;
        this.numeroEnmascarado = enmascararNumero(numeroCompleto);
        this.activo = true;
    }

    public MetodoPago(TipoPago tipo, String titular) {
        this(tipo, titular, "");
    }

    private String enmascararNumero(String numeroCompleto) {
        if (numeroCompleto == null || numeroCompleto.isEmpty()) {
            return "N/A";
        }

        String numeroLimpio = numeroCompleto.replaceAll("[\\s-]", "");

        if (numeroLimpio.length() < 4) {
            return "****";
        }

        String ultimosCuatro = numeroLimpio.substring(numeroLimpio.length() - 4);
        return "**** **** **** " + ultimosCuatro;
    }

    public boolean validar() {
        if (!activo) {
            System.out.println("Este metodo de pago esta desactivado");
            return false;
        }

        if (titular == null || titular.trim().isEmpty()) {
            System.out.println("El titular no puede estar vacio");
            return false;
        }

        System.out.println("Metodo de pago valido");
        return true;
    }

    public void desactivar() {
        this.activo = false;
        System.out.println("Metodo de pago desactivado");
    }

    public void activar() {
        this.activo = true;
        System.out.println("Metodo de pago reactivado");
    }

    public String getId() { return id; }
    public TipoPago getTipo() { return tipo; }
    public String getTitular() { return titular; }
    public void setTitular(String titular) { this.titular = titular; }
    public String getNumeroEnmascarado() { return numeroEnmascarado; }
    public boolean isActivo() { return activo; }

    @Override
    public String toString() {
        return String.format("MetodoPago[Tipo=%s, Titular=%s, Numero=%s, Activo=%s]",
                tipo.getDescripcion(), titular, numeroEnmascarado, activo);
    }

    public String formatoSeleccion() {
        return String.format("%s - %s (%s)", tipo.getDescripcion(), numeroEnmascarado, titular);
    }
}