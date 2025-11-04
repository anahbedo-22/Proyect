package usuarios;

import java.time.LocalDateTime;
import java.util.UUID;
import enums.EstadoCuenta;

public abstract class Usuario {
    protected String id;
    protected String nombre;
    protected String email;
    protected String passwordHash;
    protected String rol;
    protected LocalDateTime fechaRegistro;
    protected EstadoCuenta estadoCuenta;

    public Usuario(String nombre, String email, String password, String rol) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.email = email;
        this.passwordHash = hashPassword(password);
        this.rol = rol;
        this.fechaRegistro = LocalDateTime.now();
        this.estadoCuenta = EstadoCuenta.ACTIVA;
    }

    private String hashPassword(String password) {
        return Integer.toString(password.hashCode());
    }

    public boolean verificarPassword(String password) {
        return this.passwordHash.equals(hashPassword(password));
    }

    public abstract void mostrarPermisos();

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getRol() { return rol; }
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public EstadoCuenta getEstadoCuenta() { return estadoCuenta; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEmail(String email) { this.email = email; }
    public void setEstadoCuenta(EstadoCuenta estado) { this.estadoCuenta = estado; }

    @Override
    public String toString() {
        return String.format("Usuario[ID=%s, Nombre=%s, Email=%s, Rol=%s, Estado=%s]",
                id, nombre, email, rol, estadoCuenta);
    }
}