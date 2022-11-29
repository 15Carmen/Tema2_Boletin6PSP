import java.util.Random;

public class Estudiante implements Runnable {
    /*
    Hay cuatro estudiantes que comparten nueve libros. Los estudiantes seleccionan dos libros al azar y,
    una vez obtenidos, los utilizan durante un tiempo aleatorio de entre 3  y 5 segundos. En caso de que
    alguno de los dos libros no estuviese libre, entonces el estudiante se queda esperando hasta que algún
    libro se libere.

    Una vez han terminado de utilizar los dos libros que han reservado, los devuelven a la vez, de forma
    que otros estudiantes puedan utilizarlos. Todos los estudiantes han tenido que ser capaces de
    utilizar sus dos libros.

    Muestra mensajes cada vez que un estudiante coge dos libros y también una vez los libera, donde se
    indiquen el nombre del estudiante y los libros que ha reservado.
     */

    //Declaramos una variable de array de booleanos pública y estática que tenga un tamaño de 9
    public static final boolean[] libros = new boolean[9];

    public static void main(String[] args) {

        //Declaramos un nuevo estudiante
        Estudiante estudiante = new Estudiante();

        //Creamos 4 hilos, uno por cada estudiante
        for (int i = 1; i <= 5; i++) {              //Inicializamos el bucle en 1, lo terminamos en 4 y lo incrementamos en 1
            Thread hilo = new Thread(estudiante);   //Creamos un nuevo hilo y le pasamos el estudiante por parámetros
            hilo.setName("estudiante " + i);        //Le damos un nombre al hilo
            hilo.start();                           //Y lo inicializamos
        }

    }

    @Override
    public void run() {

        try {
            while (true) {  //Creamos un bucle infinito
                //Le asignamos a las variables libro1 y libro2 un numero random que esté entre 1 y 9
                int libro1 = new Random().nextInt(9);
                int libro2 = new Random().nextInt(9);

                while (libro2 == libro1) {  //Mientras los dos libros tengas un valor igual
                    libro2 = new Random().nextInt(9);   //Le asignamos a libro2 otro valor random
                }

                //Usamos un syncrhronized para bloquear el array de booleanos libros
                synchronized (libros) {
                    //Mientras que libros contenga a libro1 o a libro2
                    while (libros[libro1] || libros[libro2]) {
                        try {
                            //El array espera
                            libros.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    //Cuando el metodo acaba, declaramos que libros[libro1] y libros[libro2] sean verdaderas
                    libros[libro1] = true;
                    libros[libro2] = true;
                }

                //Imprimimos por pantalla el mensaje de que estudiante tiene que libros
                System.out.println("El " + /* cliente numero tal */ Thread.currentThread().getName() + " esta leyendo los libros " + libro1 + " y " + libro2);

                //Esperamos un numero aleatorio de tiempo entre 3 y 5 segundos a que se imprima el siguiente mensaje
                Thread.sleep((long) (Math.random() * (5000 - 3000) + 3000));

                //Imprimimos que estudiante ha terminado de leer
                System.out.println("El " + Thread.currentThread().getName() + " ha terminado de leer.");

                //Volvemos a usar el synchronized para bloquear a libros
                synchronized (libros) {
                    //Ahora declaramos que libros[libro1] y libros[libro2] son falsos
                    libros[libro1] = false;
                    libros[libro2] = false;

                    //Y usamos el notifyAll para continuar con todos los segmentas parados por el wait
                    libros.notifyAll();
                }

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
