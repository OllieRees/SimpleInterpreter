package commands;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import bareBonesInterpreter.CommandParser;
import commands.operations.BooleanOperation;
import variables.BooleanVariable;
import variables.Variable;

public class WhileLoop extends Command {
	
	private Queue<Command> loopCommands = new LinkedList<>();
	private BooleanOperation terminatingCondition;
	
	/** Sets the terminating condition as a Boolean
	 * 
	 * @param parameters a boolean operation or a boolean variable
	 * @throws InvalidArgumentAmountException
	 * @throws InvalidArgumentTypeException 
	 */
	public WhileLoop(List<Variable> parameters) throws InvalidArgumentAmountException, InvalidArgumentTypeException {
		super(parameters);
		if(parameters.size() != 1)
			throw new InvalidArgumentAmountException("while", 1);
	}
	
	private void setTerminatingConditions(List<Variable> parameters) throws InvalidArgumentTypeException {
	}
			
	
	private void pushCommand(Command command) {
		loopCommands.add(command);
	}
	
	private Command peekCommand() {
		return loopCommands.peek();
	}
	
	@Override
	public void execute() {
		
	}
}
