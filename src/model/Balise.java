package model;

import java.awt.Point;

import deplacement.Deplacement;
import deplacement.Verticale;
import graphicLayer.GOval;
import phase.Mouvement;
import phase.Phase;
import tools.Communication;
import tools.GlobaleVariable;

public class Balise extends SimulationElement {

	private Point position;
	private int data[];
	private int profondeur;
	private int nbData;
	private Phase phase;
	private boolean baliseRun;
	
	public Balise(Point p) {
		this.data = new int[10];
		this.position = p;
		this.profondeur = 0;
		this.nbData = 0;
		this.baliseRun = true;
	}
	
	public void setPosition(Point position) {
		this.position = position;
	}
	
	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}
	
	public Point getPosition() {
		return position;
	}

	public void addProfondeur(int profondeur) {
		this.profondeur += profondeur;
	}
	
	public boolean isMemoryFull() {
		// TODO Auto-generated method stub
		return nbData==9;
	}
	
	public void addData(int data) {
		this.data[nbData] = data;
	}
	
	public void resetData() {
		this.data = new int[10];
		this.nbData = 0;
	}
	
	@Override
	public void run() {
		if (this.profondeur > 0) { //Si on commence par une phase de profondeur (mouvement(vertical(value)))
			Phase p = new Mouvement(new Verticale(profondeur));
			while (p.nextPhase(this)!=null) {
				p.step(this);
			}
		}
		while (this.baliseRun) {
			this.phase.step(this);
			this.phase = this.phase.nextPhase(this);
		}
	}
	
}
