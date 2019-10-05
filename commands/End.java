package commands;

import java.util.List;

import parameter.Parameter;

public class End extends Command {
	
	public End(List<Parameter> parameters) throws InvalidArgumentAmountException {
		super(parameters);
	}

	@Override
	public void execute() {
		
	}

}
