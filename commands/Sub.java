package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import variables.NumberVariable;
import variables.Variable;

public class Sub extends Command {

	private Variable storeVar;
	private Number operand_l;
	private Number operand_r;
	
	public Sub(List<Variable> parameters) throws InvalidArgumentAmountException {
		super(parameters);
		if(parameters.size() != 3) 
			throw new InvalidArgumentAmountException("sub", 3);
		
		checkParameters(parameters);
		this.storeVar = (NumberVariable) parameters.get(0);
		this.operand_l = (Number) parameters.get(1).getValue();
		this.operand_r = (Number) parameters.get(2).getValue();
	}
	
	/** Checks if the parameters are {@link NumberVariable} variables.
	 *
	 * @param the parameters of {@link Add}.
	 * @throws InvalidArgumentTypeException if one of the parameters isn't a number variable.
	 */
	private void checkParameters(List<Variable> parameters) throws InvalidArgumentTypeException  {
		if( !(parameters.get(1) instanceof NumberVariable) && !(parameters.get(2) instanceof NumberVariable) ) {
			throw new InvalidArgumentTypeException("Last two parameters for Sub should be Number Variables");
		}
	}

	@Override
	public void execute() {
		List<Variable> parameters = new ArrayList<>(Arrays.asList(storeVar, new NumberVariable(null, operand_l.doubleValue() - operand_r.doubleValue())));
		(new Set(parameters)).execute();	
	}
}
