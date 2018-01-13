package application;

import core.Dice;
import core.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.CubeView;


public class DiceGame {
    //pour l'affichage
     static Group root;
     public static Scene scene;
     static Group dices = new Group();
     static Parent par;
     static PerspectiveCamera camera;

     private Stage stage;
     private Dice dice1 ;
     private Dice dice2;
     public static Player player;
     int nbLancer;



    public DiceGame(){
        player = new Player("unnamed",0);
        nbLancer = 10;
    }

    public void gameInitialize(){
        try {
            stage = IHM.stage;
            par =  FXMLLoader.load(getClass().getResource("../resources/Accueil.fxml"));
            root = new Group(par);
            scene = new Scene(root);

            camera = new PerspectiveCamera(false);
            scene.setCamera(camera);
            stage.setTitle("Dedal");
            stage.setScene(DiceGame.scene);
            root.getChildren().addAll(DiceGame.dices);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     void start(){
        if(!isOver()) {
            clearMap();
            dice1 = new Dice(new CubeView(), 450 , 200);
            dice2 = new Dice(new CubeView(), 250, 250);
            dice1.addObserver(IHM.diceView);
            dice2.addObserver(IHM.diceView);
            dices.getChildren().addAll(dice1.getCube().getMesh(), dice2.getCube().getMesh());
            dice1.roll();
            dice2.roll();
            nbLancer--;
        }
    }

     boolean isOver(){
        if(dice1 == null && dice2 == null )
            return false;
        return dice1.isRoll() || dice2.isRoll();
    }

     private void clearMap(){
        dices.getChildren().removeAll(dices.getChildren());
    }
}
