package commands;

import java.util.List;

import variables.Variable;

public class End extends Command {

	public End(List<Variable> parameters) throws InvalidArgumentAmountException {
		super(parameters);
	}

	@Override
	public void execute() {
		
	}

}
