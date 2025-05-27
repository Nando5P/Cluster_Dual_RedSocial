package classroom;

import java.time.LocalDateTime;
import java.util.*;

public abstract class Post {
    protected String titulo;
    protected LocalDateTime fecha;
    protected List<Comentario> comentarios = new ArrayList<>();

    // Constructor que inicializa el título y la fecha de creación
    public Post(String titulo) {
        this.titulo = titulo;
        this.fecha = LocalDateTime.now();
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public List<Comentario> getComentarios() {
        return comentarios;
    }

    // Métodos para añadir, eliminar y contar comentarios
    public void añadirComentario(Comentario c) { comentarios.add(c); }
    public void eliminarComentario(Comentario c) { comentarios.remove(c); }
    public int numeroComentarios() { return comentarios.size(); }

    // Método para mostrar el post y sus comentarios
    public List<Comentario> getComentariosDeUsuario(Usuario u) {
        List<Comentario> res = new ArrayList<>();
        for (Comentario c : comentarios)
            if (c.getPropietario().equals(u)) res.add(c);
        return res;
    }
}

// Clases concretas que extienden Post
class PostTexto extends Post {
    private String contenido;

    public PostTexto(String titulo, String contenido) {
        super(titulo);
        this.contenido = contenido;
    }

    public String getContenido() { return contenido; }
    @Override
    public String toString() {
        return "[Texto] " + titulo + ": " + contenido;
    }
}

class PostImagen extends Post {
    private int ancho, alto;

    public PostImagen(String titulo, int ancho, int alto) {
        super(titulo);
        this.ancho = ancho;
        this.alto = alto;
    }

    public int getAncho() { return ancho; }
    public int getAlto() { return alto; }
    public String toString() {
        return "[Imagen] " + titulo + " (" + ancho + "x" + alto + ")";
    }
}

class PostVideo extends Post {
    private String calidad;
    private int duracionSegundos;

    public PostVideo(String titulo, String calidad, int duracionSegundos) {
        super(titulo);
        this.calidad = calidad;
        this.duracionSegundos = duracionSegundos;
    }

    public String getCalidad() { return calidad; }
    public int getDuracionSegundos() { return duracionSegundos; }

    @Override
    public String toString() {
        return "[Video] " + titulo + " (" + calidad + ", " + duracionSegundos + "s)";
    }
}
