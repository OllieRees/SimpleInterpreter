package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import parameter.Parameter;
import parameter.operations.Operation;
import parameter.variables.NumberVariable;
import parameter.variables.Variable;

public class Sub extends Command {

	private Variable storeVar;
	private Number operand_l;
	private Number operand_r;
	
	public Sub(List<Parameter> parameters) throws InvalidArgumentAmountException {
		super(parameters);
		if(parameters.size() != 3) 
			throw new InvalidArgumentAmountException("sub", 3);
		if( !(parameters.get(0) instanceof Variable) )
			throw new InvalidArgumentTypeException("Store variable, in Sub, should be a variable.");	
		checkParameters(parameters);
		
		this.storeVar = (Variable) parameters.get(0);
		this.operand_l = ((NumberVariable) parameters.get(1)).getValue();
		this.operand_r = ((NumberVariable) parameters.get(2)).getValue();
	}
	
	/** Checks if the parameters are {@link NumberVariable} variables.
	 *
	 * @param the parameters of {@link Add}.
	 * @throws InvalidArgumentTypeException if one of the parameters isn't a number variable.
	 */
	private void checkParameters(List<Parameter> parameters) throws InvalidArgumentTypeException  {
		if( !(parameters.get(1) instanceof NumberVariable) && !(parameters.get(2) instanceof NumberVariable) ) {
			throw new InvalidArgumentTypeException("Last two parameters for Sub should be Number Variables");
		}
	}

	@Override
	public void execute() {
		List<Parameter> parameters = new ArrayList<>(Arrays.asList(storeVar, new NumberVariable(null, operand_l.doubleValue() - operand_r.doubleValue())));
		(new Set(parameters)).execute();	
	}
}
