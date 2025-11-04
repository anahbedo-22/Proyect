package usuarios;

import enums.EstadoCuenta;

public class AdministradorUsuario extends Usuario {
    private int nivelAcceso;
    private boolean miembroConsejoSombrio;

    public AdministradorUsuario(String nombre, String email, String password) {
        super(nombre, email, password, "ADMIN_USUARIO");
        this.nivelAcceso = 3;
        this.miembroConsejoSombrio = false;
    }

    public AdministradorUsuario(String nombre, String email, String password, int nivelAcceso) {
        this(nombre, email, password);
        setNivelAcceso(nivelAcceso);
    }

    @Override
    public void mostrarPermisos() {
        System.out.println("\n==========================================");
        System.out.println("  PERMISOS DE ADMINISTRADOR DE USUARIOS");
        System.out.println("  Nivel de Acceso: " + nivelAcceso + "/5");
        System.out.println("==========================================");
        System.out.println("- Ver lista de usuarios");
        System.out.println("- Suspender/Reactivar usuarios");
        if (nivelAcceso >= 3) {
            System.out.println("- Modificar datos de usuarios");
        }
        if (nivelAcceso >= 4) {
            System.out.println("- Eliminar usuarios");
            System.out.println("- Crear administradores");
        }
        if (nivelAcceso >= 5 || miembroConsejoSombrio) {
            System.out.println("- Acceso a logs del sistema");
            System.out.println("- ACCESO AL CONSEJO SOMBRIO");
        }
        System.out.println("==========================================\n");
    }

    public boolean suspenderUsuario(Usuario usuario) {
        if (usuario.getEstadoCuenta() == EstadoCuenta.ACTIVA) {
            usuario.setEstadoCuenta(EstadoCuenta.SUSPENDIDA);
            System.out.println("Usuario " + usuario.getNombre() + " ha sido suspendido");
            return true;
        }
        System.out.println("El usuario ya esta suspendido o inactivo");
        return false;
    }

    public boolean reactivarUsuario(Usuario usuario) {
        if (usuario.getEstadoCuenta() != EstadoCuenta.ACTIVA) {
            usuario.setEstadoCuenta(EstadoCuenta.ACTIVA);
            System.out.println("Usuario " + usuario.getNombre() + " ha sido reactivado");
            return true;
        }
        System.out.println("El usuario ya esta activo");
        return false;
    }

    public boolean puedeRealizarAccion(String accion) {
        switch (accion) {
            case "SUSPENDER":
            case "REACTIVAR":
                return nivelAcceso >= 2;
            case "MODIFICAR":
                return nivelAcceso >= 3;
            case "ELIMINAR":
            case "CREAR_ADMIN":
                return nivelAcceso >= 4;
            case "VER_LOGS":
                return nivelAcceso >= 5 || miembroConsejoSombrio;
            default:
                return false;
        }
    }

    public void ascenderAConsejoSombrio(String claveMaestra) {
        this.miembroConsejoSombrio = true;
        this.nivelAcceso = 5;
        System.out.println("Miembro ascendido al Consejo Sombrio");
    }

    public int getNivelAcceso() { return nivelAcceso; }

    public void setNivelAcceso(int nivel) {
        if (nivel >= 1 && nivel <= 5) {
            this.nivelAcceso = nivel;
        } else {
            System.out.println("Nivel invalido. Debe estar entre 1 y 5");
        }
    }

    public boolean esMiembroConsejoSombrio() { return miembroConsejoSombrio; }

    @Override
    public String toString() {
        return String.format("AdministradorUsuario[%s, Nivel=%d, Consejo Sombrio=%s]",
                super.toString(), nivelAcceso, miembroConsejoSombrio);
    }
}
