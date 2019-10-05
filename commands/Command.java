package commands;

import java.util.List;

import parameter.Parameter;
import parameter.variables.Variable;

public abstract class Command {
	
	private List<Parameter> parameters;
	
	/** Assigns the parameters associated with the commmand.
	 * 
	 * @param parameters
	 */
	public Command(List<Parameter> parameters) throws InvalidArgumentAmountException {
		this.parameters = parameters;
	}
	
	/** Gets parameters of the command 
	 * 
	 * @return  the parameters 
	 */
	public List<Parameter> getParameters() {	
		return this.parameters;
	}
	
	public abstract void execute();
}
