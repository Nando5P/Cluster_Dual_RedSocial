package classroom;

import utils.Utils;
import java.util.List;

public class Ejercicio_RedSocial {
    public static void main(String[] args) {
        RedSocial red = new RedSocial();
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- MENÚ RED SOCIAL ---");
            System.out.println("1. Añadir usuario");
            System.out.println("2. Seguir usuario");
            System.out.println("3. Dejar de seguir usuario");
            System.out.println("4. Crear post");
            System.out.println("5. Comentar post");
            System.out.println("6. Mostrar muro");
            System.out.println("7. Sugerencias de amistad");
            System.out.println("8. Salir");

            int opcion = Utils.integer("Elige una opción: ");

            switch (opcion) {
                case 1: // Añadir usuario
                    String nombre = Utils.string("Nombre de usuario: ");
                    if (red.getUsuario(nombre) == null) {
                        red.añadirUsuario(nombre);
                        System.out.println("Usuario añadido.");
                    } else {
                        System.out.println("Ese usuario ya existe.");
                    }
                    break;
                case 2: // Seguir usuario
                    Usuario u1 = seleccionarUsuario(red, "Selecciona tu usuario: ");
                    Usuario u2 = seleccionarUsuario(red, "Selecciona a quién seguir: ");
                    if (u1 != null && u2 != null && !u1.equals(u2)) {
                        u1.seguir(u2);
                        System.out.println(u1.getNombre() + " ahora sigue a " + u2.getNombre());
                    }
                    break;
                case 3: // Dejar de seguir usuario
                    Usuario u3 = seleccionarUsuario(red, "Selecciona tu usuario: ");
                    Usuario u4 = seleccionarUsuario(red, "Selecciona a quién dejar de seguir: ");
                    if (u3 != null && u4 != null && u3.getSeguidos().contains(u4)) {
                        u3.dejarDeSeguir(u4);
                        System.out.println(u3.getNombre() + " ha dejado de seguir a " + u4.getNombre());
                    }
                    break;
                case 4: // Crear post
                    Usuario autor = seleccionarUsuario(red, "Selecciona el usuario que publica: ");
                    if (autor != null) {
                        System.out.println("Tipo de post: 1.Texto 2.Imagen 3.Video");
                        int tipo = Utils.integer("Elige tipo: ");
                        Post post = null;
                        String titulo = Utils.string("Título: ");
                        switch (tipo) {
                            case 1:
                                String contenido = Utils.string("Contenido: ");
                                post = new PostTexto(titulo, contenido);
                                break;
                            case 2:
                                int ancho = Utils.integer("Ancho: ");
                                int alto = Utils.integer("Alto: ");
                                post = new PostImagen(titulo, ancho, alto);
                                break;
                            case 3:
                                String calidad = Utils.string("Calidad: ");
                                int duracion = Utils.integer("Duración (segundos): ");
                                post = new PostVideo(titulo, calidad, duracion);
                                break;
                        }
                        if (post != null) {
                            autor.añadirPost(post);
                            System.out.println("Post creado.");
                        }
                    }
                    break;
                case 5: // Comentar post
                    Usuario comentador = seleccionarUsuario(red, "Selecciona tu usuario: ");
                    if (comentador != null) {
                        Usuario propietario = seleccionarUsuario(red, "Selecciona el propietario del post: ");
                        if (propietario != null && !propietario.getPosts().isEmpty()) {
                            List<Post> posts = propietario.getPosts();
                            Utils.showFromList(posts, false);
                            int idx = Utils.integer("Selecciona el post (número): ");
                            if (Utils.checkSelection(idx, posts.size())) {
                                Post post = posts.get(idx - 1);
                                String texto = Utils.string("Texto del comentario: ");
                                Comentario c = new Comentario(texto, comentador);
                                post.añadirComentario(c);
                                System.out.println("Comentario añadido.");
                            }
                        }
                    }
                    break;
                case 6: // Mostrar muro
                    Usuario usuarioMuro = seleccionarUsuario(red, "Selecciona el usuario: ");
                    if (usuarioMuro != null) {
                        List<Post> muro = red.muro(usuarioMuro);
                        System.out.println("Muro de " + usuarioMuro.getNombre() + ":");
                        Utils.showFromList(muro, true);
                    }
                    break;
                case 7: // Sugerencias de amistad
                    Usuario usuarioSug = seleccionarUsuario(red, "Selecciona el usuario: ");
                    if (usuarioSug != null) {
                        List<Usuario> sugerencias = red.sugerenciasAmistad(usuarioSug);
                        System.out.println("Sugerencias de amistad:");
                        Utils.showFromList(sugerencias, true);
                    }
                    break;
                case 8:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        System.out.println("¡Hasta pronto!");
    }

    private static Usuario seleccionarUsuario(RedSocial red, String mensaje) {
        List<Usuario> usuarios = red.getUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios.");
            return null;
        }
        Utils.showFromList(usuarios, false);
        int idx = Utils.integer(mensaje);
        if (Utils.checkSelection(idx, usuarios.size())) {
            return usuarios.get(idx - 1);
        }
        return null;
    }
}