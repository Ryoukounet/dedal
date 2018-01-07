import core.Dice;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;



 class DiceGame {
     static Group root;
     static Scene scene;
     private static final float EDGE_LENGTH = 50;

     static PerspectiveCamera camera = new PerspectiveCamera(false);
     private Dice dice1 ;
     private Dice dice2;
     static Group dices = new Group();
     static Parent par;


     void start(){
        if(!isOver()) {
            clearMap();
            dice1 = new Dice(50, 50, 50, 457, 1070);
            dice2 = new Dice(50, 50, 50, 457, 1070);
            dice1.addObserver(IHM.diceView);
            dice2.addObserver(IHM.diceView);
            dice1.getCube().setLayoutX(450);
            dice1.getCube().setLayoutY(200);
            dice1.getCube().setTranslateZ(0);

            dice2.getCube().setLayoutX(250);
            dice2.getCube().setLayoutY(250);
            dice2.getCube().setTranslateZ(0);
            dices.getChildren().addAll(dice1.getCube(), dice2.getCube());
            dice1.roll();
            dice2.roll();
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
