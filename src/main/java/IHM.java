import core.Dice;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ui.DiceView;

import java.awt.*;

public class IHM extends Application {

    @FXML
    public Label score;

    DiceGame game;
    static DiceView diceView;

    @Override
    public void start(Stage stage) {


        try {
            DiceGame.par =  FXMLLoader.load(getClass().getResource("resources/Accueil.fxml"));
            DiceGame.root = new Group(DiceGame.par);
            DiceGame.scene = new Scene(DiceGame.root);

            DiceGame.scene.setCamera(DiceGame.camera);
            stage.setTitle("Dedal");
            stage.setScene(DiceGame.scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
        //diceView = new DiceView(score);
        DiceGame.root.getChildren().addAll(DiceGame.dices);


    }

    @FXML
    private void start(ActionEvent event){
            if(diceView == null) {
                //System.out.println("jjjj");
                diceView = new DiceView(score);
                Thread view = new Thread(diceView);
                view.start();
            }
            if(game == null || !game.isOver()){
                game = new DiceGame();
                game.start();
            }
    }

    public static void main(String[] args) {
        launch(args);
    }
}