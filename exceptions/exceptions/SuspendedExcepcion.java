package exceptions;

public class SuspendedExcepcion extends Exception {

	public SuspendedExcepcion(String s) {
		super("The Client is Suspended Since: " + s);
	}
	
}
