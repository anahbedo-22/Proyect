import usuarios.*;
import comercial.*;
import produccion.*;
import ocultos.*;
import enums.*;

import java.time.LocalDate;
import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static Map<String, Usuario> usuarios = new HashMap<>();
    private static List<Producto> productos = new ArrayList<>();
    private static List<Categoria> categorias = new ArrayList<>();
    private static List<Compra> compras = new ArrayList<>();
    private static List<Fabrica> fabricas = new ArrayList<>();
    private static Usuario usuarioActual = null;
    private static Duena sakura = null;
    private static RegistroEsclavos registroEsclavos = null;
    private static ConsejoSombrio consejo = null;

    public static void main(String[] args) {
        inicializarDatos();
        mostrarBienvenida();

        while (true) {
            if (usuarioActual == null) {
                menuLogin();
            } else {
                menuPrincipal();
            }
        }
    }

    private static void inicializarDatos() {
        categorias.add(new Categoria("Labiales", "Labiales de larga duracion"));
        categorias.add(new Categoria("Bases", "Bases liquidas y en polvo"));
        categorias.add(new Categoria("Sombras", "Paletas de sombras"));

        productos.add(new Producto("Labial Rojo Pasion", "Labial mate de larga duracion", 45000, 50, categorias.get(0)));
        productos.add(new Producto("Base Liquida Natural", "Base de cobertura media", 85000, 30, categorias.get(1)));
        productos.add(new Producto("Paleta Sunset", "12 tonos vibrantes", 120000, 20, categorias.get(2)));
        productos.add(new Producto("Labial Nude", "Tono neutro diario", 42000, 60, categorias.get(0)));

        sakura = new Duena("Cabrita Sakura", "sakura@glowup.com", "admin123", "MASTER2024");
        usuarios.put(sakura.getEmail(), sakura);

        AdministradorContenido admin1 = new AdministradorContenido("Maria Lopez", "maria@glowup.com", "maria123");
        usuarios.put(admin1.getEmail(), admin1);

        AdministradorUsuario admin2 = new AdministradorUsuario("Carlos Ruiz", "carlos@glowup.com", "carlos123", 4);
        usuarios.put(admin2.getEmail(), admin2);

        Cliente cliente1 = new Cliente("Ana Garcia", "ana@email.com", "ana123", "Calle 50 #25-30", "3001234567");
        usuarios.put(cliente1.getEmail(), cliente1);

        DesarrolladorProducto dev1 = new DesarrolladorProducto("Sofia Martinez", "sofia@glowup.com", "sofia123", "Labiales", 5);
        usuarios.put(dev1.getEmail(), dev1);

        fabricas.add(new Fabrica("Brasil", "Sao Paulo", 1000, 60));
        fabricas.add(new Fabrica("Colombia", "Bogota", 800, 50));

        consejo = new ConsejoSombrio("OPERACION ELIXIR");
    }

    private static void mostrarBienvenida() {
        System.out.println("\n==========================================");
        System.out.println("          SISTEMA GLOW UP");
        System.out.println("    Luxury Cosmetics Management System");
        System.out.println("==========================================\n");
    }

    private static void menuLogin() {
        System.out.println("\n--- LOGIN ---");
        System.out.println("1. Iniciar sesion");
        System.out.println("2. Registrar nuevo cliente");
        System.out.println("3. Salir");
        System.out.print("Opcion: ");

        int opcion = leerEntero();

        switch (opcion) {
            case 1:
                login();
                break;
            case 2:
                registrarCliente();
                break;
            case 3:
                System.out.println("Gracias por usar Glow Up!");
                System.exit(0);
                break;
            default:
                System.out.println("Opcion invalida");
        }
    }

    private static void login() {
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        Usuario usuario = usuarios.get(email);
        if (usuario != null && usuario.verificarPassword(password)) {
            usuarioActual = usuario;
            System.out.println("\nBienvenido, " + usuario.getNombre() + "!");
            usuario.mostrarPermisos();
        } else {
            System.out.println("Credenciales incorrectas");
        }
    }

    private static void registrarCliente() {
        System.out.println("\n--- REGISTRO DE CLIENTE ---");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        System.out.print("Direccion: ");
        String direccion = sc.nextLine();
        System.out.print("Telefono: ");
        String telefono = sc.nextLine();

        Cliente nuevoCliente = new Cliente(nombre, email, password, direccion, telefono);
        usuarios.put(email, nuevoCliente);
        System.out.println("Cliente registrado exitosamente!");
    }

    private static void menuPrincipal() {
        System.out.println("\n==========================================");
        System.out.println("Usuario: " + usuarioActual.getNombre() + " [" + usuarioActual.getRol() + "]");
        System.out.println("==========================================");

        if (usuarioActual instanceof Cliente) {
            menuCliente();
        } else if (usuarioActual instanceof AdministradorContenido) {
            menuAdminContenido();
        } else if (usuarioActual instanceof AdministradorUsuario) {
            menuAdminUsuario();
        } else if (usuarioActual instanceof Duena) {
            menuDuena();
        } else if (usuarioActual instanceof DesarrolladorProducto) {
            menuDesarrollador();
        }

        System.out.println("0. Cerrar sesion");
        System.out.print("Opcion: ");

        int opcion = leerEntero();
        procesarOpcion(opcion);
    }

    private static void menuCliente() {
        System.out.println("1. Ver catalogo de productos");
        System.out.println("2. Ver mi carrito");
        System.out.println("3. Agregar producto al carrito");
        System.out.println("4. Realizar compra");
        System.out.println("5. Ver mis compras");
        System.out.println("6. Agregar metodo de pago");
    }

    private static void menuAdminContenido() {
        System.out.println("1. Ver productos");
        System.out.println("2. Crear producto");
        System.out.println("3. Editar producto");
        System.out.println("4. Ver categorias");
        System.out.println("5. Crear categoria");
    }

    private static void menuAdminUsuario() {
        System.out.println("1. Listar usuarios");
        System.out.println("2. Suspender usuario");
        System.out.println("3. Reactivar usuario");
        System.out.println("4. Ver estadisticas");
    }

    private static void menuDesarrollador() {
        System.out.println("1. Ver mi portafolio");
        System.out.println("2. Registrar producto desarrollado");
        System.out.println("3. Ver productos disponibles");
    }

    private static void menuDuena() {
        System.out.println("1. Ver todos los productos");
        System.out.println("2. Ver todos los usuarios");
        System.out.println("3. Ver compras realizadas");
        System.out.println("4. Gestionar Consejo Sombrio");
        System.out.println("5. Ver fabricas");
        System.out.println("6. Acceder a Registro de Esclavos");
        System.out.println("7. Revelar historia oculta");
        System.out.println("8. Iniciar proyecto biotecnologia");
    }

    private static void procesarOpcion(int opcion) {
        if (opcion == 0) {
            usuarioActual = null;
            System.out.println("Sesion cerrada");
            return;
        }

        if (usuarioActual instanceof Cliente) {
            procesarOpcionCliente(opcion);
        } else if (usuarioActual instanceof AdministradorContenido) {
            procesarOpcionAdminContenido(opcion);
        } else if (usuarioActual instanceof AdministradorUsuario) {
            procesarOpcionAdminUsuario(opcion);
        } else if (usuarioActual instanceof Duena) {
            procesarOpcionDuena(opcion);
        } else if (usuarioActual instanceof DesarrolladorProducto) {
            procesarOpcionDesarrollador(opcion);
        }
    }

    private static void procesarOpcionCliente(int opcion) {
        Cliente cliente = (Cliente) usuarioActual;

        switch (opcion) {
            case 1:
                mostrarCatalogo();
                break;
            case 2:
                cliente.obtenerCarrito().mostrarContenido();
                break;
            case 3:
                agregarAlCarrito(cliente);
                break;
            case 4:
                realizarCompra(cliente);
                break;
            case 5:
                mostrarComprasCliente(cliente);
                break;
            case 6:
                agregarMetodoPago(cliente);
                break;
            default:
                System.out.println("Opcion invalida");
        }
    }

    private static void procesarOpcionAdminContenido(int opcion) {
        AdministradorContenido admin = (AdministradorContenido) usuarioActual;

        switch (opcion) {
            case 1:
                mostrarCatalogo();
                break;
            case 2:
                crearProducto();
                break;
            case 3:
                editarProducto();
                break;
            case 4:
                listarCategorias();
                break;
            case 5:
                crearCategoria();
                break;
            default:
                System.out.println("Opcion invalida");
        }
    }

    private static void procesarOpcionAdminUsuario(int opcion) {
        AdministradorUsuario admin = (AdministradorUsuario) usuarioActual;

        switch (opcion) {
            case 1:
                listarUsuarios();
                break;
            case 2:
                suspenderUsuario(admin);
                break;
            case 3:
                reactivarUsuario(admin);
                break;
            case 4:
                mostrarEstadisticasUsuarios();
                break;
            default:
                System.out.println("Opcion invalida");
        }
    }

    private static void procesarOpcionDesarrollador(int opcion) {
        DesarrolladorProducto dev = (DesarrolladorProducto) usuarioActual;

        switch (opcion) {
            case 1:
                dev.mostrarPortafolio();
                break;
            case 2:
                registrarProductoDesarrollado(dev);
                break;
            case 3:
                mostrarCatalogo();
                break;
            default:
                System.out.println("Opcion invalida");
        }
    }

    private static void procesarOpcionDuena(int opcion) {
        Duena duena = (Duena) usuarioActual;

        switch (opcion) {
            case 1:
                mostrarCatalogo();
                break;
            case 2:
                listarUsuarios();
                break;
            case 3:
                listarTodasLasCompras();
                break;
            case 4:
                gestionarConsejoSombrio();
                break;
            case 5:
                listarFabricas();
                break;
            case 6:
                accederRegistroEsclavos();
                break;
            case 7:
                System.out.print("Ingrese clave maestra: ");
                String clave = sc.nextLine();
                duena.revelarHistoriaOculta(clave);
                break;
            case 8:
                System.out.print("Ingrese clave maestra: ");
                String clave2 = sc.nextLine();
                duena.iniciarProyectoBiotecnologia(clave2);
                break;
            default:
                System.out.println("Opcion invalida");
        }
    }

    private static void mostrarCatalogo() {
        System.out.println("\n==========================================");
        System.out.println("           CATALOGO DE PRODUCTOS");
        System.out.println("==========================================");

        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            if (p.isActivo()) {
                System.out.println((i + 1) + ". " + p.formatoCatalogo());
            }
        }
        System.out.println("==========================================\n");
    }

    private static void agregarAlCarrito(Cliente cliente) {
        mostrarCatalogo();
        System.out.print("Numero de producto: ");
        int num = leerEntero() - 1;

        if (num >= 0 && num < productos.size()) {
            Producto producto = productos.get(num);
            System.out.print("Cantidad: ");
            int cantidad = leerEntero();
            cliente.obtenerCarrito().agregarProducto(producto, cantidad);
        } else {
            System.out.println("Producto invalido");
        }
    }

    private static void realizarCompra(Cliente cliente) {
        Carrito carrito = cliente.obtenerCarrito();

        if (carrito.estaVacio()) {
            System.out.println("El carrito esta vacio");
            return;
        }

        carrito.mostrarContenido();

        if (cliente.getMetodosPago().isEmpty()) {
            System.out.println("Debe agregar un metodo de pago primero");
            return;
        }

        System.out.println("\nMetodos de pago disponibles:");
        List<MetodoPago> metodos = cliente.getMetodosPago();
        for (int i = 0; i < metodos.size(); i++) {
            System.out.println((i + 1) + ". " + metodos.get(i).formatoSeleccion());
        }

        System.out.print("Seleccione metodo de pago: ");
        int metodoIdx = leerEntero() - 1;

        if (metodoIdx >= 0 && metodoIdx < metodos.size()) {
            Compra compra = new Compra(cliente, carrito, metodos.get(metodoIdx));
            if (compra.procesarPago()) {
                compras.add(compra);
                compra.mostrarDetalle();
                cliente.nuevoCarrito();
            }
        } else {
            System.out.println("Metodo invalido");
        }
    }

    private static void agregarMetodoPago(Cliente cliente) {
        System.out.println("\n--- AGREGAR METODO DE PAGO ---");
        System.out.println("1. Tarjeta de Credito");
        System.out.println("2. Tarjeta de Debito");
        System.out.println("3. PayPal");
        System.out.print("Tipo: ");
        int tipo = leerEntero();

        TipoPago tipoPago;
        switch (tipo) {
            case 1: tipoPago = TipoPago.TARJETA_CREDITO; break;
            case 2: tipoPago = TipoPago.TARJETA_DEBITO; break;
            case 3: tipoPago = TipoPago.PAYPAL; break;
            default:
                System.out.println("Tipo invalido");
                return;
        }

        System.out.print("Titular: ");
        String titular = sc.nextLine();
        System.out.print("Numero (16 digitos): ");
        String numero = sc.nextLine();

        MetodoPago metodo = new MetodoPago(tipoPago, titular, numero);
        cliente.agregarMetodoPago(metodo);
    }

    private static void mostrarComprasCliente(Cliente cliente) {
        System.out.println("\n--- MIS COMPRAS ---");
        List<Compra> comprasCliente = compras.stream()
                .filter(c -> c.getCliente().equals(cliente))
                .toList();

        if (comprasCliente.isEmpty()) {
            System.out.println("No tiene compras realizadas");
        } else {
            for (Compra c : comprasCliente) {
                System.out.println(c.formatoResumido());
            }
        }
    }

    private static void crearProducto() {
        System.out.println("\n--- CREAR PRODUCTO ---");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Descripcion: ");
        String desc = sc.nextLine();
        System.out.print("Precio: ");
        double precio = leerDouble();
        System.out.print("Stock inicial: ");
        int stock = leerEntero();

        listarCategorias();
        System.out.print("Numero de categoria: ");
        int catIdx = leerEntero() - 1;

        if (catIdx >= 0 && catIdx < categorias.size()) {
            Producto nuevo = new Producto(nombre, desc, precio, stock, categorias.get(catIdx));
            productos.add(nuevo);
            System.out.println("Producto creado exitosamente!");
        } else {
            System.out.println("Categoria invalida");
        }
    }

    private static void editarProducto() {
        mostrarCatalogo();
        System.out.print("Numero de producto a editar: ");
        int num = leerEntero() - 1;

        if (num >= 0 && num < productos.size()) {
            Producto p = productos.get(num);
            System.out.print("Nuevo precio (actual: " + p.getPrecio() + "): ");
            double precio = leerDouble();
            p.setPrecio(precio);
            System.out.print("Nuevo stock (actual: " + p.getStock() + "): ");
            int stock = leerEntero();
            p.setStock(stock);
            System.out.println("Producto actualizado!");
        }
    }

    private static void listarCategorias() {
        System.out.println("\n--- CATEGORIAS ---");
        for (int i = 0; i < categorias.size(); i++) {
            System.out.println((i + 1) + ". " + categorias.get(i).getNombre());
        }
    }

    private static void crearCategoria() {
        System.out.print("Nombre de categoria: ");
        String nombre = sc.nextLine();
        System.out.print("Descripcion: ");
        String desc = sc.nextLine();
        categorias.add(new Categoria(nombre, desc));
        System.out.println("Categoria creada!");
    }

    private static void listarUsuarios() {
        System.out.println("\n--- USUARIOS DEL SISTEMA ---");
        for (Usuario u : usuarios.values()) {
            System.out.println(u);
        }
    }

    private static void suspenderUsuario(AdministradorUsuario admin) {
        System.out.print("Email del usuario a suspender: ");
        String email = sc.nextLine();
        Usuario usuario = usuarios.get(email);

        if (usuario != null) {
            admin.suspenderUsuario(usuario);
        } else {
            System.out.println("Usuario no encontrado");
        }
    }

    private static void reactivarUsuario(AdministradorUsuario admin) {
        System.out.print("Email del usuario a reactivar: ");
        String email = sc.nextLine();
        Usuario usuario = usuarios.get(email);

        if (usuario != null) {
            admin.reactivarUsuario(usuario);
        } else {
            System.out.println("Usuario no encontrado");
        }
    }

    private static void mostrarEstadisticasUsuarios() {
        System.out.println("\n--- ESTADISTICAS ---");
        System.out.println("Total usuarios: " + usuarios.size());
        long clientes = usuarios.values().stream().filter(u -> u instanceof Cliente).count();
        long admins = usuarios.values().stream().filter(u -> u instanceof AdministradorContenido || u instanceof AdministradorUsuario).count();
        System.out.println("Clientes: " + clientes);
        System.out.println("Administradores: " + admins);
        System.out.println("Total compras: " + compras.size());
    }

    private static void registrarProductoDesarrollado(DesarrolladorProducto dev) {
        mostrarCatalogo();
        System.out.print("Numero de producto: ");
        int num = leerEntero() - 1;

        if (num >= 0 && num < productos.size()) {
            dev.agregarProductoDesarrollado(productos.get(num));
        }
    }

    private static void listarTodasLasCompras() {
        System.out.println("\n--- TODAS LAS COMPRAS ---");
        if (compras.isEmpty()) {
            System.out.println("No hay compras registradas");
        } else {
            for (Compra c : compras) {
                System.out.println(c.formatoResumido());
            }
        }
    }

    private static void gestionarConsejoSombrio() {
        System.out.println("\n--- CONSEJO SOMBRIO ---");
        System.out.println("1. Ver informacion del consejo");
        System.out.println("2. Agregar miembro");
        System.out.println("3. Realizar reunion");
        System.out.print("Opcion: ");
        int op = leerEntero();

        switch (op) {
            case 1:
                consejo.mostrarInformacion();
                break;
            case 2:
                System.out.print("Email del administrador: ");
                String email = sc.nextLine();
                Usuario u = usuarios.get(email);
                if (u != null) {
                    System.out.print("Clave maestra: ");
                    String clave = sc.nextLine();
                    sakura.agregarAlConsejoSombrio(u, clave);
                }
                break;
            case 3:
                System.out.print("Agenda de la reunion: ");
                String agenda = sc.nextLine();
                consejo.realizarReunion(agenda);
                break;
        }
    }

    private static void listarFabricas() {
        System.out.println("\n--- FABRICAS ---");
        for (Fabrica f : fabricas) {
            f.mostrarInformacion(true);
        }
    }

    private static void accederRegistroEsclavos() {
        System.out.print("Ingrese clave maestra: ");
        String clave = sc.nextLine();

        if (registroEsclavos == null) {
            registroEsclavos = RegistroEsclavos.obtenerInstancia(sakura, clave);
            if (registroEsclavos == null) return;
        }

        System.out.println("\n--- REGISTRO DE ESCLAVOS ---");
        System.out.println("1. Agregar trabajador");
        System.out.println("2. Listar todos");
        System.out.println("3. Ver estadisticas");
        System.out.print("Opcion: ");
        int op = leerEntero();

        switch (op) {
            case 1:
                System.out.print("Nombre: ");
                String nombre = sc.nextLine();
                System.out.print("Pais de origen: ");
                String pais = sc.nextLine();
                System.out.print("Edad: ");
                int edad = leerEntero();
                System.out.println("Fabricas disponibles:");
                for (int i = 0; i < fabricas.size(); i++) {
                    System.out.println((i + 1) + ". " + fabricas.get(i).getId());
                }
                System.out.print("Numero de fabrica: ");
                int fabIdx = leerEntero() - 1;
                if (fabIdx >= 0 && fabIdx < fabricas.size()) {
                    TrabajadorEsclavizado trabajador = new TrabajadorEsclavizado(nombre, pais, edad, fabricas.get(fabIdx).getId());
                    registroEsclavos.agregarTrabajador(trabajador, sakura, clave);
                    fabricas.get(fabIdx).asignarTrabajador(trabajador.getId(), true);
                }
                break;
            case 2:
                List<TrabajadorEsclavizado> trabajadores = registroEsclavos.listarTodos(sakura, clave);
                System.out.println("\nTotal: " + trabajadores.size());
                for (TrabajadorEsclavizado t : trabajadores) {
                    System.out.println(t.formatoResumido());
                }
                break;
            case 3:
                registroEsclavos.mostrarEstadisticas(sakura, clave);
                break;
        }
    }

    private static int leerEntero() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static double leerDouble() {
        try {
            return Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}