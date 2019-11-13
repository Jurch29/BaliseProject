package deplacement;

import java.awt.Point;

import tools.GlobaleVariable;

public class Horizontale extends Deplacement {
	
	private int value;

	public Horizontale(int value) {
		// TODO Auto-generated constructor stub
		this.value = value;
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
