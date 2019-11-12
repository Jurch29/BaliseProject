package model;

import java.awt.Point;

import deplacement.Deplacement;
import graphicLayer.GOval;
import tools.Communication;
import tools.GlobaleVariable;

public class Balise {
	
	private GOval baliseDraw;
	private int data[];
	private boolean enEmission;
	private Deplacement deplacement;
	private int profondeur;
	private boolean collectData;
	private int nbData;

	public Balise(GOval balise) {
		this.data = new int[10];
		this.baliseDraw = balise;
		this.enEmission = false;
		this.profondeur = 0;
		this.collectData = true;
		this.nbData = 0;
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
			System.out.println("collecte");
		}
		if (profondeur != 0) {
			this.goToProfondeur();
		}
		else {
//			Point p = this.deplacement.getNextPoint(baliseDraw.getPosition());
//			baliseDraw.setPosition(p);
		}
	}
	
	public void emission() {
		if (Communication.emission(this.baliseDraw.getX(), data)) {  //on à émit nos data à un satellite
			this.data = new int[10];
			this.enEmission = false;
		}
	}
	
	public void goToProfondeur() {
		this.baliseDraw.setPosition(new Point(this.baliseDraw.getX(), this.baliseDraw.getY()+GlobaleVariable.vitesseBalise));
		this.profondeur--;
	}
}
