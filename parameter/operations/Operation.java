package parameter.operations;

import bareBonesInterpreter.CommandParser;
import commands.InvalidArgumentTypeException;
import parameter.Parameter;
import parameter.variables.StringVariable;
import parameter.variables.Variable;

public abstract class Operation<E> implements Parameter {

	private Variable<E> result;
	private StringVariable operation;
	private Variable lhs;
	private Variable rhs;
	
	public Operation(String parameter, String operation) throws InvalidArgumentTypeException, InvalidArgumentTypeException {
		this.operation = new StringVariable(null, operation);
		String[] operands = parameter.split(this.operation.getValue());
		
		Parameter lhsParam = CommandParser.determineParameter(operands[0]);
		Parameter rhsParam = CommandParser.determineParameter(operands[1]);
		
		if(lhsParam instanceof Operation) 
			throw new InvalidArgumentTypeException("Left hand side of an operation shouldn't be itself an operation");
		if(lhsParam instanceof Operation) 
			throw new InvalidArgumentTypeException("Right hand side of an operation shouldn't be itself an operation");
		
		this.lhs = (Variable) lhsParam;
		this.rhs = (Variable) rhsParam;
	
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
