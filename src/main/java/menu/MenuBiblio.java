package menu;

import java.util.Scanner;
import entity.Libro;
import entity.Ejemplar;
import entity.Prestamo;
import gestion.GestionBiblio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MenuBiblio {
    //Intancias
    private static Scanner scanner = new Scanner(System.in);
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("bibliotecaPU");
    private static EntityManager em = emf.createEntityManager();
    private static GestionBiblio gestionBiblio = new GestionBiblio(em);

    //Depende del tipo de usuario se introducirá en uno u otro menu
    public static void ejecutarMenu() {
        String tipoUsuario = obtenerTipoUsuario();
        int opcion;

        do {
            mostrarMenu(tipoUsuario);
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            if (tipoUsuario.equalsIgnoreCase("administrador")) {
                ejecutarOpcionesAdministrador(opcion);
            } else {
                ejecutarOpcionesUsuarioNormal(opcion);
            }

        } while (opcion != 0);
    }

    public static String obtenerTipoUsuario() {
        System.out.println("Bienvenido al sistema de gestión de biblioteca.");
        System.out.print("Ingrese su tipo de usuario (administrador/normal): ");
        String tipoUsuario = scanner.nextLine().toLowerCase();

        while (!tipoUsuario.equals("administrador") && !tipoUsuario.equals("normal")) {
            System.out.print("Tipo de usuario no válido. Ingrese 'administrador' o 'normal': ");
            tipoUsuario = scanner.nextLine().toLowerCase();
        }

        return tipoUsuario;
    }

    public static void mostrarMenu(String tipoUsuario) {
        System.out.println("\n==== MENU DE GESTION DE BIBLIOTECA ====");
        if (tipoUsuario.equalsIgnoreCase("administrador")) {
            System.out.println("1. Gestionar Libros");
            System.out.println("2. Gestionar Ejemplares");
            System.out.println("3. Gestionar Usuarios");
            System.out.println("4. Gestionar Préstamos");
            System.out.println("5. Mostrar Todos los Libros");
            System.out.println("6. Mostrar Todos los Ejemplares");
            System.out.println("7. Mostrar Todos los Usuarios");
            System.out.println("8. Mostrar Todos los Préstamos");
        } else {
            System.out.println("1. Mostrar Libros");
            System.out.println("2. Mostrar Ejemplares");
            System.out.println("3. Mostrar Mis Préstamos");
        }
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public static void ejecutarOpcionesAdministrador(int opcion) {
        switch (opcion) {
            case 1:
                gestionarLibros();
                break;
            case 2:
                gestionarEjemplares();
                break;
            case 3:
                gestionarUsuarios();
                break;
            case 4:
                gestionarPrestamos();
                break;
            case 5:
                mostrarLibros();
                break;
            case 6:
                mostrarEjemplares();
                break;
            case 7:
                mostrarUsuarios();
                break;
            case 8:
                mostrarPrestamos();
                break;
            case 0:
                System.out.println("Saliendo del sistema...");
                break;
            default:
                System.out.println("Opción no válida, intente nuevamente.");
        }
    }

    public static void ejecutarOpcionesUsuarioNormal(int opcion) {
        switch (opcion) {
            case 1:
                mostrarLibros();
                break;
            case 2:
                mostrarEjemplares();
                break;
            case 0:
                System.out.println("Saliendo del sistema...");
                break;
            default:
                System.out.println("Opción no válida, intente nuevamente.");
        }
    }

    // Gestionar Libros
    public static void gestionarLibros() {
        int opcion;
        do {
            System.out.println("\n-- GESTION DE LIBROS --");
            System.out.println("1. Agregar Libro");
            System.out.println("2. Actualizar Libro");
            System.out.println("3. Eliminar Libro");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Ingrese Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Ingrese Autor: ");
                    String autor = scanner.nextLine();

                    // Crear un objeto Libro
                    Libro libro = new Libro(isbn, titulo, autor);

                    // Llamar al servicio con el objeto Libro
                    gestionBiblio.getLibroService().insertarLibro(libro);
                    System.out.println("Libro agregado exitosamente.");
                    break;
                case 2:
                    System.out.print("Ingrese ISBN del libro a actualizar: ");
                    isbn = scanner.nextLine();
                    System.out.print("Ingrese nuevo Título: ");
                    titulo = scanner.nextLine();
                    System.out.print("Ingrese nuevo Autor: ");
                    autor = scanner.nextLine();

                    // Crear un objeto Libro con los nuevos datos
                    libro = new Libro(isbn, titulo, autor);

                    // Llamar al servicio con el objeto Libro
                    gestionBiblio.getLibroService().actualizarLibro(libro);
                    System.out.println("Libro actualizado exitosamente.");
                    break;
                case 3:
                    System.out.print("Ingrese ISBN del libro a eliminar: ");
                    isbn = scanner.nextLine();
                    gestionBiblio.getLibroService().eliminarLibro(isbn);
                    System.out.println("Libro eliminado exitosamente.");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        } while (opcion != 0);
    }

    // Gestionar Ejemplares
    public static void gestionarEjemplares() {
        int opcion;
        do {
            System.out.println("\n-- GESTION DE EJEMPLARES --");
            System.out.println("1. Agregar Ejemplar");
            System.out.println("2. Actualizar Ejemplar");
            System.out.println("3. Eliminar Ejemplar");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    // Agregar Ejemplar
                    System.out.print("Ingrese ISBN del libro: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Ingrese estado (Disponible, Prestado, Dañado): ");
                    String estado = scanner.nextLine();

                    Ejemplar ejemplar = new Ejemplar();
                    //ejemplar.setIsbn(isbn);
                    ejemplar.setEstado(estado);
                    gestionBiblio.getEjemplarService().insertarEjemplar(ejemplar);
                    System.out.println("Ejemplar agregado exitosamente.");
                    break;
                case 2:
                    // Actualizar Ejemplar
                    System.out.print("Ingrese ID del ejemplar a actualizar: ");
                    int idEjemplar = scanner.nextInt();
                    scanner.nextLine();  // Limpiar buffer
                    System.out.print("Ingrese nuevo estado (Disponible, Prestado, Dañado): ");
                    String nuevoEstado = scanner.nextLine();

                    Ejemplar ejemplarActualizar = gestionBiblio.getEjemplarService().buscarPorId(idEjemplar);
                    if (ejemplarActualizar != null) {
                        ejemplarActualizar.setEstado(nuevoEstado);
                        gestionBiblio.getEjemplarService().actualizarEjemplar(ejemplarActualizar);
                        System.out.println("Ejemplar actualizado exitosamente.");
                    } else {
                        System.out.println("Ejemplar no encontrado.");
                    }
                    break;
                case 3:
                    // Eliminar Ejemplar
                    System.out.print("Ingrese ID del ejemplar a eliminar: ");
                    idEjemplar = scanner.nextInt();
                    gestionBiblio.getEjemplarService().eliminarEjemplar(idEjemplar);
                    System.out.println("Ejemplar eliminado exitosamente.");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        } while (opcion != 0);
    }

    // Gestionar Usuarios
    public static void gestionarUsuarios() {
        // Implementar gestión de usuarios
    }

    // Gestionar Préstamos
    public static void gestionarPrestamos() {
        int opcion;
        do {
            System.out.println("\n-- GESTION DE PRESTAMOS --");
            System.out.println("1. Registrar Préstamo");
            System.out.println("2. Registrar Devolución");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    // Registrar Préstamo
                    System.out.print("Ingrese DNI del usuario: ");
                    String dni = scanner.nextLine();
                    System.out.print("Ingrese ISBN del libro: ");
                    String isbnLibro = scanner.nextLine();
                    System.out.print("Ingrese ID del ejemplar: ");
                    int idEjemplarPrestamo = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer

                    // Registrar préstamo
                    gestionBiblio.getPrestamoService().crearPrestamo(new Prestamo());
                    System.out.println("Préstamo registrado exitosamente.");
                    break;
                case 2:
                    // Registrar Devolución
                   /* System.out.print("Ingrese ID del préstamo a devolver: ");
                    int idPrestamo = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer
                    gestionBiblio.getPrestamoService().crearDevolucion(idPrestamo);
                    System.out.println("Devolución registrada exitosamente.");
                    break;*/
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        } while (opcion != 0);
    }

    // Mostrar Libros
    public static void mostrarLibros() {
        gestionBiblio.getLibroService().getListaLibros().forEach(System.out::println);
    }

    // Mostrar Ejemplares
    public static void mostrarEjemplares() {
        gestionBiblio.getEjemplarService().getListaEjemplares().forEach(System.out::println);
    }

    // Mostrar Usuarios
    public static void mostrarUsuarios() {
        gestionBiblio.getUsuarioService().getListaUsuarios().forEach(System.out::println);
    }

    // Mostrar Préstamos
    public static void mostrarPrestamos() {
        gestionBiblio.getPrestamoService().getListaPrestamos().forEach(System.out::println);
    }
}
