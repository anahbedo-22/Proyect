package comercial;

public class LineaCarrito {
    private Producto producto;
    private int cantidad;
    private double subtotal;

    public LineaCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        calcularSubtotal();
    }

    private void calcularSubtotal() {
        this.subtotal = producto.getPrecio() * cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        calcularSubtotal();
    }

    public String formatoDetallado() {
        return String.format("%-35s %10d $%,10.2f $%,14.2f",
                producto.getNombre().length() > 35 ?
                        producto.getNombre().substring(0, 32) + "..." : producto.getNombre(),
                cantidad, producto.getPrecio(), subtotal);
    }

    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public double getSubtotal() { return subtotal; }

    @Override
    public String toString() {
        return String.format("LineaCarrito[Producto=%s, Cantidad=%d, Subtotal=$%.2f]",
                producto.getNombre(), cantidad, subtotal);
    }
}