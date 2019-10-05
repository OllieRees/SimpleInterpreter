package commands;

import java.util.List;

import command.scope.ScopedCommand;
import parameter.Parameter;

public class WhileLoop extends ScopedCommand {
	
	public WhileLoop(List<Parameter> parameters) throws InvalidArgumentAmountException, InvalidArgumentTypeException {
		super(parameters);
		this.execute();
	}		

	@Override
	public void execute() {
		super.updateCondition();
		while(super.getConditionResult()) {
			super.executeScope();
			super.updateCondition();
		}
	}	
}