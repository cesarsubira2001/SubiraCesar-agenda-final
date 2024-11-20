package classes;

import java.io.*;

public class Fitxers {
    public static String llegirLinia(String nomArxiu) throws IOException {

        BufferedReader canalLectura = null;
        try {
            canalLectura = new BufferedReader(new FileReader(nomArxiu));
        } catch (IOException e) {
            System.out.println("Problema durant l'accés a l'arxiu");
            System.exit(1);
        }
        String linia = null;

        try {
            linia = canalLectura.readLine();
            /* readLine retorna null quan arriba a EOF */
            while (linia != null) {
                System.out.println(linia);
                linia = canalLectura.readLine();
            }
        } catch (IOException e) {
            System.out.println("Problemes en llegir l'arxiu");
            System.exit(1);
        }
        canalLectura.close();
        return linia;
    }

    public static String[] obtenirCamps(String nomArxiu) throws IOException {
        String[] campsLlegits = null;
        String liniaLlegida = llegirLinia(nomArxiu);
        while (liniaLlegida != null) {
            System.out.println(liniaLlegida);
            liniaLlegida = llegirLinia(nomArxiu);
            campsLlegits = liniaLlegida.split(",");
        }
        return campsLlegits;
    }

    public static void main(String[] args) throws IOException {
        String nomArxiu = "fitxers/agenda.csv";
        String[] campsLlegits = obtenirCamps(nomArxiu);
        if (campsLlegits != null) {
            for (String camp : campsLlegits) {
                System.out.println("camp " + camp);
            }
        }
    }
}
