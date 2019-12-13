package phase;

import deplacement.Direction;
import deplacement.Verticale;
import model.Balise;
import tools.GlobaleVariable;

public class SynchSat extends Phase {
	
	boolean synchroSend;
	
	public SynchSat() {
		//un flag pour envoyer un message de ready synchro une seule fois
		this.synchroSend = false;
	}
	
	@Override
	public void step(Balise b) {
		// TODO Auto-generated method stub
		if (b.getPosition().y>GlobaleVariable.sizeY-GlobaleVariable.hauteurMer-5) {
			this.deplacement = new Verticale(1, Direction.Haut);
			b.setPosition(this.deplacement.getNextPoint(b.getPosition()));
		}
		else {
			try {
				if (!synchroSend) {
					this.synchroSend = true;
					b.synchroReady();
				}
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public Phase nextPhase(Balise b) {
		//a la fin d'une synchro on plonge
		if (b.isMemoryEmpty())
			return new Mouvement(new Verticale(b.getProfondeur(), Direction.Bas));
		
		return this;
	}
}
