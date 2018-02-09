package bugWorld;

import javafx.scene.shape.Circle;

public class Bug extends Circle{
	float dx, dy;
	double x, y;
	static int size = 20;
	String species;
	
	Bug(double x, double y, float dx, float dy, String species){
		super(x, y, size);
		this.dx = dx;
		this.dy = dy;
		this.species = species;
	}
	
	public String getSpecies(){
		return species;
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
	
	public float getDx(){
		return dx;
	}
	
	public float getDy(){
		return dy;
	}
	
	public void setSpecies(String species){
		this.species = species;
	}
	
	public void setX(double d){
		x = d;
	}
	
	public void setY(double d){
		y = d;
	}
	
	public void setDx(float f){
		dx = f;
	}
	
	public void setDy(float f){
		dy = f;
	}
}
