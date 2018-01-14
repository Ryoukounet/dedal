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

    public synchronized int getValue() {
        int upper = 8;
        int lower = -8;

        int res = Math.toIntExact(Math.round(((Math.random())) * (upper - lower) + lower));

        if (res >= 0)
            res += 2;
        else
            res -= 2;

        return res;
    }
}
