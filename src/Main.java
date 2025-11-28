import domain.ArbolAVL;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArbolAVL arbol = new ArbolAVL();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        int dato;

        System.out.println("--- SISTEMA DE GESTIÓN DE ÁRBOL AVL ---");
        System.out.println("Inicializando estructura...");

        do {
            System.out.println("\n---------------- MENU ----------------");
            System.out.println("1. Insertar nodo");
            System.out.println("2. Eliminar nodo");
            System.out.println("3. Buscar nodo");
            System.out.println("4. Mostrar contenido (Ordenado)");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(" Error: Por favor ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el valor a insertar: ");
                    try {
                        dato = Integer.parseInt(scanner.nextLine());
                        arbol.insertar(dato);
                        System.out.println(" -> Valor " + dato + " insertado correctamente.");
                    } catch (NumberFormatException e) {
                        System.out.println(" Error: Debe ingresar un número entero.");
                    }
                    break;

                case 2:
                    System.out.print("Ingrese el valor a eliminar: ");
                    try {
                        dato = Integer.parseInt(scanner.nextLine());
                        if (arbol.buscar(dato)) {
                            arbol.eliminar(dato);
                            System.out.println(" -> Valor " + dato + " eliminado.");
                        } else {
                            System.out.println(" -> El valor no existe en el árbol.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(" Error: Debe ingresar un número entero.");
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el valor a buscar: ");
                    try {
                        dato = Integer.parseInt(scanner.nextLine());
                        boolean encontrado = arbol.buscar(dato);
                        if (encontrado) {
                            System.out.println(" -> RESULTADO: El nodo " + dato + " SÍ existe en el árbol.");
                        } else {
                            System.out.println(" -> RESULTADO: El nodo " + dato + " NO se encuentra.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(" Error: Debe ingresar un número entero.");
                    }
                    break;

                case 4:
                    arbol.imprimirOrdenado();
                    break;

                case 5:
                    System.out.println("Cerrando sistema...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 5);

        scanner.close();
    }
}