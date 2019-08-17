package commands;

import java.util.List;

import bareBonesInterpreter.CommandParser;
import variables.StringVariable;
import variables.Variable;

public class Get extends Command {
	
	private Variable var; 
	
	public Get(List<Variable> parameters) throws InvalidArgumentAmountException, InvalidArgumentTypeException {
		super(parameters);
		if(parameters.size() != 1) 
			throw new InvalidArgumentAmountException("get", 1);
		checkParameterIsVariable(parameters);
		
		this.var = CommandParser.getVariableStore().getVariable(parameters.get(0).getName());
	}
	
	private void checkParameterIsVariable(List<Variable> parameters) {
		System.out.println(parameters.get(0).getName());
		if(CommandParser.getVariableStore().getVariable(parameters.get(0).getName()) == null)
			throw new InvalidArgumentTypeException("The variable called in Get does not exist");
	}
	
	@Override
	public void execute() {
		System.out.println("Variable " + var.getName() + " is " + var.getValue());
	}
}
