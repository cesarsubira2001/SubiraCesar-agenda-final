package classes;

public class Telefon {
    // Atributs
    private int numeroTelefon;
    private String etiqueta;
//    private String codiPais;

    private String PAIS_PER_DEFECTE = "Spain";

    // Constructor
//    public Telefon(int _numeroTelefon, String _etiqueta, String _codiPais) {
//        this.numeroTelefon = _numeroTelefon;
//        this.etiqueta = _etiqueta;
//        if(_codiPais.isEmpty()){
//            this.codiPais = PAIS_PER_DEFECTE;
//        } else {
//            this.codiPais = _codiPais;
//        }
//    }

    public Telefon(int _numeroTelefon, String _etiqueta) {
        this.numeroTelefon = _numeroTelefon;
        this.etiqueta = _etiqueta;
    }

    // Setters i Getters
    public int getNumeroTelefon() {
        return numeroTelefon;
    }

    public void setNumeroTelefon(int _numeroTelefon) {
        if(_numeroTelefon > 100000000 && _numeroTelefon < 999999999) {
            this.numeroTelefon = _numeroTelefon;
        }
    }

    @Override
    public String toString() {
        return this.numeroTelefon + " (" + this.etiqueta+ ")";
    }
}
