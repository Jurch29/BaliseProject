package model;

import java.awt.Point;

import deplacement.Deplacement;
import graphicLayer.GOval;
import tools.Communication;
import tools.GlobaleVariable;

public class Balise {

	private Point position;
	private int data[];
	private boolean enEmission;
	private Deplacement deplacement;
	private int profondeur;
	private boolean collectData;
	private int nbData;
	
	public Balise(Point p) {
		this.data = new int[10];
		this.position = p;
		this.enEmission = false;
		this.profondeur = 0;
		this.collectData = true;
		this.nbData = 0;
	}
	
	public Point getPosition() {
		return position;
	}
	
	public void setDeplacement(Deplacement d) {
		this.deplacement = d;
	}
	
	public void addProfondeur(int profondeur) {
		this.profondeur += profondeur;
	}
	
	public void step() {
		if (enEmission)
			this.emission();
		else if (this.collectData && this.nbData<10) {
//			System.out.println("collecte");
		}
		if (profondeur != 0) {
			this.goToProfondeur();
		}
		else {
//			this.position = this.deplacement.getNextPoint(this.position);
		}
	}
	
	public void emission() {
		if (Communication.emission(this.position.x, data)) {  //on à émit nos data à un satellite
			this.data = new int[10];
			this.enEmission = false;
		}
	}
	
	public void goToProfondeur() {
		this.position = new Point(this.position.x, this.position.y+GlobaleVariable.vitesseBalise);
		this.profondeur--;
	}
}
