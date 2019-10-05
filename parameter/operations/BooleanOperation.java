package parameter.operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import commands.InvalidArgumentAmountException;
import commands.InvalidArgumentTypeException;
import parameter.variables.BooleanVariable;
import parameter.variables.NumberVariable;
import parameter.variables.Variable;

/** Operations that assess two Variables and produce a Boolean result
 * 
 * @author Ollie Rees
 *
 */
public class BooleanOperation extends Operation<Boolean> {
	
	private static final List<String> booleanOperationValues = new ArrayList<>(Arrays.asList("!", "==", "!=", "<=", ">=", "<", ">"));
	
	public BooleanOperation(String parameter, String operation) throws InvalidArgumentAmountException, InvalidArgumentTypeException {
		super(parameter, operation);
		checkUnaryOperandType();
		checkNumericOperationsType();
	}
	
	/** Checks whether the operand for the unary operation, !, is {@link BooleanVariable}.
	 * 
	 * @throws InvalidArgumentTypeException if the operand isn't boolean
	 */
	private void checkUnaryOperandType() throws InvalidArgumentTypeException {
		if(super.getLhs() == null)
			if( !(super.getRhs() instanceof BooleanVariable) )
				throw new InvalidArgumentTypeException("Left-hand side for the unary boolean operation (!) should be boolean type");
	}
	
	/** Checks whether the operands are {@link NumberVariable} for operations <, >, <= and >=
	 * 
	 * @throws InvalidArgumentTypeException if the operands aren't numbers.
	 */
	private void checkNumericOperationsType() throws InvalidArgumentTypeException {
		if(super.getLhs() != null)
			if( !(super.getOperation().getValue().equals("==") || super.getOperation().getValue().equals("!=")) )
				if( !(super.getLhs() instanceof NumberVariable && super.getRhs() instanceof NumberVariable) )
					throw new InvalidArgumentTypeException("Both operands should be numbers for the boolean operations >, <, >= and <=");
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
	 * @return the {@link BooleanVariable} result
	 */
	public Variable<Boolean> getResult() {
		return super.getResult();
	}
	
	/** Assess the variables, producing a result.
	 * 
	 */
	@Override
	public void assess() {
		Boolean newResult;
			
			switch(super.getOperation().getValue()) {
				case "!":
					newResult = !((Boolean) super.getRhs().getValue());
					break;
				case "==":
					newResult = super.getLhs().getValue().equals(super.getRhs().getValue());
					break;
				case "!=":
					newResult = !super.getLhs().getValue().equals(super.getRhs().getValue());
					break;
				case "<":
					newResult = ((Number) super.getLhs().getValue()).doubleValue() < ((Number) super.getRhs().getValue()).doubleValue();
					break;
				case ">":
					newResult = ((Number) super.getLhs().getValue()).doubleValue() > ((Number) super.getRhs().getValue()).doubleValue();
					break;
				case "<=":
					newResult = ((Number) super.getLhs().getValue()).doubleValue() <= ((Number) super.getRhs().getValue()).doubleValue();
					break;
				case ">=":
					newResult = ((Number) super.getLhs().getValue()).doubleValue() >= ((Number) super.getRhs().getValue()).doubleValue();
					break;
				default:
					newResult = null;
					break;
			}
		super.setResult(new BooleanVariable(null, newResult));	
	}
}