package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import notification.Notification;
import notification.Notifier;
import notification.PositionChange;
import notification.Synchronizable;
import observable.Observable;
import tools.GlobaleVariable;
import tools.Tools;
import vue.BaliseFrame;

public class Satellite extends SimulationElement implements Observable {
	
	protected Point position;
	private ArrayList<Integer> memoire;
	private boolean locked;
	private BaliseFrame vue;
	private Notifier notifier;
	
	public Satellite(Point position, BaliseFrame app) {
		// TODO Auto-generated constructor stub
		this.memoire = new ArrayList<Integer>();
		this.position = position;
		this.locked = false;
		this.vue = app;
		this.notifier = new Notifier();
	}
	
	public Point getPosition() {
		return this.position;
	}
	
	public void addDataToMemory(int[] data) {
		List<Integer> list = Arrays.stream(data).boxed().collect(Collectors.toList());
		memoire.addAll(list);
		this.locked = false;
	}
	
	@Override
	public void run() {
		while (true) {
			Tools.sleep(GlobaleVariable.vitesseSimulation);
			if (this.position.x+GlobaleVariable.vitesseSat>GlobaleVariable.sizeX) {
				this.position = new Point(this.position.x+GlobaleVariable.vitesseSat-GlobaleVariable.sizeX,this.position.y);
			}
			else
				this.position = new Point(this.position.x+GlobaleVariable.vitesseSat,this.position.y);
			
			this.notifyAll(new PositionChange(this));
			this.vue.updateSatellite(this);
		}
	}
	
	public boolean lock() {
		if (!this.locked) {
			this.locked = true;
			return true;
		}
		return false;
	}

	@Override
	public void register(Synchronizable s) {
		this.notifier.addReceiver(s);
	}

	@Override
	public void unregister(Synchronizable s) {
		this.notifier.removeReceiver(s);
	}

	@Override
	public void notifyAll(Notification n) {
		this.notifier.sendNotification(n);
	}
}
