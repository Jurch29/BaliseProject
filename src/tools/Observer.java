package tools;

public interface Observer {
	void updateFrom (Observable o);
	void updateFrom (Observable o, Object arg);
}
