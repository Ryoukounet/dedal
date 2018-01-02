package ui;

import core.Player;

import java.util.Observable;
import java.util.Observer;

public class PlayerView implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        Player p = (Player) o;
    }

}
