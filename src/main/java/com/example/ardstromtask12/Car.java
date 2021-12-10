package com.example.ardstromtask12;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Car extends Application {

    private final int sceneWidth = 600,sceneHeight = 600,sceneDepth = 600; // x y z

    @Override
    public void start(Stage stage) throws IOException {
        Group group = new Group();
        Scene scene = new Scene(group, sceneWidth, sceneHeight);
        stage.setTitle("Ball");
        stage.setScene(scene);

        PVector position = new PVector(100,100);
        PVector velocity = new PVector(0,0);
        PVector acceleration = new PVector(0,0);

        Circle circle = new Circle(10); // the car is a circle deal with it
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

        //keypresses
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()){
                case W -> acceleration.y = -1;
                case S -> acceleration.y = 1;
                case A -> acceleration.x = -1;
                case D -> acceleration.x = 1;
            }
        });
        //key release
        scene.setOnKeyReleased(e -> {
            switch (e.getCode()){
                case W, S -> acceleration.y = 0;
                case A, D -> acceleration.x = 0;
            }
        });



        //timeline animation
        timeline.setOnFinished(event -> {


            velocity.add(acceleration);
            position.add(velocity);

            //x position
            if(position.x >=sceneWidth ){
                position.x = sceneWidth * 2 -position.x;
                velocity.x = -velocity.x;
            }else if(position.x<=0 ){
                position.x = -(position.x);
                velocity.x = -velocity.x;
            }

            //y position
            if(position.y>=sceneHeight ){
                position.y = sceneHeight * 2 - position.y;
                velocity.y = -velocity.y;
            } else if(position.y <=0 ){
                position.y = -position.y;
                velocity.y = -velocity.y;

            }

            //z position
            if(position.z>=sceneDepth ){
                position.z = sceneDepth * 2 - position.z;
                velocity.z = -velocity.z;
            } else if(position.z<=0 ){
                position.z = -position.z;
                velocity.z = -velocity.z;
            }


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
