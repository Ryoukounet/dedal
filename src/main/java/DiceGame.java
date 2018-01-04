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
    public static Dice dice ;
    public static Dice dice2;
    public static Group dices = new Group();
    public static Parent par;
}
