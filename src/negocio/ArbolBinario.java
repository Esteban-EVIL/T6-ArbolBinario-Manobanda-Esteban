package negocio;

import modelo.Nodo;

/**
 * Árbol binario genérico con operaciones de inserción, consulta y análisis.
 * El conteo de nodos, de hojas y la altura se implementan de forma recursiva.
 */
public class ArbolBinario<T> {

    private Nodo<T> raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    public Nodo<T> getRaiz() {
        return raiz;
    }

    // ---------------------------------------------------------------
    // Construcción
    // ---------------------------------------------------------------

    /** Crea el nodo raíz. Falla si el árbol ya tiene raíz. */
    public Nodo<T> crearRaiz(T dato) {
        if (raiz != null) {
            throw new IllegalStateException("El árbol ya tiene una raíz.");
        }
        raiz = new Nodo<>(dato);
        return raiz;
    }

    /** Agrega un hijo izquierdo al padre. Falla si la posición está ocupada. */
    public Nodo<T> agregarIzquierdo(Nodo<T> padre, T dato) {
        validarPadre(padre);
        if (padre.getIzquierdo() != null) {
            throw new IllegalStateException(
                "La posición izquierda de '" + padre.getDato() + "' ya está ocupada por '"
                + padre.getIzquierdo().getDato() + "'.");
        }
        Nodo<T> nuevo = new Nodo<>(dato);
        padre.setIzquierdo(nuevo);
        return nuevo;
    }

    /** Agrega un hijo derecho al padre. Falla si la posición está ocupada. */
    public Nodo<T> agregarDerecho(Nodo<T> padre, T dato) {
        validarPadre(padre);
        if (padre.getDerecho() != null) {
            throw new IllegalStateException(
                "La posición derecha de '" + padre.getDato() + "' ya está ocupada por '"
                + padre.getDerecho().getDato() + "'.");
        }
        Nodo<T> nuevo = new Nodo<>(dato);
        padre.setDerecho(nuevo);
        return nuevo;
    }

    private void validarPadre(Nodo<T> padre) {
        if (padre == null) {
            throw new IllegalArgumentException("El nodo padre no puede ser nulo.");
        }
    }

    // ---------------------------------------------------------------
    // Consultas (recursivas)
    // ---------------------------------------------------------------

    public boolean esVacio() {
        return raiz == null;
    }

    public int contarNodos() {
        return contarNodos(raiz);
    }

    private int contarNodos(Nodo<T> nodo) {
        if (nodo == null) return 0;
        return 1 + contarNodos(nodo.getIzquierdo()) + contarNodos(nodo.getDerecho());
    }

    public int contarHojas() {
        return contarHojas(raiz);
    }

    private int contarHojas(Nodo<T> nodo) {
        if (nodo == null) return 0;
        if (nodo.esHoja()) return 1;
        return contarHojas(nodo.getIzquierdo()) + contarHojas(nodo.getDerecho());
    }

    /** Altura en aristas: árbol vacío = -1, un solo nodo = 0. */
    public int altura() {
        return altura(raiz);
    }

    private int altura(Nodo<T> nodo) {
        if (nodo == null) return -1;
        return 1 + Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho()));
    }

    /** Busca un nodo por su dato (sin distinguir mayúsculas). Devuelve null si no existe. */
    public Nodo<T> buscar(T dato) {
        if (dato == null) return null;
        return buscar(raiz, dato.toString().trim());
    }

    private Nodo<T> buscar(Nodo<T> nodo, String texto) {
        if (nodo == null) return null;
        if (String.valueOf(nodo.getDato()).equalsIgnoreCase(texto)) return nodo;
        Nodo<T> enIzquierdo = buscar(nodo.getIzquierdo(), texto);
        if (enIzquierdo != null) return enIzquierdo;
        return buscar(nodo.getDerecho(), texto);
    }

    // ---------------------------------------------------------------
    // Visualización
    // ---------------------------------------------------------------

    /** Muestra la estructura jerárquica en consola. [I] = izquierdo, [D] = derecho. */
    public void mostrarArbol() {
        if (esVacio()) {
            System.out.println("(árbol vacío)");
            return;
        }
        System.out.println(raiz.getDato());
        mostrarHijos(raiz, "");
    }

    private void mostrarHijos(Nodo<T> nodo, String prefijo) {
        Nodo<T> izq = nodo.getIzquierdo();
        Nodo<T> der = nodo.getDerecho();

        if (izq != null) {
            boolean esUltimo = (der == null);
            System.out.println(prefijo + (esUltimo ? "└── " : "├── ") + "[I] " + izq.getDato());
            mostrarHijos(izq, prefijo + (esUltimo ? "    " : "│   "));
        }
        if (der != null) {
            System.out.println(prefijo + "└── " + "[D] " + der.getDato());
            mostrarHijos(der, prefijo + "    ");
        }
    }
}
