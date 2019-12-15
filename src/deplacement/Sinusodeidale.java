package deplacement;

import java.awt.Point;

import tools.GlobaleVariable;

public class Sinusodeidale extends Deplacement {
	
	private int amplitude;
	private Direction direction;
	private int dir;
	
	public Sinusodeidale(int a, Direction d) {
		this.amplitude = a;
		this.direction = d;
		this.dir = GlobaleVariable.vitesseBalise;
		if (this.direction==Direction.Gauche)
			dir = -GlobaleVariable.vitesseBalise;
	}

	@Override
	public Point getNextPoint(Point p) {
		if (p.x+this.dir>GlobaleVariable.sizeX)
			return new Point(0, p.y+(int) (this.amplitude * Math.sin(Math.toDegrees(p.x/4))));
		if (p.x+this.dir<0)
			return new Point(GlobaleVariable.sizeX, p.y+(int) (this.amplitude * Math.sin(Math.toDegrees(p.x/4))));
		return new Point(p.x+this.dir, p.y+(int) (this.amplitude * Math.sin(Math.toDegrees(p.x/4))));
	}
}