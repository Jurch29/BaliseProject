package phase;

import deplacement.Direction;
import deplacement.Verticale;
import model.Balise;
import tools.GlobaleVariable;

public class SynchSat extends Phase {
	
	@Override
	public void step(Balise b) {
		// TODO Auto-generated method stub
		if (b.getPosition().y>GlobaleVariable.sizeY-GlobaleVariable.hauteurMer-5) {
			this.deplacement = new Verticale(1, Direction.Haut);
			b.setPosition(this.deplacement.getNextPoint(b.getPosition()));
		}
	}
	
	@Override
	public Phase nextPhase(Balise b) {
		return this;
	}

}
