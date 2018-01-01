package core;

public class HighScore {

    private static HighScore instance;
    private int valeur;

    private HighScore() {
        this.valeur = 0;
    }

    public synchronized static HighScore getInstance() {
        if (instance == null) {
            instance = new HighScore();
        }
        return instance;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }
}
