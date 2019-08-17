package commands;

public class InvalidArgumentAmountException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	public InvalidArgumentAmountException(String message) {
		super(message);
	}
	
	public InvalidArgumentAmountException(String cmdName, int argNum) {
		super("Command " + cmdName + " must have " + argNum + " arguments.");
	}

}
