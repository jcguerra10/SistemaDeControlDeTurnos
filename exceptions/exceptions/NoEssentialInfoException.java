package exceptions;

public class NoEssentialInfoException extends Exception{
	
	public NoEssentialInfoException(String message) {
		super("Has the next not essential atribute: " + message);
	}
	
}
