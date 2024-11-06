package aplicacio;

import classes.Agenda;
import classes.Contacte;
import classes.Teclat;
import classes.Telefon;
import eines.EinesJoan;
import menu.Menu;

public class AppAgenda {
    private static final int NOM_CONTACTE = 0;
    private static final int COGNOM_CONTACTE = 1;
    private static final int TELEFON_CONTACTE = 2;
    private static final int ETIQUETA_TELEFON_CONTACTE = 3;

    static Agenda agenda = new Agenda();

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

    public static void afegirContacte() {
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

    public static void modificarContacte() {
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

    public static void afegirContactesManualment(){
        String[][] dadesContactes = {
                {"Joan","Pardo","321321132","casa"},
                {"Iván","Nieto","123123123","escola"},
                {"Vladi","Bellavista","844884484","feina"}
        };
        for (String[] contacteActual : dadesContactes) {
            Contacte nouContacte = new Contacte();
            Telefon nouTelefon = new Telefon(
                    Integer.parseInt(contacteActual[TELEFON_CONTACTE]),
                    contacteActual[ETIQUETA_TELEFON_CONTACTE]);
            nouContacte.setNom(contacteActual[NOM_CONTACTE]);
            nouContacte.setCognom(contacteActual[COGNOM_CONTACTE]);
            nouContacte.afegirUnTelefon(nouTelefon);
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
                    agenda.mostrarContactes();
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
