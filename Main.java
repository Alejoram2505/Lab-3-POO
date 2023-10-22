import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase base para representar un producto en el inventario.
 */
class Producto {
    private static int ultimoId = 0;
    protected int id;
    private String nombre;
    private int cantidadDisponible;
    private int cantidadVendidos;
    private String estado;
    private double precio;

    public Producto(String nombre, int cantidadDisponible, String estado, double precio) {
        this.id = ++ultimoId;
        this.nombre = nombre;
        this.cantidadDisponible = cantidadDisponible;
        this.cantidadVendidos = 0;
        this.estado = estado;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public int getCantidadVendidos() {
        return cantidadVendidos;
    }

    public void setCantidadVendidos(int cantidadVendidos) {
        this.cantidadVendidos = cantidadVendidos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
}

/**
 * Clase que representa productos de la categoría de bebidas.
 */
class Bebidas extends Producto {
    private int mililitros;
    private String tipo;

    public Bebidas(String nombre, int cantidadDisponible, String estado, double precio, int mililitros, String tipo) {
        super(nombre, cantidadDisponible, estado, precio);
        this.mililitros = mililitros;
        this.tipo = tipo;
    }

    public int getMililitros() {
        return mililitros;
    }

    public void setMililitros(int mililitros) {
        this.mililitros = mililitros;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

/**
 * Clase que representa productos de la categoría de snacks.
 */
class Snacks extends Producto {
    private int gramos;
    private String sabor;
    private String tamaño;

    public Snacks(String nombre, int cantidadDisponible, String estado, double precio, int gramos, String sabor, String tamaño) {
        super(nombre, cantidadDisponible, estado, precio);
        this.gramos = gramos;
        this.sabor = sabor;
        this.tamaño = tamaño;
    }

    public int getGramos() {
        return gramos;
    }

    public void setGramos(int gramos) {
        this.gramos = gramos;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }
}

/**
 * Clase que representa productos de la categoría de comida rápida.
 */
class ComidaRapida extends Producto {
    private String tamaño;
    public ComidaRapida(String nombre, int cantidadDisponible, String estado, double precio, String tamaño) {
        super(nombre, cantidadDisponible, estado, precio);
        this.tamaño = tamaño;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }
}

/**
 * Clase que gestiona el inventario de productos y realiza operaciones relacionadas.
 */
class tienda {
       public void cargarProductosDesdeArchivoCSV(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length >= 11) {
                    String categoria = partes[6].trim();

                    if (categoria.equalsIgnoreCase("Bebidas") && partes.length >= 11) {
                        int id = Integer.parseInt(partes[0].trim());
                        String nombre = partes[1].trim();
                        int cantidadDisponible = Integer.parseInt(partes[2].trim());
                        String estado = partes[3].trim();
                        double precio = Double.parseDouble(partes[4].trim());
                        int mililitros = Integer.parseInt(partes[7].trim());
                        String tipo = partes[8].trim();

                        Bebidas bebida = new Bebidas(nombre, cantidadDisponible, estado, precio, mililitros, tipo);
                        bebida.id = id;
                        productos.add(bebida);
                    } else if (categoria.equalsIgnoreCase("Snacks") && partes.length >= 11) {
                        int id = Integer.parseInt(partes[0].trim());
                        String nombre = partes[1].trim();
                        int cantidadDisponible = Integer.parseInt(partes[2].trim());
                        String estado = partes[3].trim();
                        double precio = Double.parseDouble(partes[4].trim());
                        int gramos = Integer.parseInt(partes[5].trim());
                        String sabor = partes[9].trim();
                        String tamaño = partes[10].trim();

                        Snacks snack = new Snacks(nombre, cantidadDisponible, estado, precio, gramos, sabor, tamaño);
                        snack.id = id;
                        productos.add(snack);
                    } else if (categoria.equalsIgnoreCase("ComidaRapida") && partes.length >= 11) {
                        int id = Integer.parseInt(partes[0].trim());
                        String nombre = partes[1].trim();
                        int cantidadDisponible = Integer.parseInt(partes[2].trim());
                        String estado = partes[3].trim();
                        double precio = Double.parseDouble(partes[4].trim());
                        String tamaño = partes[10].trim();

                        ComidaRapida comidaRapida = new ComidaRapida(nombre, cantidadDisponible, estado, precio, tamaño);
                        comidaRapida.id = id;
                        productos.add(comidaRapida);
                    }
                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void mostrarInforme() {
        System.out.println("Listado de categorías con el total de productos:");
        for (String categoria : obtenerCategorias()) {
            System.out.println(categoria + " - " + listarProductosDeCategoria(categoria).size());
        }

        System.out.println("Listado de productos por categoría:");
        for (String categoria : obtenerCategorias()) {
            System.out.println(categoria + ":");
            for (Producto producto : listarProductosDeCategoria(categoria)) {
                System.out.println(producto.getNombre());
            }
        }

        System.out.println("Total de ventas:");
        System.out.println("Ventas totales Q" + calcularVentasTotales());

        for (String categoria : obtenerCategorias()) {
            System.out.println("Porcentaje por categoría " + categoria + ": Q" + calcularComisionPorCategoria(categoria));
        }
    }

    private List<String> obtenerCategorias() {
        List<String> categorias = new ArrayList<>();
        for (Producto producto : productos) {
            String categoria = producto.getClass().getSimpleName();
            if (!categorias.contains(categoria)) {
                categorias.add(categoria);
            }
        }
        return categorias;
    }

     private List<Producto> productos;

    public tienda() {
        productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public Producto buscarProductoPorId(int id) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null;
    }

    public List<Producto> listarProductosDeCategoria(String categoria) {
        List<Producto> productosCategoria = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getClass().getSimpleName().equalsIgnoreCase(categoria)) {
                productosCategoria.add(producto);
            }
        }
        return productosCategoria;
    }

    public double calcularVentasTotales() {
        double totalVentas = 0;
        for (Producto producto : productos) {
            totalVentas += (producto.getPrecio() * producto.getCantidadVendidos());
        }
        return totalVentas;
    }

     public double calcularComisionPorCategoria(String categoria) {
        double totalVentasCategoria = 0;
        for (Producto producto : listarProductosDeCategoria(categoria)) {
            totalVentasCategoria += (producto.getPrecio() * producto.getCantidadVendidos());
        }
        return totalVentasCategoria * 0.2; // 20% de comisión
    }
}


public class Main {
    public static void main(String[] args) {
        tienda tienda = new tienda();
        tienda.cargarProductosDesdeArchivoCSV("productos.csv");

        
        // Ejemplo: Mostrar informe con ventas actuales y comisión por categoría
        tienda.mostrarInforme();
    }
}



