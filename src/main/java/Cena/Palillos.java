package Cena;

import java.util.concurrent.Semaphore;

public class Palillos {
    int cantidad;
    final Semaphore[] palillos; //Array de semaforos, cada palillo es un semaforo separado

    public Palillos(int cantidad) { //Constructor de la clase
      this.cantidad = cantidad;
      this.palillos = new Semaphore[cantidad];

        // Inicializar cada palillo con 1 permiso
        for (int i = 0; i < cantidad; i++) {
            palillos[i] = new Semaphore(1); // 1 permiso = disponible
        }
    }

    /**
     * @param nombre
     * @param palillo
     */
    public void cogerPalillo(String nombre, int palillo){ //Coge un palillo específico
        try {
            palillos[palillo - 1].acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param nombre
     * @param palillo
     */
    public void soltarPalillo(String nombre, int palillo){ //Suelta un palillo específico
        palillos[palillo - 1].release();
    }

    /**
     * @param filosofo
     * @return palillo derecho e izquierdo
     */
    public int[] obtenerPalillos(int filosofo){
        // Palillo izquierdo
        int izquierdo = filosofo;

        // Palillo derecho
        int derecho = (filosofo % cantidad) +1;

        //Devuelve los dos palillos recogidos
        return new int[]{izquierdo, derecho};
    }
}
