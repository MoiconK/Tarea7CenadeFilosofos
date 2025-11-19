package Cena;

import java.util.concurrent.Semaphore;

public class CenaFilosofos {
    static void main(String[] args) {
        //Inicializar los palillos
        Palillos palillos = new Palillos(5);

        for (int i = 0; i < 5; i++) {
            int idFilosofo = i + 1;

            //El primer filosofo es derecho y el resto zurdos para evitar un deadlock
            boolean esDiestro = (idFilosofo == 1);
            new Thread(new Filosofo("Filosofo "+ idFilosofo, idFilosofo, palillos, esDiestro)).start();
        }
    }
}
