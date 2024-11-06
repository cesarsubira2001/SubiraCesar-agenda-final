package classes;

public class Adressa {
    // Atributs
    private String carrer;
    private int numeroCarrer;
    private String ciutat;
    private String codiPostal;
    private String pais;

    // Constructor
    public Adressa(String _carrer,
                   int _numeroCarrer,
                   String _ciutat,
                   String _codiPostal,
                   String _pais) {
        this.carrer = _carrer;
        this.numeroCarrer = _numeroCarrer;
        this.ciutat = _ciutat;
        this.codiPostal = _codiPostal;
        this.pais = _pais;
    }

    public String getCarrer() {
        return carrer;
    }

    public void setCarrer(String _carrer) {
        this.carrer = _carrer;
    }

    public int getNumeroCarrer() {
        return numeroCarrer;
    }

    public void setNumeroCarrer(int _numeroCarrer) {
        this.numeroCarrer = _numeroCarrer;
    }

    public String getCiutat() {
        return ciutat;
    }

    public void setCiutat(String _ciutat) {
        this.ciutat = _ciutat;
    }

    public String getCodiPostal() {
        return codiPostal;
    }

    public void setCodiPostal(String _codiPostal) {
        this.codiPostal = _codiPostal;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String _pais) {
        this.pais = _pais;
    }

    @Override
    public String toString() {
        return "Adreça " +
                carrer + ", " + numeroCarrer +
                codiPostal + " - " + ciutat + '\'' +
                ", " + pais + '\'' +
                '}';
    }
}
