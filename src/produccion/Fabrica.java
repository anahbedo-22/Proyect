package produccion;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Fabrica {
    private String id;
    private String pais;
    private String ciudad;
    private int capacidadProduccion;
    private int nivelAutomatizacion;
    private List<String> trabajadoresAsignados;
    private boolean esLegal;

    public Fabrica(String pais, String ciudad, int capacidadProduccion, int nivelAutomatizacion) {
        this.id = "FAB-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.pais = pais;
        this.ciudad = ciudad;
        this.capacidadProduccion = capacidadProduccion;
        this.nivelAutomatizacion = Math.min(Math.max(nivelAutomatizacion, 0), 100);
        this.trabajadoresAsignados = new ArrayList<>();
        this.esLegal = true;
    }

    public void asignarTrabajador(String trabajadorId, boolean esEsclavo) {
        trabajadoresAsignados.add(trabajadorId);
        if (esEsclavo) {
            this.esLegal = false;
            System.out.println("ALERTA: Trabajador esclavizado asignado a " + id);
        } else {
            System.out.println("Trabajador asignado a fabrica " + id);
        }
    }

    public boolean removerTrabajador(String trabajadorId) {
        boolean removido = trabajadoresAsignados.remove(trabajadorId);
        if (removido) {
            System.out.println("Trabajador removido de fabrica " + id);
        }
        return removido;
    }

    public int calcularProduccionEfectiva() {
        int factorTrabajadores = trabajadoresAsignados.size() * 50;
        int factorAutomatizacion = (capacidadProduccion * nivelAutomatizacion) / 100;
        return factorTrabajadores + factorAutomatizacion;
    }

    public boolean puedeProducir(int cantidad, int dias) {
        return calcularProduccionEfectiva() * dias >= cantidad;
    }

    public void mostrarInformacion(boolean mostrarDetallesOcultos) {
        System.out.println("\n==========================================");
        System.out.println("      INFORMACION DE FABRICA");
        System.out.println("==========================================");
        System.out.println("ID: " + id);
        System.out.println("Ubicacion: " + ciudad + ", " + pais);
        System.out.println("Capacidad de produccion: " + capacidadProduccion + " unidades/dia");
        System.out.println("Nivel de automatizacion: " + nivelAutomatizacion + "%");
        System.out.println("Trabajadores asignados: " + trabajadoresAsignados.size());
        System.out.println("Produccion efectiva: " + calcularProduccionEfectiva() + " unidades/dia");

        if (mostrarDetallesOcultos) {
            System.out.println("\nINFORMACION CONFIDENCIAL");
            System.out.println("Estado legal: " + (esLegal ? "LEGAL" : "ILEGAL - TRABAJO ESCLAVO"));
            if (!esLegal) {
                System.out.println("Esta fabrica utiliza trabajo esclavizado");
            }
        }
        System.out.println("==========================================\n");
    }

    public void marcarComoLegal() { this.esLegal = true; }
    public void marcarComoIlegal() { this.esLegal = false; }

    public String getId() { return id; }
    public String getPais() { return pais; }
    public String getCiudad() { return ciudad; }
    public int getCapacidadProduccion() { return capacidadProduccion; }

    public void setCapacidadProduccion(int capacidad) {
        if (capacidad > 0) {
            this.capacidadProduccion = capacidad;
        }
    }

    public int getNivelAutomatizacion() { return nivelAutomatizacion; }

    public void setNivelAutomatizacion(int nivel) {
        this.nivelAutomatizacion = Math.min(Math.max(nivel, 0), 100);
    }

    public List<String> getTrabajadoresAsignados() { return new ArrayList<>(trabajadoresAsignados); }
    public int getCantidadTrabajadores() { return trabajadoresAsignados.size(); }
    public boolean isEsLegal() { return esLegal; }

    @Override
    public String toString() {
        return String.format("Fabrica[ID=%s, Ubicacion=%s-%s, Capacidad=%d, Automatizacion=%d%%, Trabajadores=%d, Legal=%s]",
                id, ciudad, pais, capacidadProduccion, nivelAutomatizacion,
                trabajadoresAsignados.size(), esLegal);
    }
}