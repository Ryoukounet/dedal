package ui;

import application.DiceGame;
import application.IHM;
import core.Player;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import persist.*;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class PlayerView implements Observer {

    @FXML
    TextField name;
    @FXML
    Label score;

    public static Stage stage;

    private Player player;
    @Override
    public void update(Observable o, Object arg) {

        player = (Player) o;
        score.setText("Score : " + player.getScore());

    }

    public void goToGame(){

        if(!name.getText().trim().equals(""))
            player.setName(name.getText());
        PersistKit pk;
        HighScore highScore;
        pk = new MysqlKit(); // ou  pk = new PostgresKit();
        highScore = pk.makeKit();
        Entry entry = new Entry();
        entry.setName(player.getName());
        entry.setScore(player.getScore());
        highScore.save(entry);
        redirect();
    }

    public void redirect(){
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("../resources/Accueil.fxml"));


        Platform.runLater(new Runnable() {
          @Override
          public void run() {
              try {
                  Parent loadScreen = (Parent) myLoader.load();
                  Scene scene;

                  scene = new Scene(loadScreen, 650, 150);

                  stage.setScene(scene);
                  DiceGame.scene = scene;

                  stage.getScene().setRoot(loadScreen);
                  DiceGame game = new DiceGame();
                  game.gameInitialize();



              } catch (IOException e) {
                  e.printStackTrace();
              }



          }
      });
    }
}
