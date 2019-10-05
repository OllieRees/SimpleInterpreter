package commands;

import java.util.List;

import command.scope.ScopedCommand;
import parameter.Parameter;

public class If extends ScopedCommand {
	
	public If(List<Parameter> parameters) throws InvalidArgumentAmountException, InvalidArgumentTypeException {
		super(parameters);
		this.execute();
	}

	@Override
	public void execute() {
		super.updateCondition();
		if(super.getConditionResult()) {
			super.executeScope();
		}
	}
}
