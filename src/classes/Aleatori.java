package classes;

import java.util.Random;

public class Aleatori {
    // Mètode que retorna un valor aleatori entre 1 i 9
    public int retornaAleatori() {
        Random random = new Random();
        return random.nextInt(8) + 1; // nextInt(9) retorna un valor entre 0 i 7, per això sumem 1
    }

    public static void aleatori() {
        Aleatori aleatori = new Aleatori();
        System.out.printf("\n\tCal que responguis la pregunta %d!\n\n",
                aleatori.retornaAleatori());
    }
}
