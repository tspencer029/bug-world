package bouncingBall;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BouncingBall extends Application{
	int width = 400, height = 300;
	float x = 100, y = 100, dx = -1.5f, dy = -1.5f;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		ArrayList<Circle> circleList = new ArrayList<Circle>();
		Circle circle = new Circle(x, y, 30, Color.BISQUE);
		Circle circle1 = new Circle(200, 200, 30);
		circleList.add(circle);
		circleList.add(circle1);

		Group root = new Group();
		root.getChildren().addAll(circleList);
		Scene scene = new Scene(root, width, height);

		KeyFrame frame = new KeyFrame(Duration.millis(16), new BouncingBallEventHandler(circleList, circle, width, height, scene, x, y, dx, dy));
		
		TimelineBuilder.create().cycleCount(javafx.animation.Animation.INDEFINITE).keyFrames(frame).build().play();

		primaryStage.setTitle("Bouncing Ball");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}
