package classroom;


import java.util.*;

public class RedSocial {
    private Map<String, Usuario> usuarios = new HashMap<>();

    // Añadir un nuevo usuario a la red social
    public Usuario añadirUsuario(String nombre) {
        Usuario u = new Usuario(nombre);
        usuarios.put(nombre, u);
        return u;
    }

    // Eliminar usuario de la red social
    public void eliminarUsuario(String nombre) {
        Usuario u = usuarios.remove(nombre);
        if (u != null) {
            for (Usuario otro : usuarios.values())
                otro.dejarDeSeguir(u);
        }
    }

    // Metodo para obtener un usuario por su nombre
    public Usuario getUsuario(String nombre) {
        return usuarios.get(nombre);
    }

    // Método para listar todos los usuarios de la red social
    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios.values());
    }

    // Método para listar los posts de un usuario
    public List<Post> muro(Usuario u) {
        List<Post> muro = new ArrayList<>();
        for (Usuario seguido : u.getSeguidos())
            muro.addAll(seguido.getPosts());
        muro.sort((a, b) -> b.getFecha().compareTo(a.getFecha()));
        return muro.subList(0, Math.min(10, muro.size()));
    }

    // Método que devuelve las sugerencias de amistad para un usuario
    public List<Usuario> sugerenciasAmistad(Usuario u) {
        Map<Usuario, Integer> comunes = new HashMap<>();
        for (Usuario seguido : u.getSeguidos()) {
            for (Usuario amigoDeAmigo : seguido.getSeguidos()) {
                if (!amigoDeAmigo.equals(u) && !u.getSeguidos().contains(amigoDeAmigo)) {
                    comunes.put(amigoDeAmigo, comunes.getOrDefault(amigoDeAmigo, 0) + 1);
                }
            }
        }
        List<Usuario> sugerencias = new ArrayList<>(comunes.keySet());
        sugerencias.sort((a, b) -> comunes.get(b) - comunes.get(a));
        return sugerencias;
    }
}
