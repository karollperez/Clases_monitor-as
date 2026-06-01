
package Ui;
import java.util.Scanner;
public class Triqui {

    public static void main(String[] args) {

    	char[][] tablero = {{'-', '-', '-'}, {'-', '-', '-'}, {'-', '-', '-'}};
    	char jugadorActual = 'X';
    	boolean juegoTerminado = false;
    	int jugadas = 0;
    	
    	Scanner sc = new Scanner(System.in);

        System.out.println("--- TRIQUI ---");
        
        while (!juegoTerminado) {
         imprimirTablero(tablero);
        System.out.println("Turno de: " + jugadorActual);
        

        System.out.print("Fila (0-2): ");
        int fila = sc.nextInt();
        System.out.print("Columna (0-2): ");
        int col = sc.nextInt();

       
        if  (fila >= 0 && fila < 3 && col >= 0 && col < 3 && tablero[fila][col] == '-') { 
        	
       
            tablero[fila][col] = jugadorActual;
      
            jugadas++;
        
            if (verificarGanador(tablero, jugadorActual)) {
                imprimirTablero(tablero);
                System.out.println("¡Felicidades! El jugador " + jugadorActual + " ha ganado.");
                juegoTerminado = true;
            } else if (jugadas == 9) { 
                imprimirTablero(tablero);
                System.out.println("¡Es un empate!");
                juegoTerminado = true;
            } else {
               
                jugadorActual = (jugadorActual == 'X') ? 'O' : 'X';
            }
        } else {
            System.out.println("Movimiento inválido. Intenta de nuevo.");
        }
    }
    sc.close();
           
    }

    public static void imprimirTablero(char[][] t) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(t[i][j] + " ");
            }
            System.out.println();
        }
    }
    

    public static boolean verificarGanador(char[][] t, char j) {
    
   
        for (int i = 0; i < 3; i++) {
        	
        
            if ((t[i][0] == j && t[i][1] == j && t[i][2] == j) || 
                (t[0][i] == j && t[1][i] == j && t[2][i] == j)) return true;
            
        }
       
        return (t[0][0] == j && t[1][1] == j && t[2][2] == j) ||
               (t[0][2] == j && t[1][1] == j && t[2][0] == j);
	    
    }
	    
	
}  
