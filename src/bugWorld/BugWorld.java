package bugWorld;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BugWorld extends Application{

	/**integers to hold height and width of the page and amounts of different objects**/
	int width = 800, height = 600, bugAmount = 10, plantAmount = 200,
			poisonAmount = 3, superBugAmount = 1;
	
	/**dx and dy speed**/
	float dx = -1.5f, dy = -1.5f;
	
	
	/**array lists to hold the three different objects**/
	ArrayList<Bug> bugList = new ArrayList<Bug>(); 
	ArrayList<Plant> plantList = new ArrayList<Plant>(); 
	ArrayList<Plant> poisonList = new ArrayList<Plant>();

	
	int nScore = 0; /**number of plants that have been eaten**/
	
	/**loads images for the four different types of objects and the background**/
	Image bugImage = new Image(getClass().getResourceAsStream("bugtrans.png"));
	Image plantImage = new Image(getClass().getResourceAsStream("treetrans.png"));
	Image bckg = new Image(getClass().getResourceAsStream("forest.png"));
	Image poisonPlant = new Image(getClass().getResourceAsStream("ivytrans.png"));
	Image superBugImage = new Image(getClass().getResourceAsStream("superbugtrans.png"));
	
	

	@Override
	public void start(Stage primaryStage) throws Exception {

		/**loop to load a number of plants in random positions**/
		for(int i = 0; i < plantAmount; i++){
			int randX = (int) (Math.random()*width*0.9)+(int)(width*0.05);
			int randY = (int) (Math.random()*height*0.9)+(int)(height*0.05);
			Plant plant = new Plant(randX, randY);
			plant.setFill(new ImagePattern(plantImage));
			plant.setStroke(Color.TRANSPARENT);
			plantList.add(plant);
		}

		/**loop to load a number of poison plants in random positions**/
		for(int i = 0; i < poisonAmount; i++){
			int randX = (int) (Math.random()*width*0.9)+(int)(width*0.05);
			int randY = (int) (Math.random()*height*0.9)+(int)(height*0.05);
			Plant ivy = new Plant(randX, randY);
			ivy.setFill(new ImagePattern(poisonPlant));
			ivy.setStroke(Color.TRANSPARENT);
			poisonList.add(ivy);
		}

		/**loop to load a number of bugs in random positions with random speeds**/
		for(int i = 0; i < bugAmount; i++){
			int randX = (int) (Math.random()*width-100);
			int randY = (int) (Math.random()*height-100);
			float randomSpeed = (float) (Math.random()*3+1.5);	
			Bug bug = new Bug(randX, randY, randomSpeed, randomSpeed, "normal");
			bug.setFill(new ImagePattern(bugImage));
			bug.setStroke(Color.TRANSPARENT);
			bugList.add(bug);
		}
		
		/**loop to add a number of super bugs**/
		for(int i = 0; i < superBugAmount; i++){
			int randX = (int) (Math.random()*width-100);
			int randY = (int) (Math.random()*height-100);
			float randomSpeed = (float) (Math.random()*3+1.5);
			Bug superBug = new Bug(randX, randY, randomSpeed, randomSpeed, "super");
			superBug.setFill(new ImagePattern(superBugImage));
			superBug.setStroke(Color.TRANSPARENT);
			bugList.add(superBug);
		}
		
			

		/**sets up the group**/
		Group root = new Group();
		
		/**creates label to keep score and sets position and colour**/
		Color newColour = new Color(255,255,255,255);
		Label score = new Label("Number of plants eaten: " + nScore + " out of " + plantAmount);
		score.setAlignment(Pos.TOP_LEFT);
		score.setTextFill(newColour);
		
		/**adds the three array lists and the label to the group**/
		root.getChildren().add(score);
		root.getChildren().addAll(bugList);
		root.getChildren().addAll(plantList);
		root.getChildren().addAll(poisonList);
		
		/**establishes scene and sets background image**/
		Scene scene = new Scene(root, width, height);
		scene.setFill(new ImagePattern(bckg));

		/**establishes keyframe and event handler**/
		KeyFrame frame = new KeyFrame(Duration.millis(16), new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				
				/**loop to go through the array list of bugs**/
				for(int i = 0; i < bugList.size(); i++){
					Bug bug = bugList.get(i);
					
					/**stops the bugs going out of bounds**/
					if(bug.getCenterX() + bug.getTranslateX() < bug.getRadius() && bug.getDx() < 0 || 
							bug.getCenterX() + bug.getTranslateX() + bug.getRadius() > scene.getWidth() && bug.getDx() > 0){
						bug.setDx(bug.getDx() * - 1);
						bug.setRotate(180);
					} 

					if(bug.getCenterY() + bug.getTranslateY() < bug.getRadius() && bug.getDy() < 0 ||
							bug.getCenterY() + bug.getTranslateY() + bug.getRadius() > scene.getHeight() && bug.getDy() > 0){
						bug.setDy(bug.getDy() * - 1);
						bug.setRotate(180);
					}

					bug.setTranslateX(bug.getTranslateX() + bug.getDx());
					bug.setTranslateY(bug.getTranslateY() + bug.getDy());
				}
				
				/**for each loops to remove the plant when a bug collides with it
				also updates nScore and score label**/
				for(Bug b : bugList){
					for(Plant p : new ArrayList<Plant> (plantList)){
						if(b.getBoundsInParent().intersects(p.getBoundsInParent())){
							plantList.remove(p);
							root.getChildren().remove(p);
							nScore++;
							score.setText("Number of plants eaten: " + nScore + " out of " + plantAmount);
						}
					}
				}
				
				/**sets bugs to change direction and rotation when they come
				into contact with another bug**/
				for(int i = 0; i < bugList.size(); i++){
					Bug b = bugList.get(i);
					for(int j = 0; j < bugList.size(); j = j + 2){
						Bug b1 = bugList.get(j);
						if(b.getBoundsInParent().intersects(b1.getBoundsInParent()) && i != j){
							b.setDx(b.getDx() * -1 );
							b1.setDy(b1.getDy() * -1);
							b.setRotate(b.getDx() + b.getDy() - 100);
							b1.setRotate(b1.getDx() + b1.getDy() + 100);
						}
					}
				}
				
				/**removes a bug when it comes into contact with a poison plant**/
				for(Bug b : new ArrayList<Bug>(bugList)){
					for(Plant p : new ArrayList<Plant> (poisonList)){
						if(p.getBoundsInParent().intersects(b.getBoundsInParent()) && b.getSpecies().equals("normal")){
							bugList.remove(b);
							root.getChildren().remove(b);
						}
					}
				}
				
				/**removes a poison plant when it comes into contact with a superbug**/
				for(Bug b : new ArrayList<Bug>(bugList)){
					for(Plant p : new ArrayList<Plant> (poisonList)){
						if(p.getBoundsInParent().intersects(b.getBoundsInParent()) && b.getSpecies().equals("super")){
							poisonList.remove(p);
							root.getChildren().remove(p);
						}
					}
				}
				
				
				/**if statement to change score label to display a message
				about whether all plants have been eaten**/
				if(plantList.isEmpty()){
					score.setText("All of the plants have been eaten");
				}
			}
		});
		
		/**builds timeline using keyframe and sets animation to be indefinite**/
		TimelineBuilder.create().cycleCount(javafx.animation.Animation.INDEFINITE).keyFrames(frame).build().play();

		/**sets up the primary stage using scene**/
		primaryStage.setTitle("Bug World");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**main method**/
	public static void main(String[] args) {
		launch();
	}

}
