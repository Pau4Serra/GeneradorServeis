public class Monitor {

    private String nom;
    private int numServeis;

    public Monitor(String nom, int numServeis) {
        this.nom = nom;
        this.numServeis = numServeis;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNumServeis() {
        return numServeis;
    }

    public void setNumServeis(int numServeis) {
        this.numServeis = numServeis;
    }
}
