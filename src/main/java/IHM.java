import core.Dice;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class IHM extends Application {

    double anchorX, anchorY;

    private static final float EDGE_LENGTH = 50;

    PerspectiveCamera camera = new PerspectiveCamera(false);

    @Override
    public void start(Stage stage) {
        Dice dice = new Dice(EDGE_LENGTH, EDGE_LENGTH, EDGE_LENGTH,457,1070);
        Dice dice2 = new Dice(EDGE_LENGTH, EDGE_LENGTH, EDGE_LENGTH,457,1070);
       // dice2.getCube().setTranslateX(450);
       // dice2.getCube().setTranslateY(100);
      //  dice2.getCube().setTranslateZ(0);
        //dice2.angleX.set(270);
      //  dice2.angleY.set(180);
        //dice2.angleY.floatValue()
     //   System.out.println(dice2.angleX.floatValue() + " " + dice2.angleY.floatValue() );
        //System.out.println(getFace( dice2.angleX.floatValue(),dice2.angleY.floatValue()));



        Group dices = new Group(dice.getCube(), dice2.getCube());
        dices.setLayoutX(0);
        dices.setLayoutY(0);

        dice.getCube().setLayoutX(450);
        dice.getCube().setLayoutY(200);
        dice.getCube().setTranslateZ(0);

        dice2.getCube().setLayoutX(250);
        dice2.getCube().setLayoutY(250);
        dice2.getCube().setTranslateZ(0);


        Scene scene;
        Parent par;
        Group root;
        Pane tb;
        try {
            par =  FXMLLoader.load(getClass().getResource("resources/Accueil.fxml"));
            root = new Group(par, dices);
            scene = new Scene(root);

            scene.setCamera(camera);
            stage.setTitle("Dedal");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
        dice.roll();
        dice2.roll();
        //dice.run();
       /* Thread test = new Thread(dice);
        test.start();
        Thread test2 = new Thread(dice2);
        test2.start();*/
    }


    public static void main(String[] args) {
        launch(args);
    }
}