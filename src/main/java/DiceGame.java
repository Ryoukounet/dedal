import core.Dice;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;

import java.awt.*;

public class DiceGame {
    public static Group root;
    public static Scene scene;
    private static final float EDGE_LENGTH = 50;

    public static PerspectiveCamera camera = new PerspectiveCamera(false);
    public Dice dice ;
    public Dice dice2;
    public static Group dices = new Group();
    public static Parent par;


    public void start(){
        if(!isOver()) {
            clearMap();
            dice = new Dice(50, 50, 50, 457, 1070);
            dice2 = new Dice(50, 50, 50, 457, 1070);
            dice.getCube().setLayoutX(450);
            dice.getCube().setLayoutY(200);
            dice.getCube().setTranslateZ(0);

            dice2.getCube().setLayoutX(250);
            dice2.getCube().setLayoutY(250);
            dice2.getCube().setTranslateZ(0);
            dices.getChildren().addAll(dice.getCube(), dice2.getCube());
            dice.roll();
            dice2.roll();
        }
    }

    public boolean isOver(){
        if(dice == null && dice2 == null )
            return false;
        return dice.isRoll() && dice2.isRoll();
    }

    public void clearMap(){

        dices.getChildren().removeAll(dices.getChildren());
    }
}
