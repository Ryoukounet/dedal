package core;

public class HighScore {
    private int valeur;

    private HighScore() {

    }

    private static class HighScoreHolder {
        public static final HighScore INSTANCE = new HighScore();
    }

    public static HighScore getInstance() {
        return HighScoreHolder.INSTANCE;
    }

    protected Object readResolve() {
        return getInstance();
    }

}
