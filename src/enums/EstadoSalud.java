package enums;

public enum EstadoSalud {
    CRITICO("Critico"),
    MALO("Malo"),
    REGULAR("Regular"),
    ESTABLE("Estable");

    private String descripcion;

    EstadoSalud(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
