package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import graphicLayer.GRect;
import tools.Communication;
import tools.GlobaleVariable;

public class Satellite {
	
	private GRect satDraw;
	private ArrayList<Integer> memoire;

	public Satellite(GRect satellite) {
		// TODO Auto-generated constructor stub
		this.satDraw = satellite;
		this.memoire = new ArrayList<Integer>();
	}
	
	public GRect getSatDraw() {
		return satDraw;
	}

	public void setSatDraw(GRect satDraw) {
		this.satDraw = satDraw;
	}
	
	public void addDataToMemory(int[] data) {
		List<Integer> list = Arrays.stream(data).boxed().collect(Collectors.toList());
		memoire.addAll(list);
	}
	
	public void step() {
		if (this.satDraw.getX()+GlobaleVariable.vitesseSat>GlobaleVariable.sizeX) {
			this.satDraw.setPosition(new Point(this.satDraw.getX()+GlobaleVariable.vitesseSat-GlobaleVariable.sizeX,this.satDraw.getY()));
		}
		else
			this.satDraw.setPosition(new Point(this.satDraw.getX()+GlobaleVariable.vitesseSat,this.satDraw.getY()));
		
		Communication.reception(this);
	}
}
