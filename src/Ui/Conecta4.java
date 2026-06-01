package Ui;

import java.util.Scanner;

public class Conecta4 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int tamaño = 4;
        char[][] tablero = new char[tamaño][tamaño];

      
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                tablero[i][j] = '-';
            }
        }

        char jugador = 'X';
        boolean ganador = false;

        while (!ganador) {

            mostrarTablero(tablero);

            System.out.println("Turno del jugador " + jugador);
            System.out.print("Ingrese una columna (0-3): ");

            int columna = sc.nextInt();

            
            if (columna < 0 || columna > 3) {
                System.out.println("Esa opcion de columna no existe, intente de nuevo.");
                continue;
            }

            
            int filaDisponible = -1;

            for (int fila = 3; fila >= 0; fila--) {
                if (tablero[fila][columna] == '-') {
                    filaDisponible = fila;
                    break;
                }
            }

            
            if (filaDisponible == -1) {
                System.out.println("La columna esta llena. Intente otra.");
                continue;
            }

          
            tablero[filaDisponible][columna] = jugador;

            
            if (verificarGanador(tablero, jugador)) {

                mostrarTablero(tablero);
                System.out.println("El jugador " + jugador + " ha ganado.");
                ganador = true;

            } 
            
          
            else if (tableroLleno(tablero)) {

                mostrarTablero(tablero);
                System.out.println("Empate. El tablero esta lleno.");
                break;

            } 
            
            else {

             
                if (jugador == 'X') {
                    jugador = 'O';
                } else {
                    jugador = 'X';
                }
            }
        }

        sc.close();
    }


    public static void mostrarTablero(char[][] tablero) {

        System.out.println();

        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 4; j++) {
                System.out.print(tablero[i][j] + " ");
            }

            System.out.println();
        }

        System.out.println();
    }


    public static boolean verificarGanador(char[][] t, char jugador) {

        // Horizontal
        for (int i = 0; i < 4; i++) {

            if (t[i][0] == jugador &&
                t[i][1] == jugador &&
                t[i][2] == jugador &&
                t[i][3] == jugador) {

                return true;
            }
        }

        // Vertical
        for (int j = 0; j < 4; j++) {

            if (t[0][j] == jugador &&
                t[1][j] == jugador &&
                t[2][j] == jugador &&
                t[3][j] == jugador) {

                return true;
            }
        }

        // Diagonal principal
        if (t[0][0] == jugador &&
            t[1][1] == jugador &&
            t[2][2] == jugador &&
            t[3][3] == jugador) {

            return true;
        }

        // Diagonal secundaria
        if (t[0][3] == jugador &&
            t[1][2] == jugador &&
            t[2][1] == jugador &&
            t[3][0] == jugador) {

            return true;
        }

        return false;
    }

    
    public static boolean tableroLleno(char[][] tablero) {

        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 4; j++) {

                if (tablero[i][j] == '-') {
                    return false;
                }
            }
        }

        return true;
    }
}