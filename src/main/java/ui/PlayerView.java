package ui;

import core.Player;
import persist.*;

import java.util.Observable;
import java.util.Observer;

public class PlayerView implements Observer {

    @Override
    public void update(Observable o, Object arg) {

        Player p = (Player) o;
    }

    public void goToGame(){
        PersistKit pk;
        HighScore highScore;
        pk = new MysqlKit(); // ou  pk = new PostgresKit();
        highScore = pk.makeKit();
        highScore.save(new Entry("Player", 10));
    }
}
