package deplacement;

import java.awt.Point;
import tools.GlobaleVariable;

public class Verticale extends Linéaire {

	public Verticale(int value, Direction d) {
		this.value = value;
		this.originalValue = value;
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
		// TODO Auto-generated method stubs
		value--;
		if (this.direction==Direction.Bas) {
			if (p.y+GlobaleVariable.vitesseBalise>GlobaleVariable.sizeY-10)
				return new Point(p.x, p.y);
			else
				return new Point(p.x, p.y+GlobaleVariable.vitesseBalise);
		}
		else if (this.direction==Direction.Haut) {
			if (p.y-GlobaleVariable.vitesseBalise<GlobaleVariable.sizeY-GlobaleVariable.hauteurMer-5) 
				return new Point(p.x, GlobaleVariable.sizeY-GlobaleVariable.hauteurMer-5);
			else
				return new Point(p.x, p.y-GlobaleVariable.vitesseBalise);
		}
		else
			return null;
	}
}
