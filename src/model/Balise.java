package model;

import graphicLayer.GOval;
import tools.Communication;

public class Balise {
	
	private GOval baliseDraw;
	private int data[];
	private boolean enEmission;

	public Balise(GOval balise) {
		this.data = new int[10];
		this.baliseDraw = balise;
		this.enEmission = true;
	}
	
	public void step() {
		if (enEmission)
			this.emission();
	}
	
	public void emission() {
		if (Communication.emission(this.baliseDraw.getX(), data)) {  //on à émit nos data à un satellite
			this.data = new int[10];
			this.enEmission = false;
		}
	}
}
