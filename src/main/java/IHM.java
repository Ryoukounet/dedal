import core.Dice;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;

public class IHM extends Application {







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
        DiceGame.root.getChildren().addAll(DiceGame.dices);


    }

    @FXML
    private void startg(ActionEvent event){
        if(DiceGame.dice != null && DiceGame.dice2 != null) {
            DiceGame.dices.getChildren().remove(DiceGame.dice.getCube());
            DiceGame.dices.getChildren().remove(DiceGame.dice2.getCube());
        }
        DiceGame.dice =  new Dice(50, 50, 50,457,1070);
        DiceGame.dice2 =  new Dice(50, 50, 50,457,1070);
        DiceGame.dice.getCube().setLayoutX(450);
        DiceGame.dice.getCube().setLayoutY(200);
        DiceGame.dice.getCube().setTranslateZ(0);

        DiceGame.dice2.getCube().setLayoutX(250);
        DiceGame.dice2.getCube().setLayoutY(250);
        DiceGame.dice2.getCube().setTranslateZ(0);
        DiceGame.dices.getChildren().addAll(DiceGame.dice.getCube(), DiceGame.dice2.getCube());
        DiceGame.dice.roll();
        DiceGame.dice2.roll();
    }

    public static void main(String[] args) {
        launch(args);
    }
}