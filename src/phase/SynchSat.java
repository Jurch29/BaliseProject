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
//			System.out.println("monte");
			this.deplacement = new Verticale(1, Direction.Haut);
			b.setPosition(this.deplacement.getNextPoint(b.getPosition()));
		}
		else {
			try {
				b.synchroReady();
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public Phase nextPhase(Balise b) {
		if (b.isMemoryEmpty()) {
			return new Mouvement(new Verticale(b.getProfondeur(), Direction.Bas));
		}
		return this;
	}

}
