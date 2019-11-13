package model;

import java.awt.Point;

import deplacement.Deplacement;
import deplacement.Verticale;
import graphicLayer.GOval;
import phase.Mouvement;
import phase.Phase;
import tools.Communication;
import tools.GlobaleVariable;

public class Balise extends Thread {

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
	
	public void run() {
		if (this.profondeur > 0)
			this.phase=new Mouvement(new Verticale(profondeur));
		while (this.baliseRun) {
			this.phase.step(this);
			this.phase = this.phase.nextPhase(this);
		}
	}
	
	public void emission() {
		if (Communication.emission(this.position.x, data)) {  //on à émit nos data à un satellite
			this.data = new int[10];
//			this.enEmission = false;
		}
	}
	
	public void goToProfondeur() {
		this.position = new Point(this.position.x, this.position.y+GlobaleVariable.vitesseBalise);
		this.profondeur--;
	}
}
