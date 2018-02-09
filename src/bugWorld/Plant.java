package bugWorld;

import javafx.scene.shape.Circle;

public class Plant extends Circle{
	static int size = 20;
	double x, y;
	
	Plant(double x, double y){
		super(x, y, size);
	}

	public int getSize(){
		return size;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public void setX(double d){
		x = d;
	}
	
	public void setY(double d){
		y = d;
	}
	
}
