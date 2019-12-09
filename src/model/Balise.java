package model;

import java.awt.Point;
import java.util.ArrayList;

import deplacement.Deplacement;
import deplacement.Direction;
import deplacement.Verticale;
import notification.Notification;
import notification.PositionChange;
import notification.SatelliteListener;
import notification.Synchronizable;
import phase.Mouvement;
import phase.Phase;
import tools.GlobaleVariable;
import tools.Tools;
import vue.BaliseFrame;

public class Balise extends SimulationElement implements SatelliteListener {

	private Point position;
	private int data[];
	private int profondeur;
	private int nbData;
	private Phase phase;
	private Deplacement dep;
	private boolean baliseRun;
	private ArrayList<Satellite> sats;
	private BaliseFrame vue;
	
	public Balise(Point p, BaliseFrame app) {
		this.data = new int[10];
		this.position = p;
		this.profondeur = 0;
		this.nbData = 0;
		this.baliseRun = true;
		this.vue = app;
		this.sats = new ArrayList<Satellite>();
	}
	
	public int[] getData() {
		return data;
	}

	public void setData(int[] data) {
		this.data = data;
	}
	
	public ArrayList<Satellite> getSats() {
		return sats;
	}

	public void setSats(ArrayList<Satellite> sats) {
		this.sats = sats;
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

	public void synchroReady() {
		//ajout dans le notifier
		for (int i = 0 ; i < this.sats.size() ; i++) {
			this.sats.get(i).register(PositionChange.class, this);
		}
	}

	public void addAllSats(ArrayList<Satellite> sats) {
		// TODO Auto-generated method stub
		this.sats.addAll(sats);
	}

//	@Override
//	public void receive(Notification n) {
//		// TODO Auto-generated method stub
//		n.run(this);
//	}
//
//	@Override
//	public void tryToSynchronizeWith(Object o) {
//		// TODO Auto-generated method stub
//		//logique métier
//		if (this.position.x>((Satellite) o).getPosition().x-10 && (this.position.x<((Satellite) o).getPosition().x+10)) {
//			//On est dans une zone de réception du satellite
//			if (((Satellite) o).lock()) {
//				((Satellite) o).addDataToMemory(this.getData());
//				this.resetData();
//				for (int i = 0 ; i < this.getSats().size() ; i++) {
//					this.getSats().get(i).unregister(this);
//				}
//			}
//		}
//	}

	@Override
	public void whenSatellitePositionChanged(Notification n) {
		// TODO Auto-generated method stub
		
	}
}
