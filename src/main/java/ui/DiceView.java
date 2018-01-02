package ui;



import core.Dice;

import java.util.Observable;
import java.util.Observer;

public class DiceView implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        Dice dice = (Dice)o;

    }
}
