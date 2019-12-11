package model;

import java.awt.Point;
import deplacement.Deplacement;
import deplacement.Direction;
import deplacement.Verticale;
import notification.Notification;
import notification.Notifier;
import notification.PositionChange;
import phase.Mouvement;
import phase.Phase;
import tools.GlobaleVariable;
import tools.Tools;
import vue.BaliseFrame;

public class Balise extends SimulationElement {

	private Point position;
	private int data[];
	private int profondeur;
	private int nbData;
	private Phase phase;
	private Deplacement dep;
	private boolean baliseRun;
	private BaliseFrame vue;
	
	public Balise(Point p, BaliseFrame app, Notifier n) {
		this.data = new int[10];
		this.position = p;
		this.profondeur = 0;
		this.nbData = 0;
		this.baliseRun = true;
		this.vue = app;
		this.notifier = n;
	}
	
	public int[] getData() {
		return data;
	}

	public void setData(int[] data) {
		this.data = data;
	}
	
	public Phase getPhase() {
		return phase;
	}
	
	public void setPhase(Phase phase) {
		this.phase = phase;
	}
	
	public Deplacement getDep() {
		return dep;
	}

	public void setDep(Deplacement dep) {
		this.dep = dep;
	}
	
	public void setPosition(Point position) {
		this.position = position;
	}
	
	public Point getPosition() {
		return position;
	}
	
	public int getNbData() {
		return this.nbData;
	}

	public int getProfondeur() {
		return this.profondeur;
	}
	
	public void addProfondeur(int profondeur) {
		this.profondeur += profondeur;
		this.phase = new Mouvement(new Verticale(this.profondeur, Direction.Bas));
	}
	
	public boolean isMemoryEmpty() {
		return this.nbData == 0;
	}
	
	public boolean isMemoryFull() {
		// TODO Auto-generated method stub
		return this.nbData==9;
	}
	
	public void addData(int data) {
		this.data[nbData] = data;
		this.nbData++;
	}
	
	public void resetData() {
//		System.out.println("Data reset");
		this.data = new int[10];
		this.nbData = 0;
	}
	
	@Override
	public void run() {
		while (this.baliseRun) {
			Tools.sleep(GlobaleVariable.vitesseSimulation);
			this.phase.step(this);
			this.phase = this.phase.nextPhase(this);
			this.vue.updateBalise(this);
		}
	}

	public void synchroReady() throws NoSuchMethodException, SecurityException {
		//ajout dans le notifier
		this.notifier.addListener(PositionChange.class,this,"tryToSynchronizeWith");
	}

	public void tryToSynchronizeWith(Notification n) throws NoSuchMethodException, SecurityException {
		Satellite s = (Satellite) n.getSource();
		if (this.position.x>s.getPosition().x-10 && this.position.x<s.getPosition().x+10) {
			//On est dans une zone de réception du satellite
			if (s.lock()) {
				s.addDataToMemory(this.getData());
				s.unlock();
				this.resetData();
				this.notifier.removeListener(PositionChange.class,this,"tryToSynchronizeWith");
			}
		}
	}
}
