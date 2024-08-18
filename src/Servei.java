public class Servei {

    private String nom;
    private int numNens;
    private int numMunis;

    public Servei(String nom, int numNens, int numMunis) {
        this.nom = nom;
        this.numNens = numNens;
        this.numMunis = numMunis;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getNumNens() {
        return numNens;
    }
    public void setNumNens(int numNens) {
        this.numNens = numNens;
    }
    public int getNumMunis() {
        return numMunis;
    }
    public void setNumMunis(int numMunis) {
        this.numMunis = numMunis;
    }
}
