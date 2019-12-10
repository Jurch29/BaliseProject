package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import notification.Notification;
import notification.Notifier;
import notification.PositionChange;
import notification.SatelliteListener;
import tools.GlobaleVariable;
import tools.Tools;
import vue.BaliseFrame;

public class Satellite extends SimulationElement {
	
	protected Point position;
	private ArrayList<Integer> memoire;
	private boolean locked;
	private BaliseFrame vue;
	
	public Satellite(Point position, BaliseFrame app, Notifier n) {
		// TODO Auto-generated constructor stub
		this.memoire = new ArrayList<Integer>();
		this.position = position;
		this.locked = false;
		this.vue = app;
		this.notifier = n;
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
			
			this.notifier.sendNotification(new PositionChange(this));
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

	public void register(Class<? extends Notification> notification, SatelliteListener s) {
		this.notifier.addListener(notification, s);
	}

	public void unregister(Class<? extends Notification> notification, SatelliteListener s) {
		this.notifier.removeListener(notification, s);
	}
}
