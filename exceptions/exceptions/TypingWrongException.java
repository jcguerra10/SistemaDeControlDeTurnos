package exceptions;

public class TypingWrongException extends Exception {

	public TypingWrongException(String s) {
		super("You type wrong: "+s);
	}

	
}
