package deplacement;

import java.awt.Point;
import java.util.List;

public class Libre extends Deplacement {
	
	private List<Deplacement> deplacements;
	private int cursor;
	
	public Libre(List<Deplacement> deplacements) {
		// TODO Auto-generated constructor stub
		this.deplacements = deplacements;
		this.cursor = 0;
	}

	@Override
	public Point getNextPoint(Point p) {
		// TODO Auto-generated method stub
		if (this.deplacements.get(this.cursor).isDeplacementFini())
			this.cursor++;
		if (this.cursor==this.deplacements.size())
			this.cursor=0;
		return this.deplacements.get(this.cursor).getNextPoint(p);
	}

}
