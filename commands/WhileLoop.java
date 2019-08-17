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
		setTerminatingConditions(parameters);
	}
	
	private void setTerminatingConditions(List<Variable> parameters) throws InvalidArgumentTypeException {
		//should be boolean or infix boolean (var1 BoolOperator var2)
		BooleanOperation boolOp = new BooleanOperation(parameters);
		if( !(parameters.get(0) instanceof BooleanVariable) || boolOp == null )
			throw new InvalidArgumentTypeException("boolean");
		
		this.terminatingCondition = boolOp;
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
