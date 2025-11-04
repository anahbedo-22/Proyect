package usuarios;

import comercial.Carrito;
import comercial.MetodoPago;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {
    private String direccionEnvio;
    private String telefono;
    private List<MetodoPago> metodosPago;
    private Carrito carritoActivo;

    public Cliente(String nombre, String email, String password) {
        super(nombre, email, password, "CLIENTE");
        this.metodosPago = new ArrayList<>();
        this.carritoActivo = new Carrito(this);
    }

    public Cliente(String nombre, String email, String password, String direccionEnvio, String telefono) {
        this(nombre, email, password);
        this.direccionEnvio = direccionEnvio;
        this.telefono = telefono;
    }

    @Override
    public void mostrarPermisos() {
        System.out.println("\n==========================================");
        System.out.println("  PERMISOS DE CLIENTE");
        System.out.println("==========================================");
        System.out.println("- Ver catalogo de productos");
        System.out.println("- Agregar productos al carrito");
        System.out.println("- Realizar compras");
        System.out.println("- Ver historial de compras");
        System.out.println("- Gestionar metodos de pago");
        System.out.println("==========================================\n");
    }

    public void agregarMetodoPago(MetodoPago metodoPago) {
        if (metodoPago != null) {
            this.metodosPago.add(metodoPago);
            System.out.println("Metodo de pago agregado exitosamente");
        }
    }

    public Carrito obtenerCarrito() {
        if (carritoActivo == null) {
            carritoActivo = new Carrito(this);
        }
        return carritoActivo;
    }

    public void nuevoCarrito() {
        this.carritoActivo = new Carrito(this);
    }

    public String getDireccionEnvio() { return direccionEnvio; }
    public void setDireccionEnvio(String direccionEnvio) { this.direccionEnvio = direccionEnvio; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public List<MetodoPago> getMetodosPago() { return new ArrayList<>(metodosPago); }
    public Carrito getCarritoActivo() { return carritoActivo; }

    @Override
    public String toString() {
        return String.format("Cliente[%s, Direccion=%s, Telefono=%s, Metodos de pago=%d]",
                super.toString(), direccionEnvio, telefono, metodosPago.size());
    }
}