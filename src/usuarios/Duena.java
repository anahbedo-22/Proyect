package usuarios;

import java.time.LocalDateTime;

public class Duena extends Usuario {
    private String claveMaestra;
    private LocalDateTime fechaCoronacion;
    private String historiaOculta;

    public Duena(String nombre, String email, String password, String claveMaestra) {
        super(nombre, email, password, "DUENA");
        this.claveMaestra = hashClaveMaestra(claveMaestra);
        this.fechaCoronacion = LocalDateTime.now();
        this.historiaOculta = "Desaparicion misteriosa de Isis la de ED - Montanas de Aso, Japon";
    }

    private String hashClaveMaestra(String clave) {
        return "MASTER_" + Integer.toHexString(clave.hashCode());
    }

    public boolean verificarClaveMaestra(String clave) {
        return this.claveMaestra.equals(hashClaveMaestra(clave));
    }

    @Override
    public void mostrarPermisos() {
        System.out.println("\n==========================================");
        System.out.println("       CABRITA SAKURA");
        System.out.println("       ACCESO TOTAL AL SISTEMA");
        System.out.println("==========================================");
        System.out.println("- Control total de usuarios");
        System.out.println("- Gestion completa de productos");
        System.out.println("- Acceso a registros financieros");
        System.out.println("- Administracion del Consejo Sombrio");
        System.out.println("- ACCESO AL REGISTRO DE ESCLAVOS");
        System.out.println("- Control de fabricas clandestinas");
        System.out.println("- Planes de biotecnologia cosmetica");
        System.out.println("==========================================");
        System.out.println("Lema: \"El maquillaje cubre imperfecciones;");
        System.out.println("       el poder borra obstaculos.\"");
        System.out.println("==========================================\n");
    }

    public void revelarHistoriaOculta(String claveMaestra) {
        if (verificarClaveMaestra(claveMaestra)) {
            System.out.println("\n========================================");
            System.out.println("   ARCHIVO CONFIDENCIAL NIVEL 10");
            System.out.println("========================================");
            System.out.println(historiaOculta);
            System.out.println("\nFecha del incidente: " + fechaCoronacion.minusDays(30).toLocalDate());
            System.out.println("Acciones mayoritarias transferidas: " + fechaCoronacion.minusDays(5).toLocalDate());
            System.out.println("Coronacion oficial: " + fechaCoronacion.toLocalDate());
            System.out.println("\nEste documento no existe oficialmente.");
            System.out.println("========================================\n");
        } else {
            System.out.println("ACCESO DENEGADO - Clave maestra incorrecta");
        }
    }

    public void agregarAlConsejoSombrio(Usuario admin, String claveMaestra) {
        if (!verificarClaveMaestra(claveMaestra)) {
            System.out.println("Clave maestra incorrecta");
            return;
        }
        if (admin instanceof AdministradorContenido) {
            ((AdministradorContenido) admin).ascenderAConsejoSombrio(claveMaestra);
        } else if (admin instanceof AdministradorUsuario) {
            ((AdministradorUsuario) admin).ascenderAConsejoSombrio(claveMaestra);
        } else {
            System.out.println("Solo administradores pueden unirse al Consejo Sombrio");
        }
    }

    public void iniciarProyectoBiotecnologia(String claveMaestra) {
        if (verificarClaveMaestra(claveMaestra)) {
            System.out.println("\nIniciando Proyecto: Elixir de Juventud");
            System.out.println("Estado: EN DESARROLLO");
            System.out.println("Inversion asignada: $50,000,000");
            System.out.println("Patente en tramite: BIOTECH-GLW-2025");
        } else {
            System.out.println("Autorizacion denegada");
        }
    }

    public LocalDateTime getFechaCoronacion() { return fechaCoronacion; }

    public String getHistoriaResumida() {
        return "Nueva duena desde " + fechaCoronacion.toLocalDate() + " tras misteriosas circunstancias";
    }

    @Override
    public String toString() {
        return String.format("DUENA[%s, Coronacion=%s]",
                super.toString(), fechaCoronacion.toLocalDate());
    }
}
