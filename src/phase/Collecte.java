package phase;

import model.Balise;

public class Collecte extends Phase {
	
	private Mouvement deplacement;

	@Override
	public void step(Balise b) {
		//TODO Auto-generated method stub
		
	}
	
	@Override
	public Phase nextPhase(Balise b) {
		if (false) {   //Si condition sortie
			return new SynchSat();
		}
		return this;
	}
	
}
