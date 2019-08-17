package commands;

import java.util.List;

import variables.Variable;

public abstract class Command {
	
	private List<Variable> parameters;
	
	/** Assigns the parameters associated with the commmand.
	 * 
	 * @param parameters
	 */
	public Command(List<Variable> parameters) throws InvalidArgumentAmountException {
		this.parameters = parameters;
	}
	
	/** Gets parameters of the command 
	 * 
	 * @return  the parameters 
	 */
	public List<Variable> getParameters() {	
		return this.parameters;
	}
	
	public abstract void execute();
}
