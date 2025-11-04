package enums;

public enum TipoPago {
    TARJETA_CREDITO("Tarjeta de Credito"),
    TARJETA_DEBITO("Tarjeta de Debito"),
    PAYPAL("PayPal"),
    TRANSFERENCIA("Transferencia Bancaria"),
    EFECTIVO("Efectivo contra entrega");

    private String descripcion;

    TipoPago(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
