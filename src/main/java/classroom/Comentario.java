package classroom;

import java.time.LocalDateTime;

public class Comentario {
    private String texto;
    private LocalDateTime fecha;
    private Usuario propietario;

    // Constructor para crear un comentario con texto y propietario
    public Comentario(String texto, Usuario propietario) {
        this.texto = texto;
        this.fecha = LocalDateTime.now();
        this.propietario = propietario;
    }

    // Getters para acceder a los atributos
    public String getTexto() {
        return texto;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public Usuario getPropietario() {
        return propietario;
    }
}
