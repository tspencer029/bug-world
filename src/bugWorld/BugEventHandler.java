package bugWorld;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

public class BugEventHandler implements EventHandler<ActionEvent>{

	int width, height;
	float dx, dy;
	Bug bug;
	Scene scene;
	ArrayList<Bug> list = new ArrayList<Bug>();

	BugEventHandler(ArrayList<Bug> list, Bug bug, int width, int height, Scene scene, float dx, float dy) {
		this.width = width;
		this.height = height;
		this.dx = dx;
		this.dy = dy;
		this.bug = bug;
		this.scene = scene;
		this.list = list;
	}
	
	@Override
	public void handle(ActionEvent event) {
		for(int i = 0; i < list.size(); i++){
			bug = list.get(i);
		if(bug.getCenterX() + bug.getTranslateX() < bug.getRadius() || 
				bug.getCenterX() + bug.getTranslateX() + bug.getRadius() > scene.getWidth()){
			dx = - dx;
		}

		if(bug.getCenterY() + bug.getTranslateY() < bug.getRadius() ||
				bug.getCenterY() + bug.getTranslateY() + bug.getRadius() > scene.getHeight()){
			dy = - dy;
		}

		bug.setTranslateX(bug.getTranslateX() + dx);
		bug.setTranslateY(bug.getTranslateY() + dy);
	}
	}
	
}
