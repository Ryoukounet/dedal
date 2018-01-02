package core;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point3D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import java.util.Observable;

public class Dice extends Observable implements Runnable {

    private Rotate axeX,axeY, axeZ;

    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);
    private final DoubleProperty angleZ = new SimpleDoubleProperty(0);
    private double heightTerrain, widthTerrain;
    private MeshView cube = new MeshView();
    private float width, height;

    public Dice(float width, float height, float depth,double heightTerrain, double widthTerrain){
        PhongMaterial diceMaterial = new PhongMaterial();
        Image image = new Image(getClass().getResourceAsStream("../resources/dice.jpg"));
        diceMaterial.setDiffuseMap(image);

        this.width = width;
        this.height = height;
        this.widthTerrain = widthTerrain;
        this.heightTerrain = heightTerrain;

        TriangleMesh mesh = new TriangleMesh();

        float hw = width / 2f;
        float hh = height / 2f;
        float hd = depth / 2f;

        float x0 = 0f;
        float x1 = 1f / 4f;
        float x2 = 2f / 4f;
        float x3 = 3f / 4f;
        float x4 = 1f;
        float y0 = 0f;
        float y1 = 1f / 3f;
        float y2 = 2f / 3f;
        float y3 = 1f;

        mesh.getPoints().addAll(
                hw, hh, hd,   //point A
                hw, hh, -hd,  //point B
                hw, -hh, hd,  //point C
                hw, -hh, -hd, //point D
                -hw, hh, hd,  //point E
                -hw, hh, -hd, //point F
                -hw, -hh, hd, //point G
                -hw, -hh, -hd //point H
        );
        mesh.getTexCoords().addAll(
                x1, y0,
                x2, y0,
                x0, y1,
                x1, y1,
                x2, y1,
                x3, y1,
                x4, y1,
                x0, y2,
                x1, y2,
                x2, y2,
                x3, y2,
                x4, y2,
                x1, y3,
                x2, y3
        );
        mesh.getFaces().addAll(
                0, 10, 2, 5, 1, 9,   //triangle A-C-B
                2, 5, 3, 4, 1, 9,    //triangle C-D-B
                4, 7, 5, 8, 6, 2,    //triangle E-F-G
                6, 2, 5, 8, 7, 3,    //triangle G-F-H
                0, 13, 1, 9, 4, 12,  //triangle A-B-E
                4, 12, 1, 9, 5, 8,   //triangle E-B-F
                2, 1, 6, 0, 3, 4,    //triangle C-G-D
                3, 4, 6, 0, 7, 3,    //triangle D-G-H
                0, 10, 4, 11, 2, 5,  //triangle A-E-C
                2, 5, 4, 11, 6, 6,   //triangle C-E-G
                1, 9, 3, 4, 5, 8,    //triangle B-D-F
                5, 8, 3, 4, 7, 3     //triangle F-D-H
        );
        mesh.getFaceSmoothingGroups().addAll(
                0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5
        );
        cube.setMesh(mesh);
        cube.setMaterial(diceMaterial);

        cube.getTransforms().setAll(
                axeX = new Rotate(0, Rotate.X_AXIS),
                axeY = new Rotate(0, Rotate.Y_AXIS),
                axeZ = new Rotate(0, Rotate.Z_AXIS)
        );
        axeX.angleProperty().bind(angleX);
        axeY.angleProperty().bind(angleY);
        axeZ.angleProperty().bind(angleZ);




    }
    public void run(){

        int i = 0;
        double x = cube.getLayoutX();
        double y = cube.getLayoutY();
        float deltaAngleX = 5;
        float deltaAngleY = 5;
        System.out.println(x + "    " + y);

        while(deltaAngleX != 0 && deltaAngleY != 0) {

            angleY.set(angleY.floatValue() - deltaAngleX);
            if(Math.abs(angleY.floatValue()) > 360)
                angleY.set(0);

            if(x < (widthTerrain - width + 30) && x > width )
                this.cube.setLayoutX(x = x + deltaAngleX);

            angleX.set(angleX.floatValue() + deltaAngleY);
            if(Math.abs(angleX.floatValue()) > 360)
                angleX.set(0);

            if(y < (heightTerrain - 40) && y > height)
                this.cube.setLayoutY(y = y + deltaAngleY);
           // System.out.println("angle x :" + angleX.floatValue() + " angle Y :" + angleY.floatValue() );
            i++;
            if((i % 60) == 0){
                if(deltaAngleX > 0)
                    deltaAngleX = deltaAngleX - 1;
                if(deltaAngleY > 0)
                    deltaAngleY = deltaAngleY - 1;

            }
            try {
                Thread.sleep(50);
                this.notifyObservers();
            }
            catch (InterruptedException ex){

            }catch (Exception ex){

            }


        }
    }

    public MeshView getCube() {
        return cube;
    }

}
