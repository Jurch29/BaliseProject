package deplacement;

import java.awt.Point;

import tools.GlobaleVariable;

public class Horizontale extends Deplacement {
	
	private int value;
	private Direction direction;

	public Horizontale(int value, Direction d) {
		//TODO Auto-generated constructor stub
		this.value = value;
		this.direction = d;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	@Override
	public boolean isDeplacementFini() {
		return value==0;
	}

	@Override
	public Point getNextPoint(Point p) {
		// TODO Auto-generated method stub
		value--;
		return new Point(p.x+GlobaleVariable.vitesseBalise, p.y);
	}

}
