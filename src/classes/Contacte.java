package classes;

import java.util.LinkedList;

public class Contacte {
    // Atributs
    private String nom;
    private String cognom;
    private LinkedList<Telefon> llistaTelefons;
    // private LinkedList<Adressa> llistaAdreces;
    // Constructor
    public Contacte() {
        this.nom = "Buit";
        this.cognom = "Buit";
        this.llistaTelefons = new LinkedList<>();
    }
    public Contacte(String _nom, String _cognom) {
        this.nom = _nom;
        this.cognom = _cognom;
        this.llistaTelefons = new LinkedList<>();
    }
    public Contacte(String _nom) {
        this.nom = _nom;
        this.cognom = "Buit";
        this.llistaTelefons = new LinkedList<>();
    }

//    public Contacte(String _cognom) {
//        this.nom = "Buit";
//        this.cognom = _cognom;
//    }
    // Getters and Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String _nom) {
        this.nom = _nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String _cognom) {
        this.cognom = _cognom;
    }

    public LinkedList<Telefon> getLlistaTelefons() {
        return llistaTelefons;
    }

    public void setLlistaTelefons(LinkedList<Telefon> _llistaTelefons) {
        this.llistaTelefons = _llistaTelefons;
    }

//    public LinkedList<Adressa> getLlistaAdreces() {
//        return llistaAdreces;
//    }
//
//    public void setLlistaAdreces(LinkedList<Adressa> _llistaAdreces) {
//        this.llistaAdreces = _llistaAdreces;
//    }

    @Override
    public String toString() {
        return   nom + ' ' + cognom + '\n'
                + "\t" + llistaTelefons;
//                + "\tTelèfons:\n\t"+ llistaTelefons + "\n" ;
//                + "\tAdreces:\n\t" + llistaAdreces;
    }

    public boolean equals(Contacte _contacte){
        return ( this.getNom().equals(_contacte.getNom()) &&
                this.getCognom().equals(_contacte.getCognom())
        );
    }

    // Mètodes més específics
    public void afegirUnTelefon(Telefon _telefon) {
        if(_telefon != null){
            this.llistaTelefons.addLast(_telefon);
        }
    }
    public void afegirLlistaTelefons(LinkedList<Telefon> _llistaTelefons) {
        for(Telefon telActual: _llistaTelefons){
            this.llistaTelefons.addAll(_llistaTelefons);
        }
    }
//    public void afegirUnaAdressa(Adressa _adressa) {
//        this.llistaAdreces.add(_adressa);
//    }
//    public void afegirLlistaAdresses(LinkedList<Adressa> _llistaAdreces) {
//        for(Adressa adressaActual: _llistaAdreces){
//            this.llistaAdreces.addAll(_llistaAdreces);
//        }
//    }
}
