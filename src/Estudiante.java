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

    public static boolean[] libros = new boolean[9];


    public static void main(String[] args) {

    }

    @Override
    public void run() {

        while (true) {
            int libro1 = new Random().nextInt(9);
            int libro2 = new Random().nextInt(9);

            while (libro2 == libro1) {
                libro2 = new Random().nextInt(9);
            }

            synchronized (libros) {

            }
        }


    }
}
