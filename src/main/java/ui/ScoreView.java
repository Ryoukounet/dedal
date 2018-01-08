package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import persist.Entry;
import persist.HighScore;
import persist.MysqlKit;
import persist.PersistKit;

import java.net.URL;
import java.util.*;

public class ScoreView implements Initializable {

    @FXML
    public ListView scores;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        PersistKit pk;
        HighScore highScore;
        pk = new MysqlKit(); // ou  pk = new PostgresKit();
        highScore = pk.makeKit();

        ArrayList entries = highScore.getEntries();
        Iterator<Entry> it = entries.iterator();
        ObservableList data = FXCollections.observableArrayList();

        while(it.hasNext()){

            data.add(it.next().toString());
        }
        scores.setItems(data);
    }
}