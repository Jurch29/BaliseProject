package model;

import java.awt.Point;

import deplacement.Deplacement;
import deplacement.Verticale;
import graphicLayer.GOval;
import phase.Mouvement;
import phase.Phase;
import tools.Communication;
import tools.GlobaleVariable;
import tools.Tools;
import vue.BaliseFrame;

public class Balise extends SimulationElement {

	private Point position;
	private int data[];
	private int profondeur;
	private int nbData;
	private Phase phase;
	private boolean baliseRun;
	
	private BaliseFrame vue;
	
	public Balise(Point p, BaliseFrame app) {
		this.data = new int[10];
		this.position = p;
		this.profondeur = 0;
		this.nbData = 0;
		this.baliseRun = true;
		
		this.vue = app;
	}
	
	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}
	
	public void setPosition(Point position) {
		this.position = position;
	}
	
	public Point getPosition() {
		return position;
	}

	public void addProfondeur(int profondeur) {
		this.profondeur += profondeur;
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
		this.data = new int[10];
		this.nbData = 0;
	}
	
	@Override
	public void run() {
		if (this.profondeur > 0) { //Si on commence par une phase de profondeur (mouvement(vertical(value)))
			Phase p = new Mouvement(new Verticale(profondeur));
			while (p.nextPhase(this)!=null) {
				Tools.sleep(GlobaleVariable.vitesseSimulation);
				p.step(this);
				vue.updateBalise(this);
			}
		}
		while (this.baliseRun) {
			Tools.sleep(GlobaleVariable.vitesseSimulation);
			this.phase.step(this);
			this.phase = this.phase.nextPhase(this);
			vue.updateBalise(this);
		}
	}
}
