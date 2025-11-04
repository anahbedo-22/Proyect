package produccion;

import usuarios.Usuario;
import comercial.Producto;
import java.util.ArrayList;
import java.util.List;

public class DesarrolladorProducto extends Usuario {
    private String especialidad;
    private List<Producto> productosDesarrollados;
    private int anosExperiencia;

    public DesarrolladorProducto(String nombre, String email, String password, String especialidad) {
        super(nombre, email, password, "DESARROLLADOR");
        this.especialidad = especialidad;
        this.productosDesarrollados = new ArrayList<>();
        this.anosExperiencia = 0;
    }

    public DesarrolladorProducto(String nombre, String email, String password, String especialidad, int anosExperiencia) {
        this(nombre, email, password, especialidad);
        this.anosExperiencia = anosExperiencia;
    }

    @Override
    public void mostrarPermisos() {
        System.out.println("\n==========================================");
        System.out.println("  PERMISOS DE DESARROLLADOR DE PRODUCTO");
        System.out.println("==========================================");
        System.out.println("- Disenar nuevos productos");
        System.out.println("- Proponer formulas");
        System.out.println("- Participar en desarrollo de productos");
        System.out.println("- Ver productos en desarrollo");
        System.out.println("\nEspecialidad: " + especialidad);
        System.out.println("Anos de experiencia: " + anosExperiencia);
        System.out.println("Productos desarrollados: " + productosDesarrollados.size());
        System.out.println("==========================================\n");
    }

    public void agregarProductoDesarrollado(Producto producto) {
        if (producto != null && !productosDesarrollados.contains(producto)) {
            productosDesarrollados.add(producto);
            System.out.println("Producto '" + producto.getNombre() + "' registrado como desarrollado por " + nombre);
        }
    }

    public String calcularNivel() {
        int puntaje = anosExperiencia + productosDesarrollados.size();
        if (puntaje < 5) return "Junior";
        else if (puntaje < 15) return "Semi-Senior";
        else if (puntaje < 30) return "Senior";
        else return "Expert";
    }

    public void mostrarPortafolio() {
        System.out.println("\n==========================================");
        System.out.println("     PORTAFOLIO DE DESARROLLADOR");
        System.out.println("==========================================");
        System.out.println("Nombre: " + nombre);
        System.out.println("Especialidad: " + especialidad);
        System.out.println("Nivel: " + calcularNivel());
        System.out.println("Anos de experiencia: " + anosExperiencia);
        System.out.println("\nProductos Desarrollados (" + productosDesarrollados.size() + "):");

        if (productosDesarrollados.isEmpty()) {
            System.out.println("  - Aun no ha desarrollado productos");
        } else {
            for (int i = 0; i < productosDesarrollados.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + productosDesarrollados.get(i).getNombre());
            }
        }
        System.out.println("==========================================\n");
    }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public List<Producto> getProductosDesarrollados() { return new ArrayList<>(productosDesarrollados); }
    public int getAnosExperiencia() { return anosExperiencia; }
    public void setAnosExperiencia(int anos) { this.anosExperiencia = anos; }

    @Override
    public String toString() {
        return String.format("DesarrolladorProducto[%s, Especialidad=%s, Nivel=%s, Productos=%d]",
                super.toString(), especialidad, calcularNivel(), productosDesarrollados.size());
    }
}
