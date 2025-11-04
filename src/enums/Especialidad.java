package enums;

public enum Especialidad {
    LABIALES("Especialista en Labiales"),
    BASES("Especialista en Bases"),
    SOMBRAS("Especialista en Sombras"),
    CORRECTORES("Especialista en Correctores"),
    PESTANAS("Especialista en Pestanas"),
    CUIDADO_PIEL("Especialista en Cuidado de la Piel"),
    BIOTECNOLOGIA("Especialista en Biotecnologia Cosmetica");

    private String descripcion;

    Especialidad(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}