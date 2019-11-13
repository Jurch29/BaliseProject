package phase;

import model.Balise;

public abstract class Phase {
	public abstract void step(Balise b);
	public Phase nextPhase(Balise b) {
		return this;
	}
}
