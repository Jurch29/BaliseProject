package deplacement;

import java.awt.Point;

public abstract class Linéaire extends Deplacement {

	protected int value;
	protected int originalValue;
	protected Direction direction;
	
	@Override
	public void resetDeplacement() {
		this.value = this.originalValue;
	}
	
	@Override
	public Point getNextPoint(Point p) {
		// TODO Auto-generated method stub
		return null;
	}

}
