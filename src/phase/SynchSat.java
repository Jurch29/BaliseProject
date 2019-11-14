package phase;

import deplacement.Deplacement;
import deplacement.Direction;
import deplacement.Verticale;
import model.Balise;
import tools.GlobaleVariable;

public class SynchSat extends Phase {
	
	private Deplacement memoryDeplacement;
	
	public SynchSat(Deplacement d) {
		this.memoryDeplacement = d;
	}
	
	@Override
	public void step(Balise b) {
		// TODO Auto-generated method stub
		if (b.getPosition().y>GlobaleVariable.sizeY-GlobaleVariable.hauteurMer-5) {
			this.deplacement = new Verticale(1, Direction.Haut);
			b.setPosition(this.deplacement.getNextPoint(b.getPosition()));
		}
		else {
			b.synchroReady();
		}
	}
	
	@Override
	public Phase nextPhase(Balise b) {
		if (b.isMemoryEmpty()) {
			return new Collecte(this.memoryDeplacement);
		}
		return this;
	}

}
