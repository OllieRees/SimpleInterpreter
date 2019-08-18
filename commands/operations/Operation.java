package commands.operations;

import java.util.List;

import bareBonesInterpreter.CommandParser;
import commands.InvalidArgumentTypeException;
import variables.StringVariable;
import variables.Variable;

public abstract class Operation<E> {

	private Variable<E> result;
	private StringVariable operation;
	private Variable lhs;
	private Variable rhs;
	
	public Operation(String parameter, String operation) throws InvalidArgumentTypeException {
		this.operation = new StringVariable(null, operation);
		String[] operands = parameter.split(this.operation.getValue());	
		this.lhs = CommandParser.determineParameter(operands[0]);
		this.rhs = CommandParser.determineParameter(operands[1]);
				
		if( !(this.lhs == null || this.rhs == null))
			if(lhs.getClass() != rhs.getClass())
				throw new InvalidArgumentTypeException("The operands of the operation should be the same type");
	}
	
	public StringVariable getOperation() {
		return this.operation;
	}
	
	public Variable getLhs() {
		return this.lhs;
	}
	
	public Variable getRhs() {
		return this.rhs;
	}
	
	public Variable<E> getResult() {
		return this.result;
	}
	
	public void setResult(Variable<E> newResult) {
		this.result = newResult;
	}
	
	public abstract void assess();
	
}
