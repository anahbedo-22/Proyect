package comercial;

import usuarios.Cliente;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Carrito {
    private String id;
    private Cliente cliente;
    private LocalDateTime fechaCreacion;
    private List<LineaCarrito> lineas;

    public Carrito(Cliente cliente) {
        this.id = UUID.randomUUID().toString();
        this.cliente = cliente;
        this.fechaCreacion = LocalDateTime.now();
        this.lineas = new ArrayList<>();
    }

    public boolean agregarProducto(Producto producto, int cantidad) {
        if (producto == null || cantidad <= 0) {
            System.out.println("Producto o cantidad invalidos");
            return false;
        }

        if (!producto.hayStock(cantidad)) {
            System.out.println("Stock insuficiente. Disponible: " + producto.getStock());
            return false;
        }

        for (LineaCarrito linea : lineas) {
            if (linea.getProducto().getId().equals(producto.getId())) {
                int nuevaCantidad = linea.getCantidad() + cantidad;
                if (producto.hayStock(nuevaCantidad)) {
                    linea.setCantidad(nuevaCantidad);
                    System.out.println("Cantidad actualizada en el carrito");
                    return true;
                } else {
                    System.out.println("No hay suficiente stock para esa cantidad");
                    return false;
                }
            }
        }

        LineaCarrito nuevaLinea = new LineaCarrito(producto, cantidad);
        lineas.add(nuevaLinea);
        System.out.println("Producto agregado al carrito");
        return true;
    }

    public boolean eliminarProducto(String productoId) {
        boolean eliminado = lineas.removeIf(linea -> linea.getProducto().getId().equals(productoId));

        if (eliminado) {
            System.out.println("Producto eliminado del carrito");
        } else {
            System.out.println("Producto no encontrado en el carrito");
        }
        return eliminado;
    }

    public boolean actualizarCantidad(String productoId, int nuevaCantidad) {
        if (nuevaCantidad <= 0) {
            return eliminarProducto(productoId);
        }

        for (LineaCarrito linea : lineas) {
            if (linea.getProducto().getId().equals(productoId)) {
                if (linea.getProducto().hayStock(nuevaCantidad)) {
                    linea.setCantidad(nuevaCantidad);
                    System.out.println("Cantidad actualizada");
                    return true;
                } else {
                    System.out.println("Stock insuficiente");
                    return false;
                }
            }
        }
        System.out.println("Producto no encontrado en el carrito");
        return false;
    }

    public double calcularTotal() {
        return lineas.stream().mapToDouble(LineaCarrito::getSubtotal).sum();
    }

    public void vaciar() {
        lineas.clear();
        System.out.println("Carrito vaciado");
    }

    public boolean estaVacio() {
        return lineas.isEmpty();
    }

    public void mostrarContenido() {
        if (estaVacio()) {
            System.out.println("El carrito esta vacio");
            return;
        }

        System.out.println("\n==========================================");
        System.out.println("        CARRITO DE COMPRAS");
        System.out.println("==========================================");
        System.out.println(String.format("%-35s %10s %12s %15s",
                "Producto", "Cantidad", "P. Unit.", "Subtotal"));
        System.out.println("------------------------------------------");

        for (LineaCarrito linea : lineas) {
            System.out.println(linea.formatoDetallado());
        }

        System.out.println("------------------------------------------");
        System.out.println(String.format("%58s $%,15.2f", "TOTAL:", calcularTotal()));
        System.out.println("==========================================\n");
    }

    public String getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public List<LineaCarrito> getLineas() { return new ArrayList<>(lineas); }
    public int getCantidadItems() { return lineas.size(); }

    @Override
    public String toString() {
        return String.format("Carrito[ID=%s, Cliente=%s, Items=%d, Total=$%.2f]",
                id.substring(0, 8), cliente.getNombre(), lineas.size(), calcularTotal());
    }
}