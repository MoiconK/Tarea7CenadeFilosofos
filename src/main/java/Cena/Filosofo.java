package Cena;

public class Filosofo implements Runnable{
    final String nombre;
    final int idFilosofo;
    Palillos palillos;
    final boolean esDiestro;

    /**
     * @param nombre
     * @param idFilosofo
     */
    public Filosofo(String nombre, int idFilosofo, Palillos palillos, boolean esDiestro) { //Constructor de la clase
        this.nombre = nombre;
        this.idFilosofo = idFilosofo;
        this.palillos = palillos;
        this.esDiestro = esDiestro;
    }

    /**
     * @throws InterruptedException
     */
    private void pensar() throws InterruptedException { // El filosofo piensa siempre
        System.out.println("El " + nombre + " está pensando...");
        Thread.sleep((long) (Math.random() * 3000));
    }

    /**
     * @throws InterruptedException
     */
    private void comer() throws InterruptedException { //Lógica de comer y mostrar los palillos libres al soltarlos
        System.out.println("El " + nombre + " esta comiendo");
        Thread.sleep((long) (Math.random() * 2000));

        // Al terminar de comer, mostrar palillos liberados
        int[] palillosSoltados = palillos.obtenerPalillos(idFilosofo);
        System.out.println("El "+nombre+" terminó de comer, palillos disponibles: "+palillosSoltados[0]+", "+palillosSoltados[1]);
    }

    /**
     * @throws InterruptedException
     */
    private void sujetarPalillos() throws InterruptedException {
        //Palillos que obtiene el filosofo
        int[] palillosRecogidos = palillos.obtenerPalillos(idFilosofo);
        int palilloIzquierdo = palillosRecogidos[0];
        int palilloDerecho = palillosRecogidos[1];

        //Adquirir palillos
        if (esDiestro) { //Si es el filosofo diestro coge los palillos al revés que el resto
            palillos.cogerPalillo(nombre, palilloDerecho);
            Thread.sleep(150);
            palillos.cogerPalillo(nombre, palilloIzquierdo);
        } else {
            palillos.cogerPalillo(nombre, palilloIzquierdo);
            Thread.sleep(150);
            palillos.cogerPalillo(nombre, palilloDerecho);
        }


        System.out.println("El "+nombre+" esta sujetando ambos palillos, ");
    }

    private void liberarPalillos(){

        //Palillos que suelta el filosofo
        int[] palillosSoltados = palillos.obtenerPalillos(idFilosofo);
        palillos.soltarPalillo(nombre, palillosSoltados[0]);
        palillos.soltarPalillo(nombre, palillosSoltados[1]);
    }

    @Override
    public void run() {
        try {
            System.out.println("El "+nombre+" está hambriento..."); //Mostrar al filosofo que está hambriento

            //El filosofo piensa primero
            pensar();

            // Tomar palillos
            sujetarPalillos();

            //Comer y mostrar los palillos libres
            comer();

            // Soltar palillos
            liberarPalillos();

            Thread.sleep(500);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
