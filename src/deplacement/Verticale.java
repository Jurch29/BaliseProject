package deplacement;

import java.awt.Point;

import tools.GlobaleVariable;

public class Verticale extends Deplacement {

	private int value;

	public Verticale(int value) {
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
		// TODO Auto-generated method stubs
		value--;
		return new Point(p.x, p.y+GlobaleVariable.vitesseBalise);
	}

}
