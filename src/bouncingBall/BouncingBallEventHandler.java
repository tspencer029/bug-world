package bouncingBall;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;

public class BouncingBallEventHandler implements EventHandler<ActionEvent> {
	
	int width, height;
	float x, y, dx, dy;
	Circle circle;
	Scene scene;
	ArrayList<Circle> list = new ArrayList<Circle>();

	BouncingBallEventHandler(ArrayList<Circle> list, Circle circle, int width, int height, Scene scene, float x, float y, float dx, float dy) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.circle = circle;
		this.scene = scene;
		this.list = list;
	}
	
	@Override
	public void handle(ActionEvent event) {
		for(int i = 0; i < list.size(); i++){
			circle = list.get(i);
		if(circle.getCenterX() + circle.getTranslateX() < circle.getRadius() || 
				circle.getCenterX() + circle.getTranslateX() + circle.getRadius() > scene.getWidth()){
			dx = - dx;
		}

		if(circle.getCenterY() + circle.getTranslateY() < circle.getRadius() ||
				circle.getCenterY() + circle.getTranslateY() + circle.getRadius() > scene.getHeight()){
			dy = - dy;
		}

		circle.setTranslateX(circle.getTranslateX() + dx);
		circle.setTranslateY(circle.getTranslateY() + dy);
	}
	}
	
}
