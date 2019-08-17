package commands.operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import commands.InvalidArgumentAmountException;
import commands.InvalidArgumentTypeException;
import variables.BooleanVariable;
import variables.NumberVariable;
import variables.StringVariable;
import variables.Variable;

/** Operations that assess two Variables and produce a Boolean result
 * 
 * @author Ollie Rees
 *
 */
public class BooleanOperation extends Operation<Boolean> {
	
	private static final List<String> booleanOperationValues = new ArrayList<>(Arrays.asList("!", "==", "!=", "<", "<=", ">", ">="));
	
	public BooleanOperation(List<Variable> parameters) throws InvalidArgumentAmountException, InvalidArgumentTypeException {
		super(parameters);
		checkOperationType(parameters);
		checkUnaryOperandType(parameters);
		checkNumericOperationsType(parameters);
		this.execute();
	}
		
	/** Checks whether the operations are represented as {@link StringVariable}.
	 * 
	 * @param parameters of the operation
	 * @throws InvalidArgumentTypeException if the operation isn't a string
	 */
	private void checkOperationType(List<Variable> parameters) throws InvalidArgumentTypeException {
		if(parameters.size() == 3)
			if( !(parameters.get(1) instanceof StringVariable) )
				throw new InvalidArgumentTypeException("Operation", "String Variable");
		if(parameters.size() == 2)
			if( !(parameters.get(0) instanceof StringVariable) )
				throw new InvalidArgumentTypeException("Operation", "String Variable");
	}
	
	/** Checks whether the operand for the unary operation, !, is {@link BooleanVariable}.
	 * 
	 * @param parameters of the opreation
	 * @throws InvalidArgumentTypeException if the operand isn't boolean
	 */
	private void checkUnaryOperandType(List<Variable> parameters) throws InvalidArgumentTypeException {
		if(parameters.size() == 2)
			if( !(parameters.get(1) instanceof BooleanVariable) )
				throw new InvalidArgumentTypeException("Operand for unary Boolean Operation", "Boolean Variable");
	}
	
	/** Checks whether the operands are {@link NumberVariable} for operations <, >, <= and >=
	 * 
	 * @param parameters of the operation
	 * @throws InvalidArgumentTypeException if the operands aren't numbers.
	 */
	private void checkNumericOperationsType(List<Variable> parameters) throws InvalidArgumentTypeException {
		if(parameters.size() == 3)
			if( !parameters.get(1).equals("==") || !parameters.get(1).equals("!=") )
				if(parameters.get(0) instanceof NumberVariable && parameters.get(1) instanceof NumberVariable)
					throw new InvalidArgumentTypeException("Operand for <, >, <= and >=", "Numeric Variable");
	}
	
	/** Gets the different boolean operations
	 * 
	 * @return the boolean operations
	 */
	public static final List<String> getBooleanOperationValues() {
		return booleanOperationValues;
	}
	
	/** Gets the result of the operation
	 * 
	 * @return the {@link Boolean} result
	 */
	public Boolean getResult() {
		return super.getResult();
	}
	
	/** Assess the variables
	 * 
	 */
	@Override
	public void execute() {
		super.getParameters();
		Boolean newResult;
		
		//determine operation
		String operation = super.getParameters().size() == 3 ? super.getParameters().get(1).toString() : super.getParameters().get(0).toString();
			switch(operation) {
				case "!":
					newResult = !(super.getParameters().get(1).getValue().equals(super.getParameters().get(1).getValue()));
					break;
				case "==":
					newResult = super.getParameters().get(0).getValue().equals(super.getParameters().get(2).getValue());
					break;
				case "!=":
					newResult = !super.getParameters().get(0).getValue().equals(super.getParameters().get(2).getValue());
					break;
				case "<":
					newResult = ((Double) super.getParameters().get(0).getValue()) < ((Double) super.getParameters().get(2).getValue());
					break;
				case ">":
					newResult = ((Double) super.getParameters().get(0).getValue()) > ((Double) super.getParameters().get(2).getValue());
					break;
				case "<=":
					newResult = ((Double) super.getParameters().get(0).getValue()) <= ((Double) super.getParameters().get(2).getValue());
					break;
				case ">=":
					newResult = ((Double) super.getParameters().get(0).getValue()) >= ((Double) super.getParameters().get(2).getValue());
					break;
				default:
					newResult = null;
					break;
			}
		super.setResult(newResult);	
	}
}