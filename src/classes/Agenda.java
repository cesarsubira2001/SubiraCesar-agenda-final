package classes;

import java.util.LinkedList;

public class Agenda {
    //Atributs
    private LinkedList<Contacte> llistaContactes;

    // Constructors
    public Agenda() {
        this.llistaContactes = new LinkedList<>();
    }

    //Getters i Setters
    public LinkedList<Contacte> getLlistaContactes() {
        return llistaContactes;
    }

    public void setLlistaContactes(LinkedList<Contacte> _llistaContactes) {
        this.llistaContactes = _llistaContactes;
    }

    public void afegirContacte(Contacte contacteAAfegir) {
        this.llistaContactes.addLast(contacteAAfegir);
    }

    public Contacte buscarContacte(Contacte contacteABuscar) {
        Contacte contacteTrobat = null;
        if (!this.llistaContactes.isEmpty()) {
            for (int i = 0; i < this.getLlistaContactes().size(); i++) {
                if ((this.llistaContactes.get(i).getNom().equalsIgnoreCase(contacteABuscar.getNom())) &&
                        (this.llistaContactes.get(i).getCognom().equalsIgnoreCase(contacteABuscar.getCognom()))) {
                    contacteTrobat = this.llistaContactes.get(i);
                }
            }
        }
        return contacteTrobat;
    }

    @Override
    public String toString() {
        String cadenaATornar = "";
        for (Contacte contacte : this.llistaContactes) {
            cadenaATornar += contacte;
        }
        return cadenaATornar;
    }

    public void eliminarContacte(Contacte contacte) {
        boolean removed = llistaContactes.remove(contacte);
        if (removed) {
            System.out.println("Contacte eliminat correctament!");
        } else {
            System.out.println("Contacte no trobat per eliminar.");
        }
    }

    public static void mostraTelefons(Contacte contacteActual) {
        String telefonsFormatCadena = "";
        LinkedList<Telefon> llistaTel = contacteActual.getLlistaTelefons();
        for (Telefon telefonActual : llistaTel) {
            telefonsFormatCadena += telefonActual;
        }
        System.out.println( contacteActual.getNom()
                + " "
                + contacteActual.getCognom()
                + "\n\t"
                + telefonsFormatCadena);
    }

    public void mostrarContactes() {
        if (this.llistaContactes.size() == 0) {
            System.out.println("La agenda està buida!");
        } else {
            for (int i = 0; i < this.getLlistaContactes().size(); i++) {
                Contacte contacteActual = this.getLlistaContactes().get(i);
                mostraTelefons(contacteActual);
            }
        }
    }

    public void modificarContacte(Contacte contacteAModificar, Contacte editatContacte) {
        for (int i = 0; i < llistaContactes.size(); i++) {
            if (llistaContactes.get(i).getNom().equalsIgnoreCase(contacteAModificar.getNom()) &&
                    llistaContactes.get(i).getCognom().equalsIgnoreCase(contacteAModificar.getCognom())) {
                llistaContactes.set(i, editatContacte);
                System.out.println("Contacte modificat correctament!");
                return;
            }
        }
        System.out.println("Contacte no trobat.");
    }

    public void afegirTelefonContacte(Contacte _contacte, Telefon _telefon) {
        for (Contacte contacte : llistaContactes) {
            if (contacte.equals(_contacte)) {
                contacte.afegirTelefon(_telefon);
                return;
            }
        }
        _contacte.afegirTelefon(_telefon);
        llistaContactes.add(_contacte);
    }

    public void afegirAdressaContacte(Contacte _contacte, Adressa _adressa) {
        for (Contacte contacte : llistaContactes) {
            if (contacte.equals(_contacte)) {
                contacte.afegirAdressa(_adressa);
                return;
            }
        }
        llistaContactes.add(_contacte);
    }

    public LinkedList<Contacte> buscaContactePerNom(String nomContacteABuscar) {
        LinkedList<Contacte> contactesTrobats = new LinkedList<>();
        for (Contacte contacte : llistaContactes) {
            if (contacte.getNom().equalsIgnoreCase(nomContacteABuscar)) {
                contactesTrobats.add(contacte);
            }
        }
        return contactesTrobats.isEmpty() ? null : contactesTrobats;
    }

    public LinkedList<Contacte> buscaContactePerNomOCognom(String criteri) {
        LinkedList<Contacte> contactesTrobats = new LinkedList<>();
        for (Contacte contacte : llistaContactes) {
            if (contacte.getNom().equalsIgnoreCase(criteri) ||
                contacte.getCognom().equalsIgnoreCase(criteri)) {
                contactesTrobats.add(contacte);
            }
        }
        return contactesTrobats.isEmpty() ? null : contactesTrobats;
    }
}

