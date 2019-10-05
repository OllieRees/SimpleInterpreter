package commands;

import java.util.List;

import bareBonesInterpreter.CommandParser;
import parameter.Parameter;
import parameter.variables.StringVariable;
import parameter.variables.Variable;

public class Get extends Command {
	
	private Variable var; 
	
	public Get(List<Parameter> parameters) throws InvalidArgumentAmountException, InvalidArgumentTypeException {
		super(parameters);
		if(parameters.size() != 1) 
			throw new InvalidArgumentAmountException("get", 1);
		
		if(!(parameters.get(0) instanceof Variable)) {
			throw new InvalidArgumentTypeException("Store variable in Get must be a variable type");
		}
		
		Variable temp = (Variable) parameters.get(0);
		checkVariableExists(temp);
		
		this.var = CommandParser.getVariableStore().getVariable(temp.getName());
	}
	
	private void checkVariableExists(Variable var) {
		String name = var.getName();
		if(CommandParser.getVariableStore().getVariable(name) == null)
			throw new InvalidArgumentTypeException("The variable called in Get does not exist");
	}
	
	@Override
	public void execute() {
		System.out.println("Variable " + var.getName() + " is " + var.getValue());
	}
}
