package utils;

public class Randomizer {

    private static Randomizer instance;

    private Randomizer() {
    }

    public synchronized static Randomizer getInstance() {
        if (instance == null) {
            instance = new Randomizer();
        }
        return instance;
    }

    public int getValue() {
        int upper = 10;
        int lower = -10;
        return (int) (Math.random() * (upper - lower)) + lower;
    }
}
