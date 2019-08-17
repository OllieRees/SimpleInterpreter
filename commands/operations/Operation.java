package commands.operations;

import java.util.List;

import commands.Command;
import commands.InvalidArgumentAmountException;
import commands.InvalidArgumentTypeException;
import variables.Variable;

public abstract class Operation<E> extends Command {

	private E result;
	
	public Operation(List<Variable> parameters) throws InvalidArgumentAmountException {
		super(parameters);
		if(parameters.size() == 3)
			if( !(parameters.get(0).getValue().equals( parameters.get(2).getValue() ) ) )
				throw new InvalidArgumentTypeException("Operands should be the same value");
	}
	
	public E getResult() {
		return this.result;
	}
	
	public void setResult(E newResult) {
		this.result = newResult;
	}
	
}
