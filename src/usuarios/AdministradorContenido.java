package usuarios;

import java.util.HashSet;
import java.util.Set;

public class AdministradorContenido extends Usuario {
    private Set<String> permisosEdicion;
    private boolean miembroConsejoSombrio;

    public AdministradorContenido(String nombre, String email, String password) {
        super(nombre, email, password, "ADMIN_CONTENIDO");
        this.permisosEdicion = new HashSet<>();
        inicializarPermisos();
        this.miembroConsejoSombrio = false;
    }

    private void inicializarPermisos() {
        permisosEdicion.add("CREAR_PRODUCTO");
        permisosEdicion.add("EDITAR_PRODUCTO");
        permisosEdicion.add("ELIMINAR_PRODUCTO");
        permisosEdicion.add("VER_PRODUCTOS");
        permisosEdicion.add("GESTIONAR_CATEGORIAS");
    }

    @Override
    public void mostrarPermisos() {
        System.out.println("\n==========================================");
        System.out.println("  PERMISOS DE ADMINISTRADOR DE CONTENIDO");
        System.out.println("==========================================");
        System.out.println("- Crear nuevos productos");
        System.out.println("- Editar productos existentes");
        System.out.println("- Eliminar productos");
        System.out.println("- Gestionar categorias");
        System.out.println("- Ver estadisticas de ventas");
        if (miembroConsejoSombrio) {
            System.out.println("- ACCESO AL CONSEJO SOMBRIO");
        }
        System.out.println("==========================================\n");
    }

    public boolean tienePermiso(String permiso) {
        return permisosEdicion.contains(permiso);
    }

    public void agregarPermiso(String permiso) {
        permisosEdicion.add(permiso);
        System.out.println("Permiso '" + permiso + "' agregado");
    }

    public void removerPermiso(String permiso) {
        if (permisosEdicion.remove(permiso)) {
            System.out.println("Permiso '" + permiso + "' removido");
        }
    }

    public void ascenderAConsejoSombrio(String claveMaestra) {
        this.miembroConsejoSombrio = true;
        agregarPermiso("ACCESO_CONSEJO_SOMBRIO");
        System.out.println("Miembro ascendido al Consejo Sombrio");
    }

    public Set<String> getPermisosEdicion() { return new HashSet<>(permisosEdicion); }
    public boolean esMiembroConsejoSombrio() { return miembroConsejoSombrio; }

    @Override
    public String toString() {
        return String.format("AdministradorContenido[%s, Permisos=%d, Consejo Sombrio=%s]",
                super.toString(), permisosEdicion.size(), miembroConsejoSombrio);
    }
}
