package comercial;

public class LineaCompra {
    private Producto producto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    public LineaCompra(Producto producto, int cantidad, double precioUnitario) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        calcularSubtotal();
    }

    private void calcularSubtotal() {
        this.subtotal = precioUnitario * cantidad;
    }

    public String formatoDetallado() {
        String nombreProducto = producto.getNombre();
        if (nombreProducto.length() > 35) {
            nombreProducto = nombreProducto.substring(0, 32) + "...";
        }

        return String.format("%-35s %10d $%,10.2f $%,14.2f",
                nombreProducto, cantidad, precioUnitario, subtotal);
    }

    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
    public double getSubtotal() { return subtotal; }

    @Override
    public String toString() {
        return String.format("LineaCompra[Producto=%s, Cantidad=%d, Precio=$%.2f, Subtotal=$%.2f]",
                producto.getNombre(), cantidad, precioUnitario, subtotal);
    }
}