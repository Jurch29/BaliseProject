package model;

import java.lang.reflect.InvocationTargetException;

import notification.Notifier;

public abstract class SimulationElement extends Thread {
	public abstract void run();
	protected Notifier notifier;
}
