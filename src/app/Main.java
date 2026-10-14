package app;

import modelo.Nodo;
import negocio.ArbolBinario;

import java.util.Scanner;

/**
 * Programa principal: menú interactivo para el árbol binario de dependencias universitarias.
 */
public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static ArbolBinario<String> arbol = new ArbolBinario<>();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            System.out.println();
            switch (opcion) {
                case 1 -> crearArbolInicial();
                case 2 -> visualizarArbol();
                case 3 -> consultarNodos();
                case 4 -> consultarHojas();
                case 5 -> consultarAltura();
                case 6 -> consultarGrado();
                case 7 -> verificarHoja();
                case 8 -> ejecutarCasosDePrueba();
                case 0 -> System.out.println("Hasta pronto.");
                default -> System.out.println("Opción inválida.");
            }
            System.out.println();
        } while (opcion != 0);
        sc.close();
    }

    // ---------------------------------------------------------------
    // Menú
    // ---------------------------------------------------------------

    private static void mostrarMenu() {
        System.out.println("===== ORGANIZACIÓN DE DEPARTAMENTOS UNIVERSITARIOS =====");
        System.out.println("1. Crear el árbol inicial");
        System.out.println("2. Visualizar el árbol");
        System.out.println("3. Consultar el número de nodos");
        System.out.println("4. Consultar el número de hojas");
        System.out.println("5. Consultar la altura");
        System.out.println("6. Consultar el grado de un nodo");
        System.out.println("7. Verificar si un nodo es hoja");
        System.out.println("8. Ejecutar casos de prueba obligatorios");
        System.out.println("0. Salir");
    }

    private static void crearArbolInicial() {
        arbol = construirArbolInicial();
        System.out.println("Árbol inicial creado correctamente.");
    }

    private static void visualizarArbol() {
        arbol.mostrarArbol();
    }

    private static void consultarNodos() {
        System.out.println("Número de nodos: " + arbol.contarNodos());
    }

    private static void consultarHojas() {
        System.out.println("Número de hojas: " + arbol.contarHojas());
    }

    private static void consultarAltura() {
        System.out.println("Altura (en aristas): " + arbol.altura());
    }

    private static void consultarGrado() {
        Nodo<String> nodo = pedirNodo();
        if (nodo != null) {
            System.out.println("Grado de " + nodo.getDato() + ": " + nodo.grado());
        }
    }

    private static void verificarHoja() {
        Nodo<String> nodo = pedirNodo();
        if (nodo != null) {
            System.out.println(nodo.getDato() + (nodo.esHoja() ? " ES hoja." : " NO es hoja."));
        }
    }

    private static Nodo<String> pedirNodo() {
        if (arbol.esVacio()) {
            System.out.println("El árbol está vacío. Cree el árbol inicial (opción 1).");
            return null;
        }
        System.out.print("Nombre de la dependencia: ");
        String nombre = sc.nextLine();
        Nodo<String> nodo = arbol.buscar(nombre);
        if (nodo == null) {
            System.out.println("No se encontró la dependencia '" + nombre.trim() + "'.");
        }
        return nodo;
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // ---------------------------------------------------------------
    // Construcción del árbol inicial
    // ---------------------------------------------------------------

    private static ArbolBinario<String> construirArbolInicial() {
        ArbolBinario<String> a = new ArbolBinario<>();
        Nodo<String> universidad = a.crearRaiz("UNIVERSIDAD");

        Nodo<String> fisei = a.agregarIzquierdo(universidad, "FISEI");
        Nodo<String> dide = a.agregarDerecho(universidad, "DIDE");

        Nodo<String> software = a.agregarIzquierdo(fisei, "SOFTWARE");
        a.agregarDerecho(fisei, "TI");

        a.agregarIzquierdo(dide, "INVEST.");
        a.agregarDerecho(dide, "VINCULACIÓN");

        a.agregarIzquierdo(software, "NIVEL1");
        a.agregarDerecho(software, "NIVEL3");
        return a;
    }

    // ---------------------------------------------------------------
    // Casos de prueba obligatorios
    // ---------------------------------------------------------------

    private static void ejecutarCasosDePrueba() {
        System.out.println("---- CASO 1: Árbol vacío ----");
        ArbolBinario<String> vacio = new ArbolBinario<>();
        System.out.println("Nodos: " + vacio.contarNodos() + " (esperado 0)");
        System.out.println("Altura: " + vacio.altura() + " (esperado -1)");
        System.out.println("¿Vacío?: " + vacio.esVacio());

        System.out.println("\n---- CASO 2: Árbol con un solo nodo ----");
        ArbolBinario<String> uno = new ArbolBinario<>();
        uno.crearRaiz("UNIVERSIDAD");
        System.out.println("Nodos: " + uno.contarNodos() + " (esperado 1)");
        System.out.println("Hojas: " + uno.contarHojas() + " (esperado 1)");
        System.out.println("Altura: " + uno.altura() + " (esperado 0)");

        System.out.println("\n---- CASO 3: Árbol inicial ----");
        ArbolBinario<String> inicial = construirArbolInicial();
        inicial.mostrarArbol();
        System.out.println("Nodos: " + inicial.contarNodos() + " (esperado 9)");
        System.out.println("Hojas: " + inicial.contarHojas() + " (esperado 5)");
        System.out.println("Altura: " + inicial.altura() + " (esperado 3)");

        System.out.println("\n---- CASO 4: Insertar en una posición ocupada ----");
        try {
            inicial.agregarIzquierdo(inicial.getRaiz(), "OTRA DEPENDENCIA");
            System.out.println("ERROR: se permitió insertar en posición ocupada.");
        } catch (IllegalStateException e) {
            System.out.println("Inserción rechazada: " + e.getMessage());
        }

        System.out.println("\n---- CASO 5: Grado de FISEI y SOFTWARE ----");
        System.out.println("Grado de FISEI: " + inicial.buscar("FISEI").grado() + " (esperado 2)");
        System.out.println("Grado de SOFTWARE: " + inicial.buscar("SOFTWARE").grado() + " (esperado 2)");
    }
}
