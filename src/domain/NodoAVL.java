package domain;

public class NodoAVL {
    int dato;
    int altura;
    NodoAVL izquierda;
    NodoAVL derecha;

    public NodoAVL(int dato) {
        this.dato = dato;
        this.altura = 1; // Un nodo nuevo es una hoja, altura inicial 1
        this.izquierda = null;
        this.derecha = null;
    }

    // --- Métodos Auxiliares para Altura y Balance ---

    // Obtener altura de forma segura (maneja nulos)
    public static int obtenerAltura(NodoAVL n) {
        return (n == null) ? 0 : n.altura;
    }

    // Calcular el factor de balance
    public static int obtenerBalance(NodoAVL n) {
        return (n == null) ? 0 : obtenerAltura(n.izquierda) - obtenerAltura(n.derecha);
    }

    // Actualizar altura basada en los hijos
    public void actualizarAltura() {
        this.altura = 1 + Math.max(obtenerAltura(this.izquierda), obtenerAltura(this.derecha));
    }

    // --- ALGORITMOS DE ROTACIÓN (Requerimiento del estudio de caso) ---

    // 1. Rotación Simple a la Derecha (Caso Izquierda-Izquierda)
    public static NodoAVL rotarDerecha(NodoAVL y) {
        NodoAVL x = y.izquierda;
        NodoAVL T2 = x.derecha;

        // Realizar rotación
        x.derecha = y;
        y.izquierda = T2;

        // Actualizar alturas (primero y, luego x porque x es la nueva raíz)
        y.actualizarAltura();
        x.actualizarAltura();

        return x; // Retorna la nueva raíz
    }

    // 2. Rotación Simple a la Izquierda (Caso Derecha-Derecha)
    public static NodoAVL rotarIzquierda(NodoAVL x) {
        NodoAVL y = x.derecha;
        NodoAVL T2 = y.izquierda;

        // Realizar rotación
        y.izquierda = x;
        x.derecha = T2;

        // Actualizar alturas
        x.actualizarAltura();
        y.actualizarAltura();

        return y; // Retorna la nueva raíz
    }

    // 3. Rotación Doble Izquierda-Derecha
    // Primero rota el hijo a la izquierda, luego la raíz a la derecha
    public static NodoAVL rotarIzquierdaDerecha(NodoAVL nodo) {
        nodo.izquierda = rotarIzquierda(nodo.izquierda);
        return rotarDerecha(nodo);
    }

    // 4. Rotación Doble Derecha-Izquierda
    // Primero rota el hijo a la derecha, luego la raíz a la izquierda
    public static NodoAVL rotarDerechaIzquierda(NodoAVL nodo) {
        nodo.derecha = rotarDerecha(nodo.derecha);
        return rotarIzquierda(nodo);
    }
}
