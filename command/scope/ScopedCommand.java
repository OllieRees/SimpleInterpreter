package command.scope;

import java.util.ArrayList;
import java.util.List;

import bareBonesInterpreter.CommandParser;
import commands.Command;
import commands.End;
import commands.InvalidArgumentAmountException;
import commands.InvalidArgumentTypeException;
import parameter.Parameter;
import parameter.operations.BooleanOperation;

public abstract class ScopedCommand extends Command {
	
	private BooleanOperation condition;
	private List<Command> scope = new ArrayList<>();

	
	public ScopedCommand(List<Parameter> parameters) {
		super(parameters);
		if(parameters.size() != 1)
			throw new InvalidArgumentAmountException(this.getClass().getSimpleName(), 1);
		if( !(parameters.get(0) instanceof BooleanOperation) )
			throw new InvalidArgumentTypeException("A scoped command's condition should be a BooleanVariable");
		
		this.condition = (BooleanOperation) parameters.get(0);
		this.condition.assess();
		
		ScopeStack.pushQueue(this);
		storeScope();
	}
	
	public BooleanOperation getCondition() {
		return this.condition;
	}
	
	public void updateCondition() {
		this.condition.assess();
	}

	public List<Command> getScope() {
		return this.scope;
	}
	
	public Boolean getConditionResult() {
		return this.condition.getResult().getValue();
	}
	
	private Command getNextCommand() {
		return CommandParser.getNextCommand();
	}
	
	public void storeScope() {
		Command nextCommand;
		System.out.println("Storing");
		while( !scopesEndReached(nextCommand = getNextCommand()) )	{	
			System.out.println("Storing " + nextCommand.getClass().toString());
			if(nextCommand != null || !(nextCommand instanceof End)) 
				this.scope.add(nextCommand);
		}		
	}
	
	public void clearScope() {
		if(!this.scope.isEmpty())
			this.scope.clear();
	}
	
	private boolean scopesEndReached(Command cmd) {
		return (cmd instanceof End && ScopeStack.peekQueue().equals(this));
	}
	
	public void executeScope() {
		System.out.println("Executing");
		for(Command cmd : scope) {
			System.out.println("Executing " + cmd.getClass().toString());
			cmd.execute();
		}
		this.updateCondition();
	}
}