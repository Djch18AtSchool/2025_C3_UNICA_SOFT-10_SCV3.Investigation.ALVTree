package domain;

public class ArbolAVL {
    private NodoAVL raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    // --- BÚSQUEDA (Requerimiento: igual a un BST normal) ---
    public boolean buscar(int dato) {
        return buscarRecursivo(raiz, dato);
    }

    private boolean buscarRecursivo(NodoAVL nodo, int dato) {
        if (nodo == null) return false;
        if (dato == nodo.dato) return true;

        if (dato < nodo.dato) {
            return buscarRecursivo(nodo.izquierda, dato);
        } else {
            return buscarRecursivo(nodo.derecha, dato);
        }
    }

    // --- INSERCIÓN ---
    public void insertar(int dato) {
        raiz = insertarRecursivo(raiz, dato);
    }

    private NodoAVL insertarRecursivo(NodoAVL nodo, int dato) {
        // 1. Inserción normal de BST
        if (nodo == null) {
            return new NodoAVL(dato);
        }

        if (dato < nodo.dato) {
            nodo.izquierda = insertarRecursivo(nodo.izquierda, dato);
        } else if (dato > nodo.dato) {
            nodo.derecha = insertarRecursivo(nodo.derecha, dato);
        } else {
            return nodo; // No se permiten duplicados
        }

        // 2. Actualizar altura del nodo actual
        nodo.actualizarAltura();

        // 3. Obtener factor de balance para ver si se desbalanceó
        int balance = NodoAVL.obtenerBalance(nodo);

        // 4. Casos de Rotación

        // Caso Izquierda-Izquierda
        if (balance > 1 && dato < nodo.izquierda.dato) {
            return NodoAVL.rotarDerecha(nodo);
        }

        // Caso Derecha-Derecha
        if (balance < -1 && dato > nodo.derecha.dato) {
            return NodoAVL.rotarIzquierda(nodo);
        }

        // Caso Izquierda-Derecha (Zig-Zag)
        if (balance > 1 && dato > nodo.izquierda.dato) {
            return NodoAVL.rotarIzquierdaDerecha(nodo);
        }

        // Caso Derecha-Izquierda (Zig-Zag)
        if (balance < -1 && dato < nodo.derecha.dato) {
            return NodoAVL.rotarDerechaIzquierda(nodo);
        }

        return nodo; // Retorna el nodo sin cambios si está balanceado
    }

    // --- ELIMINACIÓN ---
    public void eliminar(int dato) {
        raiz = eliminarRecursivo(raiz, dato);
    }

    private NodoAVL eliminarRecursivo(NodoAVL nodo, int dato) {
        // 1. Eliminación normal de BST
        if (nodo == null) return nodo;

        if (dato < nodo.dato) {
            nodo.izquierda = eliminarRecursivo(nodo.izquierda, dato);
        } else if (dato > nodo.dato) {
            nodo.derecha = eliminarRecursivo(nodo.derecha, dato);
        } else {
            // Nodo encontrado. Procedemos a borrar.
            // Caso 1 o 2: Sin hijos o un solo hijo
            if ((nodo.izquierda == null) || (nodo.derecha == null)) {
                NodoAVL temp = (null == nodo.izquierda) ? nodo.derecha : nodo.izquierda;
                if (temp == null) { // Caso: Sin hijos
                    temp = nodo;
                    nodo = null;
                } else { // Caso: Un hijo
                    nodo = temp; // Copiar contenido del hijo no vacío
                }
            } else {
                // Caso 3: Dos hijos. Obtener el sucesor (menor del subárbol derecho)
                NodoAVL temp = obtenerNodoMinimo(nodo.derecha);
                nodo.dato = temp.dato; // Copiar dato del sucesor
                nodo.derecha = eliminarRecursivo(nodo.derecha, temp.dato); // Eliminar sucesor
            }
        }

        // Si el árbol tenía un solo nodo y se borró
        if (nodo == null) return null;

        // 2. Actualizar altura
        nodo.actualizarAltura();

        // 3. Balancear (Igual que en inserción, pero verificando balance del hijo)
        int balance = NodoAVL.obtenerBalance(nodo);

        // Caso Izquierda-Izquierda
        if (balance > 1 && NodoAVL.obtenerBalance(nodo.izquierda) >= 0) {
            return NodoAVL.rotarDerecha(nodo);
        }

        // Caso Izquierda-Derecha
        if (balance > 1 && NodoAVL.obtenerBalance(nodo.izquierda) < 0) {
            return NodoAVL.rotarIzquierdaDerecha(nodo);
        }

        // Caso Derecha-Derecha
        if (balance < -1 && NodoAVL.obtenerBalance(nodo.derecha) <= 0) {
            return NodoAVL.rotarIzquierda(nodo);
        }

        // Caso Derecha-Izquierda
        if (balance < -1 && NodoAVL.obtenerBalance(nodo.derecha) > 0) {
            return NodoAVL.rotarDerechaIzquierda(nodo);
        }

        return nodo;
    }

    private NodoAVL obtenerNodoMinimo(NodoAVL nodo) {
        NodoAVL actual = nodo;
        while (actual.izquierda != null) {
            actual = actual.izquierda;
        }
        return actual;
    }

    // --- VISUALIZACIÓN ---
    // Recorrido In-Order para mostrar datos ordenados
    public void imprimirOrdenado() {
        System.out.print("Contenido del árbol (In-Order): ");
        inOrderRecursivo(raiz);
        System.out.println();
    }

    private void inOrderRecursivo(NodoAVL nodo) {
        if (nodo != null) {
            inOrderRecursivo(nodo.izquierda);
            System.out.print(nodo.dato + " ");
            inOrderRecursivo(nodo.derecha);
        }
    }
}
