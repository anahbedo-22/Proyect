package ocultos;

import usuarios.AdministradorContenido;
import usuarios.AdministradorUsuario;
import usuarios.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConsejoSombrio {
    private String id;
    private String nombreClave;
    private List<Usuario> miembros;
    private int nivelAcceso;

    public ConsejoSombrio(String nombreClave) {
        this.id = "CS-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.nombreClave = nombreClave;
        this.miembros = new ArrayList<>();
        this.nivelAcceso = 10;
    }

    public boolean agregarMiembro(Usuario usuario) {
        if (usuario instanceof AdministradorContenido || usuario instanceof AdministradorUsuario) {
            if (!miembros.contains(usuario)) {
                miembros.add(usuario);
                System.out.println("Nuevo miembro del Consejo Sombrio: " + usuario.getNombre());
                return true;
            } else {
                System.out.println("El usuario ya es miembro del consejo");
                return false;
            }
        } else {
            System.out.println("Solo administradores pueden ser miembros del Consejo Sombrio");
            return false;
        }
    }

    public boolean removerMiembro(String usuarioId) {
        boolean removido = miembros.removeIf(m -> m.getId().equals(usuarioId));
        if (removido) {
            System.out.println("Miembro removido del Consejo Sombrio");
        }
        return removido;
    }

    public boolean esMiembro(Usuario usuario) {
        return miembros.contains(usuario);
    }

    public void mostrarInformacion() {
        System.out.println("\n==========================================");
        System.out.println("        CONSEJO SOMBRIO");
        System.out.println("==========================================");
        System.out.println("ID: " + id);
        System.out.println("Nombre en clave: " + nombreClave);
        System.out.println("Nivel de acceso: " + nivelAcceso);
        System.out.println("Miembros activos: " + miembros.size());
        System.out.println("\nMiembros:");

        if (miembros.isEmpty()) {
            System.out.println("  - No hay miembros registrados");
        } else {
            for (int i = 0; i < miembros.size(); i++) {
                Usuario m = miembros.get(i);
                System.out.println("  " + (i + 1) + ". " + m.getNombre() + " (" + m.getRol() + ")");
            }
        }

        System.out.println("\nPermisos especiales:");
        System.out.println("  - Acceso a registros confidenciales");
        System.out.println("  - Visibilidad de operaciones ocultas");
        System.out.println("  - Control sobre talleres clandestinos");
        System.out.println("  - Informacion sobre planes futuros");
        System.out.println("==========================================\n");
    }

    public void realizarReunion(String agenda) {
        System.out.println("\n==========================================");
        System.out.println("     REUNION DEL CONSEJO SOMBRIO");
        System.out.println("==========================================");
        System.out.println("Nombre en clave: " + nombreClave);
        System.out.println("Asistentes: " + miembros.size());
        System.out.println("\nAgenda:");
        System.out.println(agenda);
        System.out.println("\nEsta reunion no consta en registros oficiales");
        System.out.println("==========================================\n");
    }

    public String getId() { return id; }
    public String getNombreClave() { return nombreClave; }
    public List<Usuario> getMiembros() { return new ArrayList<>(miembros); }
    public int getCantidadMiembros() { return miembros.size(); }
    public int getNivelAcceso() { return nivelAcceso; }

    @Override
    public String toString() {
        return String.format("ConsejoSombrio[ID=%s, Nombre=%s, Miembros=%d]",
                id, nombreClave, miembros.size());
    }
}
