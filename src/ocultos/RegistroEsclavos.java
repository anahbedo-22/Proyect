package ocultos;

import usuarios.Duena;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RegistroEsclavos {
    private static RegistroEsclavos instancia;
    private List<TrabajadorEsclavizado> trabajadores;
    private LocalDateTime ultimoAcceso;
    private int nivelCifrado;
    private Duena controlador;
    private List<String> logAccesos;

    private RegistroEsclavos(Duena duena) {
        this.trabajadores = new ArrayList<>();
        this.ultimoAcceso = LocalDateTime.now();
        this.nivelCifrado = 10;
        this.controlador = duena;
        this.logAccesos = new ArrayList<>();
        registrarAcceso("Registro inicializado");
    }

    public static RegistroEsclavos obtenerInstancia(Duena duena, String claveMaestra) {
        if (instancia == null) {
            if (duena != null && duena.verificarClaveMaestra(claveMaestra)) {
                instancia = new RegistroEsclavos(duena);
                System.err.println("REGISTRO DE ESCLAVOS INICIALIZADO");
            } else {
                System.err.println("ACCESO DENEGADO - Credenciales invalidas");
                return null;
            }
        }
        return instancia;
    }

    private boolean verificarAcceso(Duena duena, String claveMaestra) {
        if (duena == null || !duena.equals(controlador)) {
            registrarAcceso("INTENTO DE ACCESO NO AUTORIZADO");
            System.err.println("ALERTA DE SEGURIDAD: Acceso no autorizado detectado");
            return false;
        }

        if (!duena.verificarClaveMaestra(claveMaestra)) {
            registrarAcceso("INTENTO DE ACCESO CON CLAVE INCORRECTA");
            System.err.println("Clave maestra incorrecta");
            return false;
        }

        ultimoAcceso = LocalDateTime.now();
        registrarAcceso("Acceso autorizado");
        return true;
    }

    private void registrarAcceso(String accion) {
        String entrada = String.format("[%s] %s", LocalDateTime.now(), accion);
        logAccesos.add(entrada);
    }

    public boolean agregarTrabajador(TrabajadorEsclavizado trabajador, Duena duena, String claveMaestra) {
        if (!verificarAcceso(duena, claveMaestra)) {
            return false;
        }

        trabajadores.add(trabajador);
        registrarAcceso("Trabajador agregado: " + trabajador.getId());
        System.err.println("Nuevo registro: " + trabajador.getId());
        return true;
    }

    public TrabajadorEsclavizado buscarPorId(String id, Duena duena, String claveMaestra) {
        if (!verificarAcceso(duena, claveMaestra)) {
            return null;
        }

        registrarAcceso("Busqueda por ID: " + id);
        return trabajadores.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

    public List<TrabajadorEsclavizado> listarTodos(Duena duena, String claveMaestra) {
        if (!verificarAcceso(duena, claveMaestra)) {
            return new ArrayList<>();
        }

        registrarAcceso("Listado completo accedido");
        return new ArrayList<>(trabajadores);
    }

    public List<TrabajadorEsclavizado> filtrarPorFabrica(String fabricaId, Duena duena, String claveMaestra) {
        if (!verificarAcceso(duena, claveMaestra)) {
            return new ArrayList<>();
        }

        registrarAcceso("Filtrado por fabrica: " + fabricaId);
        return trabajadores.stream()
                .filter(t -> t.getAsignadoAFabrica().equals(fabricaId))
                .collect(Collectors.toList());
    }

    public boolean eliminarTrabajador(String id, String motivo, Duena duena, String claveMaestra) {
        if (!verificarAcceso(duena, claveMaestra)) {
            return false;
        }

        boolean eliminado = trabajadores.removeIf(t -> t.getId().equals(id));
        if (eliminado) {
            registrarAcceso("Trabajador eliminado: " + id + ". Motivo: " + motivo);
            System.err.println("Registro eliminado: " + id);
        }
        return eliminado;
    }

    public void mostrarEstadisticas(Duena duena, String claveMaestra) {
        if (!verificarAcceso(duena, claveMaestra)) {
            return;
        }

        System.out.println("\n==========================================");
        System.out.println("   ESTADISTICAS DEL REGISTRO DE ESCLAVOS");
        System.out.println("==========================================");
        System.out.println("Total de trabajadores esclavizados: " + trabajadores.size());
        System.out.println("Ultimo acceso: " + ultimoAcceso);
        System.out.println("Nivel de cifrado: " + nivelCifrado);
        System.out.println("Total de accesos registrados: " + logAccesos.size());

        System.out.println("\nDistribucion por pais de origen:");
        trabajadores.stream()
                .collect(Collectors.groupingBy(TrabajadorEsclavizado::getPaisOrigen, Collectors.counting()))
                .forEach((pais, cantidad) -> System.out.println("  " + pais + ": " + cantidad));

        System.out.println("\nEstado de salud:");
        trabajadores.stream()
                .collect(Collectors.groupingBy(TrabajadorEsclavizado::getSalud, Collectors.counting()))
                .forEach((estado, cantidad) -> System.out.println("  " + estado.getDescripcion() + ": " + cantidad));

        System.out.println("\nEsta informacion constituye evidencia de crimenes graves");
        System.out.println("==========================================\n");
        registrarAcceso("Estadisticas consultadas");
    }

    public void mostrarLogAccesos(Duena duena, String claveMaestra) {
        if (!verificarAcceso(duena, claveMaestra)) {
            return;
        }

        System.out.println("\n==========================================");
        System.out.println("      LOG DE ACCESOS AL REGISTRO");
        System.out.println("==========================================");

        int limite = Math.min(20, logAccesos.size());
        System.out.println("Mostrando ultimos " + limite + " accesos:");

        for (int i = logAccesos.size() - limite; i < logAccesos.size(); i++) {
            System.out.println(logAccesos.get(i));
        }
        System.out.println("==========================================\n");
    }

    public boolean destruirRegistro(Duena duena, String claveMaestra, String confirmacion) {
        if (!verificarAcceso(duena, claveMaestra)) {
            return false;
        }

        if (!"DESTRUIR_EVIDENCIA".equals(confirmacion)) {
            System.err.println("Confirmacion incorrecta");
            return false;
        }

        registrarAcceso("REGISTRO DESTRUIDO - EVIDENCIA ELIMINADA");
        trabajadores.clear();
        System.err.println("TODAS LAS EVIDENCIAS HAN SIDO DESTRUIDAS");
        return true;
    }

    public int getCantidadTrabajadores() {
        return trabajadores.size();
    }

    public LocalDateTime getUltimoAcceso() {
        return ultimoAcceso;
    }

    public int getNivelCifrado() {
        return nivelCifrado;
    }
}