package classes;
import java.io.*;

public class Teclat {
    // Canal d'entrada construït a partir
    // de l'entrada Standard(System.in)
    private static BufferedReader canal =
            new BufferedReader(
                    new InputStreamReader(System.in));
    private static final int ERROR_NO_ES_NOMBRE_ENTER = -100;

    public static char llegirLletraValida(char[] caractersValids, String... missatges) {
        String missatgeEntrada = missatges.length > 0 ?
                missatges[0] : "Introdueix un caràcter: ";
        String missatgeError = missatges.length > 1 ?
                missatges[1] : "Caràcter no vàlid. Torna-ho a provar.";
        boolean caracterValid = false;
        char lletra = 0;
        char lletraMinuscula;
        String cadenaLlegida;
        do {
            System.out.print(missatgeEntrada);
            cadenaLlegida = llegirCadena();
            // Si caracterLlegit és buida i
            // Si caracterLlegit té més d'una lletra
            if (cadenaLlegida != null &&
                cadenaLlegida.length() == 1) {
                lletra = cadenaLlegida.charAt(0);
                // Convertim a minúscula per comparar
                lletraMinuscula = Character.toLowerCase(lletra);

                for (char c : caractersValids) {
                    if (lletraMinuscula == Character.toLowerCase(c)) { // Validem sense distingir majúscules/minúscules
                        caracterValid = true; // Marquem el caràcter com a vàlid
                        return lletra; // Retornem el caràcter original introduït
                    }
                }
            }
            System.out.println(missatgeError); // Mostra el missatge d'error si no és vàlid
        } while (!caracterValid);
        return lletra;
    }




    // Mètode per llegir una cadena String fins al
    // final de la línia CR/LF
    public static String llegirCadena(String ... missatge) {
        if(missatge.length==1){
            System.out.print(missatge[0]);
        }
        try {
            // llegir del canal i retornar el
            // resultat sense processar perquè
            // readline() ja retorna un String
            return canal.readLine();
        } catch (IOException e) {
            // problemes amb la lectura o amb el canal...
            System.err.println("Error en la lectura: " + e.getMessage());
            return null; // Retorn null per indicar error sense tancar el programa
        }
    }
    // Mètode per llegir un enter de mida int
    public static int llegirEnterCtrl(String ... missatge) {
        if(missatge.length==1){
            System.out.print(missatge[0]);
        }
        int enterATornar = -1;
            try {
                String cadena = canal.readLine();
                // Llegir una línia del canal
                if (cadena.length() == 0) {   // si la línia és buida
                    System.out.printf("No has entrat res!!");
                } else {
                    try {  // convertir a int
                        enterATornar = Integer.parseInt(cadena);
                    } catch (NumberFormatException ex1) { // si a la conversió ha anat malament
                        // o ha fallat la conversió
                        // System.out.printf(e.getMessage()); // mostrar excepció
                        System.out.printf("\n\tERROR!\tCal entrar un enter!\n");
                        // System.exit(0); // aturar la màquina virtual
                        // return(0); // Línia de codi inaccessible.
                    }
                }
                // return Integer.parseInt(cadena); // convertir a int
            } catch (IOException ex2) { // si a la lectura ha anat malament
                // o ha fallat la conversió
                // System.out.printf(e.getMessage()); // mostrar excepció
                System.out.printf("\n\tERROR!\t2Cal entrar un enter!\n");
                // System.exit(0); // aturar la màquina virtual
                // return(0); // Línia de codi inaccessible
            }

       return enterATornar;
    }
    // Mètode per llegir un enter de mida int
    public static int llegirEnter(String ... missatge) {
        if(missatge.length==1){
            System.out.print(missatge[0]);
        }
        try {
            String cadena = canal.readLine();
            // Llegir una línia del canal
            if (cadena.length() == 0)   // si la línia és buida
                return llegirEnter();   // insistir
            else
                return Integer.parseInt(cadena); // convertir a int
        } catch (IOException e) { // si a la lectura ha anat malament
            // o ha fallat la conversió
            System.out.printf(e.getMessage()); // mostrar excepció
            System.exit(0); // aturar la màquina virtual
            return(0); // Línia de codi inaccessible
        } catch (NumberFormatException nfe){
            // System.out.printf("\n\tERROR!\tCal entrar un enter!\n");
            return(ERROR_NO_ES_NOMBRE_ENTER); // Codi ERROR no és un enter!!!
        }
    }
    // Mètode per llegir un enter de mida double
    public static double llegirDouble(String ... missatge) {
        if(missatge.length==1){
            System.out.print(missatge[0]);
        }
        try {
            String cadena = canal.readLine();
            // Llegir una línia del canal
            if (cadena.length() == 0)   // si la línia és buida
                return llegirEnter();   // insistir
            else
                return Double.parseDouble(cadena); // convertir a int
        } catch (IOException e) { // si a la lectura ha anat malament
            // o ha fallat la conversió
            System.out.printf(e.getMessage()); // mostrar excepció
            System.exit(0); // aturar la màquina virtual
            return(0); // Línia de codi inaccessible
        }
    }
    // Mètode per llegir un enter de mida double
    public static long llegirLong(String ... missatge) {
        if(missatge.length==1){
            System.out.print(missatge[0]);
        }
        try {
            String cadena = canal.readLine();
            // Llegir una línia del canal
            if (cadena.length() == 0)   // si la línia és buida
                return llegirEnter();   // insistir
            else
                return Long.parseLong(cadena); // convertir a int
        } catch (IOException e) { // si a la lectura ha anat malament
            // o ha fallat la conversió
            System.out.printf(e.getMessage()); // mostrar excepció
            System.exit(0); // aturar la màquina virtual
            return(0); // Línia de codi inaccessible
        }
    }
    public static char llegirCaracter(String ... missatge) {
        if(missatge.length==1){
            System.out.print(missatge[0]);
        }
        try {
            String s = canal.readLine();
            return s.length() == 0 ? llegirCaracter() : s.charAt(0);
        } catch (Exception var1) {
            Exception e = var1;
            System.out.println(e);
            System.exit(0);
            return 'a';
        }
    }
}

