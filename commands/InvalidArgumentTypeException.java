package commands;

public class InvalidArgumentTypeException extends IllegalArgumentException {
	
	private static final long serialVersionUID = 1L;

	public InvalidArgumentTypeException(String message) {
		super(message);
	}

	
	public InvalidArgumentTypeException(String varID, String argType) {
		super("Argument " + varID + " should be of type " + argType);
	}

}
