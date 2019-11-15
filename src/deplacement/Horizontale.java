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
		if (this.direction == Direction.Droite) {
			if (p.x+GlobaleVariable.vitesseBalise>GlobaleVariable.sizeX)
				return new Point(0, p.y);
			else
				return new Point(p.x+GlobaleVariable.vitesseBalise, p.y);
		}
		else if (this.direction == Direction.Gauche) {
			if (p.x+GlobaleVariable.vitesseBalise<0)
				return new Point(GlobaleVariable.sizeX, p.y);
			else
				return new Point(p.x-GlobaleVariable.vitesseBalise, p.y);
		}

		return null;
	}
}
