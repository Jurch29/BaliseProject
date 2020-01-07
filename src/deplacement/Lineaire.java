package deplacement;

import java.awt.Point;

public abstract class Lineaire extends Deplacement {

	protected int value;
	protected int originalValue;
	protected Direction direction;
	
	@Override
	public void resetDeplacement() {
		this.value = this.originalValue;
	}
	
	@Override
	public Point getNextPoint(Point p) {
		return null;
	}
}
