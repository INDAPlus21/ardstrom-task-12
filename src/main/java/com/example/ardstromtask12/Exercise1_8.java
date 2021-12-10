package com.example.ardstromtask12;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.robot.Robot;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Exercise1_8 extends Application {

    private final int sceneWidth = 600,sceneHeight = 600,sceneDepth = 600; // x y z
    @Override
    public void start(Stage stage) throws IOException {
        Group group = new Group();
        Scene scene = new Scene(group, sceneWidth, sceneHeight);
        stage.setTitle("Ball");
        stage.setScene(scene);

        PVector position = new PVector(100,100,100);
        PVector velocity = new PVector(0,0,0);
        PVector acceleration = new PVector(0,0);

        Circle circle = new Circle(10); // tried making it a sphere and in so making this 3d but linux and javafx hates each other
        circle.setTranslateX(position.x);
        circle.setTranslateY(position.y);
        circle.setFill(Color.ROYALBLUE);

        group.getChildren().add(circle);

        stage.show();

        //timeline basecase
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);
        timeline.getKeyFrames().addAll(
                new KeyFrame(
                        Duration.seconds(0),
                        new KeyValue(circle.translateXProperty(), position.x),
                        new KeyValue(circle.translateYProperty(), position.y)
                )
        );



        Robot robot = new Robot();
        //timeline animation
        timeline.setOnFinished(event -> {
            PVector vectorTowardsMouse = new PVector((robot.getMouseX()-stage.getX()) - position.x  , (robot.getMouseY()-stage.getY()) - position.y  );

            if(vectorTowardsMouse.mag() >= 200){
                vectorTowardsMouse.normalize();
                vectorTowardsMouse.mult(0.5); // speed of acceleration
            }else{
                vectorTowardsMouse.normalize();
                vectorTowardsMouse.mult(0.1); // speed of acceleration
            }

            acceleration.add(vectorTowardsMouse);
            acceleration.limit(1);
            velocity.add(acceleration);
            velocity.limit(10);
            position.add(velocity);
            timeline.getKeyFrames().set(
                    0,
                    new KeyFrame(
                            Duration.millis(40),
                            new KeyValue(circle.translateXProperty(), position.x),
                            new KeyValue(circle.translateYProperty(), position.y)
                    )
            );
            timeline.playFromStart();
        });
        timeline.play();
    }
}
