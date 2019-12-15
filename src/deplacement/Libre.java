package deplacement;

import java.awt.Point;
import java.util.List;

public class Libre extends Deplacement {
	
	private List<Deplacement> deplacements;
	private int cursor;
	
	public Libre(List<Deplacement> deplacements) {
		this.deplacements = deplacements;
		this.cursor = 0;
	}

	@Override
	public Point getNextPoint(Point p) {
		if (this.deplacements.get(this.cursor).isDeplacementFini()) {
			this.deplacements.get(this.cursor).resetDeplacement();
			this.cursor++;
		}
		if (this.cursor==this.deplacements.size())
			this.cursor=0;
		return this.deplacements.get(this.cursor).getNextPoint(p);
	}

}
