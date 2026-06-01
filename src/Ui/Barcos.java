package Ui;

import java.util.Random;
import java.util.Scanner;

public class Barcos {

    public static void main(String[] args) {

        int tamaño = 8;

        char[][] jugador1 = new char[tamaño][tamaño];
        char[][] jugador2 = new char[tamaño][tamaño];

        
        char[][] barcosJugador1 = new char[tamaño][tamaño];
        char[][] barcosJugador2 = new char[tamaño][tamaño];

        inicializarMatriz(jugador1);
        inicializarMatriz(jugador2);

        inicializarMatriz(barcosJugador1);
        inicializarMatriz(barcosJugador2);

        // Barcos de diferentes tamaños
        int[] barcos = {5, 3, 2};

        colocarTodosLosBarcos(barcosJugador1, barcos);
        colocarTodosLosBarcos(barcosJugador2, barcos);

        Scanner sc = new Scanner(System.in);

        boolean jugando = true;

        while (jugando) {

           //TURNO JUGADOR 1

            System.out.println("\nTABLERO JUGADOR 1");
            mostrarMatriz(jugador1);

            System.out.println("\nJugador 1, intenta encontrar los barcos");

            int fila;
            int columna;

            // VALIDAR FILA
            do {

                System.out.print("Fila (0-" + (tamaño - 1) + "): ");
                fila = sc.nextInt();

                if (fila < 0 || fila >= tamaño) {

                    System.out.println("Esa fila no existe, intenta de nuevo");
                }

            } while (fila < 0 || fila >= tamaño);

            // VALIDAR COLUMNA
            do {

                System.out.print("Columna (0-" + (tamaño - 1) + "): ");
                columna = sc.nextInt();

                if (columna < 0 || columna >= tamaño) {

                    System.out.println("Esa columna no existe, intenta de nuevo");
                }

            } while (columna < 0 || columna >= tamaño);
            
            
            
         // VALIDAR SI YA HABIA ESCOGIDO ESA POSICION
            while (jugador1[fila][columna] == '~' || jugador1[fila][columna] == 'B') {

                System.out.println("Esa coordenada ya fue usada, intenta otra");

                // PEDIR FILA OTRA VEZ
                do {

                    System.out.print("Fila (0-" + (tamaño - 1) + "): ");
                    fila = sc.nextInt();

                    if (fila < 0 || fila >= tamaño) {

                        System.out.println("Esa fila no existe, intenta de nuevo");
                    }

                } while (fila < 0 || fila >= tamaño);

                // PEDIR COLUMNA OTRA VEZ
                do {

                    System.out.print("Columna (0-" + (tamaño - 1) + "): ");
                    columna = sc.nextInt();

                    if (columna < 0 || columna >= tamaño) {

                        System.out.println("Esa columna no existe, intenta de nuevo");
                    }

                } while (columna < 0 || columna >= tamaño);
            }
            
            
            
            
            // SI LE ATINA A UN BARCO
            if (barcosJugador2[fila][columna] == 'B') {

                System.out.println("Le has atinado a un barco!");

                descubrirBarco(barcosJugador2, jugador1, fila, columna);

            } else {

                System.out.println("Ups, esto es agua");

                jugador1[fila][columna] = '~';
            }

            // VERIFICAR SI GANO
            if (ganador(barcosJugador2, jugador1)) {

                System.out.println("\nEL JUGADOR 1 GANO!");

                mostrarMatriz(jugador1);

                jugando = false;

                break;
            }

            //TURNO JUGADOR 2

            System.out.println("\nTABLERO JUGADOR 2");
            mostrarMatriz(jugador2);

            System.out.println("\nJugador 2, intenta encontrar los barcos");

         // VALIDAR FILA
            do {

                System.out.print("Fila (0-" + (tamaño - 1) + "): ");
                fila = sc.nextInt();

                if (fila < 0 || fila >= tamaño) {

                    System.out.println("Esa fila no existe, intenta de nuevo");
                }

            } while (fila < 0 || fila >= tamaño);

            // VALIDAR COLUMNA
            do {

                System.out.print("Columna (0-" + (tamaño - 1) + "): ");
                columna = sc.nextInt();

                if (columna < 0 || columna >= tamaño) {

                    System.out.println("Esa columna no existe, intenta de nuevo");
                }

            } while (columna < 0 || columna >= tamaño);
            
         // VALIDAR SI YA HABIA ESCOGIDO ESA POSICION
            while (jugador2[fila][columna] == '~' || jugador2[fila][columna] == 'B') {

                System.out.println("Esa coordenada ya fue usada, intenta otra");

                // PEDIR FILA OTRA VEZ
                do {

                    System.out.print("Fila (0-" + (tamaño - 1) + "): ");
                    fila = sc.nextInt();

                    if (fila < 0 || fila >= tamaño) {

                        System.out.println("Esa fila no existe, intenta de nuevo");
                    }

                } while (fila < 0 || fila >= tamaño);

                // PEDIR COLUMNA OTRA VEZ
                do {

                    System.out.print("Columna (0-" + (tamaño - 1) + "): ");
                    columna = sc.nextInt();

                    if (columna < 0 || columna >= tamaño) {

                        System.out.println("Esa columna no existe, intenta de nuevo");
                    }

                } while (columna < 0 || columna >= tamaño);
            }
           
            // SI LE ATINA A UN BARCO
            if (barcosJugador1[fila][columna] == 'B') {

                System.out.println("Le has atinado a un barco!");

                descubrirBarco(barcosJugador1, jugador2, fila, columna);

            } else {

                System.out.println("Ups, esto es agua");

                jugador2[fila][columna] = '~';
            }

            // VERIFICAR SI GANO
            if (ganador(barcosJugador1, jugador2)) {

                System.out.println("\nEL JUGADOR 2 GANO!");

                mostrarMatriz(jugador2);

                jugando = false;
            }
        }

        sc.close();
    }

    // Llenar matriz con agua
    public static void inicializarMatriz(char[][] matriz) {

        for (int i = 0; i < matriz.length; i++) {

            for (int j = 0; j < matriz[i].length; j++) {

                matriz[i][j] = 'O';
            }
        }
    }

    // Colocar todos los barcos
    public static void colocarTodosLosBarcos(char[][] matriz, int[] barcos) {

        for (int tamañoBarco : barcos) {

            colocarBarco(matriz, tamañoBarco);
        }
    }

    // Colocar un barco aleatoriamente
    public static void colocarBarco(char[][] matriz, int tamañoBarco) {

        Random random = new Random();

        boolean colocado = false;

        while (!colocado) {

            int fila = random.nextInt(matriz.length);
            int columna = random.nextInt(matriz[0].length);

            // true = horizontal
            // false = vertical
            boolean horizontal = random.nextBoolean();

            // VERIFICAR SI CABE HORIZONTAL
            if (horizontal) {

                if (columna + tamañoBarco <= matriz[0].length) {

                    boolean libre = true;

                    for (int i = 0; i < tamañoBarco; i++) {

                        if (matriz[fila][columna + i] == 'B') {

                            libre = false;
                        }
                    }

                    if (libre) {

                        for (int i = 0; i < tamañoBarco; i++) {

                            matriz[fila][columna + i] = 'B';
                        }

                        colocado = true;
                    }
                }

            } else {

                // VERIFICAR SI CABE VERTICAL
                if (fila + tamañoBarco <= matriz.length) {

                    boolean libre = true;

                    for (int i = 0; i < tamañoBarco; i++) {

                        if (matriz[fila + i][columna] == 'B') {

                            libre = false;
                        }
                    }

                    if (libre) {

                        for (int i = 0; i < tamañoBarco; i++) {

                            matriz[fila + i][columna] = 'B';
                        }

                        colocado = true;
                    }
                }
            }
        }
    }

    // Mostrar matriz
    public static void mostrarMatriz(char[][] matriz) {

        for (int i = 0; i < matriz.length; i++) {

            for (int j = 0; j < matriz[i].length; j++) {

                System.out.print(matriz[i][j] + " ");
            }

            System.out.println();
        }
    }

    // DESCUBRIR TODO EL BARCO
    public static void descubrirBarco(char[][] barcos, char[][] tablero, int fila, int columna) {

        // Descubrir horizontal derecha
        int j = columna;

        while (j < barcos[0].length && barcos[fila][j] == 'B') {

            tablero[fila][j] = 'B';

            j++;
        }

        // Descubrir horizontal izquierda
        j = columna - 1;

        while (j >= 0 && barcos[fila][j] == 'B') {

            tablero[fila][j] = 'B';

            j--;
        }

        // Descubrir vertical abajo
        int i = fila + 1;

        while (i < barcos.length && barcos[i][columna] == 'B') {

            tablero[i][columna] = 'B';

            i++;
        }

        // Descubrir vertical arriba
        i = fila - 1;

        while (i >= 0 && barcos[i][columna] == 'B') {

            tablero[i][columna] = 'B';

            i--;
        }

        tablero[fila][columna] = 'B';
    }

    // VERIFICAR GANADOR
    public static boolean ganador(char[][] barcos, char[][] tablero) {

        for (int i = 0; i < barcos.length; i++) {

            for (int j = 0; j < barcos[i].length; j++) {

                if (barcos[i][j] == 'B' && tablero[i][j] != 'B') {

                    return false;
                }
            }
        }

        return true;
    }
}