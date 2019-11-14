package phase;

import deplacement.Deplacement;
import model.Balise;

public abstract class Phase {
	
	protected Deplacement deplacement;
	public abstract void step(Balise b);
	public Phase nextPhase(Balise b) {
		return this;
	}
}
