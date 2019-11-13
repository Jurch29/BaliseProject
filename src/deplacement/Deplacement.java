package deplacement;

import java.awt.Point;

public abstract class Deplacement {

	public abstract Point getNextPoint(Point p);
	public boolean isDeplacementFini() {
		return false;
	}
}
