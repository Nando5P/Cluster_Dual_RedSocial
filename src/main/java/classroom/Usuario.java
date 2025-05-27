package classroom;

import java.util.*;

public class Usuario {
    private String nombre;
    private Set<Usuario> seguidos = new HashSet<>();
    private List<Post> posts = new ArrayList<>();

    // Constructor
    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }
    public Set<Usuario> getSeguidos() {
        return seguidos;
    }
    public List<Post> getPosts() {
        return posts;
    }

    // Metodo para seguir y dejar de seguir usuarios, añadir y eliminar posts, listar comentarios
    public void seguir(Usuario u) {
        seguidos.add(u);
    }
    public void dejarDeSeguir(Usuario u) {
        seguidos.remove(u);
    }
    public void añadirPost(Post p) {
        posts.add(p);
    }
    public void eliminarPost(Post p) {
        posts.remove(p);
    }
    public List<Comentario> listarComentarios() {
        List<Comentario> comentarios = new ArrayList<>();
        for (Post p : posts)
            comentarios.addAll(p.getComentariosDeUsuario(this));
        return comentarios;
    }

    // Método sobreescrito para obtener el nombre del usuario
    @Override
    public String toString() {
        return nombre;
    }
}
