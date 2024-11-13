package aplicacio;

import classes.*;
import eines.EinesJoan;
import menu.Menu;

import java.util.LinkedList;

public class AppAgenda {
    private static final int NOM_CONTACTE = 0;
    private static final int COGNOM_CONTACTE = 1;
    private static final int TELEFON_CONTACTE = 2;
    private static final int ETIQUETA_TELEFON_CONTACTE = 3;

    static Agenda agenda = new Agenda();

    public static void mostrarContactes() {
        for (Contacte contacte : agenda.getLlistaContactes()) {
            int qtatAdreces = contacte.getLlistaAdreces().size();
            int qtatTelefons = contacte.getLlistaTelefons().size();
            System.out.format("%s %s\n",
                    contacte.getNom(), contacte.getCognom());
            System.out.format("Té %d %s: \n", qtatTelefons , (qtatTelefons==1)?"telèfon":"telèfons");
            for (Telefon telefon : contacte.getLlistaTelefons()) {
                System.out.format("\t%s\n", telefon.toString());
            }
            System.out.format("Té %d %s: \n", qtatAdreces, (qtatAdreces==1)?"adreça":"adreces");
            for (Adressa adressa : contacte.getLlistaAdreces()) {
                System.out.format("%s", adressa.toString());
                if(adressa != null){
                    System.out.println();
                }
            }
            System.out.println();
        }
    }

    public static void buscarContacte(Contacte contacteABuscar) {
        Contacte contacteABuscarTrobat = agenda.buscarContacte(contacteABuscar);
        if (contacteABuscarTrobat != null) {
            System.out.print("S'ha trobat el contacte\n" + contacteABuscarTrobat);
        }
    }

    public static Contacte obteDadesContacte(){
        return new Contacte(Teclat.llegirCadena("Entra el nom del contacte: "),
                Teclat.llegirCadena("Entra el cognom del contacte: "));
    }

    public static void afegirContacte1() {
        EinesJoan.pintaComSubTitol("Afegir Dades del Contacte");
        Contacte contacteAAfegir = obteDadesContacte();
        Contacte contacteTrobat = agenda.buscarContacte(contacteAAfegir);
        if (contacteTrobat != null) {
            System.out.println("El contacte ja existeix.");
        } else {
            agenda.afegirContacte(contacteAAfegir);
            EinesJoan.pintaComSubTitol("Afegir Telefon del Contacte");
            int numeroTelefon = Teclat.llegirEnter("Número de telèfon: ");
            System.out.print("Etiqueta (Mòbil, Casa, Treball, etc.): ");
            String etiqueta = Teclat.llegirCadena();
            Telefon telefon = new Telefon(numeroTelefon, etiqueta);
            agenda.afegirTelefonContacte(contacteAAfegir, telefon);
            System.out.println("Contacte afegit amb èxit!");
            Agenda.mostraTelefons(contacteAAfegir);
        }
    }

    private static void afegirContacte() {
        EinesJoan.pintaComSubTitol("Afegir Dades del Contacte");
        String nom = Teclat.llegirCadena("Nom: ");
        String cognom = Teclat.llegirCadena("Cognom: ");

        Contacte contacte = new Contacte(nom, cognom);

        EinesJoan.pintaComSubTitol("Afegir Telefon del Contacte");
        int numeroTelefon = Teclat.llegirEnter("Número de telèfon: ");
        System.out.print("Etiqueta (Mòbil, Casa, Treball, etc.): ");
        String etiqueta = Teclat.llegirCadena();
        Telefon telefon = new Telefon(numeroTelefon, etiqueta);
        agenda.afegirTelefonContacte(contacte, telefon);
        System.out.println("Telèfon de contacte afegit amb èxit!");
        EinesJoan.pintaComSubTitol("Afegir Adreça del Contacte");
        String etiquetaAdressaContacte = Teclat.llegirCadena("Etiqueta de l'adreça: ");
        String carrerContacte = Teclat.llegirCadena("Carrer: ");
        int numeroCarrerContacte = Teclat.llegirEnter("Número del carrer: ");
        String ciutatContacte = Teclat.llegirCadena("Ciutat: ");
        String codiPostalContacte = Teclat.llegirCadena("Codi postal: ");
        String paisContacte = Teclat.llegirCadena("País: ");
        Adressa adresssaAdressaContacte = new Adressa(etiquetaAdressaContacte,
                carrerContacte, numeroCarrerContacte, ciutatContacte,
                codiPostalContacte, paisContacte);
        agenda.afegirAdressaContacte(contacte, adresssaAdressaContacte);
        System.out.println("Adreça contacte afegida amb èxit!!");
        System.out.println("Contacte afegit amb èxit!");
    }

    public static void eliminarContacte() {
        char resposta;
        char[] lletresResposta = {'S' , 'N'};
        Contacte contacteTrobat = agenda.buscarContacte(obteDadesContacte());
        if (contacteTrobat != null) {
            resposta = Teclat.llegirLletraValida(lletresResposta,
                    "Estas segur d'eliminar el contacte (S/N): ",
                    "Caràcter no vàlid. Torna-ho a provar.");
            if (Character.toUpperCase(resposta) == 'S') {
                agenda.eliminarContacte(contacteTrobat);
            } else {
                System.out.println("El contacte no s'ha eliminat!");
            }
        } else {
            System.out.println("Contacte no trobat!");
        }
    }
    private static void modificarContacte() {
        EinesJoan.pintaComSubTitol("Modificar Contacte");
        System.out.print("Nom o Cognom del contacte a modificar: ");
        String criteri = Teclat.llegirCadena();
        var contactes = agenda.buscaContactePerNomOCognom(criteri);

        if (contactes == null) {
            System.out.println("No s'ha trobat cap contacte amb aquest criteri.");
            return;
        }

        if (contactes.size() == 1) {
            modificarContacte(contactes.get(0));
        } else {
            System.out.println("S'han trobat múltiples contactes:");
            for (int i = 0; i < contactes.size(); i++) {
                System.out.println((i + 1) + ". " + contactes.get(i));
            }

            System.out.print("Escull el número del contacte a modificar: ");
            int index = Teclat.llegirEnter() - 1;

            if (index >= 0 && index < contactes.size()) {
                modificarContacte(contactes.get(index));
            } else {
                System.out.println("Opció no vàlida.");
            }
        }
    }

    private static void modificarContacte(Contacte contacte) {
        Contacte contacteAModificar = agenda.buscarContacte(obteDadesContacte());
        String nouNom = Teclat.llegirCadena("Entra el nou nom (actual = : " + contacteAModificar.getNom() + "): ");
        if (nouNom != null && nouNom.isEmpty()) {
            nouNom = contacteAModificar.getNom();
        }
        String nouCognom = Teclat.llegirCadena("Entra el nou cognom (actual = : " + contacteAModificar.getCognom() + "): ");
        if (nouCognom != null && nouCognom.isEmpty()) {
            nouCognom = contacteAModificar.getCognom();
        }
        Contacte contacteEditat = new Contacte(nouNom, nouCognom);
        agenda.modificarContacte(contacteAModificar, contacteEditat);
        System.out.println("Contacte modificat amb èxit!");
    }
    public static void modificarContacte2() {
        Contacte contacteAModificar = agenda.buscarContacte(obteDadesContacte());
        if (contacteAModificar == null) {
            System.out.println("Contacte no trobat.");
            return;
        }
        String nouNom = Teclat.llegirCadena("Entra el nou nom (actual = : " + contacteAModificar.getNom() + "): ");
        if (nouNom != null && nouNom.isEmpty()) {
            nouNom = contacteAModificar.getNom();
        }

        String nouCognom = Teclat.llegirCadena("Entra el nou cognom (actual = : " + contacteAModificar.getCognom() + "): ");
        if (nouCognom != null && nouCognom.isEmpty()) {
            nouCognom = contacteAModificar.getCognom();
        }

        Contacte contacteEditat = new Contacte(nouNom, nouCognom);
        agenda.modificarContacte(contacteAModificar, contacteEditat);
    }
    private static void guardarAgenda(String nomFitxer) {
        System.out.println("Mètode encara no desenvolupat!");
    }

    // afegirContacteFix afegit per la correcció
    private static void afegirContacteFix() {
        Contacte contacte = new Contacte("Alfons", "Palacios");

        int[] numeroTelefon = {621233456,934561232,931696500 };
        String[] etiqueta = {"mòbil", "casa", "feina"};

        for (int i = 0; i < numeroTelefon.length; i++) {
            Telefon telefon = new Telefon(numeroTelefon[i], etiqueta[i]);
            agenda.afegirTelefonContacte(contacte, telefon);
        }

        String[] etiquetaAdressaContacte = {"casa","feina"};
        String[] carrerContacte = {"Avinguda de Puig i Cadafalch","Av. Ernest Lluch"};
        int[] numeroCarrerContacte = {154, 32};
        String[] ciutatContacte = {"Mataró","Mataró"};
        String[] codiPostalContacte = {"08302","08302"};
        String[] paisContacte = {"Espanya", "Espanya"};
        for (int i = 0; i < etiquetaAdressaContacte.length; i++) {
            Adressa adresssaAdressaContacte = new Adressa(etiquetaAdressaContacte[i],
                    carrerContacte[i], numeroCarrerContacte[i], ciutatContacte[i],
                    codiPostalContacte[i], paisContacte[i]);
            agenda.afegirAdressaContacte(contacte, adresssaAdressaContacte);
        }
        System.out.println("Adreces FIXES del contacte afegides amb èxit!!");

    }

    public static void afegirContactesManualment(){
        boolean hiHaTelefons = false;
        boolean hiHaAdreces = false;
        final int NOM = 0;
        final int COGNOM = 1;
        final int ET_TEL = 2;
        final int NUM_TEL = 3;
        final int ET_ADR1 = 4;
        final int CARRER_ADR1 = 5;
        final int NUM_ADR1 = 6;
        final int CP_ADR1 = 7;
        final int CIUTAT_ADR1 = 8;
        final int PAIS_ADR1 = 9;

        String[][] dadesContactes = {
                {"Joan","Pardo",
                    "casa", "321321132",
                    "casa", "carrer Muntaner", "54","08032","Barcelona","Espanya"},
                {"Iván","Nieto",
                    "casa","123123123",
                    "casa", "Av. Ernest Lluch", "32","08302","Mataró","Espanya"},
                {"Vladi","Bellavista",
                    "casa", "844884484",
                    "casa", "Avinguda de Puig i Cadafalch", "154","08302","Mataró","Espanya"},
        };


        String[] dadesEscola = {"938412547","C/ Joaquim Costa",
                "0","08450","Llinars del Vallès","Espanya"};


        // Inicialitzem les llistes
        LinkedList<Telefon> llistaTelefons = new LinkedList<>();
        LinkedList<Adressa> llistaAdreces = new LinkedList<>();
        for (String[] contacteActual : dadesContactes) {
            // Llegir nom i cognom del contacte
            String etiqueta = contacteActual[ET_TEL];
            Contacte nouContacte = new Contacte(contacteActual[NOM], contacteActual[COGNOM]);
            Telefon nouTelefon = new Telefon(
                Integer.parseInt(contacteActual[NUM_TEL]),
                contacteActual[ET_TEL]);

            Adressa novaAdressa = new Adressa(
                    contacteActual[ET_ADR1],
                    contacteActual[CARRER_ADR1],
                    Integer.parseInt(contacteActual[NUM_ADR1]),
                    contacteActual[CIUTAT_ADR1],
                    contacteActual[CP_ADR1],
                    contacteActual[PAIS_ADR1]);

            Telefon telefonEscola = new Telefon(Integer.parseInt(dadesEscola[0]),
                    "escola");
            Adressa adressaEscola = new Adressa("escola",
                    dadesEscola[1],Integer.parseInt(dadesEscola[2]),
                    dadesEscola[3],dadesEscola[4],dadesEscola[5]);
            nouContacte.afegirTelefon(nouTelefon);
            nouContacte.afegirAdressa(novaAdressa);
            nouContacte.afegirTelefon(telefonEscola);
            nouContacte.afegirAdressa(adressaEscola);
            agenda.afegirContacte(nouContacte);
        }


    }

    public static void afegirContactesManualment2(){
        String[][] dadesContactes = {
                {"Joan","Pardo","321321132","casa"},
                {"Iván","Nieto","123123123","escola"},
                {"Vladi","Bellavista","844884484","feina"}
        };
        for (String[] contacteActual : dadesContactes) {
            Contacte nouContacte = new Contacte(contacteActual[0], contacteActual[1]);
            Telefon nouTelefon = new Telefon(
                    Integer.parseInt(contacteActual[TELEFON_CONTACTE]),
                    contacteActual[ETIQUETA_TELEFON_CONTACTE]);
            nouContacte.setNom(contacteActual[NOM_CONTACTE]);
            nouContacte.setCognom(contacteActual[COGNOM_CONTACTE]);
            nouContacte.afegirTelefon(nouTelefon);
            agenda.afegirContacte(nouContacte);
        }
    }

    public static void main(String[] args) {
        String[] opcions = {
                "Afegir Contacte",
                "Buscar Contacte",
                "Modificar Contacte",
                "Eliminar Contacte",
                "Mostrar Contactes",
                "Sortir"
        };
        String nomFitxerAmbAgenda = "";
        boolean sortir = false;

        afegirContactesManualment();

        while (!sortir) {
            int opcio = Menu.obteOpcioMenu(opcions,
                    "Menu Principal",
                    "Escull una opció");
            switch (opcio) {
                case 1:
                    afegirContacte();
                    EinesJoan.pitjaIntroPerContinuar();
                    break;
                case 2:
                    buscarContacte(obteDadesContacte());
                    EinesJoan.pitjaIntroPerContinuar();
                    break;
                case 3:
                    modificarContacte();
                    EinesJoan.pitjaIntroPerContinuar();
                    break;
                case 4:
                    eliminarContacte();
                    EinesJoan.pitjaIntroPerContinuar();
                    break;
                case 5:
                    // agenda.mostrarContactes();
                    mostrarContactes();
                    EinesJoan.pitjaIntroPerContinuar();
                    break;
                case 6:
                    guardarAgenda(nomFitxerAmbAgenda);
                    sortir = true;
                    EinesJoan.pintaComiat();
                    break;
            }
        }
    }
}
