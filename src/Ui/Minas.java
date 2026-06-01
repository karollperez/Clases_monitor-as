package Ui;

import java.util.Scanner;

public class Minas {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[][] minas = new int[5][5];
        boolean[][] abiertas = new boolean[5][5];
        boolean[][] banderas = new boolean[5][5];

        int minasColocadas = 0;
        int casillasPorAbrir = 22;

        boolean primerMovimiento = true;
        boolean perdio = false;

        System.out.println("=== BUSCAMINAS ===");

        while (!perdio && casillasPorAbrir > 0) {

            // Mostrar tablero
            System.out.println("\n----- TABLERO -----");

            for (int i = 0; i < 5; i++) {

                for (int j = 0; j < 5; j++) {

                    if (banderas[i][j]) {

                        System.out.print("# ");
                    }

                    else if (!abiertas[i][j]) {

                        System.out.print("- ");
                    }

                    else {

                        System.out.print(contarMinas(i, j, minas) + " ");
                    }
                }

                System.out.println();
            }

            System.out.println("\n1. Destapar casilla");
            System.out.println("2. Poner/Quitar bandera");
            System.out.print("Seleccione una opción: ");

            // Validar opción
            if (!sc.hasNextInt()) {

                System.out.println("Opción inválida, intente nuevamente.");
                sc.next();
                continue;
            }

            int opcion = sc.nextInt();

            if (opcion != 1 && opcion != 2) {

                System.out.println("Opción inválida, intente nuevamente.");
                continue;
            }

            // Validar fila
            System.out.print("Fila (0-4): ");

            if (!sc.hasNextInt()) {

                System.out.println("Fila inválida, intente nuevamente.");
                sc.next();
                continue;
            }

            int fila = sc.nextInt();

            // Validar columna
            System.out.print("Columna (0-4): ");

            if (!sc.hasNextInt()) {

                System.out.println("Columna inválida, intente nuevamente.");
                sc.next();
                continue;
            }

            int columna = sc.nextInt();

            // Validar rango
            if (fila < 0 || fila > 4 || columna < 0 || columna > 4) {

                System.out.println("Posición inválida, intente nuevamente.");
                continue;
            }

            // Colocar minas después del primer movimiento
            if (primerMovimiento) {

                while (minasColocadas < 3) {

                    int f = (int) (Math.random() * 5);
                    int c = (int) (Math.random() * 5);

                    if ((f == fila && c == columna) || minas[f][c] == 1) {

                        continue;
                    }

                    minas[f][c] = 1;
                    minasColocadas++;
                }

                primerMovimiento = false;
            }

            // DESTAPAR
            if (opcion == 1) {

                if (abiertas[fila][columna]) {

                    System.out.println("La casilla ya está abierta.");
                    continue;
                }

                if (banderas[fila][columna]) {

                    System.out.println("Primero quite la bandera.");
                    continue;
                }

                if (minas[fila][columna] == 1) {

                    perdio = true;
                }

                else {

                    abiertas[fila][columna] = true;
                    casillasPorAbrir--;

                    int numero = contarMinas(fila, columna, minas);

                    // Abrir vecinos si no hay minas alrededor
                    if (numero == 0) {

                        for (int i = fila - 1; i <= fila + 1; i++) {

                            for (int j = columna - 1; j <= columna + 1; j++) {

                                if (i >= 0 && i < 5 && j >= 0 && j < 5) {

                                    if (!abiertas[i][j]
                                            && !banderas[i][j]
                                            && minas[i][j] == 0) {

                                        abiertas[i][j] = true;
                                        casillasPorAbrir--;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // BANDERA
            else {

                if (abiertas[fila][columna]) {

                    System.out.println("No puede poner una bandera en una casilla abierta.");
                }

                else {

                    banderas[fila][columna] = !banderas[fila][columna];

                    if (banderas[fila][columna]) {

                        System.out.println("Bandera colocada.");
                    }

                    else {

                        System.out.println("Bandera retirada.");
                    }
                }
            }
        }

        // Tablero final
        System.out.println("\n----- TABLERO FINAL -----");

        for (int i = 0; i < 5; i++) {

            for (int j = 0; j < 5; j++) {

                if (minas[i][j] == 1) {

                    System.out.print("* ");
                }

                else {

                    System.out.print(contarMinas(i, j, minas) + " ");
                }
            }

            System.out.println();
        }

        if (perdio) {

            System.out.println("\n¡BOOM! Pisaste una mina.");
        }

        else {

            System.out.println("\n¡FELICIDADES! Ganaste.");
        }

        sc.close();
    }

    public static int contarMinas(int fila, int columna, int[][] minas) {

        int contador = 0;

        for (int i = fila - 1; i <= fila + 1; i++) {

            for (int j = columna - 1; j <= columna + 1; j++) {

                if (i >= 0 && i < 5 && j >= 0 && j < 5) {

                    if (minas[i][j] == 1) {

                        contador++;
                    }
                }
            }
        }

        return contador;
    }
}